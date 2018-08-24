<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh-CN">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8"/>
        <title><tiles:getAsString name="title" /></title>
        <meta name=renderer content=webkit>		
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description"/>
        <meta content="" name="author"/>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="<c:url value="/assets/global/plugins/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"/>" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="<c:url value="/assets/global/plugins/datatables/datatables.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/select2/css/select2.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/select2/css/select2-bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/global/plugins/icheck/skins/all.css"/>" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="<c:url value="/assets/global/css/components.min.css"/>" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<c:url value="/assets/global/css/plugins.min.css"/>" rel="stylesheet" type="text/css" />        
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="<c:url value="/assets/layouts/layout5/css/layout.min.css"/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/assets/myApps/css/custom.css"/>" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <!--<link rel="shortcut icon" href="favicon.ico" />-->    
    </head>
    <!-- END HEAD -->
    
    <body class="page-header-fixed page-sidebar-closed-hide-logo">
        <!-- BEGIN CONTAINER -->
        <div class="wrapper">
            <!-- BEGIN HEADER -->
            <tiles:insertAttribute name="header"/>
            <!-- END HEADER -->
            
            <div class="container-fluid">
                <div class="page-content" style="padding: 30px;">
                    <!-- BEGIN BODY -->
                    <tiles:insertAttribute name="body"/>
                    <!-- END BODY -->
                </div>
                
                <!-- BEGIN FOOTER -->
                <tiles:insertAttribute name="footer"/>
                <!-- END FOOTER -->
            </div>
        </div>
        <!-- END CONTAINER -->
        <!--[if lt IE 9]>
        <script src="<c:url value="/assets/global/plugins/respond.min.js"/>"></script>
        <script src="<c:url value="/assets/global/plugins/excanvas.min.js"/>"></script> 
        <script src="<c:url value="/assets/global/plugins/ie8.fix.min.js"/>"></script> 
        <![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="<c:url value="/assets/global/plugins/jquery.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/js.cookie.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/jquery.blockui.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"/>" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="<c:url value="/assets/global/scripts/datatable.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/datatables/datatables.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/jquery-validation/js/additional-methods.min.js"/>" type="text/javascript"></script>        
        <script src="<c:url value="/assets/global/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/icheck/icheck.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/select2/js/select2.full.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/jquery.pulsate.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/global/plugins/jquery-bootpag/jquery.bootpag.min.js"/>" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->        
        <script src="<c:url value="/assets/global/scripts/app.min.js"/>" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="<c:url value="/assets/layouts/layout5/scripts/layout.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/layouts/global/scripts/quick-sidebar.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/layouts/global/scripts/quick-nav.min.js"/>" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        
        <script src="<c:url value="/assets/myApps/scripts/application.js"/>" type="text/javascript"></script>
        
        <!-- begin custom scripts -->
        <tiles:importAttribute name="javascripts"/>
        <c:forEach var="script" items="${javascripts}">
            <script src="<c:url value="${script}"/>"></script>
        </c:forEach>
        <!-- end custom scripts -->
    </body>
</html>