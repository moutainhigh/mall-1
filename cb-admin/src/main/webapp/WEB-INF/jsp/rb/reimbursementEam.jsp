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
                            <kendo:grid-column title="报账订单" filterable="false" field="orderCodes"
                                               width="100" />
                            <kendo:grid-column title="申报时间" filterable="false" field="createTime"
                                               format="{0:yyyy-MM-dd HH:mm}" width="100"/>
                            <kendo:grid-column title="状态" filterable="false" field="orderState"
                                               template="#=formatOrderState(orderState)#" width="100"/>
                            <kendo:grid-column title="系统分析" filterable="false" field="fundsPoolRemark"
                                               width="100"/>
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
                                <kendo:dataSource-transport-read url="pageReimbursement.do?orderState=2" type="POST"
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
    <div class="modal-dialog" style="width: 620px;">
        <div class="modal-content" style="width: 620px;">
            <div class="modal-header" >
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">订单详情</h4>
            </div>
            <div class="modal-body clearfix" >
                <div class='ceilTitle'>报账订单：</div>
               <div id='orderBox' class='clearfix'>&nbsp;
                   <%--<div class='orderItem '>--%>
                       <%--<div class="orderNo">订单号：123456</div>--%>
                       <%--<div class='orderImg'>--%>
                           <%--<img src="../images/users/user-1.jpg" alt="">--%>
                           <%--<div class='orderDtail'><span class='orderDes'>2018款C200L运动版</span><span class='orderMoney'>+&nbsp;28.22万</span></div>--%>
                       <%--</div>--%>
                       <%--<div class='orderMoney'>订单金额：28.22万，应缴纳税：7.07万</div>--%>
                   <%--</div>--%>

               </div>
                <form name="auditForm" id="auditForm">
                    <input type="hidden" name="reimbursementId" id="reimbursementId"/>
                    <input type="hidden" id="reimbursementType" name="reimbursementType">
                <div id="reimbursement">

                </div>
                </form>

            </div>
            <div class="modal-footer" style='padding-left:200px' id="submitAudits">&nbsp;

                <%--<button class="btn btn-primary pull-right" onclick="submitAudit();" id="btnComfrim">确认</button>--%>
            </div>
        </div>
    </div>
</div>
<!-- End #main -->

