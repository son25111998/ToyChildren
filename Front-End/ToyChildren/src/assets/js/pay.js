$(document).ready(function() {

	$("#but-checkout").click(function(event) {
		$.ajax({
			url: '/ivymoda/check',
			type: 'GET'
		})
		.done(function(value) {
			if(value == "success"){
				window.location.href = "http://localhost:8090/ivymoda/thanh-toan";
			}
			else if(value=="user"){
				$.alert({
				    title: 'Ivy moda',
				    content: 'Vui lòng đăng nhập để thanh toán đơn hàng',
				    buttons: {
				        tryAgain: {
				            text: 'Đăng nhập',
				            btnClass: 'btn-gray',
				            action: function(){
				            	window.location.href = "http://localhost:8090/ivymoda/dang-nhap";
				            }
				        }
				    }
				});
			}
			else{
				$.alert({
				    title: 'Ivy moda',
				    content: 'Giỏ hàng trống không thể thanh toán',
				});
			}
		});
	});
	
	$("#but_coupon_code").click(function(event) {
		var voucher = $("#coupon_code_text").val();
		if(voucher == ""){
			$("#p_coupon").attr("style","display:block");
			$("#p_coupon").html("Vui lòng nhập mã giảm giá");
		}else{
			$.ajax({
				url: '/ivymoda/voucher/check',
				type: 'GET',
				data: {
					code: voucher
				},
			})
			.done(function(value) {
				checkVoucher(value);
			});
			
		}
	});
	
	// thanh toán
	$("#but_pay").click(function(event) {
		var payment = $("input[name=payment_method]:checked").parent().find("label").text();
		var shipping = $("#box_shipping_method").find("label").text();
		var voucher = $("#voucher-view").attr("data");
		
		console.log(payment);
		if(voucher == null){
			voucher = 1;
		}
		
		$.ajax({
			url: '/ivymoda/thanh-toan/add',
			type: 'GET',
			data: {
				voucherId: voucher,
				payment: payment,
				shipping: shipping
			},
		})
		.done(function(value) {
			if(value == "success"){
				alert("Thanh toán thành công đơn hàng");
				window.location.href = "http://localhost:8090/ivymoda/";
			}else{
				alert("Có lỗi xảy ra trong quá trình thanh toán vui lòng quay lại sau ít phút");
			}
		});
	});
});

function checkVoucher(value){
	if(value == ""){
		$("#p_coupon").attr("style","display:block");
		$("#p_coupon").html("Mã giảm giá không hợp lệ");
		
	}else{
		$("#p_coupon").attr("style","display:none");
		var voucher = $("#voucher-view");
		var html = "<td colspan='2'>Số tiền giảm giá</td><td class='text-right'> - "+value.currencyMoney+"</td>"
		voucher.attr('style', '');
		voucher.attr('data', value.id);
		voucher.html(html);
		$("#product_sub_total_end").html("<b>"+value.totalCurrencyMoney + "</b>");
	}
}