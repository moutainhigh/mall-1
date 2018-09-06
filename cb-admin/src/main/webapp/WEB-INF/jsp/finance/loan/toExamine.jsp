<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <script src="../js/zoomify/viewer.min.js"></script>
    <script src="../js/zoomify/viewer-jquery.min.js"></script>
    <link rel="stylesheet" href="../js/zoomify/viewer.min.css">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script type="application/javascript">
        function submitForm() {
            var auditRemark = $("#auditRemark").val();
            if(null == auditRemark || "" == auditRemark){
                bootbox.alert("财务审批意见不能为空");
                return false;
            }
            var adopt = $("#adopt").val();
            if(adopt == 1){
                $("#state").val("APPLY_SUCCESS");
            }else {
                $("#state").val("APPLY_FAILURE");
            }
            return true;
        }
        function check(flag) {
            var state;
            if(flag == "APPLY_SUCCESS"){
                state = $("#state").val("APPLY_SUCCESS");
            }else {
                state = $("#state").val("APPLY_FAILURE");
            }
            var auditRemark = $("#auditRemark").val();
            var loanId = $("#loanId").val();
            if(null == auditRemark || "" == auditRemark){
                bootbox.alert("财务审批意见不能为空");
                return false;
            }
            $.ajax({
                url:"editFinancialLoan.do",
                type:'POST',//GET 或POST
                data:$("#validateSubmitForm").serialize(),
                success:function(data){
                    alert("成功");
                    window.location.href = "loanApprovalList.do";
                },
                error:function(data){
                    bootbox.alert("失败");
                    window.location.href = "loanApprovalList.do";
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
                    <li class="active">借款审批</li>
                </ul>
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
    </header>
    <div id="content" class="clearfix">
        <div class="window">
            <div class="inner-padding">
                <form:form id="validateSubmitForm" cssClass="form-horizontal" method="post" commandName="financialLoan">
                <div class="subheading">
                    <h3>借款审核</h3>
                </div>
                    <form:hidden path="loanId"/>
                    <form:hidden path="state" id="state" />
                    <div class="row">
                        <div class="inline-labels">
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款人：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${financialLoan.customer.realName}
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款人手机号：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${financialLoan.customer.mobile}
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                    <div class="spacer-10"></div>

                    <div class="row">
                        <div class="inline-labels">

                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款金额：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${financialLoan.amount}
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款周期：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${financialLoan.term}个月
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>

                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>

                    <div class="row">
                        <div class="inline-labels">
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款利率：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${financialLoan.interestRate}%
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>应还总金额：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${financialLoan.repayAmount}
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>

                    <div class="spacer-10"></div>

                    <div class="row">
                        <div class="inline-labels">
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>利息：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                    ${financialLoan.interest}
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>最后还款日：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${fn:substring(financialLoan.finalRepaymentTime, 0, 10)}
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                        <div class="inline-labels">
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款时间：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                    ${fn:substring(financialLoan.createTime, 0, 19)}
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款银行：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                    ${financialLoan.bank.bankName}&nbsp;&nbsp;${financialLoan.customer.realName}&nbsp;&nbsp;${financialLoan.bank.bankCardNumber}
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="subheading">
                        <h3>审核信息</h3>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                        <div class="inline-labels">
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>状态：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                <c:choose>
                                    <c:when test="${financialLoan.state=='WAIT_LOAN'}">申请</c:when>
                                    <c:when test="${financialLoan.state=='APPLY_SUCCESS'}">已审核</c:when>
                                    <c:when test="${financialLoan.state=='APPLY_FAILURE'}">已拒绝</c:when>
                                    <c:when test="${financialLoan.state=='CANCELED'}">已取消</c:when>
                                    <c:when test="${financialLoan.state=='APPLY_TRANSFERRED'}">已转账</c:when>
                                </c:choose>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                        <div class="col-sm-1">
                            <label><span class="asterisk"></span>财务审批意见:</label>
                        </div>
                        <div class="col-sm-9 col-label">
                            <form:textarea rows="10" cols="101" id="auditRemark" path="auditRemark" maxlength="255"></form:textarea>
                        </div>
                        <div class="col-sm-1"></div>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
            </div><!-- End .inner-padding -->
        </div>
        <div class="row">
            <div class="col-sm-2">
                <div class="btn-group pull-left">
                    <input type="button" class="btn btn-default" onclick="check('APPLY_SUCCESS')" value="&nbsp;通&nbsp;过&nbsp;" >
                </div>
            </div>
            <div class="col-sm-2">
                <input type="button" class="btn btn-default" onclick="check('APPLY_FAILURE')" value="不&nbsp;通&nbsp;过&nbsp;" >
            </div>
            <div class="col-sm-2">
                <div class="btn-group pull-right">
                    <a class="btn btn-default pull-right" href="../loan/loanApprovalList.do">返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
</form:form>
<jsp:include page="../../layouts/footer.jsp"/>
</div>
</div>

</body>
</html>
