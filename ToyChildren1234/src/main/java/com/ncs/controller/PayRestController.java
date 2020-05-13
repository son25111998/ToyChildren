package com.ncs.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.common.ResponseData;
import com.ncs.model.input.PayInput;
import com.ncs.model.output.MomoQRResponse;
import com.ncs.serviceclient.MomoService;
import com.ncs.serviceclient.PayService;

@RestController
@RequestMapping(value = "/member/pay", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class PayRestController {
	@Autowired
	private PayService payService;
	@Autowired
	private MomoService momoService;
	
	@PostMapping
	public ResponseData<Object> pay(@RequestBody PayInput input, HttpServletRequest request) {
		return payService.pay(input, request);
	}
	
	@PostMapping("/payments/momo")
    public ResponseData<String> momoWatchNotification(@RequestBody String momoQRRequest) {
		ResponseData<String> response = new ResponseData<>();
        MomoQRResponse momoQRResponse = this.momoService.validateQRNotifyRequest(momoQRRequest);
        response.setData(momoQRResponse.toString());
        response.setCode(200);
        return response;
    }

    @GetMapping("/payments/momo/qrCode")
    public ResponseEntity<byte[]> momoGetQrCode(@RequestParam("amount") Long amount) {
        String uuid = UUID.randomUUID().toString();
        byte[] data = this.momoService.createQrCode(amount, uuid);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
    }
}
