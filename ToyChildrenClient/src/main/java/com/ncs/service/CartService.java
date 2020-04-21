package com.ncs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.dto.CartDto;
import com.ncs.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private ProductRepository productRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public ResponseData<List<CartDto>> getListCart(HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>getListCart Start >>>>>>>>>>>>");
		
		ResponseData<List<CartDto>> response = new ResponseData<>();
		try {
//			List<CartDto> carts = (List<CartDto>) request.getSession().getAttribute("listCartSession");
//			response.setData(carts);
		} catch (Exception e) {
			LOGGER.error("Api get list cart has exception : {}", e.getMessage());
			response.setData(null);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>getListCart End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Object> addProductToCart(CartDto cart, HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>addProductToCart Start >>>>>>>>>>>>");
		ResponseData<Object> response = new ResponseData<>();
		try {
//			boolean check = false;
//			int cartId = 0;
//
//			List<CartDto> carts = (List<CartDto>) request.getSession().getAttribute("listCartSession");
//
//			if (carts == null) {
//				carts = new ArrayList<>();
//				cartId = 1;
//			} else {
//				// get list cart in list cart session
//			}


//			for (Cart item : carts) {
//				if (item.getColorId() == cart.getColorId() && item.getProductId() == cart.getProductId()
//						&& item.getSizeId() == cart.getSizeId()) {
//					item.setQuantity(cart.getQuantity() + item.getQuantity());
//					check = true;
//				}
//			}
//
//			listCart.setCarts(carts);
//
//			request.getSession().setAttribute("listCartSession", listCart);
		} catch (Exception e) {
			LOGGER.error("Api get add product to cart has exception : {}", e.getMessage());
			response.setData(null);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		
		LOGGER.info(">>>>>>>>>>>addProductToCart Start >>>>>>>>>>>>");
		return response;
	}
}
