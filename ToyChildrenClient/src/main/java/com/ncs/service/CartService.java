package com.ncs.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	public ResponseData<List<CartDto>> getListCart(HttpServletRequest request) {
		ResponseData<List<CartDto>> response = new ResponseData<>();
		try {
//			List<CartDto> carts = (List<CartDto>) request.getSession().getAttribute("listCartSession");
//			response.setData(carts);
		} catch (Exception e) {
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}

	public ResponseData<Object> addProductToCart(CartDto cart, HttpServletRequest request) {
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
			response.setData(null);
			response.setCode(Constants.ERR_CODE_BAD_REQUEST);
			response.setMessage(Constants.MSG_TEMP + Constants.ERR_MSG_BAD_REQUEST);
		}
		return response;
	}
}
