<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>借款详情</title>
    <script type="text/javascript">
        $(document).ready(function() {

        });
    </script>
</head>
<body>
<jsp:include page="../../layouts/left.jsp"/>
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
                    <li><a href="#">借款管理</a></li>
                    <li class="active">借款详情</li>
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
                    <h2>借款详情</h2>
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
                <fieldset>
                    <legend>借款详情</legend>
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
                        </div>
                    </div>
                    <div class="spacer-30"></div>
                    <div class="row">
                        <div class="inline-labels">
                            <div class="col-sm-1">
                                <label><span class="asterisk"></span>借款银行：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                ${financialLoan.bank.bankName}(${financialLoan.bank.bankCardNumber.substring(financialLoan.bank.bankCardNumber.length()-4,financialLoan.bank.bankCardNumber.length())})
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>是否转账：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                <c:choose>
                                    <c:when test="${financialLoan.state=='WAIT_AUDIT'}">否</c:when>
                                    <c:when test="${financialLoan.state=='AUDIT_PASS'}">否</c:when>
                                    <c:when test="${financialLoan.state=='AUDIT_REFUSE'}">否</c:when>
                                    <c:when test="${financialLoan.state=='CANCELED'}">否</c:when>
                                    <c:when test="${financialLoan.state=='TRANSFERRED'}">已转账</c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <legend>审核信息</legend>
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
                                    <c:when test="${financialLoan.state=='WAIT_AUDIT'}">申请</c:when>
                                    <c:when test="${financialLoan.state=='AUDIT_PASS'}">已审核</c:when>
                                    <c:when test="${financialLoan.state=='AUDIT_REFUSE'}">已拒绝</c:when>
                                    <c:when test="${financialLoan.state=='CANCELED'}">已取消</c:when>
                                    <c:when test="${financialLoan.state=='TRANSFERRED'}">已转账</c:when>
                                </c:choose>
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
                                <label><span class="asterisk"></span>审批时间：</label>
                            </div>
                            <div class="col-sm-3 col-label">
                                <c:if test="${financialLoan.auditTime==null}">--</c:if>
                                <c:if test="${financialLoan.auditTime!=null}">${fn:substring(financialLoan.auditTime, 0, 19)}</c:if>
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>审批人：</label>
                            </div>
                            <div class="col-sm-4 col-label">
                                <c:if test="${financialLoan.approver==null}">--</c:if>
                                <c:if test="${financialLoan.approver!=null}">${financialLoan.approver}</c:if>
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
                            <textarea  rows="10" cols="101" maxlength="255"  readonly="true">${financialLoan.auditRemark}</textarea>
                        </div>
                        <div class="col-sm-1"></div>
                    </div>

                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="btn-group pull-right">
                                <a class="btn btn-default pull-right" href="../loan/loanList.do">返回</a>
                            </div>
                        </div>
                    </div>
                </fieldset>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>

            </div>
        </div>
        <jsp:include page="../../layouts/footer.jsp"/>
    </div>
</div>

</body>
</html>
