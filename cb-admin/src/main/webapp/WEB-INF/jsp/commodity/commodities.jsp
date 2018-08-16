<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>商品管理</title>
    <script type="text/javascript">

        $(document).ready(function () {
        });

        function detailItem(){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "commodityDetail.do?commodityId=" + dataItem.commodityId;
            }
        }

        function toProducts() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "editProducts.do?commodityId=" + dataItem.commodityId;
            }
        }
        function editItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditCommodity.do?commodityId=" + dataItem.commodityId;
            }
        }

        function removeItem() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认删除吗？", function (result) {
                    if (result) {
                        $.get("removeCommodityById.do", {
                            commodityId: dataItem.commodityId
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

        function upOrDownShelvesCommodity(pState){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                bootbox.confirm("确认上/下架该商品吗？", function (result) {
                    if (result) {
                        $.get("upOrDownShelvesCommodity.do", {
                            commodityId: dataItem.commodityId,
                            publishState : pState
                        }, function (data) {
                            if (data) {
                                bootbox.alert("成功");
                                $("#grid").data("kendoGrid").dataSource.read();
                            } else {
                                bootbox.alert("失败,商品未通过审核或没有货品信息");
                            }
                        });
                    }
                });
            }
        }

        function toCombinations() {
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toCombinations.do?commodityId=" + dataItem.commodityId;
            }
        }


        function formatCommodityState(commodityState) {
            switch (commodityState) {
                case "WAIT_AUDITED":
                    return "待审核";
                case "AUDITED":
                    return "审核通过";
                case "NOT_AUDITED":
                    return "审核未通过";
                case "DEL":
                    return "删除";
            }
        }

        function formatPublishState(publishState) {
            switch (publishState) {
                case "WAIT_UP_SHELVES":
                    return "待上架";
                case "UP_SHELVES":
                    return "上架";
                case "DOWN_SHELVES":
                    return "下架";
            }
        }
        $(function() {
            $("#commodityQuery").submit(function() {
                reloadGridFilters('grid');
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
                    <h2>商品管理</h2>
                </div>
                <div class="pull-right">
                    <%--<div class="btn-group">
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-star"></i>
                        </a>
                        <a class="btn btn-default" href="#" id="modal-update-trigger">
                            Modal
                        </a>
                        <a class="btn btn-default" href="#">
                            <i class="fa fa-cog"></i>
                        </a>
                    </div>--%>
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
                    <form style="width: 100%" id="commodityQuery" onsubmit="return false">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>商品编码:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" type="text" data-filter="commodityCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入商品编码"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>商品名称:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" type="text" data-filter="commodityName" data-operator="contains" class="form-control grid-filter" placeholder="请输入商品名称"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>商品状态:</strong>
                            </div>
                            <div class="toolbar-field">
                                <select data-filter="commodityState" data-operator="eq" class="form-control  grid-filter">
                                    <option value="">全部</option>
                                    <option value="WAIT_AUDITED">待审核</option>
                                    <option value="AUDITED">审核通过</option>
                                    <option value="NOT_AUDITED">审核未通过</option>
                                    <option value="DEL">已删除</option>
                                </select>
                            </div>
                            <div class="toolbar-field">
                                <strong>销售价:</strong>
                            </div>
                            <div class="toolbar-field">
                                <table>
                                    <tr>
                                        <td><input type="number" min="0" step="0.0001" data-filter="sellPrice" data-operator="gte" class="form-control grid-filter" style="width: 60px"/></td>
                                        <td>-</td>
                                        <td><input type="number" min="0" step="0.0001" data-filter="sellPrice" data-operator="lte" class="form-control grid-filter" style="width: 60px"/></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="submit" id="commodityQuerySubmit" class="btn btn-default"><i class="fa fa-search"></i>查询</button>
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
                                <a href="javascript:void(0);"  onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>
                                <a href="toAddCommodity.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>

                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="upOrDownShelvesCommodity('UP_SHELVES')" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;上架</a>
                                <a href="javascript:void(0);" onclick="upOrDownShelvesCommodity('DOWN_SHELVES')" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp;下架</a>
                                <a href="javascript:void(0);" onclick="commodityAuditItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;审核</a>
                            </div>

                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="toProducts()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;货品</a>
                                <%--<a href="javascript:void(0);" onclick="combicombi()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 组合</a>--%>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true" height="450" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="15"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="商品编码" field="commodityCode" width="100"/>
                            <kendo:grid-column title="商品图片" field="defaultPicPath" width="70" template="<img src='#=defaultPicPath#'  width='51px' height='55px'/>" sortable="false" filterable="false"/>
                            <kendo:grid-column title="商品名称" field="commodityName" width="150" template="<a href='commodityDetail.do?commodityId=#= commodityId#'>#= commodityName#</a>"/>
                            <kendo:grid-column title="商品分类" field="catalog.catalogName" width="130"/>
                            <kendo:grid-column title="商品品牌" field="brand.brandName" width="130"/>
                             <kendo:grid-column title="成本价" field="costPrice" width="80"/>
                            <kendo:grid-column title="销售价" field="sellPrice" width="80"/>
                            <kendo:grid-column title="市场价格" field="marketPrice" width="80"/>
                            <kendo:grid-column title="创建时间" field="createTime" width="150" format="{0:yyyy-MM-dd HH:mm}"/>
                            <kendo:grid-column title="状态" field="commodityState" template="#=formatCommodityState(commodityState)#" width="130"/>
                            <kendo:grid-column title="上下架状态" field="publishState" template="#=formatPublishState(publishState)#" width="130"/>
                            <kendo:grid-column title="备注" field="remark" width="150" filterable="false"/>
                        </kendo:grid-columns>
                        <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                            <kendo:dataSource-schema data="content" total="totalElements">
                            </kendo:dataSource-schema>
                            <kendo:dataSource-filter>
                                <kendo:dataSource-filterItem field="seller.sellerId" value="${seller.sellerType=='SELF_OPERATION'?null:seller.sellerId}" operator="eq"/>
                            </kendo:dataSource-filter>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="pageCommodities.do" type="POST" contentType="application/json"/>
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
    <!-- End #content -->
</div>
<!-- End #main -->

<div class="modal fade" id="commodityAuditDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">商品审核</h4>
            </div>
            <div class="modal-body">
                <form id="commodityAuditForm">
                    <div class="row">
                        <div class="col-sm-4">
                            <label>商品名称：</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="hidden" id="commodityIdHid" name="commodityId">
                            <span style="color:#073980" id="commodityNameSpan"></span>
                        </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                        <div class="col-sm-4">
                            <label>审核：</label>
                        </div>
                        <div class="col-sm-8">
                            <input type="radio" name="commodityState" value="AUDITED"/>通过
                            <input type="radio" name="commodityState" value="NOT_AUDITED" checked/>不通过
                        </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                        <div class="col-sm-4">
                            <label>备注：</label>
                        </div>
                        <div class="col-sm-8">
                            <textarea id="auditRemark" name="auditRemark" rows="8" cols="50" class='form-control validate[maxSize[255]]'></textarea>
                        </div>
                    </div>

                </form>
                <div class="clear"></div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary pull-right" onclick="submitCommodityAudit();" id="btnComfrim">确认</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#commodityAuditForm").validationEngine({
            autoHidePrompt: true, scroll: false, showOneMessage: true
        });
    });

    function commodityAuditItem() {
        var dataItem = getSelectedGridItem("grid");
        if (dataItem) {
            if(dataItem.commodityState == "AUDITED"){
                alert("该商品已通过审核");
                return ;
            }
            $('#commodityAuditDialog').modal();
            $("#commodityIdHid").val(dataItem.commodityId);
            $("#commodityNameSpan").html(dataItem.commodityName);
        }
    }

    function submitCommodityAudit(){
        if($("input[name='commodityState']:checked").val()=="NOT_AUDITED" && $("#auditRemark").val() == ""){
            bootbox.alert("请填写审核不通过原因!");
            return false;
        }
        $.get("commodityAudit.do",$("#commodityAuditForm").serialize(),function(result){
            if(result){
                $("#commodityAuditForm")[0].reset();
                $('#commodityAuditDialog').modal("hide");
                $("#grid").data("kendoGrid").dataSource.read();
            }else{
                alert("审核失败！");
            }
        });
    }
</script>
</body>
</html>
