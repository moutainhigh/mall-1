<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>新增商品分类</title>
    <script type="application/javascript">
        $(document).ready(function () {

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
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
                <!-- * This is the responsive logo * -->
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
                    <li><a href="#">商品分类</a></li>
                    <li class="active">新增商品分类</li>
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
                    <h2>新增商品分类</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="catalogs.do"><i class="fa fa-reply"></i></a>
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
            <!-- End .actionbar-->
            <div class="inner-padding">
                <form:form id="validateSubmitForm" action="addCatalog.do" cssClass="form-horizontal" method="post" commandName="catalog">
                    <fieldset>
                        <legend>新增商品分类</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>商品分类名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="catalogName" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>上级分类：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:hidden id="parentCatalogId" path="parentCatalog.catalogId"/>
                                <div class="input-group">
                                    <form:input id="parentCatalogName" readonly="true" cssClass="form-control validate[required]" path="parentCatalog.catalogName" maxlength="32" data-errormessage="请选择上级分类"/>
                                    <span class="input-group-btn">
                                        <button id="parentCatalogNameBtn" class="btn btn-default" type="button">选择</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>排序：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required],min[0]" path="sortOrder" maxlength="3"/>
                            </div>
                            <%--不是一级分类时，不需要配置--%>
                            <span id="hidd">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>分类比例配置：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required],custom[eqOne]" path="ratio" maxlength="10" />
                            </div>
                            </span>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>是否支持增值税发票：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="enabled"/>
                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk"></span>是否启用：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="enabled"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea class="form-control" path="remark" maxlength="255"></form:textarea>
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
            <div class="alert alert-warning" id="modalMsg" style="display: none;">
                <strong>提示：</strong>商品分类最多可新建三级！
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCatalog();"><i class="fa fa-check"></i>&nbsp;确认</button>
            </div>
        </div>
        <script type="application/javascript">
            var catalogId = 0;
            var catalogName = "";
            var treeLevel = "";
            $('#parentCatalogNameBtn').click(function (e) {
                $('#catalogDialog').modal();
                $('#modalMsg').hide();
                e.preventDefault();
            });

            function onSelect(e) {
                var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
                catalogId = data.id;
                catalogName = data.text;
                treeLevel = data.treeLevel;
            }

            function chooseCatalog() {
                if(treeLevel >= 3){
                    $('#modalMsg').show();
                    return;
                }
                $('#modalMsg').hide();
                $('#catalogDialog').modal("hide");
                $("#parentCatalogId").val(catalogId);
                $("#parentCatalogName").val(catalogName);
                //loadSpecs(catalogId);
                if(catalogId !=1){
                    $('#hidd').hide();
                }else{
                    $('#hidd').show();
                }
            }
        </script>
    </div>
</div>
</body>
</html>