<script type="text/javascript">

    function approval(){
        var dataItem = getSelectedGridItem("grid");
        if(dataItem){
            $('#auditDialogSp').modal();


            $.get("reimbursementDetil.do?reimbursementId="+dataItem.reimbursementId,$("#tables").serialize(),function(result){

                var data=result.data;
                var reimbursement=result.reimbursement;
                var reimbursementProcess=result.reimbursementProcess;
                var orderState=reimbursement.orderState;
                $("#reimbursementId").val(dataItem.reimbursementId);
                $("#reimbursementType").val(orderState);
                var state="";
                switch (orderState) {
                    case "FINANCE_IN_APPROVAL":
                        state="财务员审批中";
                        break;
                    case "DIRECTOR_IN_APPROVAL":
                        state= "财务主管审批中";
                        break;
                    case "ALREADY_TO_ACCOUNT":
                        state= "已到账";
                        break;
                    case "NOT_PASS_THROUGH":
                        state= "审批不通过";
                        break;
                    case "CANCEL_REIMBURSEMENT":
                        state= "取消报账";
                        break;

                }
                $("#orderBox").html("&nbsp;");
                for(var i=0;i<data.length;i++){
                    debugger;
                    var taxMoney=data[i].productPrice*data[i].tax;
                    taxMoney = taxMoney.toFixed(2);
                    $("#orderBox").append("<div class='orderItem '>\n" +
                        "                       <div class=\"orderNo\">订单号："+data[i].orderCode+"</div>\n" +
                        "                       <div class='orderImg'>\n" +
                        "                            <img src='"+data[i].imgPath+"' alt=\"\">\n" +
                        "                            <div class='orderDtail'><span class='orderDes' style='position: absolute'>"+data[i].productName+"</span><span class='orderMoney' style='position: absolute;margin-left: 130px;margin-top: 0px'>+&nbsp;"+data[i].productPrice+"</span></div>\n" +
                        "                       </div>\n" +
                        "                       <div class='orderMoney'>订单金额："+data[i].productPrice+"，应缴纳税："+taxMoney+"</div>\n" +
                        "                   </div>");
                }
                var allTaxMoney=reimbursement.tax*reimbursement.amount;
                $("#reimbursement").html(" <div class='ceilTitle' >报账总金额：</div>\n" +
                    "               <div class='reimbursementDetail' style='width: 580px'>\n" +
                    "                    <div>订单金额: "+reimbursement.amount+"</div>\n" +
                    "                    <div>缴纳税点："+reimbursement.taxRate+"，税:"+reimbursement.tax+"</div>\n" +
                    "                    <div>实际到账总金额："+reimbursement.orderAmount+"</div>\n" +
                    "               </div>\n" +
                    "\n" +
                    "               <div class='ceilTitle' >基本信息</div>\n" +
                    "               <div class='reimbursementDetail'>\n" +
                    "                    <div>报账订单号："+reimbursement.reimbursementNo+"</div>\n" +
                    "                    <div>申报时间："+reimbursement.createTime+"</div>\n" +
                    "                    <div>申报人："+reimbursement.customer.realName+"&nbsp;&nbsp;账号："+reimbursement.customer.mobile+"</div>\n" +
                    "                    <div>状态："+state+"</div>\n" +
                    "                   ");


                if(reimbursementProcess.length>0){
                    for (var j=0;j<reimbursementProcess.length;j++){
                        var process=reimbursementProcess[j].orderState;
                        if(process=="FINANCE_IN_APPROVAL"||process=="FINANCE_NOT_PASS_THROUGH"){
                            process="财务审批意见";
                        }
                        else {
                            process="财务主管审批意见";
                        }
                        $("#reimbursement").append(" <div class='approvalOpinions clearfix'>\n" +
                            "                        <div class='left'>"+process+":</div>\n" +
                            "                        <div class='right'>审批意见 ：<span style='color: red'>"+reimbursementProcess[j].remarks+"</span><br>审核时间："+reimbursementProcess[j].createTime+"<span class='approver'>审批人："+reimbursementProcess[j].user.userName+"</span></div>\n" +
                            "                    </div>");
                    }
                }

                $("#reimbursement").append("</div>");
                $("#submitAudits").html("");
                if(orderState=="FINANCE_IN_APPROVAL"||orderState=="DIRECTOR_IN_APPROVAL"){
                    $("#reimbursement").append(  "<div class='ceilTitle' >审批意见：</div>\n" +
                        "               <textarea name=\"remarks\" id=\"remarks\"  style='margin-top:10px;width:100%;height: 60px;border-color:#e7e5e5;' placeholder=\"请输入审批意见（必填）\"></textarea>");

                    $("#submitAudits").append(" <button class=\"btn btn-success\" data-dismiss=\"modal\"  onclick=\"submitAudit(1);\">通过</button>\n" +
                        "                <button class=\"btn btn-danger\" data-dismiss=\"modal\" onclick='submitAudit(2);'>不通过</button>\n" +
                        "                <button class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>");
                }


            });

        }

    }


    function submitAudit(operType){
        if($("#remarks").val() == ""){
            bootbox.alert("请填写审核原因!");
            return false;
        }
        $.get("reimbursementAuditing.do?operType="+operType,$("#auditForm").serialize(),function(result){
            if(result){
                $("#auditForm")[0].reset();
                $('#auditDialog').modal("hide");
                $("#grid").data("kendoGrid").dataSource.read();
            }else{
                bootbox.alert("审核失败！");
            }
        });
    }


    $(document).ready(function () {
        $("#commodityAuditForm").validationEngine({
            autoHidePrompt: true, scroll: false, showOneMessage: true
        });
    });

</script>

</body>
</html>
