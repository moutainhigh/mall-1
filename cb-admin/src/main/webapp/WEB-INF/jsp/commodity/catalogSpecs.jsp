<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>商品规格</title>
    <script type="application/javascript">

        $(document).ready(function () {

            var oppMsg = '${oppMsg}';
            if(oppMsg){
                commonNotify(oppMsg,"success");
            }


            var errerMsg='${errerMsg}';
            if(errerMsg!=null&&errerMsg!=""){
                commonNotify(errerMsg,"error");
            }

            $("#storeId").select2();
            $("#attributeIds").select2({
                placeholder: "请选择货品属性"
            });
            $("#cloneValidateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        form.submit();
                        return true;
                    }
                    return false;
                }
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        var form=null;
                        var sId = $("#specId").val();
                        if(sId == "" || sId == undefined || sId==null || sId==0){
                            $("#validateSubmitForm").attr("action","addSpec.do");
                        }else{
                            $("#validateSubmitForm").attr("action","editSpec.do");
                        }
                        form.submit();
                        return true;
                    }
                    return false;
                }
            });
        });

        function removeItem(specId){
            bootbox.confirm("确认删除吗？", function (result) {
                if (result) {
                    $.get("removeSpecById.do", {
                        specId: specId
                    }, function (data) {
                        if (data) {
                            bootbox.alert("成功",function(){
                                window.location.href = "catalogSpecs.do?catalogId=" + $("#catalogId").val();
                            });

                        } else {
                            bootbox.alert("删除失败，该数据已经使用，不能删除");
                        }
                    });
                }
            });
        }

        function editItem(specId,specName,remark){
            $("#specId").val(specId);
            $("#specName").val(specName);
            $("#remark").val(remark);
        }

    </script>
</head>
<body>
<aside id="sidebar-main" class="sidebar">
    <jsp:include page="../layouts/sidebar_logo.jsp"/>
    <ul class="ext-tabs-sidebar">
        <li>
            <a href="#sidebar-tab-1"><i class="fa fa-bars"></i> 导航</a>
        </li>
        <li class="active">
            <a href="#sidebar-tab-2"><i class="fa fa-folder"></i> 商品分类</a>
        </li>
    </ul>
    <div class="tab-content">
        <jsp:include page="../layouts/menu.jsp">
            <jsp:param name="active" value="active"/>
        </jsp:include>
        <div id="sidebar-tab-2" class="tab-pane active clearfix">
            <div class="sidebar-module">
                <ul class="easyfiletree">
                    <c:set var="instanceUnderCatalog" value="spec" scope="request"/>
                    <jsp:include page="catalogTree.jsp"/>
                </ul>
            </div>
        </div>
    </div>
    <div class="sidebar-line"></div>
</aside>
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
                    <li><a href="#">商品规格</a></li>
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
                    <h2>${catalog.catalogName} 商品规格管理</h2>
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
            <div class="inner-padding">
                <div class="spacer-10"></div>
                <div class="row">
                    <div class="col-sm-5">
                        <table id="specTable" class="table table-bordered table-striped">
                            <thead id="attribute-table-th">
                            <tr>
                                <th scope="col">规格名称</th>
                                <th scope="col" width="200">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${specs}" var="spec">
                                <tr>
                                    <td style="width:100%;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;"><input type='hidden' name='specId' value='${spec.specId}'/>${spec.specName}</td>
                                    <td><a href="javascript:void(0);" onclick="editItem(${spec.specId},'${spec.specName}','${spec.remark}')">编辑</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="removeItem(${spec.specId})">删除</a> </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-sm-7">
                        <fieldset>
                            <legend>复制商品规格</legend>
                            <form id="cloneValidateSubmitForm" class="form-horizontal" method="post" action="cloneSpec.do">
                                <input type="hidden" name="catalogId" value="${catalog.catalogId}"/>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <label><span class="asterisk">*</span>选择分类：</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="hidden" id="cloneCatalogId" name="cloneCatalogId"/>
                                        <div class="input-group">
                                            <input id="cloneCatalogName" readonly="true" class=" form-control validate[required]" id="cloneCatalogName" name="cloneCatalogName" maxlength="32" placeholder="仅可选择第三级商品分类"/>
                                            <span class="input-group-btn">
                                            <button id="catalogNameBtn" class="btn btn-default" type="button">选择</button>
                                        </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <button class="btn btn-default" type="submit" ><i class="fa fa-save"></i>&nbsp;复&nbsp;制&nbsp;</button>
                                    </div>
                                </div>
                            </form>
                        </fieldset>
                        <fieldset>
                            <legend>商品规格</legend>
                            <form:form id="validateSubmitForm" cssClass="form-horizontal" method="post" commandName="spec">
                            <form:hidden path="catalog.catalogId" id="catalogId"/>
                            <form:hidden path="specId" id="specId"/>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label><span class="asterisk">*</span>商品规格名称：</label>
                                </div>
                                <div class="col-sm-7">
                                    <form:input cssClass="form-control validate[required,minSize[1],maxSize[20]]" path="specName" maxlength="20"/>
                                </div>
                            </div>
                            <div class="spacer-10"></div>

                            <div class="row">
                                <div class="col-sm-4">
                                    <label>备注：</label>
                                </div>
                                <div class="col-sm-7">
                                    <form:textarea cssClass="form-control" path="remark" maxlength="255"></form:textarea>
                                </div>
                            </div>

                            <div class="spacer-30"></div>
                            <hr>
                            <div class="spacer-30"></div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="btn-group pull-right">
                                        <button id="saveBtn" class="btn btn-default" type="submit" ><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                        <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                    </div>
                                </div>
                            </div>
                            </form:form>
                        </fieldset>
                    </div>
                </div>
            </div>
            <div class="spacer-40"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-40"></div>
            <!-- End .inner-padding -->
        </div>

        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->
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
                <strong>提示：</strong>仅可选择第三级商品分类进行规格复制！
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCatalog();">确认</button>
            </div>
        </div>
        <script type="application/javascript">
            var catalogId = 0;
            var catalogName = "";
            var RATIO = 1;//分类比例配置
            var treeLevel = "";
            $('#catalogNameBtn').click(function (e) {
                $('#catalogDialog').modal();
                $('#modalMsg').hide();
                e.preventDefault();
            });

            function onSelect(e) {
                var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
                catalogId = data.id;
                catalogName = data.text;
                RATIO = data.ratio;
                treeLevel = data.treeLevel;
            }

            function chooseCatalog() {
                if(treeLevel != 3){
                    $('#modalMsg').show();
                    $('#modalMsg').html("<strong>提示：</strong>仅可选择第三级商品分类进行规格复制！");
                    return;
                }
                if($("#catalogId").val()){
                    if(Number(catalogId) == Number($("#catalogId").val())){
                        $('#modalMsg').show();
                        $('#modalMsg').html("<strong>提示：</strong>当前操作的商品分类无法选择！");
                        return;
                    }
                }
                $('#modalMsg').hide();
                $('#catalogDialog').modal("hide");
                $("#cloneCatalogId").val(catalogId);
                $("#cloneCatalogName").val(catalogName);
            }
        </script>
    </div>
</div>

</body>
</html>
