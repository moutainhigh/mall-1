<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>商品管理</title>
    <script type="text/javascript">

        function editItemFilters() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $("#cocaId").val(dataItem.cocaId);
                $("#filterTable  tr:not(:first)").empty()
                $.getJSON("getFilterItemsByCommodityCommodity.do", {
                    cocaId: dataItem.cocaId,
                    rad: Math.random()
                }, function (data) {
                    $("#filterTable tr:last").after($('#filterTr').tmpl(data));
                });
                $('#filterDialog').modal();
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确定删除吗？", function (result) {
                    if (result) {
                        $.get("removeCommodityCategoryById.do", {
                            cocaId: dataItem.cocaId,
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
    </script>
</head>
<body>
<aside id="sidebar-main" class="sidebar">
    <jsp:include page="../layouts/sidebar_logo.jsp"/>
    <ul class="ext-tabs-sidebar">
        <li>
            <a href="#sidebar-tab-1"><i class="fa fa-bars"></i> 导航</a>
        </li>
        <li class="active">
            <a href="#sidebar-tab-2"><i class="fa fa-folder"></i> 运营分类</a>
        </li>
    </ul>
    <div class="tab-content">
        <jsp:include page="../layouts/menu.jsp">
            <jsp:param name="active" value="active"/>
        </jsp:include>
        <div id="sidebar-tab-2" class="tab-pane active clearfix">
            <div class="sidebar-module">
                <ul class="easyfiletree">
                    <c:forEach var="category1" items="${categoryTree}">
                        <li class="eft-closed"><i class="fa fa-folder"></i>${category1.text}
                            <ul>
                                <c:forEach var="category2" items="${category1.items}">
                                    <li class="eft-closed"><i class="fa fa-folder"></i>${category2.text}
                                        <ul>
                                            <c:forEach var="category3" items="${category2.items}">
                                                <li><i class="fa fa-file-text"></i><a href="categoryCommodities.do?categoryId=${category3.id}">${category3.text}</a></li>
                                            </c:forEach>
                                        </ul>
                                    </li>
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
        <!-- End #header-main-top -->
        <div class="header-main-bottom">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">商品管理</a></li>
                    <li class="active"><a href="#">商品管理</a></li>
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
                    <h2>运营分类：${category.categoryName} 下属商品管理</h2>
                </div>
                <div class="pull-right">
                    <a class="btn btn-default" href="categories.do">
                        <i class="fa fa-reply"></i>
                    </a>
                </div>
            </div>
            <!-- End .inner-padding -->
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
                <div class="toolbar responsive-helper">
                    <form>
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>商品编码:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="commodity.commodityCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入商品编码"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>商品名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="commodity.commodityName" data-operator="contains" class="form-control grid-filter" placeholder="请输入商品名称"/>
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
                            <h3>商品列表</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="showCommodityDialog();" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;添加商品</a>
                                <%--<a href="javascript:void(0);" onclick="editItemFilters()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 搜索条件</a>
                                <a href="javascript:void(0);" onclick="showLogisticDialog()" class="btn btn-default"><i class="fa fa-truck"></i>&nbsp; 设置物流</a>--%>
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
                            <kendo:grid-column title="商品分类" field="commodity.catalog.catalogName" width="80"/>
                            <kendo:grid-column title="商品品牌" field="commodity.brand.brandName" width="80"/>
                            <kendo:grid-column title="商品图片" field="commodity.defaultPicPath" width="80" template="<img src='#=commodity.defaultPicPath#'  width='51px' height='55px'/>" sortable="false" filterable="false"/>
                            <kendo:grid-column title="商品编码" field="commodity.commodityCode" width="80"/>
                            <kendo:grid-column title="商品名称" field="commodity.commodityName" width="80"/>
                            <%--<kendo:grid-column title="推荐值排序" field="recommendValue" width="70"/>--%>
                            <kendo:grid-column title="成本价" field="commodity.costPrice" width="80"/>
                            <kendo:grid-column title="销售价" field="commodity.sellPrice" width="80"/>
                            <kendo:grid-column title="市场价格" field="commodity.marketPrice" width="80"/>
                            <kendo:grid-column title="创建时间" field="commodity.createTime" width="130" format="{0:yyyy-MM-dd HH:mm}"/>
                            <kendo:grid-column title="备注" field="commodity.remark" width="150" filterable="false"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-filter>
                                <kendo:dataSource-filterItem field="category.categoryId" value="${param.categoryId}" operator="eq"/>
                            </kendo:dataSource-filter>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageCommodityCategories.do" type="POST" contentType="application/json"/>
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

<div class="modal fade" id="logisticDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择物流</h4>
            </div>
            <div class="modal-body">

                <form id="logisticPriceForm">
                    <input type="hidden" name="commodityId" id="commodityId" value="0">
                    <c:forEach items="${logistics}" var="logistic">
                        <div class="row" style="width: 900px;align:left;">
                            <div class="col-sm-2">
                                <label>${logistic.logisticName}</label>
                            </div>
                            <div class="col-sm-10">
                                <c:forEach items="${logistic.logisticPrices}" var="prices" varStatus="priSta">
                                    <div class="row ext-raster demo-raster">
                                        <span class="breakpoint-sm ext-raster-line-top"></span>
                                        <span class="ext-raster-line-0"></span>
                                        <div class="col-sm-2"><input type="checkbox" name="priceId" value="${prices.priceId}"></div>
                                        <span class="breakpoint-sm ext-raster-line-2"></span>
                                        <div class="col-sm-3">首重(千克):${prices.weight} KG</div>
                                        <span class="breakpoint-sm ext-raster-line-5"></span>
                                        <div class="col-sm-3">首重价格:${prices.weightPrice} 元</div>
                                        <span class="breakpoint-sm ext-raster-line-8"></span>
                                        <div class="col-sm-4">续重价格:${prices.continuePrice} 元</div>
                                        <span class="ext-raster-line-12"></span>
                                        <span class="breakpoint-sm ext-raster-line-bottom"></span>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="spacer-10"></div>
                        <hr>
                        <div class="spacer-10"></div>
                    </c:forEach>

                    <div class="spacer-10"></div>
                </form>
                <div class="clear"></div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseLogistic();">确认</button>
            </div>
        </div>
    </div>
    <script type="application/javascript">
        function showLogisticDialog() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                $('#logisticDialog').modal();
                $("#commodityId").attr("value",dataItem.commodity.commodityId);
            }
        }

        function chooseLogistic() {
            var serializeArray=$("#logisticPriceForm").serialize();
            if(serializeArray.indexOf("priceId")==-1){
                alert("请选择物流价格");
                return;
            }
            $.post('saveCommodityLogisticPrices.do', serializeArray, function(data) {
                if(data){
                    commonNotify("添加成功！", "success");
                    $("#grid").data("kendoGrid").dataSource.read();
                }else{
                    commonNotify("添加失败！", "error");
                }
            });
            //clearCheck();
            $("#logisticPriceForm :checkbox").removeAttr("checked");
            $('#logisticDialog').modal("hide");
        }
    </script>
</div>

<div class="modal fade" id="commodityDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <form id="commodityFormId" style="float: inherit;">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择商品分类</h4>
            </div>
            <div class="modal-body">
                    <input type="hidden" name="categoryId" value="${category.categoryId}"/>
                <jsp:include page="../commodity/chooseCommodities.jsp"/>

            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="chooseCommodity();">确认</button>
            </div>
        </div>
    </div>
    </form>
    <script type="application/javascript">
        function showCommodityDialog() {
            $('#commodityDialog').modal();
        }

        function chooseCommodity() {
            var serializeArray=$("#commodityFormId").serialize();
            if(serializeArray.indexOf("selectedCommodityId")==-1){
                alert("请选择商品");
                return;
            }
            $.post('addCategoryCommodities.do', serializeArray, function(data) {
                if(data){
                    commonNotify("添加商品成功！", "success");
                    $("#grid").data("kendoGrid").dataSource.read();
                }else{
                    commonNotify("添加商品失败！商品已存在或出现错误！", "error");
                    $("#grid").data("kendoGrid").dataSource.read();
                }
            });
            clearCheck();
            reloadGridFilters('grid');
            $('#commodityDialog').modal("hide");
        }
    </script>
</div>

<div class="modal fade" id="filterDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择商品查询条件</h4>
            </div>
            <div class="modal-body" style="margin:0 20px 0 20px;min-height: 350px">
                <form id="filterForm" action="">
                    <input type="hidden" id="cocaId" name="cocaId" value="0">
                    <div class="row" >
                            <table id="filterTable" class="table table-bordered table-striped">
                                <thead>
                                <th style="width: 200px">查询条件</th>
                                <th>条件值</th>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="saveFilterItems();">确认</button>
            </div>
        </div>
    </div>
    <script id="filterTr" type="text/x-jquery-tmpl">
        <tr>
            <td>{{= filterName}}</td>
            <td>{{each filterItems}}
                <input type='checkbox' name='itemId' {{if $value.checked}}checked{{/if}} value='{{= $value.itemId}}'/>&nbsp;{{= $value.itemName}}&nbsp;&nbsp;&nbsp;
                {{/each}}
            </td>
        </tr>
    </script>
    <script type="application/javascript">

        function saveFilterItems() {
            $.post('saveCategoryCommodityFilterItems.do', $("#filterForm").serialize(), function(data) {
                if(data>0){
                    commonNotify("保存查询条件成功！", "success");
                    $('#filterDialog').modal("hide");
                }
            } );

        }
    </script>
</div>
</body>
</html>
