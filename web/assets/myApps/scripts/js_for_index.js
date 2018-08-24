/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var initArbitrageTable = function () {
    var table = $('#arbitrage_table');

    var fixedHeaderOffset = 0;
    if (App.getViewPort().width < App.getResponsiveBreakpoint('md')) {
        if ($('.page-header').hasClass('page-header-fixed-mobile')) {
            fixedHeaderOffset = $('.page-header').outerHeight(true);
        } 
    } else if ($('.page-header').hasClass('navbar-fixed-top')) {
        fixedHeaderOffset = $('.page-header').outerHeight(true);
    } else if ($('body').hasClass('page-header-fixed')) {
        fixedHeaderOffset = 64; // admin 5 fixed height
    }

    var oTable = table.dataTable({

        // Internationalisation. For more info refer to http://datatables.net/manual/i18n
        // Or you can use remote translation file
        //"language": {
        //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
        //},

        // setup rowreorder extension: http://datatables.net/extensions/fixedheader/
        fixedHeader: {
            header: true,
            headerOffset: fixedHeaderOffset
        },
        // set the initial value
        "language": {
            "search": "搜查: ",
            "lengthMenu": "  _MENU_ 记录",
            "emptyTable": "没有可显示的内容",
            "zeroRecords": "没有可显示的内容",
            "info": "显示 _START_ 至 _END_ 的 _TOTAL_ 项",
            "infoEmpty": "显示 0 至 0 的 0 项",
            "processing":     "处理中...",
            "loadingRecords": "加载...",
            "paginate": {
                "previous":"上一页",
                "next": "下一个",
                "last": "持续",
                "first": "第一"
            }
        },
        "lengthMenu": [
            [5, 10, 15, 20, -1],
            [5, 10, 15, 20, "All"] // change per page values here
        ],
        pageLength: -1,
        searching: false,
        info: false,
        paging: false,
        ordering: false,
        columnDefs: [{className: 'dt-center', targets: '_all'}]

        // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
        // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
        // So when dropdowns used the scrollable div should be removed. 
        //"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
  });
}

$(function() {
//    initArbitrageTable();
    getEventData();
    getBiggestGap();
})

function getEventData() {
    $.ajax({
       url: 'getMarketData',
       success: function(html) {
           $('#arbitrage_table tbody').empty();
           $('#arbitrage_table tbody').html(html);
       },
       complete: function(){
           setTimeout(getEventData, 1000 * 1);
       },
       error: function () {
            clearTimeout(getEventData);
        }
    });
}

function getBiggestGap() {
    $.ajax({
       url: 'getBiggestGap',
       success: function(result) {
            try {
                var json = eval(result);
                if (!$.isEmptyObject(json)) {
                    $('#exchangeName').text(json.exchange);
                    $('#symbol').text(json.symbol);
                    $('#volume').text(json.volume);
                    $('#gap').text(json.gap + "%");
                    if (parseFloat(json.gap) > 0)
                        $('#gap').css("color", "#26c281");
                    else
                        $('#gap').css("color", "#e35b5a");
                }
            } catch (e) {}
       },
       complete: function(){
           setTimeout(getBiggestGap, 500);
       },
       error: function () {
            clearTimeout(getBiggestGap);
        }
    });
}