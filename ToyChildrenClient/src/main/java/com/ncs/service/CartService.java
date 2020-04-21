package com.ncs.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.dto.CartDto;
import com.ncs.model.entity.Product;
import com.ncs.model.input.CartInput;
import com.ncs.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private ProductRepository productRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@SuppressWarnings("unchecked")
	public ResponseData<List<CartDto>> getListCart(HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>getListCart Start >>>>>>>>>>>>");

		ResponseData<List<CartDto>> response = new ResponseData<>();
		try {

			List<CartDto> carts = (List<CartDto>) request.getSession().getAttribute("listCartSession");
			response.setData(carts);
		} catch (Exception e) {
			LOGGER.error("Api get list cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getListCart End >>>>>>>>>>>>");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseData<CartDto> addProductToCart(CartInput input, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>addProductToCart Start >>>>>>>>>>>>");
		ResponseData<CartDto> response = new ResponseData<>();
		try {
			CartDto cartDto = new CartDto();
			Product product = new Product();
			boolean isExists = false;

			List<CartDto> carts = (List<CartDto>) request.getSession().getAttribute("listCartSession");

			int productId = input.getProductId();
			int quantity = input.getQuantity();

			product = productRepository.findById(productId).get();

			if (ObjectUtils.isEmpty(product)) {
				LOGGER.error(Constants.PRODUCT_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(Constants.PRODUCT_DO_NOT_EXIST);
				return response;
			}

			if (ObjectUtils.isEmpty(carts)) {
				carts = new ArrayList<>();
				cartDto.setCartId(1);
			} else {
				for (int i = 0; i < carts.size(); i++) {
					if (carts.get(i).getProduct().getId() == productId) {
						isExists = true;
						cartDto = carts.get(i);
						break;
					}
				}

				if (!isExists)
					cartDto.setCartId(carts.get(carts.size()).getCartId() + 1);
			}

			if (isExists) {
				cartDto.setQuantity(cartDto.getQuantity() + quantity);
			} else {
				cartDto.setProduct(product);
				cartDto.setQuantity(quantity);
			}

			carts.add(cartDto);

			request.getSession().setAttribute("listCartSession", carts);

			response.setData(cartDto);
		} catch (Exception e) {
			LOGGER.error("Api get add product to cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>addProductToCart Start >>>>>>>>>>>>");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseData<CartDto> updateCart(int cartId, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>updateCart Start >>>>>>>>>>>>");
		ResponseData<CartDto> response = new ResponseData<>();
		try {
			List<CartDto> carts = new ArrayList<>();
			CartDto cartDto = new CartDto();
			int quantity;

			carts = (List<CartDto>) request.getSession().getAttribute("listCartSession");

			if (!carts.isEmpty()) {

				for (int i = 0; i < carts.size(); i++) {
					if (carts.get(i).getCartId() == cartId) {
						cartDto = carts.get(i);
					}
				}

				if (!ObjectUtils.isEmpty(cartDto)) {
					quantity = cartDto.getQuantity() + 1;

					cartDto.setQuantity(quantity);

					carts.add(cartDto);

					request.getSession().setAttribute("listCartSession", carts);

					response.setData(cartDto);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Api get update product in cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>updateCart Start >>>>>>>>>>>>");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseData<Object> deleteProductOutCart(int cartId, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>deleteProductOutCart Start >>>>>>>>>>>>");
		ResponseData<Object> response = new ResponseData<Object>();
		try {
			List<CartDto> carts = (List<CartDto>) request.getSession().getAttribute("listCartSession");

			for (int i = 0; i < carts.size(); i++) {
				if (carts.get(i).getCartId() == cartId) {
					carts.remove(i);
				}
			}

			request.getSession().setAttribute("listCartSession", carts);
		} catch (Exception e) {
			LOGGER.error("Api delete product out cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>deleteProductOutCart End >>>>>>>>>>>>");
		return response;
	}
}
