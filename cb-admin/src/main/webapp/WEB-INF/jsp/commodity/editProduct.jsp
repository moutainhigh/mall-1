<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>编辑货品</title>
    <script type="application/javascript">
        $(document).ready(function () {
            $("#storeId").select2();
            $("#attributeIds").select2({
                placeholder: "请选择货品属性"
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
        });

        $('#oneLevelCatalog').val(${oneLevelCatalog.ratio});//一级分类比例
    </script>
    <%--后台页面共用的js--%>
    <script src="../js/common/fixed_common.js"></script>
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
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">商品管理</a></li>
                    <li class="active">货品信息</li>
                    <li class="active">编辑货品</li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>
    <!-- End #header-main -->

    <div id="content" class="clearfix">


        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>${commodity.commodityName} 下属货品信息</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="editProducts.do?commodityId=${commodity.commodityId}"><i class="fa fa-reply"></i></a>
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
            <div class="inner-padding">
                <form:form id="validateSubmitForm" action="editProduct.do" method="post" commandName="product">
                    <form:hidden path="productId"/>
                    <form:hidden path="commodity.commodityId"/>
                    <fieldset>
                        <legend>编辑货品</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>一级分类比例配置：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control"  path="" id="oneLevelCatalog" readonly="true"  maxlength="32"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>商品比例配置：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control " path="commodity.ratio" id="ratio" readonly="true" placeholder="默认取一级分类比例配置"  title="默认取一级分类比例配置" maxlength="11"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>货品编号：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="productNo" maxlength="32"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>货品名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="productName" maxlength="32" readonly="true"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>进货价：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group">
                                    <span class="input-group-addon">￥</span>
                                    <form:input cssClass="form-control validate[required,custom[number]]" path="costPrice" onkeyup="salePrice_f();" maxlength="11"/>
                                </div>

                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>销售价：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group">
                                    <span class="input-group-addon">￥</span>
                                    <form:input cssClass="form-control validate[required,custom[number]]" readonly="true" title="销售价等于成本价乘以比例配置"  path="salePrice" maxlength="11"/>
                                </div>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>市场价：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group">
                                    <span class="input-group-addon">￥</span>
                                    <form:input cssClass="form-control validate[required,custom[number]]" path="marketPrice" maxlength="11"/>
                                </div>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>仓库：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select id="storeId" path="store.storeId" items="${stores}" itemLabel="storeName" itemValue="storeId"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>库存数量：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,custom[number]]" path="storeNum" maxlength="12"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>重量：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group">
                                    <form:input cssClass="form-control validate[custom[number]]" path="weight" maxlength="11"/>
                                    <span class="input-group-addon">Kg</span>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <label>体积：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group">
                                    <form:input cssClass="form-control validate[custom[number]]" path="volume" maxlength="11"/>
                                    <span class="input-group-addon">m3</span>
                                </div>

                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>货品属性：</label>
                            </div>
                            <div class="col-sm-8" style="position:relative;">
                                <span style="position: absolute;top:4px;">
                                    <c:forEach var="proAttr" items="${productAttributes}">
                                        ${proAttr.attribute.attributeName}&nbsp;&nbsp;&nbsp;&nbsp;
                                    </c:forEach>
                                </span>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form:form>
                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>
            </div>
            <!-- End .inner-padding -->
        </div>
        <!-- End .window -->

        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->

<script type="application/javascript">
    $('#oneLevelCatalog').val(${oneLevelCatalog.ratio});//一级分类比例
</script>
</body>
</html>
