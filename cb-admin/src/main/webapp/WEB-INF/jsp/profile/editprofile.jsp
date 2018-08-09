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

    <title>参数配置</title>
    <script charset="utf-8" src="../editor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="../editor/lang/zh_CN.js"></script>

    <script type="text/javascript">
        function getprofileName(state){
            switch (state){
                case "ANDROID_VERSION_CODE":{
                    return "安卓版本编码";
                }
                case "ANDROID_VERSION_NAME":{
                    return "安卓版本名称";
                }
                case "ANDROID_APP_NAME":{
                    return "安卓APP名称";
                }
                case "ANDROID_URL":{
                    return "安卓APP下载地址";
                }
                case "ANDROID_DESCRIPTION":{
                    return "安卓APP更新描述";
                }
                case "GIVE_THE_THUMBS_UP":{
                    return "点赞推荐人及所有上级加5%的授信额度";
                }
                case "GIVE_THE_THUMBS_UP":{
                    return "点赞推荐人及所有上级加5%的授信额度";
                }
                case "LOAN_EXPECTED_RETURN_FIFTY":{
                    return "下单推荐人增加50%的贷款预期收益";
                }
                case "ANDROID_FORCE_UPGRADE":{
                    return "安卓APP是否强制更新";
                }

                case "SHARE_PATH":{
                    return "分享地址";
                }
                case "SHARE_TITLE":{
                    return "分享标题";
                }
                case "SHARE_ICON":{
                    return "分享图标";
                }
                case "SHARE_DESCRIPTION":{
                    return "分享描述";
                }
                case "SHARE_SHORTMESSAGE_CONTENT":{
                    return "分享短信内容";
                }
            }
            return state;
        }
        $(function () {
            $('#profileName').val(getprofileName('${profile.profileName}'))
        });
        $(document).ready(function () {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
            $('#serNo').val("")
        });
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
                    <li><a href="#">保单管理 </a></li>
                    <li><a href="#">事项组</a></li>
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
                    <h2>事项组</h2>
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
                    <legend>事项组</legend>
                    <form:form id="validateSubmitForm" action="updateProfile.do" cssClass="form-horizontal" method="post"
                               commandName="profile">
                        <form:hidden path="fileId"/>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>参数名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <input style="padding: 5px;" id="profileName" readonly="readonly" cssClass="form-control validate[required,minSize[1]]"/>
                                <form:hidden  readonly="true" path="profileName" cssClass="form-control validate[required,minSize[1]]"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>是否图片：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <div class="inline-labels">
                                    <form:radiobutton onclick="radiaCheck(1)" path="isPicture" value="1"/>是
                                    <form:radiobutton onclick="radiaCheck(0)" path="isPicture" value="0"/>否
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            function radiaCheck(value){
                                if(value==1){
                                    $('#fileValeDiv').hide();
                                    $('#fileValeImg').show();
                                    $('#fileValue').attr("readonly",true);
                                }else{
                                    $('#fileValeImg').hide();
                                    $('#fileValeDiv').show();
                                    $('#fileValue').attr("readonly",false);
                                }
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
                            /**
                             *上传图片
                             */
                            function onchangeImg(imgId){
                                var formData = new FormData();
                                formData.append("file", $('#upload')[0].files[0]);
                                $.ajax({
                                    url: "/admin/uploads/upload/INSURANCEPRODUCT.do",
                                    type: 'POST',
                                    cache: false,
                                    data: formData,
                                    processData: false,
                                    contentType: false,
                                    success: function (result) {
                                        window.editor.html(result.url)
                                    },
                                    error: function (err) {
                                    }
                                });
                            }
                            $(function () {
                                $("#headPic").click(function () {
                                    $("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
                                    $("#upload").on("change", function () {
                                        var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
                                        if (objUrl) {
                                            $("#headPic").attr("src", objUrl); //将图片路径存入src中，显示出图片
                                        }
                                    });
                                });
                            });
                            $(document).ready(function () {
                                KindEditor.ready(function (K) {
                                    window.editor = K.create('#editorContent', {
                                        uploadJson: '../upload/fileUpload.do',
                                        fileManagerJson: '../upload/fileManager.do',
                                        allowFileManager: true,
                                        afterCreate : function() {
                                            this.sync();
                                        },
                                        afterBlur:function(){
                                            this.sync();
                                        }
                                    });
                                });

                                var isPicture=${profile.isPicture};
                                if(isPicture==1){
                                    // $('#fileValeDiv').hide();
                                    // $('#fileValeImg').show();
                                    $('#fileValue').attr("readonly",true);
                                }
                            });
                        </script>
                        <div class="spacer-10"></div>
                        <div id="fileValeDiv" class="row">
                            <div class="col-sm-2">
                                <label>参数值：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-9">
                                <form:textarea  cssClass="form-control" id="editorContent" path="fileValue" cssStyle="height:500px;"></form:textarea>
                            </div>
                        </div>
                        <div id="fileValeImg" class="row" style="display: none;">
                            <div class="col-sm-2">
                                <label>参数值：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <img id="headPic" src="${profile.fileValue}" width="350px" height="350px"
                                     style="padding: 5px">
                                <input id="upload" onchange="onchangeImg('editorContent')" name="file" multiple="multiple" accept="image/*" type="file"
                                       style="display: none"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div  class="row">
                            <div class="col-sm-2">
                                <label>备注：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <form:textarea id="remarks"    path="remarks" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <button class="btn btn-default"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                                    <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
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
