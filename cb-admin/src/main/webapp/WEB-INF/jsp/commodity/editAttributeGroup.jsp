<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>编辑属性组</title>
    <script type="application/javascript">
        $(document).ready(function () {
            $('#catalogTrigger').focusin(function (e) {
                $('#catalogDialog').modal();
                e.preventDefault();
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        if ($('input[name="attributeName"]').length==0) {
                            bootbox.alert("请至少添加一个属性值!");
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            });

        });
        var idIndex = ${attributeGroup.attributes.size()==0}?0:${attributeGroup.attributes.size()};
        function addAttribute() {
            var json = {idIndex: idIndex};
            if($("#onlyWord").is(':checked')){
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').show();
                $("#attributeTable tr:last").after($('#attributeImgTr').tmpl(json));
            }else{
                $("#attributeTable tr:last").after($('#attributeTr').tmpl(json));
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').hide();
            }
            idIndex++;
        }

        function removeAttribute(indx) {
            $("#attribute" + indx).remove();
            idIndex--;
        }

        function changeGroupType(obj) {
            if($("#onlyWord").is(':checked')){
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').show();
            }else{
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').hide();
            }
        }
    </script>
    <script id="attributeImgTr" type="text/x-jquery-tmpl">
        <tr id='attribute{{= idIndex}}'>
            <td><input type="hidden" name="attributeId" value="0"><input type='text' name='attributeName' class='form-control validate[required,minSize[1]]' maxlength='32'/></td>
            <%--<td><input id="imagePath{{= idIndex}}" type='hidden' name='imagePath' value=""/><a class='btn btn-default' href="javascript:chooseImage({{= idIndex}})">选择图片</a><img id="img{{= idIndex}}" src="" style="max-height:60px"/></td>--%>
            <td><input type='text' name='sortOrder' value='{{= idIndex}}' class='form-control validate[required,custom[number]]' maxlength='2'/></td>
            <td class="text-center"><a class='btn btn-default' href='javascript:removeAttribute({{= idIndex}})'><i class='fa fa-minus-circle'></i></a></td>
        </tr>
    </script>
    <script id="attributeTr" type="text/x-jquery-tmpl">
        <tr id='attribute{{= idIndex}}'>
            <td><input type='text' name='attributeName' class='form-control validate[required,minSize[1]]' maxlength='20'/></td>
            <%--<td><input id='imagePath{{= idIndex}}' type='hidden' name='imagePath' value=""/></td>--%>
            <td><input type='text' name='sortOrder' value='{{= idIndex}}' class='form-control validate[required,custom[number]]' maxlength='2'/></td>
            <td class="text-center"><a class='btn btn-default' href='javascript:removeAttribute({{= idIndex}})'><i class='fa fa-minus-circle'></i></a></td>
        </tr>
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
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">商品管理</a></li>
                    <li><a href="#">属性管理</a></li>
                    <li><a href="#">属性组管理</a></li>
                    <li class="active">编辑属性组</li>
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
                    <h2>编辑属性组</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="editProducts.do?commodityId=${attributeGroup.commodity.commodityId}">
                        <i class="fa fa-reply"></i>
                    </a>
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
                <form:form id="validateSubmitForm" action="editAttributeGroup.do" method="post" commandName="attributeGroup">
                    <form:hidden path="commodity.commodityId"/>
                    <form:hidden path="groupId"/>
                    <fieldset>
                        <legend>编辑属性组</legend>
                        <div class="row"><div class="col-sm-2"></div><div class="col-sm-3"><form:errors path="groupName" cssClass="Validform_checktip"/></div></div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>属性名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[1]]" path="groupName" maxlength="20"/>
                            </div>
                            <%--<div class="col-sm-2">
                                <label><span class="asterisk">*</span> 显示方式：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox id="onlyWord" path="showAsImage" onchange="changeGroupType()"/>&nbsp;以图片方式显示
                            </div>--%>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 属性列表：</label>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" onclick="addAttribute();" title="添加" class="btn btn-default">
                                    <i class="fa fa-plus-circle"></i>添加属性
                                </button>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>  </label>
                            </div>
                            <div class="col-sm-8">
                                <table id="attributeTable" class="table table-bordered table-striped">
                                    <thead id="attribute-table-th">
                                    <tr>
                                        <td scope="col">属性名</td>
                                        <%--<td scope="col" width="250">图片</td>--%>
                                        <td scope="col" width="110">排序</td>
                                        <td scope="col" width="70" class="text-center">操作</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="attribute" items="${attributeGroup.attributes}">
                                        <tr id="attribute${attribute.attributeId}">
                                            <td><input type="hidden" name="attributeId" value="${attribute.attributeId}">
                                                <input type='text' name='attributeName' value="${attribute.attributeName}" class='form-control validate[required,minSize[1]]' maxlength='32'/></td>
                                            <%--<c:choose>
                                                <c:when test="${!empty attribute.imagePath}">
                                                    <td><input id="imagePath${attribute.attributeId}" type='hidden' name='imagePath' value="${attribute.imagePath}"/>
                                                        <a class='btn btn-default' href="javascript:chooseImage(${attribute.attributeId})">选择图片</a>
                                                        <img id="img${attribute.attributeId}" src="..${PIC_PATH}${attribute.imagePath}" style="max-height:60px"/>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><input id="imagePath${attribute.attributeId}" type='hidden' name='imagePath' value=""/></td>
                                                </c:otherwise>
                                            </c:choose>--%>
                                            <td><input type='text' name='sortOrder' value="${attribute.sortOrder}" class='form-control validate[required,custom[number]]' maxlength='2'/></td>
                                            <td><a type='button' title='删除' class='btn btn-default' href='javascript:removeAttribute(${attribute.attributeId})'><i class='fa fa-minus-circle'></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">

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
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>

<div class="modal fade" id="imageDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1100px;height: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择图片</h4>
            </div>
            <div class="modal-body">
                <iframe id="imageFrame" src="../media/chooseMedias.do" style="width: 100%;height: 500px;border: none"></iframe>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseImageItem();">确认</button>
            </div>
        </div>
        <script type="application/javascript">

            var idex = 0;

            function chooseImage(idx) {
                idex = idx;
                $('#imageDialog').modal().css({
                    width: 'auto'
                });
            }

            function chooseImageItem() {
                var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
                if (imagePath != null) {
                    $('#img' + idex).attr("src", ".."+PIC_PATH + imagePath);
                    $('#imagePath' + idex).val(imagePath);
                    $('#imageDialog').modal("hide");
                } else {
                    alert("请选择图片");
                }
            }
        </script>
    </div>
</div>
</body>
</html>
