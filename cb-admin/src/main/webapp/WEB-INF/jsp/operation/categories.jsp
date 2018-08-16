<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>运营分类管理</title>
    <script type="application/javascript">
        $(document).ready(function () {


        });

        function editItem(){
            var dataItem=getSelectedTreeListItem("treelist");
            if(dataItem){
                window.location.href = "toEditCategory.do?categoryId=" + dataItem.categoryId;
            }
        }

        function queryCommodity(){
            var dataItem=getSelectedTreeListItem("treelist");
            if(dataItem){
                if(dataItem.level==3){
                    window.location.href = "categoryCommodities.do?categoryId=" + dataItem.categoryId;
                }else{
                    commonNotify('第三级运营分类才能关联商品',"warning");
                }
            }
        }

        function removeItem() {
            var dataItem=getSelectedTreeListItem("treelist");
            if(dataItem){
                bootbox.confirm("确定删除吗？", function(result) {
                    if(result){
                        var categoryId=dataItem.categoryId;
                        $.get("removeCategoryById.do", {
                            categoryId : categoryId,
                        }, function(data) {
                            if (data) {
                                commonNotify("删除成功！", "success");
                                $("#treelist").data("kendoTreeList").dataSource.read();
                                window.location.reload();
                            } else {
                                commonNotify("删除失败!该运营分类存在数据关联！", "error");
                            }
                        });
                    }
                });
            }
        }
    </script>
    <style>
        .employee-photo {
            display: inline-block;
            width: 25px;
            height: 25px;
            background-size: 25px 25px;
            background-position: center center;
            vertical-align: middle;
            line-height: 25px;
            margin-left: 5px;
        }

        .employee-name {
            display: inline-block;
            vertical-align: middle;
            line-height: 28px;
            padding-left: 3px;
        }
    </style>
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
                        <li><a href="#">运营管理</a></li>
                        <li class="active">运营分类管理</li>
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
                        <h2>运营分类管理</h2>
                    </div>
                    <div class="pull-right">

                        <!-- End .dropdown -->
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
                <div class="inner-padding">
                    <div class="spacer-10"></div>
                    <div class="toolbar responsive-helper">
                        <header>
                            <div class="pull-left">
                                <h3>运营分类列表</h3>
                            </div>
                            <div class="pull-right">
                                <div class="btn-group">
                                    <a href="toAddCategory.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                    <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                    <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                                </div>
                                <a href="javascript:void(0);" onclick="queryCommodity()" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;下属商品</a>
                            </div>
                        </header>
                    </div>
                    <div class="table-wrapper">
                        <kendo:treeList name="treelist" height="450" selectable="true">
                            <kendo:treeList-columns>
                                <kendo:treeList-column field="categoryName" title="分类名称" template="<div class='employee-photo' style='background-image: url(../images/#:iconPath#);'></div><div class='employee-name'>#: categoryName #</div>"/>
                                <kendo:treeList-column field="categoryKey" title="分类关键字"/>
                                <kendo:treeList-column field="createTime" title="创建时间" format="{0:yyyy-MM-dd HH:mm}"/>
                                <kendo:treeList-column field="recommend" title="是否推荐" template="#=(recommend==true?'是':'否')#"/>
                                <kendo:treeList-column field="enabled" title="是否启用" template="#= enabled ? '是' : '否' #"/>
                                <kendo:treeList-column field="remark" title="备注"/>
                            </kendo:treeList-columns>
                            <kendo:dataSource data="${categories}">
                                <kendo:dataSource-schema>
                                    <kendo:dataSource-schema-model id="categoryId">
                                        <kendo:dataSource-schema-model-fields>
                                            <kendo:dataSource-schema-model-field name="categoryId" type="number" />
                                            <kendo:dataSource-schema-model-field name="parentId" from="parentCategoryId" type="number" nullable="true"/>
                                            <kendo:dataSource-schema-model-field name="categoryName" type="string" />
                                            <kendo:dataSource-schema-model-field name="createTime" type="date" />
                                            <kendo:dataSource-schema-model-field name="enabled" type="boolean" />
                                            <kendo:dataSource-schema-model-field name="remark" type="string" />
                                        </kendo:dataSource-schema-model-fields>
                                    </kendo:dataSource-schema-model>
                                </kendo:dataSource-schema>
                            </kendo:dataSource>
                        </kendo:treeList>
                    </div>
                </div>

                <div class="spacer-40"></div>
                <div class="hr-totop"><span>Top</span></div>
                <div class="spacer-40"></div>

                <!-- End .inner-padding -->
            </div>
            <!-- End .window -->

            <jsp:include page="../layouts/footer.jsp"/>
            <!-- End #footer-main -->
        </div>
        <!-- End #content -->
    </div>
    <!-- End #main -->


</body>
</html>
