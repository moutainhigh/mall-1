<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>新增专题</title>
    <script src="../ckeditor/ckeditor.js"></script>
    <script src="../ckeditor/lang/zh-cn.js"></script>
    <script type="application/javascript">
        $(document).ready(function () {
            $('#datepicker-publishDate, #datepicker-orderTime').datepicker({
                format: 'yyyy-mm-dd'
            });
            $('#datepicker-orderTime, #datepicker-orderTime').datepicker({
                format: 'yyyy-mm-dd'
            });
            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        if (null == $("#publishDate").val() || "" == $("#publishDate").val()) {
                            bootbox.alert("请选择发布日期!");
                            return false;
                        }
                        if (null == $("#orderTime").val() || "" == $("#orderTime").val()) {
                            bootbox.alert("请选择排序时间!");
                            return false;
                        }
                        return true;
                    }
                }
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
                        <li><a href="../console/dashboard.do">首页</a></li>
                        <li><a href="#">内容管理</a></li>
                        <li><a href="articles.do">文章专题</a></li>
                        <li class="active">新增专题</li>
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
                        <h2>新增专题</h2>
                    </div>
                    <div class="pull-right">
                        <a class="btn btn-default" href="articles.do">
                            <i class="fa fa-reply"></i>
                        </a>
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
                    <form:form id="validateSubmitForm" action="addSpecialSubject.do" method="post" commandName="subject" cssClass="form-horizontal">
                        <form:input path="subjectId" cssStyle="display: none"/>
                        <fieldset>
                            <legend>文章表单</legend>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>标题：</label>
                                </div>
                                <div class="col-sm-8">
                                    <form:input cssClass="form-control validate[required,minSize[2]]" path="subjectName" maxlength="255"/>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>简短标题：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:input cssClass="form-control  validate[required,minSize[2]]" path="shortName" maxlength="128"/>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>所属栏目：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:select cssClass="form-control" required="true" path="programa.programaId" aria-required="true">
                                        <c:forEach var="channel" items="${articleChannels}">
                                            <optgroup label="${channel.channelName}">
                                                <c:forEach var="programa" items="${programas}">
                                                    <c:if test="${programa.articleChannel.channelId==channel.channelId}">
                                                        <option value="${programa.programaId}">${programa.programaName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </optgroup>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>发布日期：</label>
                                </div>
                                <div class="col-sm-3">
                                    <kendo:datePicker id="publishDate" name="publishDate" value="${subject.publishDate}" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" culture="zh-CN"></kendo:datePicker>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span>排序时间：</label>
                                </div>
                                <div class="col-sm-3">
                                    <kendo:datePicker id="orderTime" name="orderTime" value="${subject.orderTime}" format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" culture="zh-CN"></kendo:datePicker>
                                </div>
                            </div>

                            <div class="spacer-10"></div>


                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span> 是否启用：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:checkbox path="enabled"/>
                                </div>
                                <div class="col-sm-2">
                                    <label><span class="asterisk">*</span> 是否推荐：</label>
                                </div>
                                <div class="col-sm-3">
                                    <form:checkbox path="recommend"/>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <label>备注：</label>
                                </div>
                                <div class="col-sm-8">
                                    <form:textarea cssClass="form-control" path="description"></form:textarea>
                                </div>
                            </div>
                            <div class="spacer-10"></div>
                            <hr>
                            <div class="spacer-10"></div>
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
