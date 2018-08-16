<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>价格段管理</title>

    <script type="text/javascript">


        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditPriceSection.do?sectionId=" + dataItem.sectionId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认删除吗？", function (result) {
                    if (result) {
                        $.get("removePriceSectionById.do", {
                            sectionId: dataItem.sectionId
                        }, function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                $("#grid").data("kendoGrid").dataSource.read();
                            } else {
                                bootbox.alert("失败");
                            }
                        });
                    }
                });
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
                    <li><a href="#">商品管理</a></li>
                    <li class="active"><a href="#">价格段管理</a></li>
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
                    <h2>价格段管理</h2>
                </div>
                <%--<div class="pull-right">
                    <div class="dropdown">
                        <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-comments"></i>
                            <span class="indicator-dot">3</span>
                        </a>

                        <div role="menu" class="dropdown-menu pull-center ext-dropdown-comments">
                            <div class="ext-dropdown-header">
                                <i class="fa fa-comments"></i>
                                <h5>Comments</h5>
                                <a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>
                                <span class="indicator-dot">3</span>
                            </div>
                            <div class="ext-dropdown-comments-content">
                                <div>
                                    <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
                                    <a href="#">Karma, a good thing</a>
                                    <ul>
                                        <li><span>Posted by:</span> <a href="#">Steven</a></li>
                                        <li><span>Date:</span> Dec 11, 2012</li>
                                        <li>
                                            <span>Actions:</span>
                                            <a href="#">Read</a> -
                                            <a href="#" class="delete">Delete</a>
                                        </li>
                                    </ul>
                                </div>
                                <div>
                                    <img src="../images/users/user-4.jpg" alt="" class="avatar"/>
                                    <a href="#">A simple, fast way to reduce stress</a>
                                    <ul>
                                        <li><span>Posted by:</span> <a href="#">Linda</a></li>
                                        <li><span>Date:</span> Dec 3, 2012</li>
                                        <li>
                                            <span>Actions:</span>
                                            <a href="#">Read</a> -
                                            <a href="#" class="delete">Delete</a>
                                        </li>
                                    </ul>
                                </div>
                                <div>
                                    <img src="../images/users/user-6.jpg" alt="" class="avatar"/>
                                    <a href="#">Blog: karma and revenge</a>
                                    <ul>
                                        <li><span>Posted by:</span> <a href="#">Debra Hopper</a></li>
                                        <li><span>Date:</span> Nov 18, 2012</li>
                                        <li>
                                            <span>Actions:</span>
                                            <a href="#">Read</a> -
                                            <a href="#" class="delete">Delete</a>
                                        </li>
                                    </ul>
                                </div>
                                <span>No new comments</span>
                            </div>
                            <div class="ext-dropdown-footer">
                                <p>Updated: 11/12/2012 - 14:12</p>
                                <a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>
                            </div>
                        </div>
                    </div>
                    <!-- End .dropdown -->
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
                    <!-- End .btn-group -->
                    <div class="dropdown">
                        <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-inbox"></i>
                            <span class="indicator-dot">3</span>
                        </a>

                        <div role="menu" class="dropdown-menu pull-right ext-dropdown-inbox">
                            <div class="ext-dropdown-header">
                                <h5>Inbox</h5>
                                <a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>
                                <span class="indicator-dot">3</span>
                            </div>
                            <div class="ext-dropdown-inbox-content">
                                <div>
                                    <a href="#">He did you get my new blog post?</a>
                                    <ul class="nav">
                                        <li><span>Send by:</span> <a href="#">Debra Hopper</a></li>
                                        <li><span>Date:</span> Dec 12, 2012 - 14:03:09</li>
                                        <li>
                                            <span>Actions:</span>
                                            <a href="#">Reply</a> -
                                            <a href="#">Read</a> -
                                            <a href="#">Spam</a> -
                                            <a href="#" class="delete">Delete</a>
                                        </li>
                                    </ul>
                                </div>
                                <div>
                                    <a href="#">I really love your karma theme</a>
                                    <ul class="nav">
                                        <li><span>Send by:</span> <a href="#">Nathan Rupertson</a></li>
                                        <li><span>Date:</span> Dec 3, 2012 - 22:44:12</li>
                                        <li>
                                            <span>Actions:</span>
                                            <a href="#">Reply</a> -
                                            <a href="#">Read</a> -
                                            <a href="#">Spam</a> -
                                            <a href="#" class="delete">Delete</a>
                                        </li>
                                    </ul>
                                </div>
                                <div>
                                    <a href="#">Feedback of a happy customer</a>
                                    <ul class="nav">
                                        <li><span>Send by:</span> <a href="#">Steven Watson</a></li>
                                        <li><span>Date:</span> Dec 11, 2012 - 10:53:59</li>
                                        <li>
                                            <span>Actions:</span>
                                            <a href="#">Reply</a> -
                                            <a href="#">Read</a> -
                                            <a href="#">Spam</a> -
                                            <a href="#" class="delete">Delete</a>
                                        </li>
                                    </ul>
                                </div>
                                <span>No new emails</span>
                            </div>
                            <div class="ext-dropdown-footer">
                                <div class="progress bar-small">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                                        <span class="sr-only">60% Complete</span>
                                    </div>
                                </div>
                                <p>60%</p>
                                <a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>
                            </div>
                        </div>
                    </div>
                    <!-- End .dropdown -->
                </div>--%>
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
                    <form>
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>起始价格:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="number" data-filter="startPrice" data-operator="gte" class="form-control grid-filter" style="width: 60px" onkeyup="this.value=this.value.replace(/\D/g,'')" />
                            </div>
                            <div class="toolbar-field">
                                <strong>结束价格:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="number" data-filter="endPrice" data-operator="lte" class="form-control grid-filter" style="width: 60px" onkeyup="this.value=this.value.replace(/\D/g,'')" />
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

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>价格段列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddPriceSection.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>

                        </div>
                    </header>
                </div>
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
                            <kendo:grid-column title="价格段编码" field="sectionNo" width="100px"/>
                            <kendo:grid-column title="起始价格" field="startPrice" width="100px"/>
                            <kendo:grid-column title="结束价格" field="endPrice" width="100px"/>
                            <kendo:grid-column title="启用" field="enabled" width="100px" template="#= enabled ? '是' : '否' #"/>
                            <kendo:grid-column title="备注" field="remark" filterable="false" width="200px"/>
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
                                <kendo:dataSource-transport-read url="pagePriceSections.do" type="POST" contentType="application/json"/>
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
