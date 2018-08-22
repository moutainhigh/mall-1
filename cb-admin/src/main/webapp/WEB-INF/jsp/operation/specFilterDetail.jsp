<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>过滤器详情</title>
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
                    <li><a href="#">运营管理</a></li>
                    <li><a href="#">规格过滤器配置</a></li>
                    <li class="active">过滤器详情</li>
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
                    <h2>过滤器详情</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default " href="specFilters.do"><i class="fa fa-reply"></i></a>
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

                    <fieldset>
                        <legend>过滤器详情</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>过滤器名称：</label>
                            </div>
                            <div class="col-sm-3" style="line-height: 30px;">
                                ${filter.filterName}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>排序：</label>
                            </div>
                            <div class="col-sm-3" style="line-height: 30px;">
                                ${filter.sortOrder}
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>规格值：</label>
                            </div>
                            <div class="col-sm-8">
                                <table id="filterTable" class="table table-bordered table-striped">
                                    <thead id="attribute-table-th">
                                    <tr>
                                        <td scope="col">规格值</td>
                                        <td scope="col" width="110">排序</td>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach var="item" items="${filter.filterItems}">
                                        <tr id='filterItem${item.itemId}'>
                                            <td><input type='text' name='itemName' value="${item.itemValue}" required="true" class='form-control' readonly="readonly"/></td>
                                            <td><input type='text' name='itemSortOrder' value='${item.sortOrder}' class='form-control' readonly="readonly"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-sm-2">

                            </div>
                        </div>
                        <div class="spacer-30"></div>
                        <hr>
                        <div class="spacer-30"></div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="btn-group pull-right">
                                    <a class="btn btn-default pull-right" href="specFilters.do">返回</a>
                                </div>
                            </div>
                        </div>
                    </fieldset>
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
