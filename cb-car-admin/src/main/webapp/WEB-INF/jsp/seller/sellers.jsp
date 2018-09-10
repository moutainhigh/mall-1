<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>商家查询</title>
  <script type="application/javascript">
    function editItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "toEditSeller.do?sellerId=" + dataItem.sellerId;
      }
    }

    function removeItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        bootbox.confirm("确定删除吗？", function (result) {
          if (result) {
            $.get("removeSellerById.do", {
              sellerId: dataItem.sellerId
            }, function (data) {
              if (data) {
                commonNotify("删除成功！", "success");
                $("#grid").data("kendoGrid").dataSource.read();
              } else {
                commonNotify("删除失败,商家下面有商品!", "error");
              }
            });
          }
        });
      }
    }

    function detailItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "sellerDetail.do?sellerId=" + dataItem.sellerId;
      }
    }

    function formatSellerType(sellerType) {
      switch (sellerType) {
        case "SELF_OPERATION":
          return "平台自营";
        case "SELLER":
          return "商家";
      }
    }

    function formatChannelType(channelType) {
      switch (channelType) {
        case "UNIONPAY":
          return "银联";
        case "ALIPAY":
          return "支付宝";
        case "TENPAY":
          return "财付通";
      }
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
          <li><a href="#">首页</a></li>
          <li><a href="#">商家管理</a></li>
          <li><a href="#">商家查询</a></li>
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
          <h2>商家查询</h2>
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
        <div class="toolbar responsive-helper">
          <form style="width: 100%">
            <div class="pull-left">
              <div class="toolbar-field">
                <strong>商家编码:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" data-filter="sellerCode" data-operator="contains" class="form-control grid-filter" placeholder="请输入商家编码"/>
              </div>
              <div class="toolbar-field">
                <strong>商家名称:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" data-filter="sellerName" data-operator="contains" class="form-control grid-filter" placeholder="请输入商家名称"/>
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
              <h3>商家列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="javascript:void(0);"  onclick="detailItem()" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>
                <a href="toAddSeller.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                <a href="javascript:void(0);"  onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                <a href="javascript:void(0);"  onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
              </div>
            </div>
          </header>
        </div>
        <div class="table-wrapper">
          <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="450"  >
            <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
            <kendo:grid-columns>
              <kendo:grid-column title="商家名称" field="sellerName" width="100"/>
              <kendo:grid-column title="商家编码" field="sellerCode" width="100" filterable="false"/>
              <kendo:grid-column title="商家类型" field="sellerType" width="100" template="#=formatSellerType(sellerType)#"/>
              <kendo:grid-column title="商家地址" field="sellerAddress" width="150"/>
              <kendo:grid-column title="联系人" field="linkman" width="100"/>
              <kendo:grid-column title="手机" field="mobile" width="100"/>
              <kendo:grid-column title="联系电话" field="telephone" width="100"/>
              <kendo:grid-column title="邮箱" field="email" width="120"/>
              <kendo:grid-column title="QQ" field="qq" width="100"/>
              <kendo:grid-column title="商家微信" field="wechat" width="100"/>
              <kendo:grid-column title="商家支付平台类型" field="channelType" width="120" template="#=formatChannelType(channelType)#"/>
              <kendo:grid-column title="创建时间" field="createTime" width="120" format="{0:yyyy-MM-dd HH:mm:ss}"/>
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
                <kendo:dataSource-transport-read url="pageSellers.do" type="POST" contentType="application/json"/>
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
