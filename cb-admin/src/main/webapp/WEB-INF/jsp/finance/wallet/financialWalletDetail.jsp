<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>负债管理</title>
    <script type="text/javascript">
        $(document).ready(function(){

        });
        function getTypeName(data){
            switch (data){
                case "INSURANCE_REBATE":{
                    return "保险返利";
                }
                case "INSURANCE_PURCHASE":{
                    return "保险购买";
                }
                case "MANUAL_REPAYMENT":{
                    return "手动还款";
                }
                case "INSURANCE_REPAYMENT":{
                    return "保险返利自动还款";
                }
                case "INTEREST":{
                    return "借款利息";
                }
                case "PRODUCT_RB_REPAYMENT":{
                    return "商品报帐自动还款";
                }
                case "LOAN":{
                    return "借款";
                }
                case "CAR_REPAYMENT":{
                    return "汽车返利还款";
                }
            }
            return state;
        }

    </script>
</head>
<body>
<jsp:include page="../../layouts/left.jsp"/>
<jsp:include page="../../layouts/sidebarRight.jsp"/>
<div id="main" class="clearfix">
    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">财务管理</a></li>
                    <li class="active"><a href="#">负债管理</a></li>
                </ul>
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
    </header>
    <div id="content" class="clearfix">
        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>负债管理</h2>
                </div>
            </div>
        </header>
        <div class="window">
            <div class="actionbar">
                <div class="pull-left">
                    <a href="#" class="btn small-toggle-btn" data-toggle-sidebar="left"></a>
                    <a href="#" id="lockscreen-slider-trigger" class="btn">
                        <i class="fa fa-lock"></i>&nbsp; Lock screen
                    </a>
                </div>

                <div class="pull-right">
                    <a data-toggle-sidebar="right" class="btn small-toggle-btn" href="#"></a>
                </div>
            </div>
            <div class="inner-padding">
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>还款类型:</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="transactionType" data-operator="eq" class="form-control  grid-filter">
                                    <option value="">全部</option>
                                    <option value="INSURANCE_REPAYMENT">保险返利还款</option>
                                    <option value="PRODUCT_RB_REPAYMENT">商品报账还款</option>
                                    <option value="MANUAL_REPAYMENT">手动还款</option>
                                    <option value="CAR_REPAYMENT">汽车返利还款</option>
                                </select>
                            </div>
                            <input type="hidden" data-filter="type" data-operator="eq" class="form-control grid-filter" value="SUBTRACT" />
                            <%--<input type="hidden" data-filter="customer" data-operator="contains" class="form-control grid-filter" value="${customerId}" />--%>
                        </div>
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="spacer-10"></div>
                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>负债列表</h3>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="450" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="还款人" filterable="false" field="customer.realName" width="100px"/>
                            <kendo:grid-column title="id" filterable="false" field="customer.customerId" hidden="true" width="100px"/>
                            <kendo:grid-column title="还款人手机号" filterable="false" field="customer.mobile" width="100px"/>
                            <kendo:grid-column title="还款金额" filterable="false" field="amount" width="100px" />
                            <kendo:grid-column title="备注" filterable="false" field="transactionDesc" width="100px" />
                            <kendo:grid-column title="还款类型" filterable="false" field="transactionType" width="100px" template="#=getTypeName(transactionType)#"/>
                            <kendo:grid-column title="还款时间" filterable="false" field="createTime" width="100px" />
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="choosePagedLiabilities.do?customerId=${customerId}" type="POST" contentType="application/json"/>
                                <kendo:dataSource-transport-parameterMap>
                                    <script>
                                        function parameterMap(options, type) {
                                            return JSON.stringify(options);
                                        }
                                    </script>
                                </kendo:dataSource-transport-parameterMap>
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </div>
            </div>
            <div class="spacer-40"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-40"></div>
        </div>
        <jsp:include page="../../layouts/footer.jsp"/>
    </div>
</div>
</body>
</html>
