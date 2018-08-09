<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>报账列表</title>
    <script type="application/javascript">
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

        function formatOrderState(orderState) {
            switch (orderState) {
                case "FINANCE_IN_APPROVAL":
                    return "财务员审批中";
                case "DIRECTOR_IN_APPROVAL":
                    return "财务主管审批中";
                case "ALREADY_TO_ACCOUNT":
                    return "已到账";
                case "NOT_PASS_THROUGH":
                    return "审批不通过";
                case "CANCEL_REIMBURSEMENT":
                    return "取消报账";

            }
        }
        function auditItem(reimbursementId){

            $('#auditDialog').modal();


            $("#trs").html("");
            $.get("reimbursementOrder.do?reimbursementId="+reimbursementId,$("#tables").serialize(),function(result){
                debugger;
                var rem=result.data;
                    for(var i=0;i<rem.length;i++){
                        $("#trs").append("<tr name='trtd'><td>"+rem[i].order.orderCode+"</td><td>"+rem[i].order.totalPrice+"</td><td>"+rem[i].order.createTime+"</td></tr>");
                    }
            });


        }

        function checkTime() {
            if ($('#createTime').val() > $('#createTimes').val() && '' != $('#createTimes').val()) {
                alert("开始时间不能大于结束时间")
                $('#createTimes').val('')
            }
        }

        function approval(){
            var dataItem = getSelectedGridItem("grid");
            $('#auditDialogSp').modal();
            // if (dataItem) {
            //     window.location.href = "reimbursementOrders.do?reimbursementId=" + dataItem.reimbursementId;
            // }

        }
    </script>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>

<jsp:include page="../layouts/sidebarRight.jsp"/>

<div id="main" class="clearfix">


    <header id="header-main">
        <div class="header-main-top">
            <div class="pull-left">
                <!-- * This is the responsive logo * -->
                <a href="#" id="logo-small"><h4></h4><h5></h5></a>
            </div>
            <div class="pull-right">
                <!-- * This is the trigger that will show/hide the menu * -->
                <!-- * if the layout is in responsive mode              * -->
                <a href="#" id="responsive-menu-trigger">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div>
        <!-- End #header-main-top -->
        <!-- End #header-main-bottom -->
    </header>
    <!-- End #header-main -->

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>报账</h2>
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
                    <!-- End .btn-group -->

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
                                <strong>报账编号:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="reimbursementNo" data-operator="contains"
                                       class="form-control grid-filter" placeholder="请输入保单编号"/>
                            </div>

                            <div class="toolbar-field">
                                <strong>报账人:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="customer.realName" data-operator="contains"
                                       class="form-control grid-filter" placeholder="请输入报账人"/>
                            </div>




                        </div>
                        <div class="pull-left">

                            <div class="toolbar-field">
                                <strong>手机号码:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="customer.mobile" data-operator="contains"
                                       class="form-control grid-filter" placeholder="请输入手机号码"/>
                            </div>

                            <div class="toolbar-field">
                                <strong>状态:</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="orderState" id="payState" data-operator="eq"
                                        class="form-control  grid-filter">
                                    <option value="">全部</option>
                                    <option value="FINANCE_IN_APPROVAL">财务员审批中</option>
                                    <option value="DIRECTOR_IN_APPROVAL">财务主管审批中</option>
                                    <option value="CASHIER_IN_APPROVAL">出纳审批中</option>
                                    <option value="ALREADY_TO_ACCOUNT">已到账</option>
                                    <option value="NOT_PASS_THROUGH">审批不通过</option>
                                </select>
                            </div>

                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i
                                        class="fa fa-search"></i>查询
                                </button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空
                                </button>
                            </div>
                        </div>
                        <!-- End .pull-right -->
                    </form>
                </div>
                <div class="spacer-10"></div>

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>报账列表</h3>
                        </div>

                        <div class="pull-right">

                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="approval()" class="btn btn-default"><i
                                        class="fa fa-info-circle"></i>&nbsp;审批</a>
                            </div>
                        </div>


                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"
                                filterable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="报账单号" filterable="false" field="reimbursementNo" width="100"
                                              />
                            <kendo:grid-column title="报账人" filterable="false" field="customer" width="100"
                                               template="#=customer.realName#" />
                            <kendo:grid-column title="报账人手机" filterable="false" field="customer"
                                               template="#=customer.mobile#" width="100"/>
                            <kendo:grid-column title="报账总金额" filterable="false" field="amount"
                                               width="100"/>
                            <kendo:grid-column title="税" filterable="false" field="tax"
                                                width="100"/>
                            <kendo:grid-column title="报账订单总金额" filterable="false" field="orderAmount"
                                                width="100"/>
                            <%--<kendo:grid-column title="报账订单" filterable="false"--%>
                                               <%--width="100" template="<a href='reimbursementOrders.do?reimbursementId=#= reimbursementId#' style='color:blue'>查看</a>" />--%>
                            <kendo:grid-column title="报账订单" filterable="false"
                                               width="100" template="<a href=javascript:void(0)' onclick='auditItem(#= reimbursementId#)' style='color:blue'>查看</a>" />
                            <kendo:grid-column title="申报时间" filterable="false" field="createTime"
                                               format="{0:yyyy-MM-dd HH:mm}" width="100"/>
                            <kendo:grid-column title="状态" filterable="false" field="orderState"
                                               template="#=formatOrderState(orderState)#" width="100"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                                <kendo:dataSource-schema-model>
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                                    </kendo:dataSource-schema-model-fields>
                                </kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageReimbursement.do" type="POST"
                                                                 contentType="application/json"/>
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

            <div class="spacer-30"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-10"></div>

            <!-- End .inner-padding -->
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>

<div class="modal fade" id="auditDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">订单详情</h4>
            </div>
            <div class="modal-body" id="reimbursementIds">
                <table class="table table-bordered table-striped" id="tables">
                    <thead>
                    <tr>
                        <th scope="col" data-rt-column="code">订单编号</th>
                        <th scope="col" data-rt-column="operate">订单总额</th>
                        <th scope="col" data-rt-column="name">创建时间</th>

                    </tr>
                    </thead>
                    <tbody id="trs">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--<button class="btn btn-primary pull-right" onclick="submitAudit();" id="btnComfrim">确认</button>--%>
            </div>
        </div>
    </div>
</div>




<div class="modal fade" id="auditDialogSp" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">订单详情</h4>
            </div>
            <div class="modal-body" >
                <table class="table table-bordered table-striped" >
                    <thead>
                    <tr>
                        <th scope="col" data-rt-column="code"></th>
                        <th scope="col" data-rt-column="operate"></th>
                        <th scope="col" data-rt-column="name"></th>

                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <%--<button class="btn btn-primary pull-right" onclick="submitAudit();" id="btnComfrim">确认</button>--%>
            </div>
        </div>
    </div>
</div>
<!-- End #main -->

<script type="text/javascript">
    $(document).ready(function () {
        $("#commodityAuditForm").validationEngine({
            autoHidePrompt: true, scroll: false, showOneMessage: true
        });
    });

</script>

</body>
</html>
