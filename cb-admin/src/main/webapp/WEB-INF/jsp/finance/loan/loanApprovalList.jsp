<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>借款审批</title>
    <script type="text/javascript">
        function getStateName(data){
            switch (data){
                case "WAIT_LOAN":{
                    return "申请";
                }
                case "APPLY_SUCCESS":{
                    return "已审核";
                }
                case "APPLY_FAILURE":{
                    return "已拒绝";
                }
                case "CANCELED":{
                    return "已取消";
                }
                case "APPLY_TRANSFERRED":{
                    return "已转账";
                }
            }
            return state;
        }
        function getTypeName(data){
            switch (data){
                case "NON_REPAYMENT":{
                    return "未还款";
                }
                case "OVERDUE":{
                    return "已逾期";
                }
                case "APPLY_REIMBURSEMENT":{
                    return "已还款";
                }
            }
            return state;
        }

        function confirm(){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toExamine.do?loanId=" + dataItem.loanId;
            }
        }

        function transferAccounts(state){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                if(state != dataItem.state){
                    bootbox.alert("该状态不能确定转账!");
                    return false;
                }
            }
            bootbox.confirm("确认转账吗？", function (result) {
                if (result) {
                    $.get("transferAccounts.do", {
                        loanId: dataItem.loanId,
                        state : state
                    }, function (data) {
                        debugger;
                        if (data.result=="success") {
                            bootbox.alert("成功");
                            $("#grid").data("kendoGrid").dataSource.read();
                        } else {
                            bootbox.alert("失败");
                        }
                    });
                }
            });
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
                    <li class="active"><a href="#">借款审批</a></li>
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
                    <h2>借款审批</h2>
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
                                <strong>借款人:</strong>
                                <input type="hidden" id="withdrawIdHid" name="withdrawId">
                            </div>
                            <div class="toolbar-field">
                                <input type="text"  data-filter="customer.realName" data-operator="contains" class="form-control grid-filter" placeholder="请输入借款人"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>借款人手机号 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text"  data-filter="customer.mobile" data-operator="contains" class="form-control grid-filter" placeholder="请输入借款人手机号"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>借款金额 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <table>
                                    <tr>
                                        <td><input type="number" data-filter="amount" data-operator="gte" class="form-control grid-filter" style="width: 60px" placeholder="最小"/></td>
                                        <td>-</td>
                                        <td><input type="number" data-filter="amount" data-operator="lte" class="form-control grid-filter" style="width: 60px" placeholder="最大"/></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="toolbar-field">
                                <strong>状态 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="state" data-operator="eq" class="form-control  grid-filter">
                                    <option value="">全部</option>
                                    <option value="WAIT_LOAN">申请</option>
                                    <option value="APPLY_SUCCESS">已审核</option>
                                    <option value="APPLY_FAILURE">已拒绝</option>
                                    <option value="CANCELED">已取消</option>
                                    <option value="APPLY_TRANSFERRED">已转账</option>
                                </select>
                            </div>
                            <div class="toolbar-field">
                                <strong>借款周期 :</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="term" data-operator="eq" class="form-control  grid-filter">
                                    <option value="">全部</option>
                                    <option value="6">6个月</option>
                                    <option value="12">12个月</option>
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
                    </form>
                </div>
                <div class="spacer-10"></div>
                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>借款列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="confirm()" class="btn btn-default">&nbsp;审核</a>
                            </div>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="transferAccounts('APPLY_SUCCESS')" class="btn btn-default">&nbsp;转账确认</a>
                            </div>
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
                            <kendo:grid-column field="loanId" width="350px" hidden="true"/>
                            <kendo:grid-column title="审核时间" field="auditTime" width="350px" hidden="true"/>
                            <kendo:grid-column title="借款人" filterable="false" field="customer.realName" width="100px"/>
                            <kendo:grid-column title="借款人手机号" filterable="false" field="customer.mobile" width="100px"/>
                            <kendo:grid-column title="借款金额" filterable="false" field="amount" width="100px" />
                            <kendo:grid-column title="借款周期" filterable="false" field="term" width="100px" />
                            <kendo:grid-column title="借款利率" filterable="false" field="interestRate" width="100px" />
                            <kendo:grid-column title="应还总金额" filterable="false" field="repayAmount" width="100px" />
                            <kendo:grid-column title="利息" filterable="false" field="interest" width="100px" />
                            <kendo:grid-column title="最后还款日" filterable="false" field="finalRepaymentTime" width="100px" />
                            <kendo:grid-column title="借款状态" filterable="false" field="state" width="100px" template="#=getStateName(state)#"/>
                            <kendo:grid-column title="还款状态" filterable="false" field="repaymentState" width="100px" template="#=getTypeName(repaymentState)#"/>
                            <kendo:grid-column title="借款时间" filterable="false" field="createTime" width="100px" />
                            <kendo:grid-column title="借款账户" filterable="false" field="bank.bankCardNumber" width="100px" />
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageLoanList.do?state=1" type="POST" contentType="application/json"/>
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
