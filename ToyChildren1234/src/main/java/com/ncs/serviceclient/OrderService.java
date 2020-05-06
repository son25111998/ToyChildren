package com.ncs.serviceclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.dao.OrderDao;
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.common.util.Utils;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
import com.ncs.model.entity.Order;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.output.GetListOrderOutput;
import com.ncs.model.output.OrderOutput;
<<<<<<< HEAD
import com.ncs.repositoryclient.OrderDetailClientRepository;
import com.ncs.repositoryclient.OrderClientRepository;
=======
<<<<<<< HEAD:ToyChildrenClient/src/main/java/com/ncs/serviceclient/OrderService.java
import com.ncs.repositoryclient.OrderDetailRepository;
import com.ncs.repositoryclient.OrderRepository;
=======
import com.ncs.model.output.Pagination;
import com.ncs.repositoryclient.OrderClientRepository;
import com.ncs.repositoryclient.OrderDetailClientRepository;
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1:ToyChildren1234/src/main/java/com/ncs/serviceclient/OrderService.java
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

@Service
public class OrderService {
	@Autowired
	private OrderClientRepository orderRepository;
	@Autowired
	private OrderDetailClientRepository orderDetailRepository;
<<<<<<< HEAD
	@Autowired
	private OrderDao orderDao;
=======
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String ORDER_FIELD = "Đơn hàng";
	private static final String PATH_FILE_EXCEL = "/templates/TemplateExcel.xlsx";
<<<<<<< HEAD
//	private static final String SUCCESS_FIELD = "Thành công";
//	private static final String FAIL_FIELD = "Thất bại";
=======
	private static final int SIZE_DEFAULT = 10;
	private static final String SUCCESS_FIELD = "Thành công";
	private static final String FAIL_FIELD = "Thất bại";
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

