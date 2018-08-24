/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var handlePulsate = function () {
    if (!jQuery().pulsate) {
        return;
    }

    if (App.isIE8() == true) {
        return; // pulsate plugin does not support IE8 and below
    }

    if (jQuery().pulsate) {
        jQuery('.pulsate-regular').pulsate({
            color: "#bf1c56"
        });

        jQuery('#pulsate-once').click(function () {
            $('#pulsate-once-target').pulsate({
                color: "#399bc3",
                repeat: false
            });
        });

        jQuery('#pulsate-crazy').click(function () {
            $('#pulsate-crazy-target').pulsate({
                color: "#fdbe41",
                reach: 50,
                repeat: 10,
                speed: 100,
                glow: true
            });
        });
    }
}

var handleiCheck = function() {
    if (!$().iCheck) {
        return;
    }

    $('.icheck').each(function() {
        var checkboxClass = $(this).attr('data-checkbox') ? $(this).attr('data-checkbox') : 'icheckbox_minimal-grey';
        var radioClass = $(this).attr('data-radio') ? $(this).attr('data-radio') : 'iradio_minimal-grey';

        if (checkboxClass.indexOf('_line') > -1 || radioClass.indexOf('_line') > -1) {
            $(this).iCheck({
                checkboxClass: checkboxClass,
                radioClass: radioClass,
                insert: '<div class="icheck_line-icon"></div>' + $(this).attr("data-label")
            });
        } else {
            $(this).iCheck({
                checkboxClass: checkboxClass,
                radioClass: radioClass
            });
        }
    });
};


$(function() {
   $(".mask_integer").inputmask({
        "mask": "9",
        "repeat": 10,
        "greedy": false
    }); // ~ mask "9" or mask "99" or ... mask "9999999999"
        
    $(".mask_decimal").inputmask('decimal', {
        rightAlign: false
    }); //disables the right alignment of the decimal input    
    
    $('.date-picker').datepicker({
        rtl: App.isRTL(),
        orientation: "right",
//        format: 'yyyy-mm-dd',
        autoclose: true,
        language: 'zh-CN'
    });
    
    $(".month-picker").datepicker( {
        format: "yyyy-mm",
        startView: "months", 
        minViewMode: "months",
        autoclose: true,
        language: 'zh-CN'
    }); 
    
    $(".tooltips").tooltip();
    
    handlePulsate();
    
    handleiCheck();
    
    $('.search-option').change(function() {
       $('#searchForm').submit();
    });
    
    $('.search-option').on('ifChecked', function() {
       $('#searchForm').submit();
    });
});


