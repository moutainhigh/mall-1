<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>新增运营分类</title>
    <script type="text/javascript">

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
                    <h2>新增运营分类</h2>
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
                <form:form id="validateSubmitForm" cssClass="form-horizontal"  action="addCategory.do" method="post" commandName="category">
                    <fieldset>
                        <legend>新增运营分类</legend>
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
                                    <form:input id="parentCategoryName" readonly="true" cssClass="form-control validate[required]" path="parentCategory.categoryName" maxlength="32"/>
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
                                <form:input  path="lowestPrice" maxlength="32"/>-
                                <form:input path="highestPrice" maxlength="32"/>万
                            </div>
                        </div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 图标：</label>
                            </div>
                            <div class="col-sm-9">
                                    <%--图片上传控件--%>
                                <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                                <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                                <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                                <script type="text/javascript">
                                    $(function(){
                                        $("#validateSubmitForm").validationEngine({
                                            autoHidePrompt: true, scroll: false, showOneMessage: true,
                                            onValidationComplete: function (form, valid) {
                                                if (valid) {
                                                    var defaultPicPath = $('input[name="imgurl"]');
                                                    if (defaultPicPath.size()==0) {
                                                        bootbox.alert("请至少选择一张图片!");
                                                        return false;
                                                    } else {
                                                        return true;
                                                    }
                                                }
                                            }
                                        });
                                        var initPreview = new Array();//展示元素
                                        var initPreviewConfig = new Array();//展示设置
                                        //初始化图片上传组件
                                        $("#picUrl").fileinput({
                                            uploadUrl: "/admin/uploads/uploadFile/CATEGORY.do",
                                            showCaption: true,
                                            minImageWidth: 50,
                                            minImageHeight: 50,
                                            showUpload:true, //是否显示上传按钮
                                            showRemove :false, //显示移除按钮
                                            showPreview :true, //是否显示预览
                                            showCaption:false,//是否显示标题
                                            browseOnZoneClick: true,//是否显示点击选择文件
                                            language: "zh" ,
                                            showClose: false,
                                            showBrowse : false,
                                            maxFileSize : 2000,
                                            autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                            overwriteInitial: false,//不覆盖已存在的图片
                                            browseClass:"btn btn-primary", //按钮样式
                                            // layoutTemplates:{
                                            //     actionUpload:''    //设置为空可去掉上传按钮
                                            // },
                                            maxFileCount: 1  //上传的个数
                                        }).on("fileuploaded", function (event, data) {
                                            var response = data.response;
                                            //添加url到隐藏域
                                            var html='<input name="imgurl" type="hidden" id="'+response.timeStr+'" value="'+response.url+','+response.fileName+','+response.timeStr+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                            //上传完成回调
                                            var index=0;
                                            if(initPreview.length>0 ){
                                                index=initPreview.length;
                                            }
                                            initPreview[index]  = response.url;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/CATEGORY.do";
                                            config.key=response.timeStr;
                                            initPreviewConfig[index]=config;
                                            $("#picUrl").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            $(".btn-default").attr("disabled",false);
                                        }).on("filepredelete", function(jqXHR) {
                                            debugger;
                                            var abort = true;
                                            if (confirm("确定要删除吗？(删除后不会恢复)")) {
                                                abort = false;
                                            }
                                            return abort;
                                        }).on('filedeleted', function(event, id) {
                                            $("#"+id).remove();
                                            for (var i=0;i<initPreview.length;i++)
                                            {
                                                if(initPreview[i].indexOf(id) != -1){
                                                    initPreview.splice(i)
                                                    initPreviewConfig.splice(i)
                                                }
                                            }
                                        }).on('filebatchselected', function (event, files) {//选中文件事件
                                            $(".kv-file-upload").click();
                                        });
                                    })
                                </script>
                                <input id="picUrl" name="file" type="file" class="file-loading" accept="image/*" multiple>
                                <div id="imgDiv">

                                </div>
                                    <%--图片上传控件结束--%>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
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
                                <form:textarea cssClass="form-control" path="description" maxlength="512"></form:textarea>
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
                                <form:textarea cssClass="form-control" path="remark"  maxlength="255"></form:textarea>
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
</body>
</html>
