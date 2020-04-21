$(document).ready(function() {
	
	activePage();
	
	
	
	$(".list-inline>li").click(function() {
		
		if($(this).attr("data-page") == 0){
			return;
		}
		
		var pageNow = $(this).attr("data-page");
		var pageMax = $("#phantrang").find('ul').attr("data-max");
		
		$("#phantrang").find('ul').attr("data-page-active",pageNow);
		
		activePage();
		
		var page = $("#phantrang").find('ul').attr("data-page-active");

		var filter = $("#dieuhuong");
		
		if(filter.length == 0){
			var key = $("#key").attr("data-key");

			$.ajax({
				url: '/ivymoda/tim-kiem/',
				type: 'POST',
				data: {
					key: key,
					page: page
				}
			})
			.done(function(product) {
				dataProductAjax(product);
			})
		}
		else{
			var path = $("#dieuhuong").find("ul").attr("data");
			$.ajax({
				url: '/ivymoda/danh-muc/',
				type: 'GET',
				data: {
					path: path,
					page: page
				}
			})
			.done(function(product) {
				dataProductAjax(product);
			})
		}
	});
});

function activePage() {
	var pageActive = $("#phantrang").find('ul').attr("data-page-active");
	var pageMax = $("#phantrang").find('ul').attr("data-max");
	var pageNext = parseInt(pageActive) + parseInt(1);
	var pagePrev = parseInt(pageActive) - parseInt(1);
	
	$(".list-inline>li").removeClass("active-page");
	
	$(".list-inline>li.page").each(function() {
		var page = $(this).attr("data-page");
		if(page == pageActive){
			$(this).addClass("active-page");
		}
	});
	
	if(pageActive == 1){
		$(".list-inline").find('.page-prev').attr("data-page","0");
	}
	else{
		$(".list-inline").find('.page-prev').attr("data-page",pagePrev);
	}
	
	if(pageActive == pageMax){
		$(".list-inline").find('.page-next').attr("data-page","0");
	}
	else{
		$(".list-inline").find('.page-next').attr("data-page",pageNext);
	}
}


function dataProductAjax(product){
	var html = "";
	if(product == ""){
		html += "<div class='col-md-3 col-sm-4 col-xs-6'";
	    html += "Không tìm thấy sản phẩm phù hợp";
		html += "</div>";
	}else{
		$.each(JSON.parse(product), function( index, obj ) {
			html += "<div class='col-md-3 col-sm-4 col-xs-6'>";
			html +=	"<a href='/ivymoda/san-pham/"+obj.path+"'>";
			html +=	"<img src='"+obj.imageFirts+"' style='display: inline;'></a>";
			html += "<div class='title_product'>";
			html += "<a href='/ivymoda/san-pham/"+obj.path+"'>"+obj.name+"</a>";
			html += "</div>";
			html += "<div class='price_product text-center'>";
			if(obj.saleProduct&&!obj.outOfStock){
				html += "<span class='price_product_main'>"+obj.currencyMoney+"</span>";
				html += "<span class='price_product_sale'>"+obj.currencyMoneyAfterSale+"</span>";
			}else{
				html += "<span class='price_product_sale'>"+obj.currencyMoney+"</span>";
			}
			html += "</div>";
			html += "<div class='new_product text-center'>";
			if(obj.saleProduct){
				html += "<span style='color: red'> - "+ obj.sale.reduction + " %</span>";
			}
			html += "&nbsp;";
			if(obj.newProduct){
				html += "<span>_new_&nbsp; </span>";
			}
			html += "</div>";
			if(obj.saleProduct){
				html += "<div class='sale_products'>Sale</div>";
			}
			html += "</div>";
		});
	}
	$("#list-product").empty();
	$("#list-product").html(html);
}