	public ResponseData<GetListOrderOutput> getListOrder(int page, int size, String date) {
		LOGGER.info(">>>>>>>>>>>getListOrder Start >>>>>>>>>>>>");
		ResponseData<GetListOrderOutput> response = new ResponseData<GetListOrderOutput>();
		try {
<<<<<<< HEAD
			// get data in db
			GetListOrderOutput output = orderDao.getListOrder(page, size, date);

			// set data order output
			List<OrderOutput> orderOutputs = output.getOrders();

			// set list order detail in order
			for (OrderOutput item : orderOutputs) {
				List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(item.getOrderId());
				item.setOrderDetails(orderDetails);
			}

			output.setOrders(orderOutputs);

			response.setData(output);
=======
			GetListOrderOutput orderOutputs = new GetListOrderOutput();
			List<OrderOutput> orders = new ArrayList<>();
			Pagination pagination = new Pagination();

			Page<Order> ordersPage;
			OrderOutput orderOutput;
			StringBuilder fullName;

			if (page < 1)
				page = 1;
			if (size < 0)
				size = SIZE_DEFAULT;

			Pageable pageable = PageRequest.of(page - 1, SIZE_DEFAULT, Sort.by("createDate").descending());

			if (StringUtils.isEmpty(date)) {
				ordersPage = orderRepository.findAll(pageable);
			} else {
				ordersPage = orderRepository.findByCreateDate(pageable, Utils.convertStringToDate(date));
			}

			for (Order order : ordersPage) {
				orderOutput = new OrderOutput();
				fullName = new StringBuilder();

				fullName.append(order.getCustomer().getFirstName());
				fullName.append(" ");
				fullName.append(order.getCustomer().getMiddleName());
				fullName.append(" ");
				fullName.append(order.getCustomer().getLastName());

				orderOutput.setOrderId(order.getId());
				orderOutput.setCounpon(order.getCoupon());
				orderOutput.setCreateDate(order.getCreateDate());
				orderOutput.setCustomerName(fullName.toString());
				orderOutput.setPayment(order.getPayment());
				orderOutput.setPhone(order.getCustomer().getPhone());
				orderOutput.setShipping(order.getShipping());
				orderOutput.setStatus(order.getStatus());
				orderOutput.setTax(order.getTax());
				orderOutput.setOrderDetails(orderDetailRepository.findByOrder(order));

				orders.add(orderOutput);
			}

			// set value in pagination
			pagination.setPage(page);
			pagination.setSize(size);
			pagination.setTotalRecord(ordersPage.getTotalElements());

			orderOutputs.setOrders(orders);
			orderOutputs.setPagination(pagination);

			response.setData(orderOutputs);
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		} catch (Exception e) {
			LOGGER.error("Api get order has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getListOrder End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Order> updateStatusOrder(int orderId) {
		LOGGER.info(">>>>>>>>>>>updateStatusOrder Start >>>>>>>>>>>>");
		ResponseData<Order> response = new ResponseData<Order>();
		try {
			// get data in db
			Order order = orderRepository.findById(orderId);

			// case order null or null empty
			if (ObjectUtils.isEmpty(order)) {
				LOGGER.error("{} {}", ORDER_FIELD, Constants.RECORD_DO_NOT_EXIST);
				response.setCode(Constants.UNKNOWN_ERROR_CODE);
				response.setMessage(ORDER_FIELD + " " + Constants.RECORD_DO_NOT_EXIST);
			}

			// update status order
			if (order.getStatus() == Constants.STATUS_ACTIVE_VALUE) {
				order.setStatus(Constants.STATUS_INACTIVE_VALUE);
			} else {
				order.setStatus(Constants.STATUS_ACTIVE_VALUE);
			}

			orderRepository.save(order);

			response.setData(order);
		} catch (Exception e) {
			LOGGER.error("Api update status order has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>updateStatusOrder End >>>>>>>>>>>>");
		return response;
	}

	public void exportFileExcel(HttpServletResponse response, String date) {
		LOGGER.info(">>>>>>>>>>>exportFileExcel Start >>>>>>>>>>>>");
		try {
<<<<<<< HEAD

			// get data in db
//			GetListOrderOutput output = orderDao.getListOrder(page, size, date);

			// set data order output
//			List<OrderOutput> orderOutputs = output.getOrders();
//
//			// set list order detail in order
//			for (OrderOutput item : orderOutputs) {
//				List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(item.getOrderId());
//				item.setOrderDetails(orderDetails);
//			}
//			output.setOrders(orderOutputs);
			exportFile(response, orderRepository.findAll());
=======
			List<OrderOutput> orders = new ArrayList<>();

			List<Order> ordersPage;
			OrderOutput orderOutput;
			StringBuilder fullName;

			if (StringUtils.isEmpty(date)) {
				ordersPage = orderRepository.findAll();
			} else {
				ordersPage = orderRepository.findByCreateDate(Utils.convertStringToDate(date));
			}

			for (Order order : ordersPage) {
				orderOutput = new OrderOutput();
				fullName = new StringBuilder();

				fullName.append(order.getCustomer().getFirstName());
				fullName.append(" ");
				fullName.append(order.getCustomer().getMiddleName());
				fullName.append(" ");
				fullName.append(order.getCustomer().getLastName());

				orderOutput.setOrderId(order.getId());
				orderOutput.setCounpon(order.getCoupon());
				orderOutput.setCreateDate(order.getCreateDate());
				orderOutput.setCustomerName(fullName.toString());
				orderOutput.setPayment(order.getPayment());
				orderOutput.setPhone(order.getCustomer().getPhone());
				orderOutput.setShipping(order.getShipping());
				orderOutput.setStatus(order.getStatus());
				orderOutput.setTax(order.getTax());
				orderOutput.setOrderDetails(orderDetailRepository.findByOrder(order));

				orders.add(orderOutput);
			}

			exportFile(response, orders);
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		} catch (Exception e) {
			LOGGER.error("Api export file excel has exception : {}", e.getMessage());
		}
		LOGGER.info(">>>>>>>>>>>exportFileExcel End >>>>>>>>>>>>");
	}

	@SuppressWarnings("resource")
<<<<<<< HEAD
	private void exportFile(HttpServletResponse response, List<Order> orders) {
=======
	private void exportFile(HttpServletResponse response, List<OrderOutput> orders) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
		try {
			int lastRowTemp = 0;

			// read file excel template
			InputStream in = OrderService.class.getResourceAsStream(PATH_FILE_EXCEL);
			// get workbook template
			XSSFWorkbook workbook = new XSSFWorkbook(in);

			XSSFSheet sheet = workbook.cloneSheet(0);
<<<<<<< HEAD
			
=======

>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
			workbook.setSheetName(1, "Thống kê");
			lastRowTemp = sheet.getLastRowNum() + 1;

			// Create a cell style body
			XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setAlignment(HorizontalAlignment.LEFT);

			// check data not null or empty
			if (!orders.isEmpty()) {
				int length = orders.size();

				// write data in template file to new file
				for (int i = 0; i < length; i++) {
					// create row in sheet
					XSSFRow row = sheet.createRow(lastRowTemp + i);

<<<<<<< HEAD
					Order order = orders.get(i);
=======
					OrderOutput order = orders.get(i);
					StringBuilder builder = new StringBuilder();

					// set order detail
					int count = 0;
					for (OrderDetail item : order.getOrderDetails()) {
						if(count > 0) {
							builder.append("\r\n");
						}
						builder.append(item.getProduct().getName());
						builder.append(" - ");
						builder.append(item.getQuantity());
						count++;
					}
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1

					// write data in cell
					XSSFCell cell = row.createCell(0);
					cell.setCellValue(i + 1);
					cell.setCellStyle(cellStyle);

					cell = row.createCell(1);
<<<<<<< HEAD
					cell.setCellValue(order.getCustomer().getLastName());
=======
					cell.setCellValue(order.getCustomerName());
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
					cell.setCellStyle(cellStyle);

					cell = row.createCell(2);
					cell.setCellValue(order.getCreateDate());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(3);
<<<<<<< HEAD
					cell.setCellValue("Chi tiet don hang"); // TODO
					cell.setCellStyle(cellStyle);

					cell = row.createCell(4);
					cell.setCellValue(order.getPayment());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(5);
					cell.setCellValue(order.getShipping().getName());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(6);
					cell.setCellValue(order.getCustomer().getPhone());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(7);
					cell.setCellValue(order.getStatus());
=======
					cell.setCellValue(order.getPhone());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(4);
					cell.setCellValue(builder.toString());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(5);
					cell.setCellValue(order.getPayment());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(6);
					cell.setCellValue(order.getShipping().getName());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(7);
					cell.setCellValue(order.getTax().getId());
					cell.setCellStyle(cellStyle);
					
					cell = row.createCell(8);
					cell.setCellValue(order.getCounpon().getCode());
					cell.setCellStyle(cellStyle);
					
					cell = row.createCell(9);
					cell.setCellValue(order.getMoney());
					cell.setCellStyle(cellStyle);
					
					cell = row.createCell(10);
					if(order.getStatus() == 1) {
						cell.setCellValue(SUCCESS_FIELD);
					}else {
						cell.setCellValue(FAIL_FIELD);
					}
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
					cell.setCellStyle(cellStyle);
				}
			}
			workbook.removeSheetAt(0);

			// write file
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);

<<<<<<< HEAD
			response.setHeader("Content-Disposition", "attachment; filename=test.xlsx");
			IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
=======
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=ThongKe.xlsx");
			IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()), response.getOutputStream());
		} catch (IOException e) {
>>>>>>> 83106c2f9b89b7686be50a4864a5bbcf1c34b2b1
			e.printStackTrace();
		}
	}
}
