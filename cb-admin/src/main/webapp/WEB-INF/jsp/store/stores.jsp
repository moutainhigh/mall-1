<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>仓库查询</title>
  <script type="application/javascript">
    function editItem(){
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "toEditStore.do?storeId=" + dataItem.storeId;
      }
    }

    function removeItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        bootbox.confirm("确认删除吗？", function (result) {
          if (result) {
            $.get("removeStoreById.do", {
              storeId: dataItem.storeId
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

    function formatProvince(province){
      var provinceStr = province.replace(province,$.citySelector.getProvince(province));
      return provinceStr;
    }

    function formatCity(city){
      var cityStr = city.replace(city,$.citySelector.getCity(city));
      return cityStr;
    }

    function formatDistrict(district){
      var districtStr = district.replace(district,$.citySelector.getDistrict(district));
      return districtStr;
    }
  </script>
</head>
<body>

<jsp:include page="../layouts/left.jsp"/>

<jsp:include page="../layouts/sidebarRight.jsp"/>

<div id="main" class="clearfix">

  <!-- ********************************************
       * MAIN HEADER:                             *
       *                                          *
       * the part which contains the breadcrumbs, *
       * dropdown menus, toggle sidebar button    *
       ******************************************** -->

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
          <li><a href="#">库存管理</a></li>
          <li><a href="#">仓库查询</a></li>
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
          <h2>仓库查询</h2>
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

    <!-- ********************************************
         * WINDOW:                                  *
         *                                          *
         * the part which contains the main content *
         ******************************************** -->

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
                <strong>仓库编码:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="storeCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入仓库名称"/>
              </div>
              <div class="toolbar-field">
                <strong>仓库名称:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="storeName" data-operator="contains" class="form-control grid-filter" placeholder="请输入仓库编码"/>
              </div>
              <div class="spacer-10"></div>
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
              <h3>仓库列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="toAddStore.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                <a href="javascript:editItem();"  class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                <a href="javascript:removeItem();"  class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
              </div>
            </div>
          </header>
        </div>
        <div class="table-wrapper">
          <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"  >
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-columns>
              <kendo:grid-column title="仓库编码" field="storeCode"  />
              <kendo:grid-column title="仓库名" field="storeName" width="150px" />
              <kendo:grid-column title="省" field="province" template="#= formatProvince(province)#"/>
              <kendo:grid-column title="市" field="city" template="#= formatCity(city)#"/>
              <kendo:grid-column title="区" field="district" template="#= formatDistrict(district)#"/>
              <kendo:grid-column title="地址" field="address"  />
              <kendo:grid-column title="邮编" field="post"  />
              <kendo:grid-column title="备注" field="remark"  />
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
                <kendo:dataSource-transport-read url="pageStores.do" type="POST" contentType="application/json"/>
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

</body>
</html>
