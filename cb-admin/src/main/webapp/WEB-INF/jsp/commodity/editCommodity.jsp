<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>编辑商品</title>
    <script charset="utf-8" src="../editor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="../editor/lang/zh_CN.js"></script>

    <script type="application/javascript">

        var curStep = 0;

        $(document).ready(function () {
            KindEditor.ready(function (K) {
                window.editor = K.create('#editorContent', {
                    uploadJson: '../editor/jsp/upload_json.jsp',
                    fileManagerJson: '../editor/jsp/file_manager_json.jsp',
                    allowFileManager: true,
                    afterCreate : function() {
                        this.sync();
                    },
                    afterBlur:function(){
                        this.sync();
                    }
                });
            });

            $("#brandId").select2();

            $.citySelector.init({
                province: "province",
                city: "city",
                preProvince: "${commodity.province}",
                preCity: "${commodity.city}"
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if (valid) {
                        var defaultPicPath = $('input:radio[name="defaultPicPath"]:checked').val();
                        if (defaultPicPath == null) {
                            bootbox.alert("请至少选择一张商品默认图片!");
                            return false;
                        }
                        if (null == $("#editorContent").val() || "" == $("#editorContent").val()) {
                            bootbox.alert("请填写商品详情内容!");
                            return false;
                        }
                        return true;
                    }
                }
            });
        });

        function loadAttributeGroups(catalogId) {
            $.getJSON("getAttributeGroupsByCatalogId.do", {
                catalogId: catalogId
            }, function (json) {
                var agHtml = "";
                $.each(json, function (date, value) {
                    var groupHtml = '<div class="row"><div class="col-sm-2"><label>' + value.groupName + '：</label></div><div class="col-sm-9">';
                    $.each(value.attributes, function (data, attribute) {
                        groupHtml += '<label style="float:left;"><input type="checkbox" name="attributeId" style="opacity:1" value="' + attribute.attributeId + '"/>&nbsp;' + attribute.attributeName + '&nbsp;&nbsp;&nbsp;&nbsp;</label>';
                    });
                    groupHtml += "</div></div>";
                    agHtml += groupHtml;
                });
                $("#attributeGroupDiv").html(agHtml);
            });
        }

        function loadSpecs(catalogId) {
            $.getJSON("getSpecsByCatalogId.do", {
                catalogId: catalogId
            }, function (json) {
                $("#specTable  tr:not(:first)").empty()
                $.each(json, function (date, value) {
                    var newRow = "<tr><td><input type='hidden' name='specId' value='" + value.specId + "'/>" + value.specName + "</td><td><input type='text' name='specValue' class='form-control'/></td></tr>";
                    $("#specTable tr:last").after(newRow);

                });
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
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">商品管理</a></li>
                    <li class="active">编辑商品</li>
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
                    <h2>编辑商品</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="commodities.do"><i class="fa fa-reply"></i></a>
                </div>
            </div>
        </header>
        <div class="window">
            <div class="inner-padding">
                <form:form id="validateSubmitForm" action="editCommodity.do" cssClass="form-horizontal" method="post" commandName="commodity">
                    <form:hidden path="commodityId"/>
                    <fieldset>
                        <legend>商品表单</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品分类：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:hidden id="catalogId" path="catalog.catalogId"/>
                                <div class="input-group">
                                    <form:input id="catalogName" readonly="true" cssClass="form-control validate[required]" path="catalog.catalogName" maxlength="32"/>
                                    <span class="input-group-btn">
                                        <button id="catalogNameBtn" class="btn btn-default" type="button">选择</button>
                                    </span>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品品牌：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select id="brandId" path="brand.brandId" items="${brands}" itemLabel="brandName" itemValue="brandId"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品标题：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="commodityTitle" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品价格段：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select path="priceSection.sectionId" cssClass="form-control simpleselect validate[required]">
                                    <c:forEach items="${priceSections}" var="section">
                                        <option value="${section.sectionId}">${section.startPrice} - ${section.endPrice}</option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品编码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2],custom[onlyLetterNumber]" path="commodityCode" maxlength="32" data-errormessage-custom-error="编码只能输入数字和英文字母"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品简称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="shortName" maxlength="255"/>
                            </div>

                        </div>

                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品全称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="commodityName" maxlength="255"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品拼音名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="commodityPYName" maxlength="64"/>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 成本价：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="costPrice" maxlength="12"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 销售价：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="sellPrice" maxlength="12"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 市场价：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="marketPrice" maxlength="12"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 商品单位：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select class="form-control simpleselect" path="unit">
                                    <form:options items="${unit}" itemLabel="name"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>重量：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="weight" maxlength="12"/>
                            </div>
                            <div class="col-sm-2">
                                <label>体积：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input type="text" cssClass="form-control validate[required,custom[number]]" path="volume" maxlength="12"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 产地省份：</label>
                            </div>
                            <div class="col-sm-3">
                                <select class="form-control validate[required]" id="province" name="province">
                                </select>

                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 产地市区：</label>
                            </div>
                            <div class="col-sm-3">
                                <select class="form-control validate[required]" id="city" name="city">
                                </select>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO关键字：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input type="text" cssClass="form-control" path="seoKey" maxlength="255"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO标题：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input type="text" cssClass="form-control" path="seoTitle" maxlength="255"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO描述：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea rows="10" cols="101" path="seoDescription" maxlength="255"></form:textarea>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 热门商品：</label>
                            </div>
                            <div class="col-sm-1">
                                <form:checkbox path="popular" cssClass="checkbox-master"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 特惠商品：</label>
                            </div>
                            <div class="col-sm-1">
                                <form:checkbox path="special"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 推荐商品：</label>
                            </div>
                            <div class="col-sm-1">
                                <form:checkbox path="recommend"/>
                            </div>
                        </div>
                        <%--<div class="spacer-10"></div>--%>
                        <%--<div class="row">--%>

                            <%--<div class="col-sm-2">--%>
                                <%--<label><span class="asterisk">*</span>赠品：</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-1">--%>
                                <%--<form:checkbox path="giveaway"/>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-2">--%>
                                <%--<label><span class="asterisk">*</span> 换购：</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-1">--%>
                                <%--<form:checkbox path="barter"/>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-2">--%>
                                <%--<label><span class="asterisk">*</span> 预售：</label>--%>
                            <%--</div>--%>
                            <%--<div class="col-sm-1">--%>
                                <%--<form:checkbox path="preSell"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>商品规格：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="specTable" class="table table-bordered table-striped">
                                    <thead id="attribute-table-th">
                                    <tr>
                                        <th scope="col" width="200">规格名称</th>
                                        <th scope="col">值</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${currentSpecs}" var="cSpec">
                                        <tr>
                                            <td><input type='hidden' name='specId' value='${cSpec.spec.specId}'/>${cSpec.spec.specName}</td>
                                            <td><input type='text' name='specValue' class='form-control' value="${cSpec.value}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>商品图片：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="imageTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th class="text-center" width="120">默认封面图片</th>
                                        <th scope="col">图片预览</th>
                                        <th class="text-center" width="100">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="image" varStatus="status" items="${imageSet}">
                                        <tr id="imageTr${status.index}">
                                            <td class='text-center'>
                                                <input type='radio' name='defaultPicPath' value='${image}' <c:if test="${commodity.defaultPicPath==image}">checked="true"</c:if>/></td>
                                            <td><input type='hidden' name='imagePath' value='${image}'/><img src="../images/${image}_89_89.jpg" style="max-height:120px"/></td>
                                            <td class='text-center'><a type='button' title='删除' class='btn btn-default' href='javascript:removeImage(${status.index})'><i class='fa fa-minus-circle'></i></a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">
                                <a id="chooseImageBtn" class="btn btn-default">添加商品图片</a>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-6">
                                <label>商品详情内容</label>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <form:textarea cssClass="form-control" id="editorContent" path="content" cssStyle="height:500px;"></form:textarea>
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
<div class="modal fade" id="catalogDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择商品分类</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="margin-top:-20px;margin-bottom:-20px">
                    <div class="sidebar-module">
                        <kendo:treeView name="treeview" select="onSelect">
                            <kendo:dataSource data="${catalogTree}">
                            </kendo:dataSource>
                        </kendo:treeView>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCatalog();">确认</button>
            </div>
        </div>
        <script type="application/javascript">
            var catalogId = 0;
            var catalogName = "";
            $('#catalogNameBtn').click(function (e) {
                $('#catalogDialog').modal();
                e.preventDefault();
            });

            function onSelect(e) {
                var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
                catalogId = data.id;
                catalogName = data.text;
            }

            function chooseCatalog() {
                $('#catalogDialog').modal("hide");
                $("#catalogId").val(catalogId);
                $("#catalogName").val(catalogName);
                loadSpecs(catalogId);
            }
        </script>

    </div>
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
                <button class="btn btn-primary pull-right" onclick="chooseImage();">确认</button>
            </div>
        </div>
        <script type="application/javascript">

            var idIndex = ${imageSet==null?0:imageSet.size()};

            $('#chooseImageBtn').click(function (e) {
                $('#imageDialog').modal().css({
                    width: 'auto'
                });
                ;
                e.preventDefault();
            });

            function chooseImage() {
                var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
                if (imagePath != null) {
                    var newRow = "<tr id='imageTr" + idIndex + "'><td class='text-center'><input type='radio' name='defaultPicPath' value='" + imagePath + "'/></td><td><input type='hidden' name='imagePath' value='" + imagePath + "'/><img src='.." + PIC_PATH + imagePath + "' style='max-height:120px'/></td><td class='text-center'><a type='button' title='删除' class='btn btn-default' href='javascript:removeImage(" + idIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
                    $("#imageTable tr:last").after(newRow);
                    idIndex++;
                    $('#imageDialog').modal("hide");
                } else {
                    alert("请选择图片");
                }
            }

            function removeImage(indx) {
                $("#imageTr" + indx).remove();
            }
        </script>
    </div>
</div>
</body>
</html>
