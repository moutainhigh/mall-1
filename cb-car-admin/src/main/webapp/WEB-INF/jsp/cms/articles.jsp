<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="bodyCss" value="hide-right-sidebar" scope="request"/>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>文章管理</title>
    <script type="text/javascript">
        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditArticle.do?articleId=" + dataItem.articleId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确定删除吗？", function (result) {
                    if (result) {
                        $.get("removeArticleById.do", {
                            articleId: dataItem.articleId,
                            rad: Math.random()
                        }, function (data) {
                            if (data != 0) {
                                commonNotify("删除成功！", "success");
                                $("#grid").data("kendoGrid").dataSource.read();
                            } else {
                                commonNotify("删除失败!", "error");
                            }
                        });
                    }
                });
            }
        }

        function enabledItem(enabled) {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $.get("enableArticleById.do", {
                    articleId: dataItem.articleId,
                    enabled: enabled,
                    rad: Math.random()
                }, function (data) {
                    if (data != 0) {
                        commonNotify("操作成功！", "success");
                        $("#grid").data("kendoGrid").dataSource.read();
                    } else {
                        commonNotify("操作失败!", "error");
                    }
                });
            }
        }

        function filterPrograma(programaId){
            $("#programaId").val(programaId);
            reloadGridFilters('grid')
        }

        function formatSubjetc(specialSubject){
            if(null != specialSubject){
                return specialSubject.subjectName;
            }else{
                return "无";
            }

        }

    </script>
</head>

<aside id="sidebar-main" class="sidebar">
    <jsp:include page="../layouts/sidebar_logo.jsp"/>
    <ul class="ext-tabs-sidebar">
        <li>
            <a href="#sidebar-tab-1"><i class="fa fa-bars"></i> 导航</a>
        </li>
        <li class="active">
            <a href="#sidebar-tab-2"><i class="fa fa-folder"></i> 栏目</a>
        </li>
    </ul>
    <div class="tab-content">
        <jsp:include page="../layouts/menu.jsp">
            <jsp:param name="active" value="active"/>
        </jsp:include>
        <div id="sidebar-tab-2" class="tab-pane active clearfix">
            <input type="hidden" id="programaId" data-filter="programa.programaId" data-operator="eq" class="grid-filter" value="0"/>
            <div class="sidebar-module">
                <ul class="easyfiletree">
                    <%--频道--%>
                    <c:forEach var="channel" items="${articleChannels}">
                        <li class="eft-open"><i class="fa fa-folder-open"></i>${channel.channelName}
                            <ul>
                                <%--栏目--%>
                                <c:forEach var="programa" items="${programas}">
                                    <c:if test="${programa.articleChannel.channelId==channel.channelId}">
                                        <li class="eft-open"><i class="fa fa-folder-open"></i><a href="javascript:filterPrograma(${programa.programaId})">${programa.programaName}</a>
                                            <%--<ul>--%>
                                                <%--&lt;%&ndash;专题&ndash;%&gt;--%>
                                                <%--<c:forEach var="specialSubject" items="${specialSubjects}">--%>
                                                    <%--<c:if test="${specialSubject.programa.programaId==programa.programaId}">--%>
                                                        <%--<li><i class="fa fa-file-text"></i><a href="javascript:filterPrograma(${programa.programaId})">${specialSubject.subjectName}</a></li>--%>
                                                    <%--</c:if>--%>
                                                <%--</c:forEach>--%>
                                            <%--</ul>    --%>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="sidebar-line"></div>
</aside>
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
                    <li class="active"><a href="articles.do">文章管理</a></li>
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
                    <h2>文章管理</h2>
                </div>
                <div class="pull-right">
                    <div class="btn-group">
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-star"></i>
                        </a>
                        <a class="btn btn-default" href="#" id="modal-update-trigger">
                            Modal
                        </a>
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-cog"></i>
                        </a>
                    </div>
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
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>文章名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="articleTitle" data-operator="contains" class="form-control grid-filter" placeholder="请输入文章名称"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>文章作者:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="author" data-operator="contains" class="form-control grid-filter" placeholder="请输入文章作者"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>状态:</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="enabled" data-operator="eq" class="form-control simpleselect grid-filter">
                                    <option value="">全部</option>
                                    <option value="true">启用</option>
                                    <option value="false">停用</option>
                                </select>
                            </div>
                        </div>
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="spacer-10"></div>

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h4>文章列表</h4>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddArticle.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="enabledItem(true)" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;启用</a>
                                <a href="javascript:void(0);" onclick="enabledItem(false)" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 停用</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="500" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="图片" field="picPath" width="140px" template="<img src='#=picPath#'  width='120px' height='60px'/>" sortable="false" filterable="false"/>
                            <kendo:grid-column title="文章名称" field="articleTitle" width="150px"/>
                            <kendo:grid-column title="频道" field="programa.articleChannel.channelName" width="130px" filterable="false"/>
                            <kendo:grid-column title="栏目" field="programa.programaName" width="80px"/>
                            <kendo:grid-column title="所属专题" field="specialSubject" template="#=formatSubjetc(specialSubject)#" width="150" filterable="false"/>
                            <kendo:grid-column title="简短标题" field="shortTitle" width="80px"/>
                            <kendo:grid-column title="作者" field="author" width="80"/>
                            <kendo:grid-column title="来源" field="origin" width="80"/>
                            <kendo:grid-column title="发布日期" field="publishDate" width="80" format="{0:yyyy-MM-dd}"/>
                            <kendo:grid-column title="排序时间" field="orderTime" width="120" format="{0:yyyy-MM-dd}"/>
                            <kendo:grid-column title="创建时间" field="createTime" width="120" format="{0:yyyy-MM-dd HH:mm}"/>
                            <kendo:grid-column title="状态" field="enabled" template="#= enabled ? '启用' : '停用' #" width="80"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageArticles.do" type="POST" contentType="application/json"/>
                                <kendo:dataSource-transport-parameterMap>
                                    <script>
                                        function parameterMap(options, type) {
                                            return JSON.stringify(options);
                                        }
                                    </script>
                                </kendo:dataSource-transport-parameterMap>
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </div>

            </div>

            <div class="spacer-40"></div>
            <div class="hr-totop"><span>Top</span></div>
            <div class="spacer-40"></div>

        </div>

        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>
</body>
</html>
