<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>保险合同编号管理</title>

    <script type="text/javascript">

        $(document).ready(function () {
            $("#createTime").kendoDatePicker({
                format: "yyyy-MM-dd",
                culture: "zh-CN",
                parseFormats: ["yyyy-MM-dd"]
            });
            $("#createTimes").kendoDatePicker({
                format: "yyyy-MM-dd",
                culture: "zh-CN",
                parseFormats: ["yyyy-MM-dd"]
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
                    <li><a href="#">保单管理</a></li>
                    <li class="active"><a href="#">保险合同编号管理</a></li>
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
                    <h2>保险合同编号管理</h2>
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
                </div>
            </div>
            <!-- End .actionbar-->
            <div class="inner-padding">
                <div class="toolbar responsive-helper">
                    <form style="width: 100%">
                        <div class="pull-left">
                            <div class="toolbar-field">
                                <strong>合同编号:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input type="text" data-filter="codeNo" data-operator="contains"
                                       class="form-control grid-filter" placeholder="请写合同编号"/>
                            </div>
                            <div class="toolbar-field">
                                <strong>创建时间:</strong>
                            </div>
                            <div class="toolbar-field">
                                <input name="createTime" id="createTime" placeholder="请选择开始时间" data-filter="createTime"
                                       data-operator="gte" class="form-control grid-filter"/>
                            </div>

                            <div class="toolbar-field">
                                <strong>-</strong>
                            </div>
                            <div class="toolbar-field">
                                <input name="createTime" id="createTimes" placeholder="请选择结束时间" data-filter="createTime"
                                       data-operator="lte" class="form-control grid-filter"/>
                            </div>
                        </div>
                        <!-- End .pull-left -->
                        <div class="pull-right">
                            <div class="toolbar-field">
                                <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i
                                        class="fa fa-search"></i>查询
                                </button>
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
                            <h3>保险合同编号管理</h3>
                        </div>
                        <div class="pull-right">
                            <div class="btn-group">
                                <a id="chooseImageBtn" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;导入</a>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="table-wrapper">
                    <kendo:grid name="grid" pageable="true" sortable="true" filterable="true" selectable="true"
                                height="450" resizable="true">
                        <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
                        <kendo:grid-filterable extra="false">
                            <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
                            <kendo:grid-filterable-operators>
                                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
                            </kendo:grid-filterable-operators>
                        </kendo:grid-filterable>
                        <kendo:grid-columns>
                            <kendo:grid-column title="ID" field="codeId" width="100px"/>
                            <kendo:grid-column title="合同编号" field="codeNo" width="100px"/>
                            <kendo:grid-column filterable="false" title="是否使用" field="useed" width="100px"
                                               template="#= useed ? '是' : '否' #"/>
                            <kendo:grid-column filterable="false" title="创建时间" field="createTime"
                                               format="{0:yyyy-MM-dd HH:mm}" width="100"/>
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
                                <kendo:dataSource-transport-read url="pageInsuranceOrderCode.do" type="POST"
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
        <div class="modal fade" id="imageDialog" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" style="width: 1100px;height: 600px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">选择EXCEL</h4>
                    </div>
                    <div class="modal-body" style="padding-bottom: 80px">
                        <script type="text/javascript">
                        function upload(){
                            var formData = new FormData();
                            formData.append("file", $('#upfile')[0].files[0]);
                            $.ajax({
                                url: "/admin/insuranceordercode/uploadPayerCreditInfoExcel.do",
                                type: 'POST',
                                cache: false,
                                data: formData,
                                processData: false,
                                contentType: false,
                                beforeSend : function(){     //请求成功前触发的局部事件
                            },
                            success: function (result) {
                                bootbox.alert("上传成功！");
                                setTimeout("window.location.reload()",500);
                            },
                            error: function (err) {
                                bootbox.alert("上传失败，请检查文件格式");
                            }
                            });
                        }
                        </script>
                            <div style="float: left">
                                <input id="upfile" type="file" name="upfile">
                            </div>
                            <div style="float: left;margin-left: 50px">
                                <button class="btn btn-primary" onclick="upload()">导入</button>
                                <button class="btn btn-primary" type="submit">导入</button>
                                <a class="btn btn-primary" href="../templates/excel/insurance.xlsx">模板下载</a>
                            </div>
                    </div>
                </div>
                <script type="application/javascript">
                    var selectType = "icon";
                    $('#chooseImageBtn').click(function (e) {
                        selectType = "image";
                        $('#imageDialog').modal().css({
                            width: 'auto'
                        });
                        e.preventDefault();
                    });
                </script>
            </div>
        </div>
    </div>
</div>
</body>
</html>
