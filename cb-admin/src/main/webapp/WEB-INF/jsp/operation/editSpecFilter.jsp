<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>编辑过滤器</title>
    <script type="text/javascript">
        $(document).ready(function () {

            $("#validateSubmitForm").validationEngine({
                autoHidePrompt: true, scroll: false, showOneMessage: true,
                onValidationComplete: function (form, valid) {
                    if(valid){
                        if ($('input[name="itemName"]').length==0) {
                            bootbox.alert("请至少添加一个子条件!");
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
            });

        });

        var idIndex = ${filter.filterItems.size()==0}?0:${filter.filterItems.size()};
        function addFilterItem() {
            var json={idIndex:idIndex};
            $("#filterTable tr:last").after($('#filterTr').tmpl(json));
            idIndex++;
        }

        function removeFilterItem(indx) {
            $("#filterItem" + indx).remove();
            idIndex--;
        }
    </script>
    <script id="filterTr" type="text/x-jquery-tmpl">
        <tr id='filterItem{{= idIndex}}'>
            <td><input type="hidden" name="itemId" value="0"/><input type='text' name='itemName' class='form-control validate[required]'/></td>
            <td><input type='text' name='itemSortOrder' value='{{= idIndex}}' class='form-control validate[required,min[0],custom[integer]]'/></td>
            <td class="text-center"><a class='btn btn-default' href='javascript:removeFilterItem({{= idIndex}})'><i class='fa fa-minus-circle'></i></a></td>
        </tr>
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
                    <li><a href="#">运营管理</a></li>
                    <li><a href="#">规格过滤器配置</a></li>
                    <li class="active">编辑过滤器</li>
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
                    <h2>编辑过滤器</h2>
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
                <form:form id="validateSubmitForm" data-asf-expireafter="1" data-asf-time="10" action="editSpecFilter.do" method="post" commandName="filter">
                    <form:hidden path="filterId"/>
                    <fieldset>
                        <legend>编辑过滤器</legend>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>过滤器名称：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required]"  path="filterName" maxlength="10" />
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span>排序：</label>
                            </div>
                            <div class="col-sm-3">
                                <form:input cssClass="form-control validate[required,min[0],custom[integer]]" path="sortOrder" maxlength="4"/>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label><span class="asterisk">*</span> 规格值：</label>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" onclick="addFilterItem();" title="添加" class="btn btn-default">
                                    <i class="fa fa-plus-circle"></i>添加规格值
                                </button>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label> <span class="asterisk">*</span></label>
                            </div>
                            <div class="col-sm-8">
                                <table id="filterTable" class="table table-bordered table-striped">
                                    <thead id="attribute-table-th">
                                    <tr>
                                        <td scope="col">规格值</td>
                                        <td scope="col" width="110">排序</td>
                                        <td scope="col" width="70" class="text-center">操作</td>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach var="item" items="${filter.filterItems}">
                                        <tr id='filterItem${item.itemId}'>
                                            <td><input type="hidden" name="itemId" value="${item.itemId}"/> <input type='text' name='itemName' value="${item.itemValue}"  class='form-control validate[required]'/></td>
                                            <td><input type='text' name='itemSortOrder' value='${item.sortOrder}'  class='form-control' maxlength='4'/></td>
                                            <td class="text-center"><a class='btn btn-default' href='javascript:removeFilterItem(${item.itemId})'><i class='fa fa-minus-circle'></i></a></td>
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
