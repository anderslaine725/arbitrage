<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="page-header">
    <nav class="navbar mega-menu" role="navigation">
        <div class="container-fluid">
            <div class="clearfix navbar-fixed-top">
                <!-- Brand and toggle get grouped for better mobile display -->
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="toggle-icon">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </span>
                </button>
                <!-- End Toggle Button -->
                <!-- BEGIN LOGO -->
                <a id="index" class="page-logo" href="index.html">
                    <img src="<c:url value="/assets/layouts/layout5/img/logo.png"/>" alt="Logo"> 
                </a>
                <!-- END LOGO -->
            </div>
            <!-- BEGIN HEADER MENU -->
            <div class="nav-collapse collapse navbar-collapse navbar-responsive-collapse">
                <ul class="nav navbar-nav">
                    <c:choose>
                        <c:when test="${navTitle == 'index'}">
                            <li class="dropdown dropdown-fw dropdown-fw-disabled  active open selected">
                                <a href="javascript:;" class="text-uppercase">
                                    <i class="fa fa-exchange"></i> Exchange 
                                </a>                        
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/gap"/>" class="text-uppercase">
                                    <i class="icon-puzzle"></i> Gap 
                                </a>
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/coin"/>" class="text-uppercase">
                                    <i class="fa fa-bitcoin"></i> Coin 
                                </a>
                            </li>
                        </c:when>
                            
                        <c:when test="${navTitle == 'gap'}">
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/index"/>" class="text-uppercase">
                                    <i class="fa fa-exchange"></i> Exchange 
                                </a>                        
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled active open selected">
                                <a href="javascript:;" class="text-uppercase">
                                    <i class="icon-puzzle"></i> Gap 
                                </a>
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/coin"/>" class="text-uppercase">
                                    <i class="fa fa-bitcoin"></i> Coin 
                                </a>
                            </li>
                        </c:when>
                            
                        <c:when test="${navTitle == 'coin'}">
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/index"/>" class="text-uppercase">
                                    <i class="fa fa-exchange"></i> Exchange 
                                </a>                        
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/gap"/>" class="text-uppercase">
                                    <i class="icon-puzzle"></i> Gap 
                                </a>
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled active open selected">
                                <a href="javascript:;" class="text-uppercase">
                                    <i class="fa fa-bitcoin"></i> Coin 
                                </a>
                            </li>
                        </c:when>    
                        
                        <c:otherwise>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled  active open selected">
                                <a href="javascript:;" class="text-uppercase">
                                    <i class="fa fa-exchange"></i> Exchange 
                                </a>                        
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/gap"/>" class="text-uppercase">
                                    <i class="icon-puzzle"></i> Gap 
                                </a>
                            </li>
                            <li class="dropdown dropdown-fw dropdown-fw-disabled">
                                <a href="<c:url value="/app/coin"/>" class="text-uppercase">
                                    <i class="fa fa-bitcoin"></i> Coin 
                                </a>
                            </li>
                        </c:otherwise>    
                    </c:choose>                    
                </ul>
            </div>
            <!-- END HEADER MENU -->
        </div>
    </nav>
</header>
