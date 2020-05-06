$(document).ready(function() {

    $('body').on('click', 'a.fly-cart', function() {
        var cart = $('.fa-shopping-cart');
        var imgtodrag = $(this).parent('.coupon-content').find("img").eq(0);

        if (imgtodrag) {
            var imgclone = imgtodrag.clone()
                .offset({
                    top: imgtodrag.offset().top,
                    left: imgtodrag.offset().left
                })
                .css({
                    'opacity': '0.5',
                    'position': 'absolute',
                    'height': '150px',
                    'width': '150px',
                    'z-index': '100'
                })
                .appendTo($('body'))
                .animate({
                    'top': cart.offset().top + 10,
                    'left': cart.offset().left + 10,
                    'width': 75,
                    'height': 75
                }, 1000, 'easeInOutExpo');

            imgclone.animate({
                'width': 0,
                'height': 0
            }, function() {
                $(this).detach()
            });
        }

        // show notification
        $('.success-add-cart').addClass('animation-cart-delay');
    });

    $('body').on('click', '#but_choose_product', function() {
        $('.success-add-cart').addClass('animation-cart');
    });

});