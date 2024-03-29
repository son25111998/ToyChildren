package com.ncs.serviceclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.common.util.Utils;
import com.ncs.dao.OderDao;
import com.ncs.model.converter.OrderDetailConverter;
import com.ncs.model.entity.Customer;
import com.ncs.model.entity.Order;
import com.ncs.model.entity.OrderDetail;
import com.ncs.model.input.OderInput;
import com.ncs.model.output.OrderDetailOutput;
import com.ncs.model.output.OrderOutput;
import com.ncs.model.output.OrderOutput2;
import com.ncs.repositoryclient.CustomerRepository;
import com.ncs.repositoryclient.OrderClientRepository;
import com.ncs.repositoryclient.OrderDetailClientRepository;

@Service
public class OrderService {
	@Autowired
	private OrderClientRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OderDao oderDao;

	@Autowired
	private OrderDetailClientRepository orderDetailRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String ORDER_FIELD = "Đơn hàng";
	private static final String PATH_FILE_EXCEL = "/templates/TemplateExcel.xlsx";
	private static final int SIZE_DEFAULT = 10;
	private static final String SUCCESS_FIELD = "Thành công";
	private static final String FAIL_FIELD = "Thất bại";
	private static final int STATUS_2 = 2;

	public ResponseData<List<Order>> getOrderByProfile(HttpServletRequest request) {
		LOGGER.info(">>>>>>>>>>>getListOrder Start >>>>>>>>>>>>");
		ResponseData<List<Order>> response = new ResponseData<>();
		try {
			List<Order> orders = new ArrayList<>();

			String jwtToken = request.getHeader("Authorization");
			;

			String[] split_string = jwtToken.split("\\.");
			String base64EncodedBody = split_string[1];
			Base64 base64Url = new Base64(true);

			JsonNode node = new ObjectMapper().readTree(base64Url.decode(base64EncodedBody));

			String username = node.path("username").asText();

			Customer customer = customerRepository.findByUserName(username);

			orders = orderRepository.findByCustomerOrderByIdDesc(customer);

			int money;
			for (Order order : orders) {
				order.setOrderDetails(
						OrderDetailConverter.convertToListOrderDetailOutput(orderDetailRepository.findByOrder(order)));

				money = 0;

				// count money
				for (OrderDetailOutput orderDetail : order.getOrderDetails()) {
					money += orderDetail.getQuantity() * orderDetail.getProduct().getPrice()
							* (1 - orderDetail.getProduct().getDiscount() / 100);
				}

				money = money - order.getShipping().getCost() - order.getCoupon().getSale();

				order.setSumMoney(money);
			}

			response.setData(orders);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			LOGGER.error("Api get order has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getListOrder End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<OrderOutput2> getListOrder(OderInput input) {
		LOGGER.info(">>>>>>>>>>>getListOrder Start >>>>>>>>>>>>");
		ResponseData<OrderOutput2> response = new ResponseData<>();
		try {
			OrderOutput2 orders = new OrderOutput2();

			if (StringUtils.isEmpty(input.getPage()) || input.getPage() < 0)
				input.setPage(0);
			if (StringUtils.isEmpty(input.getSize()) || input.getSize() < 0)
				input.setSize(SIZE_DEFAULT);

			orders = oderDao.getListOrder(input);

			for (Order order : orders.getOrdersPage()) {
				order.setOrderDetails(
						OrderDetailConverter.convertToListOrderDetailOutput(orderDetailRepository.findByOrder(order)));
			}

			response.setData(orders);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			LOGGER.error("Api get order has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getListOrder End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Order> getOrderById(int orderId) {
		LOGGER.info(">>>>>>>>>>>getOrderById Start >>>>>>>>>>>>");
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

			order.setOrderDetails(
					OrderDetailConverter.convertToListOrderDetailOutput(orderDetailRepository.findByOrder(order)));

			response.setData(order);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			LOGGER.error("Api get order by id has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}

		LOGGER.info(">>>>>>>>>>>getOrderById End >>>>>>>>>>>>");
		return response;
	}

	public ResponseData<Order> updateStatusOrder(int orderId, int status) {
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
			order.setStatus(status);

			orderRepository.save(order);

			response.setData(order);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
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
		} catch (Exception e) {
			LOGGER.error("Api export file excel has exception : {}", e.getMessage());
		}
		LOGGER.info(">>>>>>>>>>>exportFileExcel End >>>>>>>>>>>>");
	}

	@SuppressWarnings("deprecation")
	public ResponseData<List<Long>> getListMoneyByMonth() {
		LOGGER.info(">>>>>>>>>>>getListMoneyByMonth Start >>>>>>>>>>>>");
		ResponseData<List<Long>> response = new ResponseData<>();
		try {
			List<Long> result = new ArrayList<>();
			int month;
			int money;
			long sumMoney1 = 0, sumMoney2 = 0, sumMoney3 = 0, sumMoney4 = 0, sumMoney5 = 0, sumMoney6 = 0,
					sumMoney7 = 0, sumMoney8 = 0, sumMoney9 = 0, sumMoney10 = 0, sumMoney11 = 0, sumMoney12 = 0;

			List<Order> orders = orderRepository.findByStatus(STATUS_2);

			orders.forEach(order -> {
				order.setOrderDetails(
						OrderDetailConverter.convertToListOrderDetailOutput(orderDetailRepository.findByOrder(order)));
			});

			for (Order order : orders) {
				month = order.getCreateDate().getMonth() + 1;
				money = 0;

				// count money
				for (OrderDetailOutput orderDetail : order.getOrderDetails()) {
					money += orderDetail.getQuantity() * orderDetail.getProduct().getPrice()
							* (1 - orderDetail.getProduct().getDiscount() / 100);
				}

				money = money - order.getShipping().getCost() - order.getCoupon().getSale();

				switch (month) {
				case 1:
					sumMoney1 += money;
					break;
				case 2:
					sumMoney2 += money;
					break;
				case 3:
					sumMoney3 += money;
					break;
				case 4:
					sumMoney4 += money;
					break;
				case 5:
					sumMoney5 += money;
					break;
				case 6:
					sumMoney6 += money;
					break;
				case 7:
					sumMoney7 += money;
					break;
				case 8:
					sumMoney8 += money;
					break;
				case 9:
					sumMoney9 += money;
					break;
				case 10:
					sumMoney10 += money;
					break;
				case 11:
					sumMoney11 += money;
					break;
				case 12:
					sumMoney12 += money;
					break;
				default:
					break;
				}
			}

			result.add(sumMoney1);
			result.add(sumMoney2);
			result.add(sumMoney3);
			result.add(sumMoney4);
			result.add(sumMoney5);
			result.add(sumMoney6);
			result.add(sumMoney7);
			result.add(sumMoney8);
			result.add(sumMoney9);
			result.add(sumMoney10);
			result.add(sumMoney11);
			result.add(sumMoney12);

			response.setData(result);
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			LOGGER.error("getListMoneyByMonth has exception : {}", e);
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		LOGGER.info(">>>>>>>>>>>getListMoneyByMonth Start >>>>>>>>>>>>");
		return response;
	}

	@SuppressWarnings("resource")
	private void exportFile(HttpServletResponse response, List<OrderOutput> orders) {
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

					OrderOutput order = orders.get(i);
					StringBuilder builder = new StringBuilder();

					// set order detail
					int count = 0;
					for (OrderDetail item : order.getOrderDetails()) {
						if (count > 0) {
							builder.append("\r\n");
						}
						builder.append(item.getProduct().getName());
						builder.append(" - ");
						builder.append(item.getQuantity());
						count++;
					}

					// write data in cell
					XSSFCell cell = row.createCell(0);
					cell.setCellValue(i + 1);
					cell.setCellStyle(cellStyle);

					cell = row.createCell(1);
					cell.setCellValue(order.getCustomerName());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(2);
					cell.setCellValue(order.getCreateDate());
					cell.setCellStyle(cellStyle);

					cell = row.createCell(3);
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
					if (order.getStatus() == 1) {
						cell.setCellValue(SUCCESS_FIELD);
					} else {
						cell.setCellValue(FAIL_FIELD);
					}
					cell.setCellStyle(cellStyle);
				}
			}
			workbook.removeSheetAt(0);

			// write file
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);

			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=ThongKe.xlsx");
			IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
