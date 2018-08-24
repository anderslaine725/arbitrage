<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- BEGIN BREADCRUMBS -->
<!--<div class="breadcrumbs">
    <h1>Exchange</h1>
    <ol class="breadcrumb">
        <li class="active">
            <a href="javascript:;">Home</a>
        </li>
    </ol>
     Sidebar Toggle Button 
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".page-sidebar">
        <span class="sr-only">Toggle navigation</span>
        <span class="toggle-icon">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </span>
    </button>
     Sidebar Toggle Button 
</div>-->
<!-- END BREADCRUMBS -->

<div class="page-content-container">
    <div class="page-content-row">
        <div class="page-content-col">
            <!-- BEGIN PAGE BASE CONTENT -->
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet box">
                        <div class="portlet-body form">
                            <form:form modelAttribute="searchForm" action="index" cssClass="form-horizontal"  method="post">
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="control-label col-md-4">Exchange</label>
                                                <div class="col-md-8">
                                                    <form:select path="exchange" cssClass="form-control search-option">
                                                        <c:forEach var="exchange" items="${exchanges}">
                                                            <form:option value="${exchange.id}">${exchange.description}</form:option>
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label class="control-label col-md-4">Fiat / Coin</label>
                                                <div class="col-md-8">
                                                    <div class="input-group">
                                                        <div class="icheck-inline">
                                                            <c:forEach var="fiat_coin" items="${fiat_coins}">
                                                                <label><form:radiobutton cssClass="icheck search-option" path="fiatCoin" value="${fiat_coin.id}"/> ${fiat_coin.description}</label>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="note note-info">
                            <p style="font-size: 16px;"> 
                                <span style="color: #4db3a4; font-weight: 600; text-transform: uppercase;" id="exchangeName">Binance</span> 
                                <span style="color: #49a7fa; font-weight: 600; margin-left: 20px;" id="symbol">USD</span>
                                <span style="font-weight: 600; margin-left: 20px;" id="volume">Volumn</span>
                                <span style="color: #e35b5a; font-weight: 600; margin-left: 20px;" id="gap">0.00%</span>
                                <!--<span style="color: #26c281; font-weight: 600; margin-left: 20px;">1.30%</span>-->
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet box">
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-hover table-header-fixed" id="arbitrage_table">
                                <thead>
                                    <tr>
                                        <th colspan="2" class="align-center"> Coins </th>
                                        <th style="display: none"></th>
                                        <c:forEach var="exchange" items="${_exchanges}">
                                            <c:if test="${exchange.id != searchForm.exchange}">
                                                <th colspan="2" class="align-center"> ${exchange.id} </th>
                                                <th style="display: none"></th>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END PAGE BASE CONTENT -->
        </div>
    </div>
</div>