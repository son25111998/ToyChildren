// onclick view image
$(document).ready(function() {
	var url = '';
	$("div.product-image img").click(function(){
		var url = $(this).attr('src');

		var html = "<img id='demo4' src='"+url+"'>";
		$(".demowrap").html(html);
	});
	
	$(".show-product-size").click(function(){
		$("#product-size-text").attr("data-size",$(this).attr("data-size-current"));
		$(".show-product-size").removeClass("active-color");
		$(this).addClass("active-color");
	});
	
	$(".show-product-color").click(function(){
		$("#product-color-text").attr("data-color",$(this).attr("data-color-current"));
	});
	
	$("#but_choose_product").click(function(){
		var color = $("#product-color-text").attr("data-color");
		var size = $("#product-size-text").attr("data-size");
		var product = $("#product-detail").attr("data-value");
		var sale = $(".price_productDetail").attr("data-sale");
		var image = $("#demo4").attr("src");
		var cart = {};

		var mess = "Vui lòng chọn ";
		var check = false;
		var quantity = $("input[name=product_sub_quantity]").val();
		
		if(quantity > 4){
			quantity = 3;
		}
		
		$(".vuilongchon").attr("style","display:none");
		
		if(size == ""){
			mess = mess+"size";
			check = true;
			$(".vuilongchon").attr("style","display:block");
		}
		if(color == ""){
			if(check){
				mess = mess+" , color";
			}else{
				mess = mess + "color";
			}
			$(".vuilongchon").attr("style","display:block");
		}
		
		$(".vuilongchon").text(mess);
		
		if(size != "" && product != "" && color != ""){
			cart["productId"] = product;
			cart["colorId"] = color;
			cart["sizeId"] = size;
			cart["saleId"] = sale;
			cart["quantity"] = quantity;
			cart["image"] = image;
			$.ajax({
				url: '/ivymoda/gio-hang/add',
				type: 'POST',
				dataType: 'json',
				data: {
					cart: JSON.stringify(cart)
				}
			}).done(function(value) {
				alertAddCart(value.responseText)
			})
			.fail(function(value) {
				alertAddCart(value.responseText)
			});
		}
	});
	
	$(".filter_show").click(function() {
		$("#show-filter").toggleClass('filter-hide');
	});

	$(".filter > p").click(function(event) {
		$(this).parent().find('p').attr('style', '');
		$(this).attr('style', 'border:1px solid black;');

		var data = $(this).attr('data-item');
		$(this).parent().attr("data",data);
	});
	
	$("#but_filter_remove").click(function(event) {
		$(".filter > p").attr('style', '');
		$(".filter").removeAttr('data');
		$("input[name=hid_quantity_live]").prop('checked', false);

		$(".filter_color > select").val('0').attr('checked', 'checked');
	});


	$("#but_filter_product").click(function(event) {
		var category = $("h4.product").attr('data');
		var size = $(".filter_size").attr("data");
		var color = $(".filter_color").attr("data");
		var material = $("select[name=att_chat_lieu]").val();
		var design = $("select[name=att_kieu_dang]").val();
		var pattern = $("select[name=att_hoa_tiet]").val();
		if($("input[name=hid_quantity_live]").is(':checked')){
			var status = 1;
		}else{
			var status = 0;
		}

		var filter = {};
		filter["categoryId"] = category;
		filter["sizeId"] = size;
		filter["colorId"] = color;
		filter["materialId"] = material;
		filter["designId"] = design;
		filter["patternId"] = pattern;
		filter["status"] = status;

		console.log(filter);
		
		$.ajax({
			url: '/ivymoda/danh-muc/filter',
			type: 'GET',
			dataType: 'json',
			data: {
				filter: JSON.stringify(filter)
			}
		})
		.done(function(value) {
			console.log("success");
		})
		.fail(function(value) {
			console.log("error");
		});
	});
});

function AutoActive(){
	var color = $("li.show-product-color");
	var size = $("li.show-product-size");
	
	if(color.length == 1){
		$(color).addClass("active-color");
	}
	
	if(size.length == 1){
		$(size).addClass("active-size");
	}
	
	$(size).each(function() {
		var activeSize = $(this).hasClass("active-size");
		if(activeSize){
			$("#product-size-text").text($(this).attr("data-size-text"));
			$("#product-size-text").attr("data-size",$(this).attr("data-size-current"));
		}
	});
	
	$(color).each(function() {
		var activeColor = $(this).hasClass("active-color");
		if(activeColor){
			$("#product-color-text").text($(this).attr("data-color-text"));
			$("#product-color-text").attr("data-color",$(this).attr("data-color-current"));
		}
	});
}

function alertAddCart(value){
	if(value == "fail"){
		alert("Vui lòng chọn màu sắc hoặc kích cỡ khác");
	} else if(value == "OOS"){
		alert("Sản phẩm này đang hết hàng vui lòng chọn màu sắc hoặc kích cỡ khác");
	}
	else{
		$.confirm({
		    title: 'Ivy moda',
		    content: 'Thêm thành công sản phẩm vào giỏ hàng',
		    typeAnimated: true,
		    buttons: {
		        tryAgain: {
		            text: 'Đến giỏ hàng',
		            btnClass: 'btn-gray',
		            action: function(){
		            	window.location.href = "http://localhost:8090/ivymoda/gio-hang";
		            }
		        },
		        'Tiếp tục mua sắm': function () {
		     
		        }
		    }
		});
	}
}

/* Avatar start */
function BrowseServer(startupPath) {
	var finder = new CKFinder();
	finder.basePath = '../';
	finder.startupPath = startupPath;
	finder.selectActionFunction = SetFileField;
	finder.popup();
}

// This is a sample function which is called when a file is selected in
// CKFinder.
function SetFileField(fileUrl, data) {
	document.getElementById("imgpreview").src = fileUrl;
	$("#urlAvatar").attr("value",fileUrl);
}
