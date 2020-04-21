$(document).ready(function(){

    $.each( $('.menu-cat-left a'), function( key, value ) {
        // get a's href
        var aHref = $(this).attr('href') ;
        // if current url as same as a's href
        if(aHref == document.URL)
        {
            var _grand_parent = $(this).closest('.menu-list');
            $(_grand_parent).addClass('in');
        }
    });

    $('#but_subscribe').click(function(){
        var email = $('#txt_subscribe_email').val();
        if (email == '' || !validateEmail(email))
        {
            p_message('#subscribe_error', '<p class="required">Vui lòng nhập địa chỉ email hợp lệ</p>');
        }else{
            subscribe_email(email);
        }
    });

    $('#txt_subscribe_email').keypress(function (e) {

        var email = $('#txt_subscribe_email').val();
        if (e.which == 13) {
            subscribe_email(email);
            return false;
        }
    });

    $(".filter_show").click(function(){
        $(".filter_hide").toggle();
    });

    $(".filter_products_size li a").click(function() {
        $(".filter_products_size li").removeClass('selected');
        $(this).parent().addClass('selected');
    });

    $(".filter_products_color li a").click(function() {
        $(".filter_products_color li").removeClass('selected');
        $(this).parent().addClass('selected');
    });

    $('#but_filter_product').click(function(){
        var size = $('.filter_products_size').find('li.selected').data('size');
        var color = $('.filter_products_color').find('li.selected').data('color');
        var att_chat_lieu = $('#att_chat_lieu').val();

        if (typeof (size) == 'undefined' && typeof (color) == 'undefined' && typeof (att_chat_lieu) == 'undefined')
        {
            p_message('#msg_error_size_color', 'Vui lòng chọn kích cỡ, màu sắc hoặc thuộc tính để lọc')


        }else{

            if (typeof (size) != 'undefined')
            {
                $('#hid_size').val(size);
            }

            if (typeof (color) != 'undefined')
            {
                $('#hid_color').val(color);
            }

            $('#frm_cat').submit();
        }
    });

    $('#but_filter_remove').click(function(){
        var link = $('#frm_cat').data('slug');
        if(link == '' || link == undefined)
        {
            link = 'tim-kiem';
        }

        document.location.href = base_url+link;;
    });

    $('#sel_order').change(function(){
        $('#frm_cat').submit();
    });

    $('.show-product-color').click(function(){

        var el =  $(this);
        var image_color = el.data('image-color');
        var el_demo = $('#demo4');
        $('#hid_color_text').val(el.data('color-text'));

        //reset zoom image
        if(image_color != '')
        {
            el_demo.attr('src', image_color);
        }

        el_demo.ImageZoom({type:'standard',zoomSize:[480,300],bigImageSrc:image_color,zoomViewerClass:'standardViewer'});

        //size active
        var size_active = String(el.data('size-active'));
        $('.bg_x_size').show();

        if (size_active != ''  && size_active != null)
        {
            if (size_active.indexOf(',') != -1)
            {
                //set active
                var bg_size = size_active.split(',');
                for (var i=0; i<bg_size.length; i++)
                {
                    $('#product_size_'+bg_size[i]).find('.bg_x_size').hide();
                }
            }else{
                $('#product_size_'+size_active).find('.bg_x_size').hide();
            }
        }

        //change active color
        $('.show-product-color').removeClass('active-color');
        el.addClass('active-color');

        var text_color = el.data('color-text');
        $('#product-color-text').html(text_color+'<label class="required"><em>*</em></label>');

        //trigger size color
        check_product(false);
    });

    $('.show-product-size').click(function(){
        var el = $(this);
        var text_size = el.data('size-text');
        $('#product-size-text').html(text_size);
        $('#hid_size_text').val(el.data('size-text'));

        //remove class
        $('.show-product-size').removeClass('active-size');
        //set class active
        el.addClass('active-size');

        //color active
        var color_active = String(el.data('color-active'));
        $('.bg_x_color').show();
        if (color_active != ''  && color_active != null)
        {
            //show all
            if (color_active.indexOf(',') != -1)
            {
                //set active
                var bg_color = color_active.split(',');
                for (var i=0; i<bg_color.length; i++)
                {
                    $('#product_color_'+bg_color[i]).find('.bg_x_color').hide();
                }
            }else{
                $('#product_color_'+color_active).find('.bg_x_color').hide();
            }

        }

        //trigger size color
        check_product(false);
    });



    $(document).ready(function() {
        $("#owl-demo_1").owlCarousel({
            loop:false,
            margin:10,
            nav:false,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:3
                },
                1000:{
                    items:5
                }
            }

        });

        $("#owl-demo_2").owlCarousel({
            loop:false,
            nav:true,
            margin:10,
            navText : ['<i class="fa fa-angle-left fa-5x" aria-hidden="true"></i>','<i class="fa fa-angle-right fa-5x" aria-hidden="true"></i>'],
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:3
                },
                1000:{
                    items:5
                }
            }

        });

        var viewport = jQuery(window).width();
        if(viewport < 479) //mobile
        {
            $('.owl-next').attr('style','right: 16px !important');
            $('.owl-prev').attr('style','left: 31px !important');
        }
    });

    $(".holine_show").click(function(){
        $(".holine_hide").toggle();
    });

    $('.number_price').format_number_price();

    var pay_d = new Date();
    var year = pay_d.getFullYear() - 35;
    var max_year = pay_d.getFullYear() - 10;
    pay_d.setFullYear(year);

    $('.datepicker').datepicker({
        yearRange: "1950:"+max_year,
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        defaultDate: pay_d,
    });

    $('#register_region_id').change(function(){
        show_city('#register_region_id', '#register_city_id');
    });

    $('#register_region_id_other').change(function(){
        show_city('#register_region_id_other', '#register_city_id_other');
    });

    $('#register_city_id').change(function(){
        show_vnward('#register_region_id', '#register_city_id', '#vnward_id');
    });

    $('#register_city_id_other').change(function(){
        show_vnward('#register_region_id_other', '#register_city_id_other', '#vnward_id_other');
    });

    function show_city(register_id_in,  city_id_out)
    {
        var region_id = $(register_id_in).val();

        if (region_id != '' && region_id != -1)
        {
            $.ajax({
                type: 'post',
                url: base_url+'ajax/getCity',
                dataType: 'json',
                data: {'region_id':region_id}
            }).done(function(data){
                $(city_id_out).html(data.city_html);
            }).fail(function () {
                //console.log('error-connection');
            });
        }
    }

    function show_vnward(register_id_in,  city_id_out, vnward_out)
    {
        var region_id = $(register_id_in).val();
        var city_id = $(city_id_out).val();

        if (region_id != '' && region_id != -1 && city_id != '-1')
        {
            $.ajax({
                type: 'post',
                url: base_url+'ajax/getVnward',
                dataType: 'json',
                data: {'region_id':region_id,'city_id':city_id}
            }).done(function(data){
                $(vnward_out).html(data.vnward_html);
            }).fail(function () {
                //console.log('error-connection');
            });
        }
    }


    $("#user_register").click(function(){
        $(".pay_hidden_ts_1").show();
    });

    $("#user_bt").click(function(){
        $(".pay_hidden_ts_1").hide();
    });

    $("#checkbox_id").click(function(){
        var check_address_other = $(this).is(':checked');
        if (check_address_other)
        {
            $(".pay_hidden_ts_main").hide();
            $(".pay_hidden_ts").show();
        }else{
            $(".pay_hidden_ts_main").show();
            $(".pay_hidden_ts").hide();
        }
    });

    $(document).on('click', 'input[name="shipping_method"]', function(){
        var type = 'ship';
        changeInfoCheckout(type);
    });

    $(document).on('click', 'input[name="payment_method"]', function(){
        var type = 'payment';
        changeInfoCheckout(type);
    });

    $(document).on('click', '#but_coupon_code', function(){
        var type = 'coupon_add';
        changeInfoCheckout(type);
    });

    // $(document).on('click', '.but_call_checkout', function(){
    //     var type = $(this).data('type');
    //     changeInfoCheckout(type);
    // });

    $(document).on('change', '#order_account_code', function(){
        var type = $(this).data('type');
        changeInfoCheckout(type);
    });

    $(document).on('click', '#but_coupon_delete', function(){
        var type = 'coupon_remove';
        changeInfoCheckout(type);
    });

    $('#btn-facebook-login').on('click', function () {

        FB.login(function (response) {
            if (response.authResponse) {
                $.ajax({
                    url: base_url + 'ajax/user_login_facebook',
                    type: 'POST',
                    dataType: 'json',
                    data: response.authResponse
                }).done(function (data) {
                    if (data.status == 1) {
                        window.location.reload();
                    } else {
                        $('#msg_login').html(data.message).show();
                    }
                }).fail(function (msg) {
                    // console.log('loi:');
                    // console.log(msg);
                });
            }
        }, {scope: 'email,public_profile', auth_type: 'rerequest'});

    });

    $('#btn-google-login').on('click', function () {
        gapi.auth2.getAuthInstance().signIn().then(function (e) {
            if (gapi.auth2.getAuthInstance().isSignedIn.get()) {
                $.ajax({
                    url: base_url + 'ajax/user_login_google',
                    type: 'POST',
                    dataType: 'json',
                    data: e.getAuthResponse()
                }).done(function (data) {
                    if (data.status == 1) {
                        window.location.reload();
                    } else {
                        $('#msg_login').html(data.message).show();
                    }
                }).fail(function (msg) {
                    //console.log(msg);
                });
            }
        });
    });

    $(document).on('click', '#but_customer_pay', function(){
        var customer_cash = $(this).data('customer-cash');
        var customer_cash_out = $('#customer_cash_out').val().replace('.', '');
        var product_sub_total_end = $('#product_sub_total_end').data('total-end');
        customer_cash_out = customer_cash_out.trim();
        var type = 'cash_out';

        if (customer_cash_out > customer_cash || (customer_cash_out > product_sub_total_end) || customer_cash_out < 0 || customer_cash_out == '')
        {
            p_message('#p_msg_alert', 'Vui lòng nhập số tiền hợp lệ.');
            return false;
        }else{
            //send cash out
            cashOut('cash_out', customer_cash_out);
        }
    });

    $('#che_pay_milys').click(function(){
        if($(this).prop('checked')){
            $('#box_pay_milys').show();
        }else{
            $('#box_pay_milys').hide();
            $('#customer_cash_out').val('');
            cashOut('cash_out', 0);
        }
    });

    $('#but_customer_pay_delete').click(function(){
        $('#box_pay_milys').hide();
        $('#che_pay_milys').attr('checked', false);
        cashOut('cash_out', 0);
    });

    $('#but_cart_send').on('click', function(){
        var check = true;
        $('#frm_cart_mail input').each(function(){
            var el = $(this).val();
            if (el == '')
            {
                p_message('#p_cart_mail', '<span class="required">Vui lòng nhập đầy đủ các thông tin yêu cầu</span>');
                check = false;
                return false;
            }
        });

        if (check)
        {
            var data_send = $('#frm_cart_mail').serialize();
            add_loading();
            $.ajax({
                type: 'post',
                url: base_url+'ajax/cart_mail',
                dataType: 'json',
                data: data_send
            }).done(function(data){
                if(data.status == 1)
                {
                    p_message('#p_cart_mail', '<span class="msg_success">'+data.msg+'</span>');
                    window.setTimeout(function(){
                        $("#myModal_mail .close").click();
                    }, 1000);
                }else{
                    p_message('#p_cart_mail', '<span class="required">'+data.msg+'</span>');
                }

                remove_loading();

            }).fail(function () {
                //console.log('error-connection');
                remove_loading();
            });
        }
    });

    $("#menu_fixed").affix({
        offset: {
            top : 40,
            bottom: 340,
        }
    });

    $('#but_step2').click(function () {
        $('#but_step2').prop("disabled", true);
        $('#frm_step2').submit();
    });

    $('.but_order_cancel').click(function(){
        if (confirm('Bạn chắn chắn muốn huỷ đơn hàng này?')){
            var invoice_no = $(this).data('invoice-no');
            document.location.href = base_url+'customer/order_cancel/'+invoice_no;
        }

        return false;
    });

    $(document).on('click', '#view_showroom', function(){
        check_product(true);
    });

    $('#but-landing').click(function(){
        add_loading();
        $.ajax({
            type: 'post',
            url: base_url+'ajax/landing_voucher',
            dataType: 'json',
            data: $('#frm_landing').serialize()
        }).done(function(data){
            if(data.status == 1)
            {
                p_message('#subscribe_error', data.message);
                remove_loading();
            }else{
                p_message('#subscribe_error', data.message);
                remove_loading();
            }

        }).fail(function () {
            //console.log('error-connection');
            remove_loading();
        });

    });

});

