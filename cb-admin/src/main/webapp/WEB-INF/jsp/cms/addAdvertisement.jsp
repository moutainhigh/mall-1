<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>新增广告</title>
    <script src="../js/plugins/jquery-validation/dist/additional-methods.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
            });
        });

        function returns(){
            window.location.href = "advertisements.do";
        }
    </script>

</head>
<body>


<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->


<div id="sidebar-sec" class="sidebar">

    <div class="sidebar-sec-top"></div>


    <div class="sidebar-module">
        <form class="input-group">
            <input type="text" name="" class="form-control" placeholder="Type A head..." id="typeahead-sidebar-search"/>

            <div class="input-group-btn">
                <!-- can NOT be used with typeahead
                <a href="#" class="clear-input"><i class="fa fa-times-circle"></i></a>
                -->
                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
            </div>
        </form>
    </div>
    <!-- End .sidebar-module -->

    <div class="sidebar-line"><!-- A seperator line --></div>


    <ul class="ext-tabs-sidebar">
        <li class="active">
            <a href="#sidebar-tab-3"><i class="fa fa-group"></i> Users</a>
        </li>
        <li>
            <a href="#sidebar-tab-4"><i class="fa fa-check"></i> ToDo</a>
        </li>
    </ul>
    <!-- End .ext-tabs-sidebar -->
    <div class="tab-content">
        <div id="sidebar-tab-3" class="tab-pane active">


            <div class="sidebar-module">
                <ul class="mini-list">
                    <li>
                        <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Steven Watson</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-2.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Maris Bradley</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-3.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Wyatt Brooke</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-4.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Elly Martel</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-5.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Martin Gardenar</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-6.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Debra Hopper</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                    <li>
                        <img src="../images/users/user-7.jpg" alt="" class="avatar"/>
                        <ul>
                            <li><a href="#" class="bold">Nathan Rupertson</a></li>
                            <li><a href="#">dummyemail@mail.com</a></li>
                        </ul>
                    </li>
                </ul>
                <!-- End .mini-list -->
            </div>
            <!-- End .sidebar-module -->
        </div>
        <div id="sidebar-tab-4" class="tab-pane">

            <!-- ********** -->
            <!-- NEW MODULE -->
            <!-- ********** -->

            <div class="sidebar-module">
                <div class="sidebar-todo">
                    <div class="sidebar-todo-day">
                        <h5>Due Today</h5>
                        <ul>
                            <li>
                                <label class="line-through">Start project X <input type="checkbox" name="" checked/><span></span></label>
                            </li>
                            <li>
                                <label>Email the invoice<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Call client T<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Take a coffe break<input type="checkbox" name=""/><span></span></label>
                            </li>
                        </ul>
                    </div>
                    <!-- End .sidebar-todo-day -->
                    <div class="sidebar-todo-day">
                        <h5>Due Tomorrow <span class="indicator-pill">32</span></h5>
                        <ul>
                            <li>
                                <label>Meeting with client T<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Meeting with client X<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label class="line-through">Buy new apple<input type="checkbox" name="" checked/><span></span></label>
                            </li>
                        </ul>
                    </div>
                    <!-- End .sidebar-todo-day -->
                    <div class="sidebar-todo-day">
                        <h5>Due Next Week</h5>
                        <ul>
                            <li>
                                <label>Start project T<input type="checkbox" name=""/><span></span></label>
                            </li>
                            <li>
                                <label>Buy new headphones<input type="checkbox" name=""/><span></span></label>
                            </li>
                        </ul>
                    </div>
                    <!-- End .sidebar-todo-day -->
                </div>
                <!-- End .sidebar-todo -->
            </div>
            <!-- End .sidebar-module -->
        </div>
    </div>
    <!-- End .tab-content -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

    <div class="sidebar-module">
        <div class="circular-stats">
            <div class="circular-stats-inner">
                <div class="circular-stats-data">
                    <strong>2779</strong>
                    <span>+ 31%</span>
                </div>
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                       value="31" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
            </div>
        </div>
        <!-- End .circular-stats -->
        <div class="circular-stats-infobox">
            <strong>This day</strong>
            <span>Lorem ipsum</span>
            <a href="#" class="btn btn-default">View</a>
        </div>
        <!-- End .circular-stats-infobox -->
        <div class="spacer-20"></div>
        <div class="circular-stats">
            <div class="circular-stats-inner">
                <div class="circular-stats-data">
                    <strong>12899</strong>
                    <span>+ 77%</span>
                </div>
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                       value="77" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
            </div>
        </div>
        <!-- End .circular-stats -->
        <div class="circular-stats-infobox">
            <strong>This month</strong>
            <span>Lorem ipsum</span>
            <a href="#" class="btn btn-default">View</a>
        </div>
        <!-- End .circular-stats-infobox -->
        <div class="spacer-20"></div>
        <div class="circular-stats">
            <div class="circular-stats-inner">
                <div class="circular-stats-data">
                    <strong>82229</strong>
                    <span>+ 89%</span>
                </div>
                <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                       value="89" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
            </div>
        </div>
        <!-- End .circular-stats -->
        <div class="circular-stats-infobox">
            <strong>This year</strong>
            <span>Lorem ipsum</span>
            <a href="#" class="btn btn-default">View</a>
        </div>
        <!-- End .circular-stats-infobox -->
    </div>
    <!-- End .sidebar-module -->
