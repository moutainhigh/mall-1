<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>系统配置</title>

    <script type="text/javascript">
        function detailItem(){
            var dataItem = getSelectedGridItem("grid");
            if (dataItem) {
                window.location.href = "toEditProfiles.do?fileId=" + dataItem.fileId;
            }
        }
        function getprofileValue(value,isPicture){
            if(isPicture==1){
                return "<img src='"+value+"'  width='120px' height='120px'/>"
            }
            return value;
        }
        function getprofileName(state){
            switch (state){
                case "ANDROID_VERSION_CODE":{
                    return "安卓版本编码";
                }
                case "ANDROID_VERSION_NAME":{
                    return "安卓版本名称";
                }
                case "ANDROID_APP_NAME":{
                    return "安卓APP名称";
                }
                case "ANDROID_URL":{
                    return "安卓APP下载地址";
                }
                case "ANDROID_DESCRIPTION":{
                return "安卓APP更新描述";
            }
                case "GIVE_THE_THUMBS_UP":{
                    return "点赞推荐人及所有上级加5%的授信额度";
                }
                case "GIVE_THE_THUMBS_UP":{
                    return "点赞推荐人及所有上级加5%的授信额度";
                }
                case "LOAN_EXPECTED_RETURN_FIFTY":{
                    return "下单推荐人增加50%的贷款预期收益";
                }
                case "ANDROID_FORCE_UPGRADE":{
                    return "安卓APP是否强制更新";
                }
                case "SHARE_PATH":{
                    return "分享地址";
                }
                case "SHARE_TITLE":{
                    return "分享标题";
                }
                case "SHARE_ICON":{
                    return "分享图标";
                }
                case "SHARE_DESCRIPTION":{
                    return "分享描述";
                }
                case "SHARE_SHORTMESSAGE_CONTENT":{
                    return "分享短信内容";
                }
                case "FINACIAL_FREE_RATE":{
                    return "提现手续费";
                }
            }
            return state;
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
                    <li><a href="#">系统配置 </a></li>
                    <li><a href="#">系统配置 </a></li>
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
                    <h2>系统配置 </h2>
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
            <!-- End .toolbar -->

                <div class="spacer-10"></div>

                <div class="toolbar responsive-helper">
                    <header>
                        <div class="pull-left">
                            <h3>系统配置 </h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:void(0);"  onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;修改</a>
                            </div>
                            <div class="btn-group">
                                <a href="toAddProfile.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;安卓版本更新</a>
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
                            <kendo:grid-column title="ID" field="fileId" width="30px"/>
                            <kendo:grid-column title="名称" field="profileName" template="#=getprofileName(profileName)#" width="200px"/>
                            <kendo:grid-column title="值" field="fileValue" template="#=getprofileValue(fileValue,isPicture)#" width="200px"/>
                            <kendo:grid-column title="备注" field="remarks"  width="200px"/>
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
                                <kendo:dataSource-transport-read url="pageProfile.do" type="POST"
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