function process_check_showroom()
{
    var check_login = $('#hid_login').val();
    var hid_ip = $('#hid_ip').val();
    //Cookies.remove(hid_ip, { path: '' }); // removed!
    //Cookies.set(hid_ip, '{"long":123,"lat":234}', { expires: 365, path: ''});
    var real_ip_xxx = Cookies.get(hid_ip);

    if(real_ip_xxx == undefined){

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showLocation, showErrorPosition);
            p_message('#msg_cuahang', 'Vui lòng cho phép chúng tôi lấy thông tin địa chỉ.');
        } else {
            p_message('#msg_cuahang', 'Trình duyệt không khỗ trợ lấy thông tin địa chỉ.');
            console.log('test-az');
        }


    }else{
        var log_info = jQuery.parseJSON(real_ip_xxx);
        showInfoShop(log_info.latitude, log_info.longitude);
    }
}

function showErrorPosition(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            console.log("User denied the request for Geolocation.");
            showInfoShop(-1, -1);
            break;
        case error.POSITION_UNAVAILABLE:
            // console.log("Location information is unavailable.");
            break;
        case error.TIMEOUT:
            // console.log("The request to get user location timed out.");
            break;
        case error.UNKNOWN_ERROR:
            // console.log("An unknown error occurred.");
            break;
    }
}

