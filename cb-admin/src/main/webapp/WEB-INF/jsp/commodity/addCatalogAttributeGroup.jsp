<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>新增属性组</title>
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
        var idIndex = 0;
        function addAttribute() {
            var json = {idIndex: idIndex};
            if ($("#onlyWord").is(':checked')) {
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').show();
                $("#attributeTable tr:last").after($('#attributeImgTr').tmpl(json));
            } else {
                $("#attributeTable tr:last").after($('#attributeTr').tmpl(json));
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').hide();
            }
            idIndex++;
        }

        function removeAttribute(indx) {
            $("#attribute" + indx).remove();
        }

        function changeGroupType(obj) {
            if ($("#onlyWord").is(':checked')) {
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').show();
            } else {
                $('#attributeTable tr').find('td:eq(1) td:eq(2)').hide();
            }
        }

        /**
         *上传图片
         */
        function onchangeImg(imgId){
            var formData = new FormData();
            formData.append("file", $('#upload'+imgId)[0].files[0]);
            $.ajax({
                url: "/admin/uploads/upload/INSURANCEPRODUCT.do",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    $('#imagePath'+imgId).val(result.url);
                },
                error: function (err) {
                }
            });
        }

        //建立一個可存取到該file的url
        function getObjectURL(file) {
            var url = null;
            if (window.createObjectURL != undefined) { // basic
                url = window.createObjectURL(file);
            } else if (window.URL != undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL != undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        }

        function addImage(indexa){
            $("#upload"+indexa).click(); //隐藏了input:file样式后，点击头像就可以本地上传
            $("#upload"+indexa).on("change", function () {
                var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
                if (objUrl) {
                    $("#img"+indexa).attr("src", objUrl); //将图片路径存入src中，显示出图片
                }
            });
        }
        $(function () {


        });
    </script>
    <script id="attributeImgTr" type="text/x-jquery-tmpl">
        <tr id='attribute{{= idIndex}}'>
            <td><input type='text' name='attributeName' class='form-control validate[required,minSize[1]]' maxlength='32'/></td>
<%--            <td><input id="imagePath{{= idIndex}}" type='hidden' name='imagePath' value=''/>
                 <img id="img{{= idIndex}}"    onclick="addImage({{= idIndex}})" src="" width="130px" height="120px" style="padding: 5px">
                 <input id="upload{{= idIndex}}" onchange="onchangeImg('{{= idIndex}}')" name="file" multiple="multiple" accept="image/*" type="file" style="display: none"/>
            </td>--%>
            <td><input type='text' name='sortOrder' value='{{= idIndex}}' class='form-control validate[required,min[0],custom[number]]' maxlength='2' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
            <td class="text-center"><a class='btn btn-default' href='javascript:removeAttribute({{= idIndex}})'><i class='fa fa-minus-circle'></i></a></td>
        </tr>
    </script>

    <script id="attributeTr" type="text/x-jquery-tmpl">
        <tr id='attribute{{= idIndex}}'>
            <td><input type='text' name='attributeName' class='form-control validate[required,minSize[1]]' maxlength='32'/></td>
            <%--<td><input id='imagePath{{= idIndex}}' type='hidden' name='imagePath' value=''/></td>--%>
            <td><input type='text' name='sortOrder' value='{{= idIndex}}' class='form-control validate[required,min[0],custom[number]]' maxlength='2' onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
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
                    <li><a href="#">属性管理</a></li>
                    <li><a href="#">属性组管理</a></li>
                    <li class="active">新增属性组</li>
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
                    <h2>新增属性组</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="catalogAttributeGroups.do">
                        <i class="fa fa-reply"></i>
                    </a>

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
                <form:form id="validateSubmitForm" action="addCatalogAttributeGroup.do" method="post" commandName="attributeGroup">
                    <fieldset>
                        <legend>新增属性组</legend>
                        <div class="row"><div class="col-sm-2"></div><div class="col-sm-3"><form:errors path="groupName" cssClass="Validform_checktip"/></div></div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>属性组名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[1]]" path="groupName" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>商品分类：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:hidden id="catalogId" path="catalog.catalogId"/>
                                <div class="input-group">
                                    <form:input id="catalogName" readonly="true" cssClass="form-control validate[required]" path="catalog.catalogName" maxlength="64" data-errormessage="请选择商品分类"/>
                                    <span class="input-group-btn">
                                        <button id="catalogNameBtn" class="btn btn-default" type="button">选择</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <%--<div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 显示方式：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox id="onlyWord" path="showAsImage" onchange="changeGroupType()"/>&nbsp;以图片方式显示
                            </div>
                        </div>--%>
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
                                <label> <span class="asterisk">*</span></label>
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
                    $('#img' + idex).attr("src", ".." + PIC_PATH + imagePath);
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
