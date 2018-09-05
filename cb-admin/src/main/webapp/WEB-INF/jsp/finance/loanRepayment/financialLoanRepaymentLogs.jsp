<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>还款管理</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#createTime").kendoDatePicker({
                format: "yyyy-MM-dd",
                culture: "zh-CN",
                parseFormats: ["yyyy-MM-dd"]
            });
            $("#createTimes").kendoDatePicker({
                format: "yyyy-MM-dd",
                culture: "zh-CN",
                parseFormats: ["yyyy-MM-dd"]
            });
        });

        function getTypeName(loanRepaymentType){
            switch (loanRepaymentType){
                case "INSURANCE_REBATE_REPAYMENT":{
                    return "保险返利还款";
                }
                case "COMMODITY_REIMBURESE_REPAYMENT":{
                    return "商品报账还款";
                }
                case "MANUAL_REIMBURSEMENT_REPAYMENT":{
                    return "手动还款";
                }
                case "CAR_REBATE_REPAYMENT":{
                    return "汽车返利还款";
                }
            }
            return state;
        }

        function checkTime() {
            if ($('#createTime').val() > $('#createTimes').val() && '' != $('#createTimes').val()) {
                alert("开始时间不能大于结束时间")
                $('#createTimes').val('')
            }
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
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">财务管理</a></li>
                    <li class="active"><a href="#">提现管理</a></li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>
    <div id="content" class="clearfix">
        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>提现管理</h2>
                </div>
                <div class="pull-right">
                    <div class="btn-group">
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-star"></i>
                        </a>
                        <a class="btn btn-default" href="#" id="modal-update-trigger">
                            Modal
                        </a>
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-cog"></i>
                        </a>
                    </div>
                </div>
            </div>
            <!-- End .inner-padding -->
        </header>
        <!-- End #header-sec -->

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
            <!-- End .actionbar-->
            <div class="inner-padding">
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>还款人:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text"  data-filter="customer.realName" data-operator="contains" class="form-control grid-filter" placeholder="请输入还款人"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>还款人手机号 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text"  data-filter="customer.mobile" data-operator="contains" class="form-control grid-filter" placeholder="请输入还款人手机号"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>还款金额 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <table>
                                    <tr>
                                        <td><input type="number" data-filter="repayAmount" data-operator="gte" class="form-control grid-filter" style="width: 60px" placeholder="最小"/></td>
                                        <td>-</td>
                                        <td><input type="number" data-filter="repayAmount" data-operator="lte" class="form-control grid-filter" style="width: 60px" placeholder="最大"/></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="toolbar-field">
                                <strong>还款时间 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <table>
                                    <tr>
                                        <td>
                                            <input name="createTime" onchange="checkTime()"
                                                   onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" id="createTime"
                                                   placeholder="请选择开始时间" data-filter="createTime" data-operator="gte"
                                                   class="form-control grid-filter"/>
                                        </td>
                                        <td>-</td>
                                        <td>
                                            <input name="createTime" onchange="checkTime()"
                                                   onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" id="createTimes"
                                                   placeholder="请选择结束时间" data-filter="createTime" data-operator="lte"
                                                   class="form-control grid-filter"/>
                                            </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="toolbar-field">
                                <strong>还款类型 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="loanRepaymentType" data-operator="eq" class="form-control  grid-filter">
                                    <option value="">--还款类型--</option>
                                    <option value="INSURANCE_REBATE_REPAYMENT">保险返利还款</option>
                                    <option value="COMMODITY_REIMBURESE_REPAYMENT">商品报账还款</option>
                                    <option value="MANUAL_REIMBURSEMENT_REPAYMENT">手动还款</option>
                                    <option value="CAR_REBATE_REPAYMENT">汽车返利还款</option>
                                </select>
                            </div>
                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                        <!-- End .pull-right -->
                    </form>
                </div>
                <!-- End .toolbar -->

                <div class="spacer-10"></div>

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>提现列表</h3>
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
                            <kendo:grid-column field="poolId" width="350px" hidden="true"/>
                            <kendo:grid-column title="负债人" filterable="false" field="customer.realName" width="100px"/>
                            <kendo:grid-column title="负债人手机号" filterable="false" field="customer.mobile" width="100px"/>
                            <kendo:grid-column title="还款类型" filterable="false" field="loanRepaymentType" template="#=getTypeName(loanRepaymentType)#" width="100px" />
                            <kendo:grid-column title="还款金额" filterable="false" field="repayAmount" width="100px"/>
                            <kendo:grid-column title="还款时间" filterable="false" field="createTime" width="100px"/>
                            <kendo:grid-column title="备注" filterable="false" width="100px" />
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageLoanRepaymentList.do" type="POST" contentType="application/json"/>
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

            <!-- End .inner-padding -->
        </div>
        <jsp:include page="../../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
</div>
</body>
</html>
