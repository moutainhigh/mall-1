<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>修改消息</title>
    <script charset="utf-8" src="../editor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="../editor/lang/zh_CN.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            KindEditor.ready(function (K) {
                window.editor = K.create('#editorContent2', {
                    uploadJson: '../upload/fileUpload.do',
                    fileManagerJson: '../upload/fileManager.do',
                    allowFileManager: true,
                    afterCreate: function () {
                        this.sync();
                    },
                    afterBlur: function () {
                        this.sync();
                    }
                });
            });

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if (valid) {
                        var defaultPicPath = $('input[name="digestPic"]');
                        if(defaultPicPath.size() > 1){
                            bootbox.alert("只能选择一张摘要图片!");
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
            });
        });

        function resetForm(){
            $("#pushTitle").val("");
            $("#pushDigest").text("");
            $("#messageContent").text("");
            
        }
    </script>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->

<!-- End #sidebar-sec -->

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
                    <li><a href="#">首页 </a></li>
                    <li><a href="#">运营管理 </a></li>
                    <li><a href="#">消息中心配置</a></li>
                    <c:choose>
                        <c:when test="message.messageId > 0">
                            <li><a href="#">消息修改</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="#">消息新增</a></li>
                        </c:otherwise>
                    </c:choose>


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
                    <c:choose>
                        <c:when test="message.messageId > 0">
                            <h2>消息修改</h2>
                        </c:when>
                        <c:otherwise>
                            <h2>消息新增</h2>
                        </c:otherwise>
                    </c:choose>
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
                <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
                <fieldset>
                    <c:choose>
                        <c:when test="message.messageId > 0">
                            <legend>消息修改</legend>
                        </c:when>
                        <c:otherwise>
                            <legend>消息新增</legend>
                        </c:otherwise>
                    </c:choose>
                    <form:form id="validateSubmitForm" action="editMessage.do" cssClass="form-horizontal" method="post"
                               commandName="message">
                        <form:hidden path="messageId"/>
                        <form:hidden path="createTime"/>
                        <div class="spacer-10"></div>
                        <div id="fileValeDiv" class="row">
                            <div class="col-sm-2">
                                <label>推送标题：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <form:input id="pushTitle" path="pushTitle" cssClass="form-control validate[required,minSize[2]]" maxlength="100"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>
                        <div  class="row">
                            <div class="col-sm-2">
                                <label>消息摘要：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <form:input id="messageDigest" path="messageDigest" cssClass="form-control validate[required,minSize[2]]" maxlength="200"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>摘要图片：</label>
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
                                        $("#fileInput").fileinput({
                                            uploadUrl: "/admin/uploads/uploadFile/MESSAGEDIGEST.do",
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
                                            // },
                                            // layoutTemplates:{
                                            maxFileSize : 2000,
                                            allowedFileExtensions: ["jpg", "png", "gif"],
                                            autoReplace : true,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                                            overwriteInitial: false,//是否覆盖已存在的图片
                                            browseClass:"btn btn-primary", //按钮样式,
                                            //     actionUpload:''    //设置为空可去掉上传按钮
                                            maxFileCount: 1  //允许同时上传的最大文件个数
                                        }).on("fileuploaded", function (event, data) {
                                            var response = data.response;
                                            //添加url到隐藏域
                                            var html='<input name="digestPic" type="hidden" id="'+response.timeStr+'" value="'+response.url+','+response.fileName+','+response.timeStr+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                            //上传完成回调
                                            var index=0;
                                            if(initPreview.length>0 ){
                                                index=initPreview.length;
                                            }
                                            initPreview[index]  = response.url;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/MESSAGEDIGEST.do";
                                            config.key=response.timeStr;
                                            initPreviewConfig[index]=config;
                                            $("#fileInput").fileinput('refresh', {
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
                                            for (var i=0;i<initPreview.length;i++){
                                                if(initPreview[i].indexOf(id) != -1){
                                                    initPreview.splice(i);
                                                    initPreviewConfig.splice(i);
                                                }
                                            }
                                        }).on('filebatchselected', function (event, files) {//选中文件事件
                                            $(".kv-file-upload").click();
                                        });
                                        //加载图片
                                        var a='${listAttachment}';
                                        var json=eval('(' + a + ')');
                                        for(var i=0,l=json.length;i<l;i++){
                                            initPreview[i]  = json[i].filePath;
                                            var config = new Object();
                                            config.caption = "";
                                            config.url="/admin/uploads/delete/MESSAGEDIGEST.do";
                                            config.key=json[i].inputId;
                                            initPreviewConfig[i]=config;
                                            $("#fileInput").fileinput('refresh', {
                                                initialPreview: initPreview,
                                                initialPreviewConfig: initPreviewConfig,
                                                initialPreviewAsData: true
                                            });
                                            var html='<input name="digestPic" type="hidden" id="'+json[i].inputId+'" value="'+json[i].filePath+','+json[i].fileName+','+json[i].inputId+'">';
                                            $('#imgDiv').html($('#imgDiv').html()+html);
                                        }
                                    });
                                </script>
                                <input id="fileInput" name="file" type="file" class="file-loading" accept="image/*" data-min-file-count="1">
                                <div id="imgDiv">
                                </div>
                            <%--图片上传控件结束--%>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>

                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>
                        <div  class="row">
                            <div class="col-sm-2">
                                <label>消息内容：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <form:textarea id="editorContent2" path="messageContent" cssClass="form-control validate[required,maxSize[40]]" maxlength="40"/>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button class="btn btn-default"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button type="reset" class="btn btn-default" onclick="resetForm()"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </fieldset>
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


</body>
</html>
