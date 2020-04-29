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
	private List<CartDto> carts = new ArrayList<>();

	public ResponseData<List<CartDto>> getListCart(HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>getListCart Start >>>>>>>>>>>>");

		ResponseData<List<CartDto>> response = new ResponseData<>();
		try {

			response.setData(this.carts);

			LOGGER.info("{}", this.carts);
		} catch (Exception e) {
			LOGGER.error("Api get list cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getListCart End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<CartDto> addProductToCart(CartInput input, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>addProductToCart Start >>>>>>>>>>>>");
		ResponseData<CartDto> response = new ResponseData<>();
		try {
			CartDto cartDto = new CartDto();
			Product product = new Product();
			boolean isExists = false;
			int index = 0;

			int productId = input.getProductId();
			int quantity = input.getQuantity();

			product = productRepository.findById(productId).orElse(null);

			if (ObjectUtils.isEmpty(product)) {
				LOGGER.error("Sản phẩm {}", Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage("Sản phẩm " + Constants.RECORD_DO_NOT_EXIST);
				return response;
			}

			if (ObjectUtils.isEmpty(this.carts)) {
				this.carts = new ArrayList<>();
				cartDto.setCartId(1);
			} else {
				for (int i = 0; i < this.carts.size(); i++) {
					if (this.carts.get(i).getProduct().getId() == productId) {
						isExists = true;
						cartDto = this.carts.get(i);
						index = i;
						this.carts.remove(i);
						break;
					}
				}

				if (!isExists)
					cartDto.setCartId(this.carts.get(this.carts.size() - 1).getCartId() + 1);
			}

			if (isExists) {
				cartDto.setQuantity(cartDto.getQuantity() + quantity);
				this.carts.add(index, cartDto);
			} else {
				cartDto.setProduct(product);
				cartDto.setQuantity(quantity);
				this.carts.add(cartDto);
			}

			request.getSession().setAttribute(Constants.CART_SESSION, this.carts);
			LOGGER.info("{}", this.carts);
			response.setData(cartDto);
		} catch (Exception e) {
			LOGGER.error("Api get add product to cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>addProductToCart End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<CartDto> updateCart(int cartId, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>updateCart Start >>>>>>>>>>>>");
		ResponseData<CartDto> response = new ResponseData<>();
		try {
			CartDto cartDto = new CartDto();
			int index = 0;
			int quantity;

			if (!this.carts.isEmpty()) {

				for (int i = 0; i < this.carts.size(); i++) {
					if (this.carts.get(i).getCartId() == cartId) {
						cartDto = this.carts.get(i);
						index = i;
						break;
					}
				}

				if (!ObjectUtils.isEmpty(cartDto)) {
					quantity = cartDto.getQuantity() + 1;

					cartDto.setQuantity(quantity);

					this.carts.add(index, cartDto);

					request.getSession().setAttribute(Constants.CART_SESSION, this.carts);

					response.setData(cartDto);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Api get update product in cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>updateCart End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Object> deleteProductOutCart(int cartId, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>deleteProductOutCart Start >>>>>>>>>>>>");
		ResponseData<Object> response = new ResponseData<Object>();
		try {
			int i = 0;
			CartDto cartDto;
			int cartNewId;
			int cartOldId;

			for (i = 0; i < this.carts.size(); i++) {
				if (this.carts.get(i).getCartId() == cartId) {
					this.carts.remove(i);
					break;
				}
			}

			// set cartId
			for (i = 0; i < this.carts.size(); i++) {
				cartDto = this.carts.get(i);
				cartOldId = cartDto.getCartId();
				if (cartOldId > cartId) {
					cartNewId = cartOldId - 1;
					cartDto.setCartId(cartNewId);
				}
			}

			request.getSession().setAttribute(Constants.CART_SESSION, this.carts);
		} catch (Exception e) {
			LOGGER.error("Api delete product out cart has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>deleteProductOutCart End >>>>>>>>>>>>");
		return response;
	}
}
