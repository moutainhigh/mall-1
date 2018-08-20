<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>货品信息</title>

    <script type="application/javascript">
        $(document).ready(function () {

            var oppMsg = '${oppMsg}';
            if(oppMsg){
                commonNotify(oppMsg, "success");
            }

            $("#storeId").select2();
            $("#attributeIds").select2({
                placeholder: "请选择货品属性"
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
            var flag = true;
            $("#costPrice,#marketPrice").blur(function(){
                if (flag) {
                    flag = false;
                    var inputValue = $(this).val();
                    var startPrice = '${commodity.priceSection.startPrice}';
                    var endPrice = '${commodity.priceSection.endPrice}';
                    var documentId = $(this).attr("id");
                    if (documentId == "costPrice") {
                        var salePrive = $("#salePrice").val();
                        if(Number(salePrive) < Number(startPrice) || Number(endPrice) < Number(salePrive)){
                            bootbox.alert("价格须介于商品价格段"+startPrice+"—"+endPrice+"范围内!");
                            $(this).val('');
                            $("#salePrice").val('');
                            setInterval(function(){
                                flag = true;
                            }, 2000);
                        } else {
                            flag = true;
                        }
                    } else {
                        if(Number(inputValue) < Number(startPrice) || Number(endPrice) < Number(inputValue)){
                            bootbox.alert("价格须介于商品价格段"+startPrice+"—"+endPrice+"范围内!");
                            $(this).val('');
                            setInterval(function(){
                                flag = true;
                            }, 2000);
                        } else {
                            flag = true;
                        }
                    }

                }
            });
        });

        function removeProduct(productId) {
            bootbox.confirm("确认删除吗?", function(result) {
                if(result){
                    $.post('removeProductById.do', {productId:productId}, function(data) {
                        if(data.resultType=="SUCCESS"){
                            $("#product"+productId).remove();
                            commonNotify("删除成功！", "success");
                        }else{
                            commonNotify("货品已被关联，删除失败！请选择下架", "error");
                        }
                    } );
                }
            });
        }

        function defaultProduct(productId,commodityId) {
            var state="${commodity.publishState}";
            if(state=='UP_SHELVES'){
                bootbox.alert("失败,请先下架商品!");
                return false;
            }
            bootbox.confirm("确认设置为默认货品吗?", function(result) {
                if(result){
                    $.post('defaultProductById.do', {productId:productId,commodityId:commodityId}, function(data) {
                        if(data.resultType=="SUCCESS"){
                            bootbox.alert("成功");
                            window.location.reload();
                        }else{
                            bootbox.alert("失败"+data.data);
                        }
                    } );
                }
            });
        }

        function removeGroup(groupId) {
            bootbox.confirm("确认删除吗?", function(result) {
                if(result){
                    $.post('removeAttributeGroupById.do', {groupId:groupId}, function(data) {
                        if(data.resultType=="SUCCESS"){
                            $("#group"+groupId).remove();
                            $("#attributeGroup"+groupId).remove();
                            commonNotify("删除成功！", "success");
                        }else{
                            commonNotify("货品已被关联，删除失败！请选择下架", "error");
                        }
                    } );
                }
            });
        }

        function checkAttributes(obj){
            if($(obj).is(":checked")){
                var groupId=$(obj).attr("data-gid");
                var attrId=$(obj).val();
                $("input[name='attributeIds']:checked").each(function() {
                    if(attrId!=$(this).val()&&groupId==$(this).attr("data-gid")){
                        $(this).attr("checked",false);
                    }
                });
            }
        }


        function upOrDownShelvesProduct(pid,pState){
            var state="${commodity.publishState}";
            if(pState=='DOWN_SHELVES'&&state=='UP_SHELVES'){
                bootbox.alert("失败,请先下架商品!");
                return false;
            }
            bootbox.confirm("确认上/下架该货品吗？", function (result) {
                if (result) {
                    $.get("upOrDownShelvesProduct.do", {
                        productId: pid,
                        publishState : pState
                    }, function (data) {
                        if (data) {
                            bootbox.alert("成功");
                            window.location.reload();
                        } else {
                            bootbox.alert("失败,货品未通过审核或没有货品信息");
                        }
                    });
                }
            });
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
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">商品管理</a></li>
                    <li class="active">货品信息</li>
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
                    <a class="btn btn-default" href="commodities.do"><i class="fa fa-reply"></i></a>
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

                <div class="comments-toolbar">
                    <div class="pull-left">
                        <h4>商品属性列表</h4>
                    </div>
                    <div class="pull-right">
                        <div class="btn-group">
                            <a href="javascript:selectCatalogAttributeGroup();" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;添加商品属性</a>
                            <a href="toAddAttributeGroup.do?commodityId=${commodity.commodityId}" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;添加自定义商品属性</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-bordered ">
                            <thead>
                            <th style="width: 200px">属性名称</th>
                            <th style="width: 100px">图片</th>
                            <th>属性值</th>
                            <th scope="col" style="width: 100px" class="text-center">操作</th>
                            </thead>
                            <tbody>
                            <c:forEach var="group" items="${attributeGroups}">
                                <tr id="group${group.groupId}">
                                    <td>${group.groupName}</td>
                                    <td>${group.showAsImage==true?"是":"否"}</td>
                                    <td>
                                        <c:forEach var="attribute" items="${group.attributes}">
                                             &nbsp;${attribute.attributeName}
                                            <c:if test="${!empty attribute.imagePath}">
                                                <img src="..${PIC_PATH}${attribute.imagePath}">
                                            </c:if>
                                            &nbsp;&nbsp;&nbsp;
                                        </c:forEach>
                                    </td>
                                    <td class="text-center">
                                        <a href="toEditAttributeGroup.do?groupId=${group.groupId}" title="编辑" class=" btn-less"><i class="fa fa-edit"></i></a>
                                        <a href="javascript:removeGroup(${group.groupId});" title="删除" class=" btn-less"><i class="fa fa-trash-o"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="spacer-25"></div>

            <div class="inner-padding">
                <div class="subheading">
                    <h3>货品列表 <span class="text-muted">(${fn:length(products)})</span></h3>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table  class="table table-bordered table-striped">
                            <thead >
                            <tr>
                                <th scope="col" width="140">编号</th>
                                <th scope="col" >货品名称</th>
                                <th scope="col" width="100">进货价</th>
                                <th scope="col" width="100">销售价</th>
                                <th scope="col" width="100">市场价</th>
                                <th scope="col" width="140">仓库名称</th>
                                <th scope="col" width="100">库存</th>
                                <th scope="col" width="120">上下架状态</th>
                                <th scope="col" width="120">是否默认货品</th>
                                <th scope="col" width="140" class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${products}">
                                <tr id="product${product.productId}">
                                    <td>${product.productNo}</td>
                                    <td>${product.productName}</td>
                                    <td>${product.costPrice}</td>
                                    <td>${product.salePrice}</td>
                                    <td>${product.marketPrice}</td>
                                    <td>${product.store.storeName}</td>
                                    <td>${product.storeNum}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${product.publishState=='WAIT_UP_SHELVES' || product.publishState=='DOWN_SHELVES'}">
                                                已下架
                                            </c:when>
                                            <c:otherwise>
                                                已上架
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${commodity.defaultProduct.productId!=product.productId}">
                                                否
                                            </c:when>
                                            <c:otherwise>
                                                默认
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-left">
                                        <c:choose>
                                            <c:when test="${product.publishState=='WAIT_UP_SHELVES' || product.publishState=='DOWN_SHELVES'}">
                                                <a href="javascript:upOrDownShelvesProduct('${product.productId}','UP_SHELVES');" title="上架" class=" btn-less"><i class="fa fa-arrow-up"></i></a>
                                                <a href="toEditProduct.do?productId=${product.productId}" title="编辑" class=" btn-less"><i class="fa fa-edit"></i></a>
                                                <a href="javascript:removeProduct(${product.productId});" title="删除" class=" btn-less"><i class="fa fa-trash-o"></i></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="javascript:upOrDownShelvesProduct('${product.productId}','DOWN_SHELVES');" title="下架" class=" btn-less"><i class="fa fa-arrow-down"></i></a>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${commodity.defaultProduct.productId!=product.productId}"><!-- 设置默认货品 -->
                                            <a href="javascript:defaultProduct('${product.productId}','${commodity.commodityId}');" title="默认" class=" btn-less"><i class="fa fa-level-up"></i></a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="inner-padding">
                <form:form id="validateSubmitForm" action="addProduct.do" method="post" commandName="product">
                    <form:hidden path="commodity.commodityId"/>
                    <fieldset>
                        <legend>新增货品</legend>
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
                                <form:input cssClass="form-control " path="commodity.ratio" id="ratio" readonly="true"  placeholder="默认取一级分类比例配置"  title="默认取一级分类比例配置" maxlength="11"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>货品编号：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input readonly="true" cssClass="form-control validate[required,minSize[2]]" path="productNo" maxlength="32"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>进货价：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group">
                                    <span class="input-group-addon">￥</span>
                                    <form:input cssClass="form-control validate[required,custom[number]]" path="costPrice" onkeyup="salePrice_f();" maxlength="11"/>
                                </div>

                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>销售价：</label>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group input-group">
                                    <span class="input-group-addon">￥</span>
                                    <form:input cssClass="form-control validate[required,custom[number]]" path="salePrice" readonly="true" title="销售价等于成本价乘以比例配置" maxlength="11"/>
                                </div>
                            </div>
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
                        <div class="spacer-10"></div>
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
                                <form:input cssClass="form-control validate[required,custom[checkPositive]]" required="true" path="storeNum" maxlength="12"/>
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

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>货品属性：</label>
                            </div>
                            <div class="col-sm-8">
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <th style="width: 200px">属性名称</th>
                                    <th>属性值</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="group" items="${attributeGroups}">
                                        <tr id="attributeGroup${group.groupId}">
                                            <td>${group.groupName}</td>
                                            <td>
                                                <c:forEach var="attribute" items="${group.attributes}">
                                                <form:checkbox cssClass="validate[minCheckbox[${fn:length(attributeGroups) }]]" path="attributeIds" data-gid="${group.groupId}" onchange="checkAttributes(this);" value="${attribute.attributeId}" data-errormessage="请选择至少${fn:length(attributeGroups) }个属性"/>&nbsp;${attribute.attributeName}
                                                    <c:if test="${!empty attribute.imagePath}">
                                                        <img src="..${PIC_PATH}${attribute.imagePath}">
                                                    </c:if>
                                                    &nbsp;&nbsp;&nbsp;
                                            </c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">
                                <label><form:errors path="attributeIds"/> </label>
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
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>

<form id="commodityAttributeGroupsForm" action="addCommodityAttributeGroups.do" method="post">
    <input type="hidden" name="commodityId" value="${commodity.commodityId}">
    <div class="modal fade" id="attributeGroupDialog" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 800px; ">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">选择商品属性</h4>
                </div>
                <div class="modal-body">
                    <div class="row" style="margin-top:-20px;margin-bottom:-20px">
                        <div class="sidebar-module">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <th style="width: 100px">属性名称</th>
                                <th>属性值</th>
                                </thead>
                                <tbody>
                                <c:forEach var="group" items="${catalogAttributeGroups}">
                                    <tr>
                                        <td><input cssClass="validate[minCheckbox[1]]" type="checkbox" name="groupId" value="${group.groupId}">&nbsp;${group.groupName}</td>
                                        <td>
                                            <c:forEach var="attribute" items="${group.catalogAttributes}">
                                                &nbsp;${attribute.attributeName}
                                                <c:if test="${!empty attribute.imagePath}">
                                                    <img src="..${PIC_PATH}${attribute.imagePath}">
                                                </c:if>
                                                &nbsp;&nbsp;&nbsp;
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;关闭</button>
                    <button type="button" class="btn btn-primary pull-right" onclick="chooseAttribute();"><i class="fa fa-check"></i>&nbsp;添加</button>
                </div>
            </div>
            <script type="application/javascript">

                function selectCatalogAttributeGroup() {
                    $('#attributeGroupDialog').modal();
                }

                function chooseAttribute() {
                    var gIds=$("input[type='checkbox'][name='groupId']:checked").size();
                    if(0==gIds){
                        bootbox.alert("请选择属性");
                        return ;
                    }
                    $("#commodityAttributeGroupsForm")[0].submit();
                    $('#attributeGroupDialog').modal("hide");
                }
                $('#oneLevelCatalog').val(${oneLevelCatalog.ratio});//一级分类比例
            </script>
        </div>
    </div>
</form>
<%--后台页面共用的js--%>
<script src="../js/common/fixed_common.js"></script>
</body>
</html>