function setCookieLatLong(str){
    var hid_ip = $('#hid_ip').val();
    Cookies.set(hid_ip, str, { expires: 365, path: ''});
}

function showInfoShop(lat, long, count_call)
{
    var product_key = $('#hid_product_key').val();
    if(count_call == '')
    {
        count_call = 0;
    }

    data_send = {latitude:lat, longitude:long, product_key:product_key, count_call: count_call};

    //set lai cookie
    var hid_ip = $('#hid_ip').val();
    var str = '{"latitude":'+lat+', "longitude":'+long+'}';
    Cookies.set(hid_ip, str, { expires: 365, path: ''});

    add_loading();
    $.ajax({
        type: 'post',
        url: base_url+'ajax/info_position',
        dataType: 'json',
        data: data_send
    }).done(function(data){

        if(data.status == 1)
        {
            $('.list_cuahang').html(data.box_shop);
            p_message('#msg_cuahang', '');
        }else{
            $('.list_cuahang').html('');
            p_message('#msg_cuahang', data.message);
        }

        remove_loading();
    }).fail(function () {
        //console.log('error-connection');
        remove_loading();
    });
}

function showLocation(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    showInfoShop(latitude, longitude);
}

function changeInfoCheckout(type)
{
    var data_send = '';
    switch (type)
    {
        case 'ship':
            var ship_checked = $('input[name="shipping_method"]:checked').val();
            data_send = {'ship_id': ship_checked, 'type':type};
            break;

        case 'payment':
            var payment_method = $('input[name="payment_method"]:checked').val();
            data_send = {'payment_method': payment_method, 'type':type};
            break;

        case 'coupon_add':
            var coupon_code_text = $('#coupon_code_text').val();
            if(coupon_code_text == '' || coupon_code_text == 0)
            {
                p_message('#p_coupon', 'Vui lòng nhập mã giảm giá');
                return false;
            }

            data_send = {'coupon_code_text': coupon_code_text, 'type':type};
            break;

        case 'coupon_remove':
            $('#but_coupon_delete').hide();
            $('#coupon_code_text').val('');

            data_send = {'type':type};
            break;

        case 'account_code_add':
            var order_account_code = $('#order_account_code').val();
            data_send = {'order_account_code': order_account_code, 'type':type};
            break;

        case 'account_code_remove':
            $('#but_account_code_delete').hide();
            $('#order_account_code').val('');

            data_send = {'type':type};
            break;

        default:
            data_send = '';
    }

    add_loading();

    $.ajax({
        type: 'post',
        url: base_url+'ajax/info_checkout',
        dataType: 'json',
        data: data_send
    }).done(function(data){

        if(type == 'coupon_add')
        {
            if(data.coupon == 'ok')
            {
                //coupon valid
                //view button remove coupon
                $('#but_coupon_delete').show();
            }else{
                p_message('#p_coupon', 'Mã giảm giá không hợp lệ');
            }
        }


        if(type == 'account_code_add')
        {
            if(data.status_account_code == 'ok')
            {
                p_message('#p_coupon', 'Mã nhân viên hợp lệ');
            }
        }

        $('#box_product_total').html(data.box_product_total);
        remove_loading();
    }).fail(function () {
        //console.log('error-connection');
        remove_loading();
    });



}

