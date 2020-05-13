package com.ncs.serviceclient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.dto.CartDto;
import com.ncs.model.entity.Coupon;
import com.ncs.model.entity.Customer;
import com.ncs.model.entity.Order;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.entity.Product;
import com.ncs.model.entity.Shipping;
import com.ncs.model.entity.Tax;
import com.ncs.model.input.PayInput;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String COUPON_FILED = "Mã giảm giá";
	private static final String SHIPPING_FILED = "Hình thức giao hàng";
	private static final String TAX_FILED = "Thuế";

	@Transactional(rollbackOn = Exception.class)
	public ResponseData<Object> pay(PayInput input, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>pay Start >>>>>>>>>>>>");
		ResponseData<Object> response = new ResponseData<Object>();
		try {
			List<CartDto> carts = new ArrayList<>();
			Order order = new Order();
			Shipping shipping = new Shipping();
			Coupon coupon = new Coupon();
			Tax tax = new Tax();
			Customer customer = new Customer();
//			Account account = new Account();

//			CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
//					.getPrincipal();
//			account = userDetails.getAccount();

			// get data input
			int shippingId = input.getShippingId();
			int couponId = input.getCouponId();
			int taxId = input.getTaxId();
			int payment = input.getPayment();
			carts = input.getCarts();

			// get data in db
			shipping = shippingRepository.findById(shippingId).orElse(null);
			coupon = couponRepository.findById(couponId).orElse(null);
			tax = taxRepository.findById(taxId).orElse(null);
//			customer = customerRepository.findByAccount(account);
			customer = customerRepository.findById(1).get(); // TODO

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
			order.setCreateDate(new Date());
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
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			LOGGER.error("Api pay has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		LOGGER.info(">>>>>>>>>>>pay End >>>>>>>>>>>>");
		return response;
	}
}
