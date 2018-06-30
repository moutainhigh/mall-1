<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>修改频道</title>
    <script type="application/javascript">
        $(document).ready(function () {
            $("#channelCategoryIds").select2();
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        var flag=false;
                        $("input[type='checkbox'][name='channelTypes']:checked").each(function(){
                            if($(this).is(':checked')){
                                flag=true;
                            }
                        });
                        var selItem = $("#selectChannelStyle").val();
                        var channelVideoURL = $("#channelVideoURL").val();
                        if( "BANNER" ==  selItem){
                            if( null == channelVideoURL || "" == channelVideoURL ){
                                bootbox.alert("当频道前台菜单风格为'横幅显示'时,必须输入视频URL!");
                                return false;
                            }
                        }
                        var channelURL = $("#channelURL").val();
                        if( "SIMPLE" ==  selItem){
                            if( null == channelURL || "" == channelURL ){
                                bootbox.alert("当频道前台菜单风格为'简单显示'时,必须输入跳转链接URL!");
                                return false;
                            }
                        }

                        if(!flag){
                            bootbox.alert("请选择频道类型!");
                            return false;
                        }
                        if (null == $("#operaImgPath").val() || "" == $("#operaImgPath").val()) {
                            bootbox.alert("请选择宣传图片!");
                            return false;
                        }

                        return true;
                    }
                }
            });

            displayChannelVideoURL();
            displayChannelURL();

            $("#selectChannelStyle").change(function(){
                displayChannelVideoURL();
                displayChannelURL();
            });



        });

        function displayChannelVideoURL(){
            var selItem = $("#selectChannelStyle").val();
            if( "BANNER" ==  selItem){
                $("#channelVideoURLDiv").show();
            }else{
                $("#channelVideoURLDiv").hide();
            }
        }

        function displayChannelURL(){
            var selItem = $("#selectChannelStyle").val();
            if( "SIMPLE" ==  selItem){
                $("#channelURLDiv").show();
            }else{
                $("#channelURLDiv").hide();
            }
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
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">内容管理</a></li>
                    <li><a href="#">频道栏目</a></li>
                    <li class="active">修改频道</li>
                </ul>
                <!-- End .breadcrumb -->
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
                    <h2>修改频道</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="channelProgramas.do"><i class="fa fa-reply"></i></a>

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
                <form:form id="validateSubmitForm" action="editArticleChannel.do" cssClass="form-horizontal"  method="post" commandName="channel">
                    <form:hidden path="channelId"/>
                    <fieldset>
                        <legend>频道表单</legend>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>频道名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]"  path="channelName" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label>热门提示名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control"  path="tipName" maxlength="32"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>频道关键字：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="channelKey" maxlength="128"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>频道编码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2],custom[onlyLetterNumber]]" data-errormessage-custom-error="只可输入英文或数字"   path="channelCode" maxlength="32"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>频道位置：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select path="channelPosition" cssClass="form-control simpleselect">
                                    <form:option value="HEARDER">头部</form:option>
                                    <form:option value="BANNER">横幅</form:option>
                                    <form:option value="FOOTER">尾部</form:option>
                                </form:select>
                            </div>

                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 前台菜单风格：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select id="selectChannelStyle" path="channelStyle" cssClass="form-control simpleselect">
                                    <form:options items="${channelStyle}" itemLabel="name"></form:options>
                                </form:select>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row" id="channelVideoURLDiv">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>视频URL：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input  id="channelVideoURL" path="channelVideoURL" cssClass="form-control" maxlength="512" title="当频道前台菜单风格为'横幅显示'时,必须输入视频URL" ></form:input>
                            </div>

                        </div>
                        <div class="spacer-10"></div>
                        <div class="row" id="channelURLDiv">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>跳转链接URL：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input  id="channelURL" path="channelURL" cssClass="form-control" maxlength="512" title="当频道前台菜单风格为'横幅显示'时,必须输入视频URL" ></form:input>
                            </div>

                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>SEO关键字：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input cssClass="form-control validate[required,minSize[2]]"  path="seoKey" maxlength="128"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>SEO标题：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:input cssClass="form-control validate[required,minSize[2]]"  path="seoTitle" maxlength="255"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>SEO描述：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea cssClass="form-control validate[maxSize[512]]" path="seoDescription"></form:textarea>
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>频道描述：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea cssClass="form-control validate[required]" path="description"></form:textarea>
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>排序：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,custom[integer],min[0]]" path="sortOrder" maxlength="4"/>
                            </div>

                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 是否启用：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:checkbox path="enabled"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 频道类型：</label>
                            </div>
                            <div class="col-sm-3">
                                <input type="checkbox" name="channelTypes" value="WEB_SITE" cssClass="form-control validate[minCheckbox[1]]" <c:if test="${fn:contains(channel.articleChannelTypes, 'WEB_SITE')}">checked="checked" </c:if>/>网站
                                <input type="checkbox" name="channelTypes" value="PAD" cssClass="form-control validate[minCheckbox[1]]" <c:if test="${fn:contains(channel.articleChannelTypes, 'PAD')}">checked="checked" </c:if>/>平板
                                <input type="checkbox" name="channelTypes" value="MOBILE" cssClass="form-control validate[minCheckbox[1]]" <c:if test="${fn:contains(channel.articleChannelTypes, 'MOBILE')}">checked="checked" </c:if>/>手机
                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 宣传图片：</label>
                            </div>
                            <div class="col-sm-3">
                                <a id="chooseImageBtn" class="btn btn-default">选择</a>
                                <form:hidden path="operaImgPath" id="operaImgPath"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 图片预览：</label>
                            </div>
                            <div class="col-sm-8">
                                <img id="previewImagePath" src="..${PIC_PATH}${channel.operaImgPath}" style="max-width:600px ">
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
                    $("#operaImgPath").val(imagePath);
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