function cashOut(type, customer_cash_out)
{
    add_loading();
    $.ajax({
        type: 'post',
        url: base_url+'ajax/cash_out',
        dataType: 'json',
        data: {'customer_cash_out':customer_cash_out, 'type':type}
    }).done(function(data){
        $('#box_product_total').html(data.box_product_total);
        if(data.status == -2  || data.status == -3)
        {
            //p_message('#p_msg_alert', data.msg);
        }

        remove_loading();
    }).fail(function () {
        //console.log('error-connection');
        remove_loading();
    });
}

function p_message(idc,msg)
{
    $(idc).html(msg);
    $(idc).show();
    $(idc).delay(100000).fadeOut(500);
}


function validateEmail($email) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    return emailReg.test( $email );
}

function add_loading() {
    $('.modal_loading').show();
}

function remove_loading() {
    $('.modal_loading').hide();
}

function show_row(row){
    $('#hid_row').val(row);
    $('#hid_status').val('setrow');
    $('#frm_cat').submit();
}

function check_product(check_show_room)
{
    //get size, color
    var size = $('.active-size').data('size-current');
    var color = $('.active-color').data('color-current');
    //$('#frm_showrom').hide();
    $('#hid_product_key').val('');

    var product_sub_id = '';
    var product_sub_status = 0;

    //set default
    var el = $('#but_choose_product');
    el.attr('data-product-sub-id', product_sub_id);
    el.attr('data-product-sub-status', product_sub_status);

    if (typeof (size) == 'undefined')
    {
        size = '';
    }

    if (typeof (color) == 'undefined')
    {
        color = '';
    }

    if (size == '' && color == '')
    {
        $('.vuilongchon').text('Vui lòng chọn màu sắc, size');
        return false;
    }

    if (typeof (product_sub) != 'undefined')
    {
        var obj_product = eval(jQuery.parseJSON(product_sub));
        var size_color = String(size+'_'+color);

        if(typeof (obj_product[size_color]) !== 'undefined')
        {
            //console.log('hello');
            var coupon_price_sale = obj_product[size_color]['coupon_price_sale'];
            var coupon_price_org = obj_product[size_color]['product_sub_price'];
            var product_sub_sku = obj_product[size_color]['product_sub_sku'];

            //show xem cac cua hang con hang
            //$('#frm_showrom').show();

            //thay doi lai barcode
            $("#barcode").barcode(product_sub_sku, "code39", {barWidth:1, barHeight:40, showHRI:true, moduleSize:9});

            //thay doi lai product key => product sku
            $('#hid_product_key').val(product_sub_sku);

            // console.log(coupon_price_sale+'--'+coupon_price_org);

            if (coupon_price_sale !== 'undefined' && coupon_price_sale  != '' && coupon_price_sale != 0)
            {
                //change price sale
                $('#display_price_sale').text(coupon_price_sale);
            }

            if (coupon_price_org != 'undefined' && coupon_price_org  != '' && coupon_price_org  != 0)
            {
                //change price
                $('#display_price_org').text(coupon_price_org);
            }

            product_sub_status = obj_product[size_color]['product_sub_status'];
            product_sub_id = obj_product[size_color]['product_sub_id'];

            $('.vuilongchon').text('');
            if(product_sub_status == 0 || product_sub_status == '')
            {
                $('.vuilongchon').html('Sản phẩm đã hết hàng Online.<br>&nbsp;Bạn có thể "Tìm cửa hàng".');
                if(check_show_room) process_check_showroom();
                return false;
            }else{
                el.attr('data-product-sub-id', product_sub_id);
                el.attr('data-product-sub-status', product_sub_status);
                if(check_show_room) process_check_showroom();
            }


        }else{
            if( color != '' && size == '')
            {
                $('.vuilongchon').text('Vui lòng chọn size *');
            }else if (color == '' && size != ''){
                $('.vuilongchon').text('Vui lòng chọn màu *');
            }else{
                $('.vuilongchon').html('Sản phẩm đã hết hàng Online.<br>&nbsp;Bạn có thể "Tìm cửa hàng".');
                if(check_show_room) process_check_showroom();
            }

            return false;
        }
    }
}

function subscribe_email(email){
    $.ajax({
        type: 'post',
        url: base_url+'ajax/subscribe',
        dataType: 'json',
        data: {'email':email}
    }).done(function(data){
        if(data.status == 1)
        {
            p_message('#subscribe_error', '<p class="msg_success">'+data.message+'</p>');
        }else{
            p_message('#subscribe_error', '<p class="required">'+data.message+'</p>');
        }

    }).fail(function () {
        //console.log('error-connection');
    });
}

$(function() {
    $("img.lazy").lazyload({
        threshold : 200,
        effect : "fadeIn"
    });
});

