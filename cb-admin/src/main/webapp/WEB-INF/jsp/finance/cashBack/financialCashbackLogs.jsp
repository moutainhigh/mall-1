<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>返现列表</title>
    <script type="application/javascript">
        function checkTime() {
            if ($('#amount').val() > $('#amounts').val() && '' != $('#amounts').val()) {
                alert("开始金额不能大于结束金额")
                $('#amounts').val('')
            }
        }

        function formatOrderState(orderState) {
            switch (orderState) {
                case "WAIT":
                    return "待返现";
                case "FINISHED":
                    return "已返现";
                case "FAILED":
                    return "返现失败";
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
                    <h2>返现列表</h2>
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
                                <strong>返现人:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="customerName" data-operator="contains"
                                       class="form-control grid-filter" placeholder="返现人"/>
                            </div>

                            <div class="toolbar-field">
                                <strong>返现人手机:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="customer.mobile" data-operator="contains"
                                       class="form-control grid-filter" placeholder="返现人手机"/>
                            </div>




                        </div>
                        <div class="pull-left">

                            <div class="toolbar-field">
                                <strong>返现金额:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="amount" data-operator="gte" id="amount"
                                       class="form-control grid-filter" placeholder="返现金额"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>-</strong>
                            </div>
                            <div class="toolbar-field">
                                <input name="createTime" onchange="checkTime()"
                                       onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" id="amounts"
                                       placeholder="返现金额" data-filter="amount" data-operator="lte"
                                       class="form-control grid-filter"/>
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
                            <h3>返现列表</h3>
                        </div>

                        <div class="pull-right">

                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="approval()" class="btn btn-default"><i
                                        class="fa fa-info-circle"></i>&nbsp;查看</a>
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
                            <kendo:grid-column title="返现人" filterable="false" field="customerName" width="100"
                                              />
                            <kendo:grid-column title="返现人手机" filterable="false" field="customer" width="100"
                                               template="#=customer.mobile#" />
                            <kendo:grid-column title="返现金额" filterable="false" field="amount"
                                              width="100"/>
                            <kendo:grid-column title="状态" filterable="false" field="state"
                                               template="#=formatOrderState(state)#"  width="100"/>
                            <kendo:grid-column title="返现时间" filterable="false" field="createTime"
                                               format="{0:yyyy-MM-dd HH:mm}" width="100"/>
                            <kendo:grid-column title="返利保单" filterable="false" field="orderNo"
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
                                <kendo:dataSource-transport-read url="pageFinacialInsuCashbackLog.do" type="POST"
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

        <jsp:include page="../../layouts/footer.jsp"/>
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
                    var taxMoney = data[i].amount.toFixed(2);

                    var productPrice=data[i].productPrice;
                    if(productPrice>10000){
                        productPrice=(productPrice/10000).toFixed(2)+"万";
                    }
                    if(taxMoney>10000){
                        taxMoney=(taxMoney/10000).toFixed(2)+"万";
                    }
                    var tax =reimbursement.tax;
                    if(tax>10000){
                        tax=(tax/10000).toFixed(2)+"万";
                    }
                    var amount=reimbursement.amount;
                    if(amount>10000){
                         amount=(amount/10000).toFixed(2)+"万";
                    }
                    var orderAmount=reimbursement.orderAmount;
                    if(orderAmount>10000){
                        orderAmount=(orderAmount/10000).toFixed(2)+"万";
                    }

                    $("#orderBox").append("<div class='orderItem '>\n" +
                        "                       <div class=\"orderNo\">订单号："+data[i].orderCode+"</div>\n" +
                        "                       <div class='orderImg'>\n" +
                        "                            <img src='"+data[i].imgPath+"' alt=\"\">\n" +
                        "                            <div class='orderDtail'><span class='orderDes' style='position: absolute'>"+data[i].productName+"</span><span class='orderMoney' style='position: absolute;margin-left: 130px;margin-top: 0px'>+&nbsp;"+data[i].productPrice+"</span></div>\n" +
                        "                       </div>\n" +
                        "                       <div class='orderMoney'>订单金额："+productPrice+"，应缴纳税："+taxMoney+"</div>\n" +
                        "                   </div>");
                }
                $("#reimbursement").html(" <div class='ceilTitle' >报账总金额：</div>\n" +
                    "               <div class='reimbursementDetail' style='width: 580px'>\n" +
                    "                    <div>订单金额: "+amount+"</div>\n" +
                    "                    <div>缴纳税点："+(reimbursement.taxRate)*100+"%，税:"+tax+"</div>\n" +
                    "                    <div>实际到账总金额："+orderAmount+"</div>\n" +
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
                alert("审核失败！");
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
