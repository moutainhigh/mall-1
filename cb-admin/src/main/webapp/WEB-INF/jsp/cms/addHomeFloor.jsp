<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>新增首页楼层</title>
    <script type="application/javascript">


        $(document).ready(function () {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        var defaultPicPath = $('input[name="imgurl"]');
                        var defaultPicPath1 = $('input[name="imgurl1"]');
                        if (defaultPicPath.size()==0) {
                            bootbox.alert("请至少选择一张图片!");
                            return false;
                        }else if (defaultPicPath1.size()==0) {
                            bootbox.alert("请至少选择一张图片!");
                            return false;
                        }else if($('input[name="commodityId"]').length==0 && $('input[name="categoryId"]').length==0 && $('input[name="brandId"]').length==0){
                            bootbox.alert("请至少添加商品或者分类或者品牌其中一条数据!");
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            });

        });
        function removeCommodity(indx) {
            $("#commodity" + indx).remove();
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
                    <li><a href="#">内容管理</a></li>
                    <li><a href="#">首页管理</a></li>
                    <li class="active">新增首页楼层</li>
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
                    <h2>新增首页楼层</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="homeFloors.do"><i class="fa fa-reply"></i></a>

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
                <form:form id="validateSubmitForm" action="addHomeFloor.do" cssClass="form-horizontal" method="post" commandName="homeFloor">
                    <fieldset>
                        <legend>首页楼层表单</legend>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>楼层名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2],maxSize[32]]"  path="floorName" maxlength="32"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>排序：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input id="sortOrder" cssClass="form-control validate[required,custom[integer],min[0]]" path="sortOrder" maxlength="4"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">

                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 布局：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:radiobutton path="floorLayout" value="HORIZONTAL"/>&nbsp;&nbsp;横幅(图片大小：1270*270)&nbsp;&nbsp;&nbsp;
                                <form:radiobutton path="floorLayout" value="VERTICAL"/>&nbsp;&nbsp;左边(图片大小：423*611)
                            </div>
                            <div class="col-sm-2">
                                <label> 是否启用：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="enabled"/>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>图标：</label>
                            </div>
                            <div class="col-sm-9">
                                    <%--图片上传控件--%>
                                <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                                <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                                <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                                <script type="text/javascript">
                                    $(function(){
                                        var initPreview = new Array();//展示元素
                                        var initPreviewConfig = new Array();//展示设置
                                        //初始化图片上传组件
                                        $("#picUrl").fileinput({
                                            uploadUrl: "/admin/uploads/uploadFile/HOMEFLOORICO.do",
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
                                            allowedFileExtensions: ["jpg", "png", "gif"],
                                            autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                            overwriteInitial: false,//不覆盖已存在的图片
                                            browseClass:"btn btn-primary", //按钮样式
                                            // layoutTemplates:{
                                            //     actionUpload:''    //设置为空可去掉上传按钮
                                            // },
                                            maxFileCount: 10  //上传的个数
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
                                            config.url="/admin/uploads/delete/HOMEFLOORICO.do";
                                            config.key=response.timeStr;
                                            initPreviewConfig[index]=config;
                                            $("#picUrl").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            $(".btn-default").attr("disabled",false);
                                        }).on("filepredelete", function(jqXHR) {
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
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>宣传图片：</label>
                            </div>
                            <div class="col-sm-9">
                                    <%--图片上传控件--%>
                                <script type="text/javascript">
                                    $(function(){
                                        var initPreview = new Array();//展示元素
                                        var initPreviewConfig = new Array();//展示设置
                                        //初始化图片上传组件
                                        $("#picUrl1").fileinput({
                                            uploadUrl: "/admin/uploads/uploadFile/HOMEFLOORPROPAGANDA.do",
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
                                            autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                            overwriteInitial: false,//不覆盖已存在的图片
                                            browseClass:"btn btn-primary", //按钮样式
                                            // layoutTemplates:{
                                            //     actionUpload:''    //设置为空可去掉上传按钮
                                            // },
                                            maxFileCount: 10  //上传的个数
                                        }).on("fileuploaded", function (event, data) {
                                            var response = data.response;
                                            //添加url到隐藏域
                                            var html='<input name="imgurl1" type="hidden" id="'+response.timeStr+'" value="'+response.url+','+response.fileName+','+response.timeStr+'">';
                                            $('#imgDiv1').html($('#imgDiv1').html()+html);
                                            //上传完成回调
                                            var index=0;
                                            if(initPreview.length>0 ){
                                                index=initPreview.length;
                                            }
                                            initPreview[index]  = response.url;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/HOMEFLOORPROPAGANDA.do";
                                            config.key=response.timeStr;
                                            initPreviewConfig[index]=config;
                                            $("#picUrl1").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            $(".btn-default").attr("disabled",false);
                                        }).on("filepredelete", function(jqXHR) {
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
                                <input  id="picUrl1" name="file" type="file" class="file-loading" accept="image/*" multiple>
                                <div id="imgDiv1">

                                </div>
                                    <%--图片上传控件结束--%>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>商品列表：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="commodityTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col">商品名称</th>
                                        <th scope="col" width="140">排序</th>
                                        <th scope="col" width="80">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" onclick="showCommodityDialog();" title="添加" class="btn btn-default">
                                    <i class="fa fa-plus-circle"></i>添加商品
                                </button>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>分类列表：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="categoryTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col">分类名称</th>
                                        <th scope="col" width="140">排序</th>
                                        <th scope="col" width="80" class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" id="addCategoryBtn"  title="添加" class="btn btn-default">
                                    <i class="fa fa-plus-circle"></i>添加分类
                                </button>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>品牌列表：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="brandTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col">品牌名称</th>
                                        <th scope="col" width="140">排序</th>
                                        <th scope="col" width="80" class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" onclick="showBrandDialog()"  title="添加" class="btn btn-default">
                                    <i class="fa fa-plus-circle"></i>添加品牌
                                </button>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row" style="display: none;">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>广告列表：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="advertisementTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col">广告标题</th>
                                        <th scope="col" width="140">排序</th>
                                        <th scope="col" width="80" class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" onclick="showAdvertisementDialog()"  title="添加" class="btn btn-default">
                                    <i class="fa fa-plus-circle"></i>添加广告
                                </button>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea cssClass=" form-control validate[maxSize[255]]" path="remark" ></form:textarea>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button onclick="clearInput('form-control')" type="button" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
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
            var selectType="icon";
            $('#chooseImageBtn').click(function (e) {
                selectType="image";
                $('#imageDialog').modal().css({
                    width: 'auto'
                });
                e.preventDefault();
            });

            $('#chooseIconBtn').click(function (e) {
                selectType="icon";
                $('#imageDialog').modal().css({
                    width: 'auto'
                });
                e.preventDefault();
            });

            function chooseImage() {
                var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
                if (imagePath != null) {
                    if(selectType=="image"){
                        $("#previewImagePath").attr("src", ".."+PIC_PATH + imagePath);
                        $("#imagePath").val(imagePath);
                        $('#imageDialog').modal("hide");
                    }else{
                        var extension = imagePath.substring(imagePath.lastIndexOf('.'), imagePath.length).toLowerCase();
                        if(extension==".png"){
                            $('#previewIconPath').attr("src",".."+PIC_PATH+imagePath);
                            $('#iconPath').val(imagePath);
                            $('#imageDialog').modal("hide");
                        }else {
                            alert("请选择png格式的背景透明图片");
                        }
                    }
                } else {
                    alert("请选择图片");
                }
            }
        </script>
    </div>
</div>

<div class="modal fade" id="categoryDialog" tabindex="-1" role="dialog" aria-hidden="true">
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
                            <kendo:dataSource data="${categoryTree}">
                            </kendo:dataSource>
                        </kendo:treeView>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCategory();">确认</button>
            </div>
        </div>
    </div>
    <script type="application/javascript">
        var idIndex=0;
        var categoryId = 0;
        var categoryName = "";
        $('#addCategoryBtn').click(function (e) {
            $('#categoryDialog').modal();
            e.preventDefault();
        });

        function chooseCategory() {
            $('#categoryDialog').modal("hide");
            var newRow = "<tr id='category" + idIndex + "'><td><input type='hidden' name='categoryId' class='form-control' value='"+categoryId+"'/>"+categoryName+"</td><td><input type='text' name='categoryOrder' class='form-control validate[required,custom[integer]]' value='"+idIndex+"'/></td><td class='text-center'><a type='button' title='删除' class='btn btn-default' href='javascript:removeCategory(" + idIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
            $("#categoryTable tr:last").after(newRow);
            idIndex++;
        }

        function onSelect(e) {
            var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
            categoryId = data.id;
            categoryName = data.text;
        }

        function removeCategory(indx) {
            $("#category" + indx).remove();
            idIndex--;
        }

    </script>
</div>


<div class="modal fade" id="commodityDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择商品</h4>
            </div>
            <div class="modal-body">
                    <jsp:include page="../commodity/chooseCommodities.jsp"/>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCommodity();">确认</button>
            </div>
        </div>
    </div>
    <script type="application/javascript">
        var idcIndex = 0;

        function showCommodityDialog() {
            $('#commodityDialog').modal();
        }

        function chooseCommodity() {
            var selectedCommodityIds=$("#commodityGrid input[type='checkbox'][name='selectedCommodityId']:checked");
            if(selectedCommodityIds!=null&&selectedCommodityIds.length>0){
                $.each(selectedCommodityIds,function(n,selectedBox) {
                    var gridData = $("#commodityGrid").data("kendoGrid").dataSource;
                    var selectedCommodityId=$(selectedBox).attr('value');
                    $.each(gridData.data(),function(i,dataItem){
                        if(dataItem.commodityId==selectedCommodityId){
                            var newRow = "<tr id='commodity" + idcIndex + "'><td><input type='hidden' name='commodityId' value='"+selectedCommodityId+"'/>"+dataItem.commodityName+"</td><td><input type='text' name='commodityOrder' class='form-control validate[required,custom[integer]]' value='"+idcIndex+"'/></td><td><a type='button' title='删除' class='btn btn-default' href='javascript:removeCommodity(" + idcIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
                            $("#commodityTable tr:last").after(newRow);
                            idcIndex++;
                            return ;
                        }
                    });
                });
            }else{
                alert("请选择商品");
                return ;
            }
            clearCheck();
            $('#commodityDialog').modal("hide");
        }
        function removeCommodity(indx) {
            $("#commodity" + indx).remove();
            idcIndex--;
        }
    </script>
</div>

<div class="modal fade" id="brandDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择品牌</h4>
            </div>
            <div class="modal-body">
                <jsp:include page="../commodity/chooseBrand.jsp"/>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseBrand();">确认</button>
            </div>
        </div>
    </div>
    <script type="application/javascript">
        var idcIndex = 0;

        function showBrandDialog() {
            $('#brandDialog').modal();
        }

        function chooseBrand() {
            var selectedBrandIds=$("#brandGrid input[type='checkbox'][name='selectedBrandId']:checked");
            if(selectedBrandIds!=null&&selectedBrandIds.length>0){
                $.each(selectedBrandIds,function(n,selectedBox) {
                    var gridData = $("#brandGrid").data("kendoGrid").dataSource;
                    var selectedBrandId=$(selectedBox).attr('value');
                    $.each(gridData.data(),function(i,dataItem){
                        if(dataItem.brandId==selectedBrandId){
                            var newRow = "<tr id='brand" + idcIndex + "'><td><input type='hidden' name='brandId' value='"+selectedBrandId+"'/>"+dataItem.brandName+"</td><td><input type='text' name='brandOrder' class='form-control validate[required,custom[integer]]' value='"+idcIndex+"'/></td><td><a type='button' title='删除' class='btn btn-default' href='javascript:removeBrand(" + idcIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
                            $("#brandTable tr:last").after(newRow);
                            idcIndex++;
                            return ;
                        }
                    });
                });
            }else{
                alert("请选择品牌");
                return ;
            }
            clearCheck();
            $('#brandDialog').modal("hide");
        }
        function removeBrand(indx) {
            $("#brand" + indx).remove();
            idcIndex--;
        }
    </script>
</div>

<div class="modal fade" id="showAdvertisementDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择广告</h4>
            </div>
            <div class="modal-body">
                <jsp:include page="../commodity/chooseAdvertment.jsp"/>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseAdvertisement();">确认</button>
            </div>
        </div>
    </div>
    <script type="application/javascript">
        var idcIndex = 0;

        function showAdvertisementDialog() {
            $('#showAdvertisementDialog').modal();
        }

        function chooseAdvertisement() {
            var selectedAdvertIds=$("#advertGrid input[type='checkbox'][name='selectedAdvertId']:checked");
            if(selectedAdvertIds!=null&&selectedAdvertIds.length>0){
                $.each(selectedAdvertIds,function(n,selectedBox) {
                    var gridData = $("#advertGrid").data("kendoGrid").dataSource;
                    var selectedAdvertId=$(selectedBox).attr('value');
                    $.each(gridData.data(),function(i,dataItem){
                        if(dataItem.advertId==selectedAdvertId){
                            var newRow = "<tr id='advertisement" + idcIndex + "'><td><input type='hidden' name='advertId' value='"+selectedAdvertId+"'/>"+dataItem.advertTitle+"</td><td><input type='text' name='advertOrder' class='form-control validate[required,custom[integer]]' value='"+idcIndex+"'/></td><td><a type='button' title='删除' class='btn btn-default' href='javascript:removeAdvertisement(" + idcIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
                            $("#advertisementTable tr:last").after(newRow);
                            idcIndex++;
                            return ;
                        }
                    });
                });
            }else{
                alert("请选择广告");
                return ;
            }
            clearCheck();
            $('#showAdvertisementDialog').modal("hide");
        }
        function removeAdvertisement(indx) {
            $("#advertisement" + indx).remove();
            idcIndex--;
        }
    </script>
</div>

</body>
</html>
