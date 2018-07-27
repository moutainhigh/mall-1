<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>七牛图片管理</title>
    <script type="text/javascript">
        function getState(state){
            switch (state){
                case "RUNNING":{
                    return "运行中";
                }
                case "WAIT":{
                    return "等待";
                }
                case "CANCEL":{
                    return "取消";
                }
            }
        }
        function getObjectType(objectType){
            switch (objectType){
                case "COMMODITY":{
                    return "商品";
                }
                case "BRAND":{
                    return "品牌";
                }
                case "ADVERT":{
                    return "广告";
                }
                case "ATTRIBUTE":{
                    return "商品属性";
                }
                case "HOMEFLOORPROPAGANDA":{
                    return "首页宣传";
                }
                case "HOMEFLOORICO":{
                    return "首页";
                }
                case "CATEGORY":{
                    return "运营分类";
                }
                case "ARTICLE":{
                    return "文章";
                }
                case "CHANNER":{
                    return "频道";
                }
                case "PROGRAMA":{
                    return "栏目";
                }
                case "ACTIVITY":{
                    return "活动";
                }
                case "OTHER":{
                    return "其它";
                }
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
                    <li class="active"><a href="#">七牛图片管理</a></li>
                </ul>
                <!-- End .breadcrumb -->
            </div>
            <div class="pull-right">
                <p>Version 1.0.0</p>
            </div>
        </div>
        <!-- End #header-main-bottom -->
    </header>
    <div id="content" class="clearfix">
        <header id="header-sec">
            <div class="inner-padding">
                <div class="pull-left">
                    <h2>七牛图片管理</h2>
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
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="pull-left">
                                <div class="toolbar-field">
                                    <strong>业务对象:</strong>
                                </div>
                                <div class="toolbar-field">
                                    <div class="toolbar-field">
                                        <select data-filter="objectType"  data-operator="eq"
                                                class="form-control  grid-filter">
                                            <option value="">全部</option>
                                            <option value="COMMODITY">商品</option>
                                            <option value="BRAND">品牌</option>
                                            <option value="ADVERT">广告</option>
                                            <option value="ATTRIBUTE">商品属性</option>
                                            <option value="HOMEFLOORPROPAGANDA">首页宣传</option>
                                            <option value="HOMEFLOORICO">首页</option>
                                            <option value="CATEGORY">运营分类</option>
                                            <option value="ARTICLE">文章</option>
                                            <option value="CHANNER">频道</option>
                                            <option value="PROGRAMA">栏目</option>
                                            <option value="ACTIVITY">活动</option>
                                            <option value="OTHER">其它</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="toolbar-field">
                                    <strong>状态:</strong>
                                </div>
                                <div class="toolbar-field">
                                    <div class="toolbar-field">
                                        <select data-filter="state"  data-operator="eq"
                                                class="form-control  grid-filter">
                                            <option value="">全部</option>
                                            <option value="RUNNING">运行中</option>
                                            <option value="WAIT">等待</option>
                                            <option value="CANCEL">取消</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                                &nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                            </div>
                        </div>
                        <!-- End .pull-right -->
                    </form>
                </div>
                <!-- End .toolbar -->
                <div class="spacer-10"></div>

                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="450" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="图片" field="filePath" width="25" template="<img src='#=filePath#'  width='70px' height='65px'/>" sortable="false" filterable="false"/>
                            <kendo:grid-column title="业务对象" template="#=getObjectType(objectType)#" filterable="false" field="objectType"  width="100px"/>
                            <kendo:grid-column title="业务对象ID" filterable="false" field="objectId"  width="100px"/>
                            <kendo:grid-column title="状态" template="#=getState(state)#" filterable="false" field="state"  width="100px"/>
                            <kendo:grid-column title="创建时间" filterable="false" field="createTime" format="{0:yyyy-MM-dd HH:mm}" width="100px"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                                <kendo:dataSource-schema-model>
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                                    </kendo:dataSource-schema-model-fields>
                                </kendo:dataSource-schema-model>
                            </kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageAttachment.do" type="POST" contentType="application/json"/>
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

            <!-- End .inner-padding -->
        </div>
        <jsp:include page="../layouts/footer.jsp"/>
        <!-- End #footer-main -->
    </div>
</div>
</body>
</html>