</div>
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
                    <li><a href="#">首页</a></li>
                    <li><a href="#">内容管理</a></li>
                    <li><a href="#">广告管理</a></li>
                    <li class="active">新增广告</li>
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
                    <h2>新增广告</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="advertisements.do"><i class="fa fa-reply"></i></a>
                </div>
            </div>
            <!-- End .inner-padding -->
        </header>
        <!-- End #header-sec -->

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
                <form:form id="validateSubmitForm" cssClass="form-horizontal" data-asf-expireafter="1" data-asf-time="10" action="addAdvertisement.do" method="post" commandName="advertisement">

                    <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
                    <fieldset>
                        <legend>新增广告</legend>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>标题：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input  cssClass="form-control validate[required,minSize[2]]"  path="advertTitle" maxlength="512"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 编码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="advertCode" maxlength="64"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 广告类型：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select path="advertisementType" cssClass="form-control simpleselect">
                                    <form:options items="${advertisementType}" itemLabel="name"/>
                                </form:select>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 客户端类型：</label>
                            </div>
                            <div class="col-sm-3">
                                <input type="checkbox" name="clientTypesTemporary" value="PC" cssClass="form-control validate[minCheckbox[1]]"/>网站
                                <input type="checkbox" name="clientTypesTemporary" value="PAD" cssClass="form-control validate[minCheckbox[1]]"/>平板
                                <input type="checkbox" name="clientTypesTemporary" value="MOBILE" cssClass="form-control validate[minCheckbox[1]]"/>手机
                            </div>
                        </div>

                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 广告位：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select path="advertisementPlace" cssClass="form-control simpleselect">
                                    <form:options items="${advertisementPlace}" itemLabel="name"/>
                                </form:select>
                            </div>
                            <div class="col-sm-2">
                                <label> 视频路径：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control" path="videoPath" maxlength="512"/>
                            </div>
                        </div>

                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 广告URL类型：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select path="advertisementURLType" cssClass="form-control simpleselect">
                                    <form:options items="${advertisementURLType}" itemLabel="name"/>
                                </form:select>
                            </div>
                            <div class="col-sm-2">
                                <label> 广告URL：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control" path="advertURL" maxlength="5121"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 是否启用：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:radiobutton path="enabled" value="1"/>启用
                                <form:radiobutton path="enabled" value="0"/>停用
                            </div>
                        </div>

                        <div class="spacer-20"></div>
                        <hr>
                        <div class="spacer-20"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 广告图片：</label>
                            </div>
                            <div class="col-sm-3">
                                <a id="chooseImageBtn" class="btn btn-default">选择</a>
                                <form:hidden path="picPath" id="imagePath"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>图片预览：</label>
                            </div>
                            <div class="col-sm-8">
                                <img id="previewImagePath" src="" style="max-width:600px ">
                            </div>
                        </div>



                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>内容：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea  cssClass="form-control validate[maxSize[5000]]" path="content"></form:textarea>
                            </div>
                        </div>
                        <div class="spacer-10"></div>

                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea cssClass="form-control validate[maxSize[5000]]" path="remark"></form:textarea>
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
            <!-- End .inner-padding -->
        </div>
        <!-- End .window -->


        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
    <!-- End #content -->
</div>
<!-- End #main -->

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
                $('#chooseImageBtn').click(function (e) {
                    $('#imageDialog').modal().css({
                        width: 'auto'
                    });
                    e.preventDefault();
                });

                function chooseImage() {
                    var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
                    if (imagePath != null) {
                        $("#previewImagePath").attr("src", ".."+PIC_PATH + imagePath);
                        $("#imagePath").val(imagePath);
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
