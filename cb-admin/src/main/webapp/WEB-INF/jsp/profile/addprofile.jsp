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

    <title>ANDROID导入</title>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
            $('#serNo').val("")
        });
    </script>
</head>
<body>
<div id="mainload" style="width:5000px;height:5000px;filter:alpha(opacity=5);opacity:.05;position:absolute;top:0;left:0;z-index:98;background:#000;display: none"></div>
<div id="loadgif" style="position:absolute;top:40%;left:40%;z-index: 999;display: none">
    　　<img src="../images/git/loading.gif"  />
</div>
<jsp:include page="../layouts/left.jsp"/>
<div id="main" class="clearfix">
    <header id="header-main">
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页 </a></li>
                    <li><a href="#">系统配置</a></li>
                    <li><a href="#">ANDROID导入</a></li>
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
                    <h2>ANDROID导入</h2>
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
                <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
                <fieldset>
                    <legend>事项组</legend>
                    <link rel="stylesheet" href="../js/qiniu/style/index.css">
                    <script src="https://cdn.bootcss.com/json3/3.3.2/json3.min.js"></script>
                    <script type="text/javascript" src='../js/qiniu/js/plupload.full.min.js'></script>
                    <script type="text/javascript" src='../js/qiniu/js/qiniu.js'></script>
                    <script type="text/javascript" src='../js/qiniu/component/widget.js'></script>
                    <script type="text/javascript" src='../js/qiniu/common/common.js'></script>
                    <script type="text/javascript" src='../js/qiniu/component/ui.js'></script>
                    <script type="text/javascript" src='../js/qiniu/scripts/uploadWithOthers.js'></script>
                    <script type="text/javascript" src='../js/qiniu/scripts/uploadWithForm.js'></script>
                    <script type="text/javascript" src='../js/qiniu/scripts/uploadWithSDK.js'></script>
                    <script type="text/javascript" src='../js/qiniu/main.js'></script>
                    <script>
                        function uploadQiniuFile() {
                            uploadWithSDK(token, putExtra, config, domain);
                        }
                    </script>

                    <div class="mainContainer" style="margin-top: -100px">
                        <form method="post" enctype="multipart/form-data" id="form" action="upload">
                            <div id="box2">
                                <button class="select-button">选择文件</button>
                                <input class="file-input" type="file" id="selectFile" onchange="uploadQiniuFile()"/>
                            </div>
                        </form>
                        <div class="nav-box" style="margin-top:30px">
                            <ul id="myTab" class="nav nav-tabs">
                                <div id="process" class="tab-content">
                                    <div class="tab-pane fade in active" id="h5">
                                        <table class="table table-striped table-hover text-left" style="margin-top:30px">
                                            <thead>
                                            <tr>
                                                <th class="col-md-4">Filename</th>
                                                <th class="col-md-2">Size</th>
                                                <th class="col-md-6">Detail</th>
                                            </tr>
                                            </thead>
                                            <tbody id="fsUploadProgress">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <form:form id="validateSubmitForm" action="addProfile.do"
                               cssClass="form-horizontal" method="post">
                    <div class="spacer-30"></div>
                    <div class="spacer-30"></div>
                    <div class="spacer-30"></div>
                    <div class="spacer-30"></div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label><span class="asterisk">*</span>安卓版本编号：</label>
                        </div>
                        <div class="col-sm-3">
                            <input   name="ANDROID_VERSION_CODE" id="ANDROID_VERSION_CODE" value=""
                                   cssClass="form-control validate[required,minSize[1]]"
                                   style="width: 600px;padding: 5px;"/>
                        </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label><span class="asterisk">*</span>安卓版本名称：</label>
                        </div>
                        <div class="col-sm-3">
                            <input  name="ANDROID_VERSION_NAME" id="ANDROID_VERSION_NAME" value=""
                                   cssClass="form-control validate[required,minSize[1]]"
                                   style="width: 600px;padding: 5px;"/>
                        </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label><span class="asterisk">*</span>安卓名称：</label>
                        </div>
                        <div class="col-sm-3">
                            <input name="ANDROID_APP_NAME" value=""
                                   cssClass="form-control validate[required,minSize[1]]"
                                   style="width: 600px;padding: 5px;"/>
                        </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label><span class="asterisk">*</span>安卓URL：</label>
                        </div>
                        <div class="col-sm-3">
                            <input readonly="readonly" name="ANDROID_URL" id="ANDROID_URL" value=""
                                   cssClass="form-control validate[required,minSize[1]]"
                                   style="width: 600px;padding: 5px;"/>
                        </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                        <div class="col-sm-2">
                            <label><span class="asterisk">*</span>安卓描述：</label>
                        </div>
                        <div class="col-sm-3">
                                <textarea rows="3" cols="100" name="ANDROID_DESCRIPTION"
                                          cssClass="form-control"
                                         ></textarea>
                        </div>
                    </div>

                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="btn-group pull-right">
                                <button class="btn btn-default"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;
                                </button>
                                <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;
                                </button>
                            </div>
                        </div>
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
