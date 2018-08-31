<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>客户积分查询</title>
  <script type="application/javascript">

    function detailItem(){
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "customerDetail.do?customerId=" + dataItem.customerId;
      }
    }
    function formatSex(sex) {
      switch (sex) {
        case true:
          return "男";
        case false:
          return "女";
      }
    }

  </script>
</head>
<body>



  <jsp:include page="../layouts/left.jsp"/>
  <!-- End aside -->

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
            <li><a href="#">客户管理</a></li>
            <li class="active">客户积分查询</li>
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
            <h2>客户积分查询</h2>
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
          <div class="toolbar responsive-helper">
            <form style="width: 100%">
              <div class="pull-left">
                <div class="toolbar-field">
                  <strong>姓名:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" data-filter="customer.realName" data-operator="contains" class="form-control grid-filter" placeholder="请输入姓名"/>
                </div>
                <div class="toolbar-field">
                  <strong>手机号:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" data-filter="customer.mobile" data-operator="contains" class="form-control grid-filter" placeholder="请输入手机号"/>
                </div>
              </div><!-- End .pull-left -->
              <div class="pull-right">
                <div class="toolbar-field">
                  <button type="button" class="btn btn-default" onclick="reloadGridFilters('grid')"><i class="fa fa-search"></i>查询</button>
                  &nbsp;&nbsp;&nbsp;
                  <button type="button" class="btn btn-default" onclick="clearFilters('grid')">清空</button>
                </div>
              </div><!-- End .pull-right -->
            </form>
          </div><!-- End .toolbar -->

          <div class="spacer-10"></div>

          <div class="toolbar responsive-helper">
            <header>
              <div class="pull-left">
                <h3>积分列表</h3>
              </div>
              <div class="pull-right">
                <div class="btn-group">
                  <%--<a href="javascript:void(0);" onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>--%>
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
                <kendo:grid-column title="账户名" field="customer.accountName" width="150px" />
                <kendo:grid-column title="真实姓名" field="customer.realName" width="100px"/>
                <kendo:grid-column title="积分来源" field="origin" width="100px"/>
                <kendo:grid-column title="积分" field="score" width="100px"/>
                <kendo:grid-column title="积分时间" field="integralTime" width="150px" format="{0:yyyy-MM-dd HH:mm}" filterable="false" sortable="false"/>
                <kendo:grid-column title="备注" field="remark" width="150px" filterable="false" sortable="false"/>
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
                  <kendo:dataSource-transport-read url="pageIntegrals.do" type="POST" contentType="application/json"/>
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
      <!-- End .window -->

      <!-- ********************************************
           * FOOTER MAIN:                             *
           *                                          *
           * the part which contains things like      *
           * chat, buttons, copyright and             *
           * dropup menu(s).                          *
           ******************************************** -->

      <jsp:include page="../layouts/footer.jsp"/>
      <!-- End #footer-main -->
    </div>
    <!-- End #content -->
  </div>
  <!-- End #main -->


</body>
</html>
