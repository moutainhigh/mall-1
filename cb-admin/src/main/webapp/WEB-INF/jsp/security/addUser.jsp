<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>新增用户</title>

    <script type="text/javascript">
        $(document).ready(function() {
            $.validationEngineLanguage.allRules.specialChar = {
                "regex": /[`~!@#$^&*()=|{}':;',\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/,
                "alertText": "包含特殊字符"
            }
            $("#roleIds").select2();
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });

        });


        /**
         * 手机验证
         * @param value
         * @returns {boolean}
         */
        function checkPhone(value) {
            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if(!myreg.test($("#phone").val()))
            {
                alert('请输入有效的手机号码！');
                $('#phone').val("");
                return false;
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
                    <li><a href="#">系统管理</a></li>
                    <li><a href="#">用户管理</a></li>
                    <li class="active"><a href="#">新增用户</a></li>
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
                    <h2>新增用户</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="users.do"><i class="fa fa-reply"></i></a>
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
                <form:form id="validateSubmitForm" action="addUser.do" cssClass="form-horizontal" method="post" commandName="user">

                    <fieldset>
                        <legend>新增用户</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>用户名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" cssClass="form-control validate[required,minSize[2],custom[specialChar]]" path="userName" maxlength="32"/>
                                <form:errors path="userName" cssClass="Validform_checktip"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>真实姓名：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input id="realName" onkeyup="this.value=this.value.replace(/[^\u4E00-\u9FA5]/g,'')" cssClass="form-control validate[required,minSize[2],custom[specialChar]]" path="realName" maxlength="32"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">

                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>密码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:password onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')"  cssClass="form-control validate[required,minSize[6]]" path="password" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>确认密码：</label>
                            </div>
                            <div class="col-sm-3">
                                <input type="password" onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')"  class="form-control validate[equals[password]]"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">

                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>手机号码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input  id="phone" cssClass="form-control validate[required,custom[checkPhone]]" path="mobile" maxlength="11"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>邮箱：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]/g,'');"   cssClass="form-control validate[required,custom[email]]" path="email" maxlength="64"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>职务：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" cssClass="form-control validate[required,minSize[2]]" path="position" maxlength="64"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>性别：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:radiobutton path="sex" value="true"/><span></span>男
                                <form:radiobutton path="sex" value="false"/><span></span>女
                            </div>
                        </div>

                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">

                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>角色：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:select cssClass="simpleselect validate[groupRequired[roleIds]]" id="roleIds" path="roldIds" items="${roles}" itemLabel="roleName" itemValue="roleId" multiple="false"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>备注：</label>
                            </div>
                            <div class="col-sm-8">
                                <form:textarea cssClass="form-control" path="remark" maxlength="255"></form:textarea>
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
    </div>
</div>

</body>
</html>
