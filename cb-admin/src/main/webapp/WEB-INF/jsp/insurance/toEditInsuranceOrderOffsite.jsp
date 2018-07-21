<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <script src="../js/zoomify/viewer.min.js"></script>
    <script src="../js/zoomify/viewer-jquery.min.js"></script>
    <link rel="stylesheet" href="../js/zoomify/viewer.min.css">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <script type="application/javascript">
        function changeOtherMatter(flag) {
            if(flag){
                $("#otherMatterDiv").show();
            }else {
                $("#otherMatterDiv").hide();
            }
        }

        function submitForm() {
            var flag = $("input[name='otherMatter']:checked").val();
            if(flag=="false"){
                $("#insuranceOrderOffsite_otherMatter").val("");
            }
            return true;
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
                    <li><a href="#">保单管理</a></li>
                    <li class="active">保单详情</li>
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
                <form:form id="validateSubmitForm" action="editInsuranceOrderOffsite.do" cssClass="form-horizontal" method="post" commandName="insuranceOrder" onsubmit="return submitForm();">
                <div class="subheading">
                    <h3>异地投保编辑</h3>
                </div>
                <c:choose>
                    <c:when test="${insuranceOrder.insuranceOrderOffsite!=null}">
                        <form:hidden path="insuranceOrderOffsite.offsiteId"/>
                        <form:hidden path="orderId"/>
                        <div class="inner-padding">
                            <div class="spacer-30">1、您的户籍所在地是哪里？
                            </div>
                            <div style="float: left;margin-left: 10px">
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.sensue" maxlength="60"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                2、您目前工作所在城市或地区名？单位名称？工作单位所属行业？您的职务？
                            </div>
                            <div style="float: left;margin-left: 10px">
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.workplace" maxlength="100"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                3、请说明您离开投保地的原因？前往何地？出行目的？（如是工作或学习，请提供单位或学校的名称和地址，并详细告知工作内容）
                            </div>
                            <div  style="float: left;margin-left: 10px">
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.leaveReason" maxlength="100"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                4、您一年中平均在投保地逗留的时间多长？每次回投保地的时间间隔多久？您往来投保地和上述异地之间经常乘坐的交通工具是什么？
                            </div>
                            <div  style="float: left;margin-left: 10px">
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.stayTime" maxlength="100"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                5、您在投保地或异地是否已落实居住住所？如已落实请简述居住地址、环境？
                            </div>
                            <div style="float: left;margin-left: 10px">
                                <form:textarea rows="10" cols="101" path="insuranceOrderOffsite.offsiteAddress" maxlength="100"></form:textarea>
                            </div>
                        </div>

                        <div class="inner-padding">
                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30">
                                6、是否有其他需要说明事项：
                                <input type="radio"  name="otherMatter" value="true" <c:if test="${not empty insuranceOrder.insuranceOrderOffsite.otherMatter}"> checked</c:if> onclick="changeOtherMatter(true);"/> 是&nbsp;&nbsp;
                                <input type="radio"  name="otherMatter" value="false" <c:if test="${empty insuranceOrder.insuranceOrderOffsite.otherMatter}" > checked </c:if> onclick="changeOtherMatter(false);"/> 否
                            </div>
                            <div id="otherMatterDiv" style='float: left;margin-left: 10px; <c:if test="${empty insuranceOrder.insuranceOrderOffsite.otherMatter}"> display:none;</c:if>' >
                                <form:textarea id="insuranceOrderOffsite_otherMatter" rows="10" cols="101" path="insuranceOrderOffsite.otherMatter" maxlength="100"></form:textarea>
                            </div>

                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="inner-padding">
                            <div class="spacer-30" style="color: red;">无异地投保
                            </div>
                        </div>

                    </c:otherwise>
                </c:choose>

            </div><!-- End .inner-padding -->
        </div>
        <div class="spacer-30"></div>
        <hr>
        <div class="spacer-30"></div>
        <div class="row">
            <div class="col-sm-2">
                <div class="btn-group pull-right">

                </div>
            </div>
            <div class="col-sm-2">
                <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
            </div>
            <div class="col-sm-2">
                <div class="btn-group pull-right">
                    <a class="btn btn-default pull-right" href="insuranceOrderDetail.do?orderId=${insuranceOrder.orderId}">返回</a>
                </div>
            </div>
        </div>
        <div class="spacer-30"></div>
        <hr>
        <div class="spacer-30"></div>
        <div class="row">
            <div class="col-sm-2">
                <div class="btn-group pull-right">

                </div>
            </div>
        </div>
    </div>
</div>
</form:form>
<jsp:include page="../layouts/footer.jsp"/>
</div>
</div>

</body>
</html>
