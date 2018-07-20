<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>编辑运营分类</title>
    <script type="text/javascript">
        $(document).ready(function () {

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        if (null == $("#iconPath").val() || "" == $("#iconPath").val()) {
                            bootbox.alert("请选择图标!");
                            return false;
                        }
                        return true;
                    }
                }
            });

        });


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
                    <li><a href="#">运营管理</a></li>
                    <li><a href="#">运营分类管理</a></li>
                    <li class="active">新增运营分类</li>
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
                    <h2>编辑运营分类</h2>
                </div>
                <div class="pull-right">

                    <a class="btn btn-default" href="categories.do">
                        <i class="fa fa-reply"></i>
                    </a>
                </div>
            </div>
            <!-- End .inner-padding -->
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
            <!-- End .actionbar-->
            <div class="inner-padding">
                <form:form id="validateSubmitForm" cssClass="form-horizontal" action="editCategory.do" method="post" commandName="category">
                    <form:hidden path="categoryId"/>
                    <fieldset>
                        <legend>编辑运营分类</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 分类名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required]" path="categoryName" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>上级分类：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:hidden id="categoryId" path="parentCategory.categoryId"/>
                                <div class="input-group">
                                    <form:input id="parentCategoryName" readonly="true" cssClass="form-control validate[required]" path="parentCategory.categoryName" maxlength="32" data-errormessage="请选择上级分类"/>
                                    <span class="input-group-btn">
                                        <button id="parentCategoryNameBtn" class="btn btn-default" type="button">选择</button>
                                    </span>
                                </div>
                            </div>

                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 排序：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,min[0],custom[integer]]" path="sortOrder" maxlength="4"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 是否启用：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="enabled"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 分类编号：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required]" path="categoryNo" maxlength="32"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 价格区间：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input path="lowestPrice" maxlength="32"/>-
                                <form:input path="highestPrice" maxlength="32"/>万
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 图标：</label>
                            </div>
                            <div class="col-sm-3">
                                <a id="chooseIconBtn" class="btn btn-default">选择</a>
                                <form:hidden path="iconPath" id="iconPath" />
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 图标预览：</label>
                            </div>
                            <div class="col-sm-8">
                                <img id="previewIconPath" src="../images/${category.iconPath}_20_20.png" style="max-width:60px ">
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <hr>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 分类关键字：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:input cssClass="form-control validate[required]" path="categoryKey" maxlength="128"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>分类描述：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea cssClass="form-control" path="description"  maxlength="512"></form:textarea>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="spacer-10"></div>
                        <hr>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> SEO关键字：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:input cssClass="form-control validate[required]" path="seoKey" maxlength="128"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> SEO标题：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:input cssClass="form-control validate[required]" path="seoTitle" maxlength="255"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO描述：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea cssClass="form-control" path="seoDescription"  maxlength="512"></form:textarea>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea cssClass="form-control" path="remark" maxlength="255"></form:textarea>
                            </div>
                            <div class="col-sm-1"></div>
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

<div class="modal fade" id="categoryDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择运营分类</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="margin-top:-20px;margin-bottom:-20px">
                    <div class="sidebar-module">
                        <kendo:treeView name="treeview" select="onSelect">
                            <kendo:dataSource data="${categoryTree}">
                            </kendo:dataSource>
                        </kendo:treeView>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCategory();"><i class="fa fa-check"></i>&nbsp;确认</button>
            </div>
        </div>
        <script type="application/javascript">
            var categoryId = 0;
            var categoryName = "";
            $('#parentCategoryNameBtn').click(function (e) {
                $('#categoryDialog').modal();
                e.preventDefault();
            });

            function onSelect(e) {
                var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
                categoryId = data.id;
                categoryName = data.text;
            }

            function chooseCategory() {
                $('#categoryDialog').modal("hide");
                $("#categoryId").val(categoryId);
                $("#parentCategoryName").val(categoryName);
                //loadSpecs(catalogId);
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
            $('#chooseIconBtn').click(function (e) {
                $('#imageDialog').modal().css({
                    width: 'auto'
                });
                e.preventDefault();
            });

            function chooseImage() {
                var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
                if (imagePath != null) {
                    var extension = imagePath.substring(imagePath.lastIndexOf('.'), imagePath.length).toLowerCase();
                    if(extension==".png"){
                        $('#previewIconPath').attr("src",".."+PIC_PATH+imagePath);
                        $('#iconPath').val(imagePath);
                        $('#imageDialog').modal("hide");
                    }else {
                        alert("请选择png格式的背景透明图片");
                    }
                } else {
                    alert("请选择图片");
                }
            }
        </script>
    </div>
</div>

</body>
</html>
