package com.ncs.serviceclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.dao.OrderDao;
import com.ncs.model.entity.Order;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.output.GetListOrderOutput;
import com.ncs.model.output.OrderOutput;
import com.ncs.repositoryclient.OrderDetailRepository;
import com.ncs.repositoryclient.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderDao orderDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String ORDER_FIELD = "Đơn hàng";
	private static final String PATH_FILE_EXCEL = "/templates/TemplateExcel.xlsx";
//	private static final String SUCCESS_FIELD = "Thành công";
//	private static final String FAIL_FIELD = "Thất bại";

	public ResponseData<GetListOrderOutput> getListOrder(int page, int size, String date) {
		LOGGER.info(">>>>>>>>>>>getListOrder Start >>>>>>>>>>>>");
		ResponseData<GetListOrderOutput> response = new ResponseData<GetListOrderOutput>();
		try {
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
		} catch (Exception e) {
			LOGGER.error("Api export file excel has exception : {}", e.getMessage());
		}
		LOGGER.info(">>>>>>>>>>>exportFileExcel End >>>>>>>>>>>>");
	}

	@SuppressWarnings("resource")
	private void exportFile(HttpServletResponse response, List<Order> orders) {
		try {
			int lastRowTemp = 0;

			// read file excel template
			InputStream in = OrderService.class.getResourceAsStream(PATH_FILE_EXCEL);
			// get workbook template
			XSSFWorkbook workbook = new XSSFWorkbook(in);

			XSSFSheet sheet = workbook.cloneSheet(0);
			
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

					Order order = orders.get(i);

					// write data in cell
					XSSFCell cell = row.createCell(0);
					cell.setCellValue(i + 1);
					cell.setCellStyle(cellStyle);

					cell = row.createCell(1);
					cell.setCellValue(order.getCustomer().getLastName());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(2);
					cell.setCellValue(order.getCreateDate());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(3);
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
					cell.setCellStyle(cellStyle);
				}
			}
			workbook.removeSheetAt(0);

			// write file
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);

			response.setHeader("Content-Disposition", "attachment; filename=test.xlsx");
			IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
