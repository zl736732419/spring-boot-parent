(function ($) {
    $('#corsBtn').off('click').on('click', function () {
        var url="http://192.168.1.114:8080/api/get";
        $.get(url, function (data) {
            $('#callback').html(data);
        });
    });
})(jQuery);