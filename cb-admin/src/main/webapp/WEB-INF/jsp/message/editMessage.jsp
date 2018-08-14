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
                window.editor = K.create('#editorContent1', {
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
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
            $('#serNo').val("");
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
                    <li><a href="#">系统设置 </a></li>
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
                        <div class="spacer-10"></div>
                        <div id="fileValeDiv" class="row">
                            <div class="col-sm-2">
                                <label>推送标题：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <form:input id="pushTitle" path="pushTitle" cssClass="form-control validate[required,minSize[1]]"/>
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
                                <form:textarea id="editorContent1" path="messageDigest" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>
                        <div class="spacer-10"></div>
                        <div  class="row">
                            <div class="col-sm-2">
                                <label>消息内容：<span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-3">
                                <form:textarea id="editorContent2" path="messageContent" cssClass="form-control"/>
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
