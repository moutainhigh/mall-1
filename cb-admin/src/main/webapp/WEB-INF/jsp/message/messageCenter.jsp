<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>消息中心配置</title>

    <script type="text/javascript">

        /**
         * 消息详情
         */
        function detailItem(){
            var itemData = getKendoSelectedRowData();
            if (itemData) {
                window.location.href = "messageDetail.do?messageId=" + itemData.messageId;
            }else{
                commonNotify("请从表格中选择要操作的数据", "error");
            }
        }

        /**
         * 点击-修改
         */
        function editItem(){
            var data = getKendoSelectedRowData();
            if (data) {
                if(!data.pushTime){
                    window.location.href = "toEditMessage.do?messageId=" + data.messageId;
                }else{
                    commonNotify("已推送的消息无法修改!", "error");
                }
            }else {
                commonNotify("请从表格中选择要操作的数据", "error");
            }
        }

        /**
         * 点击-删除
         */
        function removeItem() {
            var itemData = getKendoSelectedRowData();
            if(itemData){
                if(itemData.pushTime){
                    commonNotify("已推送的消息无法删除!", "error");
                    return;
                }
                bootbox.confirm("确定删除吗？", function(result) {
                    if(result){
                        $.get("removeMessageById.do", {
                            messageId : itemData.messageId,
                        }, function(data) {
                            if (data) {
                                commonNotify("删除成功！", "success");
                                //$("#treelist").data("kendoTreeList").dataSource.read();
                                window.location.reload();
                            } else {
                                commonNotify("删除失败!", "error");
                            }
                        });
                    }
                });
            }else {
                commonNotify("请从表格中选择要操作的数据", "error");
            }
        }

        /**
         * 点击-推送
         */
        function pushMessage() {
            var data = getKendoSelectedRowData();
            if (data) {
                if(!data.pushTime){
                    window.location.href = "pushMessage.do?messageId=" + data.messageId;
                }else{
                    commonNotify("已推送的消息无法再次推送!", "error");
                }
            }else {
                commonNotify("请从表格中选择要操作的数据", "error");
            }
        }

        /**
         * 获取kendo选中的行对象
         * @returns {*}
         */
        function getKendoSelectedRowData(){
            var grid = $("#grid").data("kendoGrid");
            var dataRows = grid.items();
            // 获取行号
            var rowIndex = dataRows.index(grid.select());
            // 获取行对象
            return grid.dataItem(grid.select());
        }

        function getPushStatus(pushStatus){
            switch (pushStatus){
                case "HAVE_NOT_PUSHED":{
                    return "未推送";
                }
                case "HAVE_PUSHED":{
                    return "已推送";
                }
            }
            return pushStatus;
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
                    <li><a href="#">首页 </a></li>
                    <li><a href="#">运营管理 </a></li>
                    <li><a href="#">消息中心配置 </a></li>
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
                    <h2>消息中心配置</h2>
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
            <!-- End .toolbar -->

            <div class="spacer-10"></div>

            <div class="toolbar responsive-helper">
                <header>
                    <div class="pull-left">
                        <h3>消息中心配置 </h3>
                    </div>
                    <div class="pull-right">
                        <div class="btn-group">
                            <a href="javascript:void(0);"  onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>
                        </div>
                        <div class="btn-group">
                            <a href="toEditMessage.do?messageId=0" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;消息新增</a>
                        </div>
                        <div class="btn-group">
                            <a href="javascript:void(0);"  onclick="editItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;修改</a>
                        </div>
                        <div class="btn-group">
                            <a href="javascript:void(0);" onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                        </div>
                        <div class="btn-group">
                            <a href="javascript:void(0);" onclick="pushMessage()" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;推送</a>
                        </div>
                    </div>
                </header>
            </div>
            <div class="table-wrapper">
                <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true"
                            height="450" resizable="true">
                    <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="20"/>
                    <kendo:grid-filterable extra="false">
                        <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                        <kendo:grid-filterable-operators>
                            <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                            <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                        </kendo:grid-filterable-operators>
                    </kendo:grid-filterable>
                    <kendo:grid-columns>
                        <kendo:grid-column title="ID" hidden="true" field="messageId" width="30px"/>
                        <kendo:grid-column title="推送标题" sortable="false" field="pushTitle"  width="200px"/>
                        <kendo:grid-column title="消息摘要" sortable="false" field="messageDigest" width="200px"/>
                        <kendo:grid-column title="摘要图片" field="digestPic" width="140px" template="<img src='#=digestPic#'  width='120px' height='60px'/>" sortable="false" filterable="false"/>
                        <kendo:grid-column title="推送状态" filterable="false" sortable="false" field="pushSatus" template="#=getPushStatus(pushStatus)#" width="100px"/>
                        <kendo:grid-column title="推送时间" field="pushTime" format="{0:yyyy-MM-dd HH:mm}" width="150px" filterable="false" sortable="false"/>
                        <kendo:grid-column title="作者" field="messageDespatcher" width="150px" filterable="false" sortable="false"/>
                        <kendo:grid-column title="创建时间" field="createTime" format="{0:yyyy-MM-dd HH:mm}"  width="150px" filterable="false" sortable="false"/>
                    </kendo:grid-columns>
                    <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                        <kendo:dataSource-schema data="content" total="totalElements">
                            <kendo:dataSource-schema-model>
                                <kendo:dataSource-schema-model-fields>
                                    <kendo:dataSource-schema-model-field name="pushTime" type="string"/>
                                    <kendo:dataSource-schema-model-field name="createTime" type="string"/>
                                </kendo:dataSource-schema-model-fields>
                            </kendo:dataSource-schema-model>
                        </kendo:dataSource-schema>
                        <kendo:dataSource-transport>
                            <kendo:dataSource-transport-read url="pageMessage.do" type="POST"
                                                             contentType="application/json"/>
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
