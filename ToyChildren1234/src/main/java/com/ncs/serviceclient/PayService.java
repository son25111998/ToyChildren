package com.ncs.serviceclient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.dto.CartDto;
import com.ncs.model.entity.Account;
import com.ncs.model.entity.Coupon;
import com.ncs.model.entity.Customer;
import com.ncs.model.entity.Order;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.entity.Product;
import com.ncs.model.entity.Shipping;
import com.ncs.model.entity.Tax;
import com.ncs.model.input.PayInput;
import com.ncs.repositoryclient.AccountClientRepository;
import com.ncs.repositoryclient.CouponRepository;
import com.ncs.repositoryclient.CustomerRepository;
import com.ncs.repositoryclient.OrderClientRepository;
import com.ncs.repositoryclient.OrderDetailClientRepository;
import com.ncs.repositoryclient.ProductClientRepository;
import com.ncs.repositoryclient.ShippingRepository;
import com.ncs.repositoryclient.TaxRepository;

@Service
public class PayService {
	@Autowired
	private OrderClientRepository orderRepository;
	@Autowired
	private OrderDetailClientRepository orderDetailRepository;
	@Autowired
	private ShippingRepository shippingRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private TaxRepository taxRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductClientRepository productRepository;
	@Autowired
	private MomoService momoService;
	@Autowired
	private AccountClientRepository accountRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String COUPON_FILED = "Mã giảm giá";
	private static final String SHIPPING_FILED = "Hình thức giao hàng";
	private static final String TAX_FILED = "Thuế";

	@Transactional(rollbackOn = Exception.class)
	public ResponseData<Order> pay(PayInput input, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>pay Start >>>>>>>>>>>>");
		ResponseData<Order> response = new ResponseData<>();
		try {
			List<CartDto> carts = new ArrayList<>();
			Order order = new Order();
			Shipping shipping = new Shipping();
			Coupon coupon = new Coupon();
			Tax tax = new Tax();
			Customer customer = new Customer();
			Account account = new Account();

			String jwtToken = request.getHeader("Authorization");;
			
			String[] split_string = jwtToken.split("\\.");
	        String base64EncodedBody = split_string[1];
	        Base64 base64Url = new Base64(true);
	        
	        JsonNode node = new ObjectMapper().readTree(base64Url.decode(base64EncodedBody));
	        
	        String username = node.path("username").asText();

			account = accountRepository.findByUsername(username);

			// get data input
			int shippingId = input.getShippingId();
			int couponId = input.getCouponId();
			int taxId = input.getTaxId();
			int payment = input.getPayment();
			carts = input.getCarts();
			String uuid = UUID.randomUUID().toString();

			// get data in db
			shipping = shippingRepository.findById(shippingId).orElse(null);
			coupon = couponRepository.findById(couponId).orElse(null);
			tax = taxRepository.findById(taxId).orElse(null);
			customer = customerRepository.findByAccount(account);

			// case carts null or empty
			if (ObjectUtils.isEmpty(carts)) {
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(Constants.CART_EMPTY);
				return response;
			}

			// case shipping null or empty
			if (ObjectUtils.isEmpty(shipping)) {
				LOGGER.error("{} : {}", SHIPPING_FILED, Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(SHIPPING_FILED + " " + Constants.RECORD_DO_NOT_EXIST);
				return response;
			}

			// case coupon null or empty
			if (ObjectUtils.isEmpty(coupon)) {
				LOGGER.error("{} : {}", COUPON_FILED, Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(COUPON_FILED + " " + Constants.RECORD_DO_NOT_EXIST);
				return response;
			}

			// case tax null or empty
			if (ObjectUtils.isEmpty(tax)) {
				LOGGER.error("{} : {}", TAX_FILED, Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(TAX_FILED + " " + Constants.RECORD_DO_NOT_EXIST);
				return response;
			}

			// set data in order
			order.setCreateDate(Calendar.getInstance().getTime());

			if (payment == Constants.PAYMENT_MOMO_CODE) {
				order.setQrcode(momoService.createQrCode(input.getSumMoney(), uuid));
			}

			order.setPayment(payment);
			order.setStatus(Constants.STATUS_ACTIVE_VALUE);
			order.setCoupon(coupon);
			order.setCustomer(customer);
			order.setShipping(shipping);
			order.setTax(tax);

			// save order in db
			order = orderRepository.save(order);

			LOGGER.info("Order : {}", order);

			// save cart in db
			for (CartDto cart : carts) {
				OrderDetail orderDetail = new OrderDetail();
				Product product = cart.getProduct();

				// set data in order detail
				orderDetail.setOrder(order);
				orderDetail.setProduct(cart.getProduct());
				orderDetail.setQuantity(cart.getQuantity());

				// save order detail in db
				LOGGER.info("Order detail : {}", orderDetailRepository.save(orderDetail));

				// update amount product
				product.setAmount(product.getAmount() - cart.getQuantity());

				LOGGER.info("Product update: {}", productRepository.save(product));
			}

			response.setData(order);
			response.setCode(Constants.SUCCESS_CODE);

			if (payment == Constants.PAYMENT_MOMO_CODE) {
				response.setMessage(Constants.SUCCESS_MOMO_MSG);
			} else {
				response.setMessage(Constants.SUCCESS_MSG);
			}
		} catch (Exception e) {
			LOGGER.error("Api pay has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		LOGGER.info(">>>>>>>>>>>pay End >>>>>>>>>>>>");
		return response;
	}
}
