<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>站内信查询</title>
  <script type="application/javascript">

    function publishItem(published) {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {

        bootbox.confirm("确定发布吗？", function (result) {
            if (result) {
              $.get("publishSystemLetter.do", {
                letterId: dataItem.letterId,
                published: published,
                rad: Math.random()
              }, function (data) {
                switch(data){
                  case "success":{
                    commonNotify("操作成功！", "success");
                    $("#grid").data("kendoGrid").dataSource.read();
                    break;
                  }
                  case "published":{
                    bootbox.confirm("该站内信已经发布，请勿重复发布!", function (result) {});
                    $("#grid").data("kendoGrid").dataSource.read();
                    break;
                  }
                  default :{
                    commonNotify("操作失败!", "error");
                    $("#grid").data("kendoGrid").dataSource.read();
                    break;
                  }
                }
              });
            }
        });

      }
    }

    function editItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "toEditSystemLetter.do?letterId=" + dataItem.letterId;
      }
    }
    function detailItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        window.location.href = "toSystemLetterDetail.do?letterId=" + dataItem.letterId;
      }
    }

    function removeItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        bootbox.confirm("确定删除吗？", function (result) {
          if (result) {
            $.get("removeSystemLetterById.do", {
              letterId: dataItem.letterId,
              rad: Math.random()
            }, function (data) {
              if (data != 0) {
                commonNotify("删除成功！", "success");
                $("#grid").data("kendoGrid").dataSource.read();
              } else {
                commonNotify("删除失败!", "error");
              }
            });
          }
        });
      }
    }
    function formatPublishType(publishType){
      switch(publishType){
        case "ALL_CUSTOMER":
          return "所有用户";
        case "SPECFIC_CUSTOMER":
          return "指定用户";
      }
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
          <li><a href="#">运营管理</a></li>
          <li><a href="#">站内信查询</a></li>
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
          <h2>站内信查询</h2>
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
          <form style="width: 100%">
            <div class="pull-left">
              <div class="toolbar-field">
                <strong>站内信标题:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="title" data-operator="contains" class="form-control grid-filter" placeholder="请输入站内信标题"/>
              </div>
              <div class="toolbar-field">
                <strong>站内信内容:</strong>
              </div>
              <div class="toolbar-field">
                <input type="text" data-filter="content" data-operator="contains" class="form-control grid-filter" placeholder="请输入站内信内容"/>
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
              <h3>站内信列表</h3>
            </div>
            <div class="pull-right">
              <div class="btn-group">
                <a href="javascript:publishItem(true);" class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;发布</a>
              </div>
              <div class="btn-group">
                <a href="javascript:detailItem();"  class="btn btn-default"><i class="fa fa-info-circle"></i>&nbsp;详情</a>
                <a href="toAddSystemLetter.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
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
              <kendo:grid-column title="标题" field="title" width="350px" />
              <kendo:grid-column title="发布类型" field="publishType"  template="#=formatPublishType(publishType)#"/>
              <kendo:grid-column title="收件人" field="recipient"  />
              <kendo:grid-column title="内容" field="content"  />
              <kendo:grid-column title="发布状态" field="published" template="#= published ? '是' : '否' #"  />
              <kendo:grid-column title="发布时间" field="publishTime"  format="{0:yyyy-MM-dd HH:mm:ss}" />
              <%--<kendo:grid-column title="读取状态" field="readed" template="#= readed ? '已读' : '未读' #"  />--%>
              <kendo:grid-column title="创建时间" field="createTime"  format="{0:yyyy-MM-dd HH:mm:ss}" />
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
                <kendo:dataSource-transport-read url="pageSystemLetters.do" type="POST" contentType="application/json"/>
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
