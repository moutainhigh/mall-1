<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>分类管理</title>
    <jsp:include page="../layouts/left.jsp"/>
    <script type="application/javascript">

        var dataMsg='${dataMsg}';
        if(dataMsg!=null&&dataMsg!=""){
            var msgType='${msgType}';
            commonNotify(dataMsg,msgType==null||msgType==''?"error":msgType);
        }

        //修改
        function editItem(){
            var dataItem=getSelectedTreeListItem("treelist");
            if(dataItem){
                window.location.href = "toEditCatalog.do?catalogId=" + dataItem.catalogId;
            }
        }

        //删除
        function removeItem() {
            var dataItem=getSelectedTreeListItem("treelist");
            if(dataItem){
                if(dataItem.catalogCode && dataItem.catalogCode.length <= 3*2){
                    commonNotify("根分类和一级分类禁止删除!", "error");
                    return;
                }
                bootbox.confirm("确定删除吗？", function(result) {
                    if(result){
                        var catalogId=dataItem.catalogId;
                        $.post("removeById.do", {
                            catalogId : catalogId
                        }, function(data) {
                            if (data) {
                                commonNotify("操作成功！", "success");
                                window.location.href ="catalogs.do";
                            } else {
                                commonNotify("操作失败!", "error");
                            }
                        });
                    }
                });
            }
        }

        //停用/启用
        function enabledItem(enabled) {
            var dataItem = getSelectedTreeListItem("treelist");
            if (dataItem) {
                if(dataItem.catalogCode && dataItem.catalogCode.length == 3){
                    commonNotify("根分类禁止进行停用/启用操作!", "error");
                    return;
                }
                $.get("enableBaseDataById.do", {
                    catalogId: dataItem.catalogId,
                    enabled: enabled,
                    rad: Math.random()
                }, function (data) {
                    if (data) {
                        commonNotify("操作成功！", "success");
                        window.location.href ="catalogs.do";
                    } else {
                        commonNotify("操作失败!", "error");
                    }
                });
            }
        }
    </script>
</head>
<body>

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
                    <li><a href="#">分类管理</a></li>
                    <li class="active">基础数据管理</li>
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
                    <h2>分类管理</h2>
                </div>
                <div class="pull-right"></div>
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
                            <h4>商品分类列表</h4>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="toAddBaseData.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                                <a href="javascript:void(0);" onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                <a href="javascript:void(0);" onclick="removeItem();" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                            </div>
                            <div class="btn-group">
                                <a href="javascript:void(0);" onclick="enabledItem(true)" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;启用</a>
                                <a href="javascript:void(0);" onclick="enabledItem(false)" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 停用</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:treeList name="treelist" height="450" selectable="true">
                        <kendo:treeList-columns>
                            <kendo:treeList-column field="baseDataName" title="分类名称"></kendo:treeList-column>
                            <kendo:treeList-column field="baseDataCode" title="分类编码"></kendo:treeList-column>
                            <kendo:treeList-column field="remark" title="分类描述"></kendo:treeList-column>
                            <kendo:treeList-column field="sortOrder" title="顺序"></kendo:treeList-column>
                            <kendo:treeList-column field="enabled" title="状态" template="#= enabled ? '启用' : '停用' #"></kendo:treeList-column>
                        </kendo:treeList-columns>
                        <kendo:dataSource data="${datas}">
                            <kendo:dataSource-schema>
                                <kendo:dataSource-schema-model id="id">
                                    <kendo:dataSource-schema-model-fields>
                                        <kendo:dataSource-schema-model-field name="id" type="number" editable="false" nullable="false"/>
                                        <kendo:dataSource-schema-model-field name="parentId" from="parentBaseDataId" type="number" nullable="true"/>
                                        <kendo:dataSource-schema-model-field name="baseDataName" type="string"/>
                                        <kendo:dataSource-schema-model-field name="enabled" type="number"/>
                                        <kendo:dataSource-schema-model-field name="remark" type="string"/>
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
        </div>
        <jsp:include page="../layouts/footer.jsp"/>
    </div>
</div>
</body>
</html>
<script type="application/javascript">

    var dataMsg='${dataMsg}';
    if(dataMsg!=null&&dataMsg!=""){
        var msgType='${msgType}';
        commonNotify(dataMsg,msgType==null||msgType==''?"error":msgType);
    }
</script>
