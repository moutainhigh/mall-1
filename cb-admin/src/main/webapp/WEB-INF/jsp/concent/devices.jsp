<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>厨电设备</title>
  <script type="application/javascript">

    function filterConcent(concentId){
      $("#concentId").val(concentId);
      reloadGridFilters('grid');
    }

    function formatDeviceType(deviceType) {
      switch (deviceType) {
        case "STEAM_BOX":
          return "冰箱";
        case "OVEN":
          return "烤箱";
        case "DISINFECTION_CABINET":
          return "消毒柜";
        case "EXHAUST_HOOD":
          return "吸油烟机";
      }
    }

    function formatDeviceState(deviceState) {
      switch (deviceState) {
        case "WAIT_WORK":
          return "待工作";
        case "WORKING":
          return "工作中";
        case "WORK_END":
          return "工作结束";
        case "MALFUNCTION":
          return "工作故障";
      }
    }

    function editItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "toEditDevice.do?deviceId=" + dataItem.deviceId;
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
      <a href="#sidebar-tab-2"><i class="fa fa-folder"></i> PAD设备</a>
    </li>
  </ul>
  <div class="tab-content">
    <jsp:include page="../layouts/menu.jsp">
      <jsp:param name="active" value="active"/>
    </jsp:include>
    <div id="sidebar-tab-2" class="tab-pane active clearfix">
      <input type="hidden" id="concentId" data-filter="concent.concentId" data-operator="eq" class="grid-filter" value="0"/>
      <div class="sidebar-module">
        <ul class="easyfiletree">
          <c:forEach var="concent" items="${concents}">
              <li><i class="fa fa-file-text"></i><a href="javascript:filterConcent(${concent.concentId})">${concent.concentName}</a></li>
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
          <li><a href="#">设备管理</a></li>
          <li><a href="#">厨电设备</a></li>
        </ul>
        <!-- End .breadcrumb -->
      </div>
      <div class="pull-right">
        <p>Version 1.0.0</p>
      </div>
    </div>
    <!-- End #header-main-bottom -->
  </header>
  <!-- End #header-main -->

  <div id="content" class="clearfix">


    <header id="header-sec">
      <div class="inner-padding">
        <div class="pull-left">
          <h2>厨电设备</h2>
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
          <!-- End .btn-group -->

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

        <div class="spacer-10"></div>

        <div class="toolbar responsive-helper">
          <header>
            <div class="pull-left">
              <h3>厨电设备列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <%--<a href="javascript:detailItem();"  class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>--%>
                <a href="toAddDevice.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                <a href="javascript:editItem();"  class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                <%--<a href="javascript:removeItem();"  class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>--%>
              </div>
            </div>
          </header>
        </div>
        <div class="table-wrapper">
          <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"  filterable="true">
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-filterable extra="false">
              <kendo:grid-filterable-messages filter="查询" clear="清除" info="请输入查询条件:"/>
              <kendo:grid-filterable-operators>
                <kendo:grid-filterable-operators-string contains="包含" eq="等于"/>
                <kendo:grid-filterable-operators-date gte="小于" eq="等于" lte="大于"/>
              </kendo:grid-filterable-operators>
            </kendo:grid-filterable>
            <kendo:grid-columns>
              <kendo:grid-column title="设备ID编号" field="deviceCode" width="100" filterable="true"/>
              <kendo:grid-column title="设备类型" field="deviceType" width="100" filterable="true" template="#=formatDeviceType(deviceType)#"/>
              <kendo:grid-column title="设备运行状态" field="deviceState" width="100" filterable="true" template="#=formatDeviceState(deviceState)#"/>
              <kendo:grid-column title="生产日期" field="factoryDate" width="100" filterable="true" format="{0:yyyy-MM-dd HH:mm}"/>
              <kendo:grid-column title="注册日期" field="createTime" width="100" filterable="true" format="{0:yyyy-MM-dd HH:mm}"/>
              <kendo:grid-column title="设备版本号" field="version" width="100" filterable="true"/>
              <kendo:grid-column title="所属PAD设备名称" field="concent.concentName" width="150" filterable="true"/>
              <kendo:grid-column title="备注" field="remark" width="100"/>
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
                <kendo:dataSource-transport-read url="pageDevices.do" type="POST" contentType="application/json"/>
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

      <div class="spacer-30"></div>
      <div class="hr-totop"><span>Top</span></div>
      <div class="spacer-10"></div>

      <!-- End .inner-padding -->
    </div>

    <jsp:include page="../layouts/footer.jsp"/>
    <!-- End #footer-main -->
  </div>
  <!-- End #content -->
</div>
<!-- End #main -->

</body>
</html>
