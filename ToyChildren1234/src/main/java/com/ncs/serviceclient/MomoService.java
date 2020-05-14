package com.ncs.serviceclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mservice.shared.constants.Parameter;
import com.mservice.shared.utils.Encoder;
import com.ncs.common.ResponseData;
import com.ncs.common.constants.Constants;
import com.ncs.model.output.MomoConfim;
import com.ncs.model.output.MomoQRResponse;

@Service
public class MomoService {

	private final Logger LOGGER = LoggerFactory.getLogger(MomoService.class);

	private final String STORESLUG = "MOMOKELI20200511-13021998";
	private final String PARTNER_CODE = "MOMOKELI20200511";
	private final String ACCESS_KEY = "M7QxWaSyPxYu3v33";
	private final String SECRET_KEY = "yYwoBAVj831NjzZNzI6mirQDfQiOEn1Z";
	private final String DOMAIN = "https://test-payment.momo.vn";
	private final String NOTIFY_URL = "https://101.99.14.221:9000/api/v1/pay/momo";
	private final String RETURN_URL = "https://101.99.14.221:9000/api/v1/pay/momo";
	private final String pathCreateQr = "/pay/store/";
	private final String pathConfim = "/pay/confirm";

	public ResponseData<String> createRequest(String orderId, String sumMoney) {
		LOGGER.info(">>>>>>>>>>>createRequest Start >>>>>>>>>>>>");
		ResponseData<String> response = new ResponseData<>();
		try {
			StringBuilder url = new StringBuilder();
			String uuid = UUID.randomUUID().toString();
			
			String signatureBuilder = new StringBuilder().append("partnerCode").append("=").append(PARTNER_CODE)
					.append("&").append("accessKey").append("=").append(ACCESS_KEY).append("&").append("requestId")
					.append("=").append(uuid).append("&").append("amount").append("=").append(sumMoney).append("&")
					.append("orderId").append("=").append(orderId).append("&").append("orderInfo").append("=")
					.append("Test thanh toan momo").append("&").append("returnUrl").append("=").append(RETURN_URL)
					.append("notifyUrl").append("=").append(NOTIFY_URL).append("extraData").append("=").append("merchantName=BABYSHOP")
					.toString();

			String signature = Encoder.signHmacSHA256(signatureBuilder, SECRET_KEY);
			
			url.append("https://test-payment.momo.vn/gw_payment/payment/qr?");
			url.append("partnerCode=");
			url.append(PARTNER_CODE);
			url.append("&accessKey=");
			url.append(ACCESS_KEY);
			url.append("&requestId=");
			url.append(uuid);
			url.append("&amount=");
			url.append(sumMoney);
			url.append("&orderId=");
			url.append(orderId);
			url.append("&signature=");
			url.append(signature);
			url.append("&requestType=captureMoMoWallet");
			
			response.setCode(Constants.SUCCESS_CODE);
			response.setMessage(Constants.SUCCESS_MSG);
			response.setData(url.toString());
		} catch (Exception e) {
			LOGGER.error("Api create request momo has exception : {}", e.getMessage());
			response.setCode(Constants.UNKNOWN_ERROR_CODE);
			response.setMessage(Constants.UNKNOWN_ERROR_MSG);
		}
		LOGGER.info(">>>>>>>>>>>createRequest Start >>>>>>>>>>>>");
		return response;
	}

	public String createUrlQrcode(Long amount, String billId) {
		String urlQrCode = "";
		try {
			String signatureBuilder = new StringBuilder().append("storeSlug").append("=").append(STORESLUG).append("&")
					.append(Parameter.AMOUNT).append("=").append(amount).append("&").append("billId").append("=")
					.append(billId).toString();

			String signature = Encoder.signHmacSHA256(signatureBuilder, SECRET_KEY);

			urlQrCode = new StringBuilder().append(DOMAIN + pathCreateQr).append(STORESLUG).append("?").append("a")
					.append("=").append(amount).append("&").append("b").append("=").append(billId).append("&")
					.append("s").append("=").append(signature).toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return urlQrCode;
	}

	public byte[] createQrCode(Long amount, String uuid) {
		byte[] jpgByteArray = new byte[0];
		try {
			String data = this.createUrlQrcode(amount, uuid);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(matrix, "JPG", outputStream);
			jpgByteArray = outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jpgByteArray;
	}

	public MomoQRResponse validateQRNotifyRequest(String momoQRRequest) {
		String[] pairs = momoQRRequest.split("&");
		Map<String, String> qrMomoReq = new HashMap<>();
		MomoQRResponse momoQRResponse = null;
		for (String pair : pairs) {
			System.out.println("______________" + pair);
			String[] child = pair.split("=");
			qrMomoReq.put(child[0], child[1]);
		}
		try {
			String signatureBuilder = new StringBuilder().append(Parameter.AMOUNT).append("=")
					.append(Long.parseLong(qrMomoReq.get("amount"))).append("&").append("message").append("=")
					.append(URLDecoder.decode(qrMomoReq.get("message"), StandardCharsets.UTF_8.toString()))
					.append("momoTransId").append("=").append(qrMomoReq.get("transaction_id")).append("partnerRefId")
					.append("=").append(qrMomoReq.get("order_id")).append("status").append("=")
					.append(Integer.parseInt(qrMomoReq.get("status_code"))).toString();

			String signature = Encoder.signHmacSHA256(signatureBuilder, SECRET_KEY);

			momoQRResponse = new MomoQRResponse();
			momoQRResponse.setAmount(Long.parseLong(qrMomoReq.get("amount")));
			momoQRResponse.setMessage(URLDecoder.decode(qrMomoReq.get("message"), StandardCharsets.UTF_8.toString()));
			momoQRResponse.setStatus(Integer.parseInt(qrMomoReq.get("status_code")));
			momoQRResponse.setPartnerRefId(qrMomoReq.get("order_id")); // uuid
			momoQRResponse.setMomoTransId(qrMomoReq.get("transaction_id"));
			momoQRResponse.setSignature(signature);
		} catch (Exception e) {
			e.printStackTrace();
		}
		confim(momoQRResponse);
		return momoQRResponse;
	}

	private void confim(MomoQRResponse momoQRResponse) {
		try {
			MomoConfim momoConfim = new MomoConfim();
			momoConfim.setPartnerCode(PARTNER_CODE);
			momoConfim.setPartnerRefId(momoQRResponse.getPartnerRefId());
			momoConfim.setRequestId(UUID.randomUUID().toString());
			if (momoQRResponse.getStatus() == 0) {
				momoConfim.setRequestType("capture");
			} else {
				momoConfim.setRequestType("revertAuthorize");
			}
			momoConfim.setMomoTransId(momoQRResponse.getMomoTransId());
			momoConfim.setCustomerNumber("0969346468");
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(DOMAIN + pathConfim);
			StringEntity entity = new StringEntity(momoConfim.toString());
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			CloseableHttpResponse response = client.execute(httpPost);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
