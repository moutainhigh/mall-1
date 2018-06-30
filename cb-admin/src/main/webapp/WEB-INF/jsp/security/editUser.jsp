<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>编辑用户</title>

    <script type="text/javascript">
        $(document).ready(function () {

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true
            });
        });

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
                    <li><a href="#">系统管理</a></li>
                    <li><a href="#">用户管理</a></li>
                    <li class="active"><a href="#">编辑用户</a></li>
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
                    <h2>编辑用户</h2>
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
                <form:form id="validateSubmitForm" action="editUser.do" cssClass="form-horizontal" method="post" commandName="user">
                    <form:hidden path="userId"/>

                    <fieldset>
                        <legend>编辑用户</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>用户名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="userName" maxlength="32"/>
                                <form:errors path="userName" cssClass="Validform_checktip"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>真实姓名：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,minSize[2]]" path="realName" maxlength="32"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">

                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>手机号码：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,custom[phone]]" path="mobile" maxlength="11"/>
                            </div>
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>邮箱：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,custom[email]]" path="email" maxlength="64"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>职务：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control" path="position" maxlength="64"/>
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
                            <div class="col-sm-8">
                                <c:forEach items="${roles}" var="role">
                                    <c:set var="choose" value="false"></c:set>
                                    <c:forEach items="${user.roles}" var="vrole">
                                        <c:if test="${role.roleId == vrole.roleId}">
                                            <c:set var="choose" value="true"></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <input type="checkbox" name="roldIds" value="${role.roleId}" <c:if test="${choose}">checked</c:if>/>${role.roleName}
                                </c:forEach>
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
