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
        window.location.href = "toEditSupplier.do?supplierId=" + dataItem.supplierId;
      }
    }

    function removeItem() {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        bootbox.confirm("确定删除吗？", function (result) {
          if (result) {
            $.get("removeSupplierById.do", {
              supplierId: dataItem.supplierId,
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

    function enabledItem(enabled) {
      var dataItem = getSelectedGridItem("grid");
      if (dataItem) {
        $.get("enableEvaluateById.do", {
          evaluateId: dataItem.evaluateId,
          enabled: enabled,
          rad: Math.random()
        }, function (data) {
          if (data != 0) {
            commonNotify("操作成功！", "success");
            $("#grid").data("kendoGrid").dataSource.read();
          } else {
            commonNotify("操作失败!", "error");
          }
        });
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
                  <strong>商家类型:</strong>
                </div>
                <div class="toolbar-field">
                  <select class="form-control simpleselect">
                    <option>全部</option>
                    <option>企业</option>
                    <option>个人</option>
                  </select>
                </div>
                <div class="toolbar-field">
                  <strong>商家名称:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" class="form-control" placeholder="请输入商家名称"/>
                </div>
                <div class="toolbar-field">
                  <strong>机构代码:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" class="form-control" placeholder="请输入机构代码"/>
                </div>
                <div class="spacer-10"></div>
                <div class="toolbar-field">
                  <strong>联系人:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" class="form-control" placeholder="请输入联系人"/>
                </div>
                <div class="toolbar-field">
                  <strong>手机号:</strong>
                </div>
                <div class="toolbar-field">
                  <input type="text" class="form-control" placeholder="请输入手机号"/>
                </div>
              </div>
              <!-- End .pull-left -->
              <div class="pull-right">
                <div class="toolbar-field">
                  <button type="button" class="btn btn-default"><i class="fa fa-search"></i>查询</button>
                  <button type="button" class="btn btn-default">清空</button>
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
                <h3>供应商列表</h3>
              </div>
              <div class="pull-right">
                <div class="btn-group">
                  <a href="../supplier/supplierDetail.do" class="btn btn-default"><i class="fa fa-info-circle"></i>详情</a>
                  <a href="../supplier/toAddSupplier.do" class="btn btn-default"><i class="fa fa-plus-circle"></i>&nbsp;新增</a>
                  <a href="javascript:void(0);"  onclick="editItem()" class="btn btn-default"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                  <a href="javascript:void(0);"  onclick="removeItem()" class="btn btn-default"><i class="fa fa-trash-o"></i>&nbsp; 删除</a>
                </div>
              </div>
            </header>
          </div>
          <div class="table-wrapper">
            <%
              HashMap<String, Object> data = new HashMap<String, Object>();
              data.put("groupId", "#=groupId#");
            %>
            <kendo:grid name="grid" pageable="false" sortable="true" selectable="true" height="510"  >
              <kendo:grid-pageable refresh="true" pageSizes="true" buttonCount="5" pageSize="10"/>
              <kendo:grid-columns>
                <kendo:grid-column title="供应商名称" field="supplierName" width="350px" />
                <kendo:grid-column title="供应商编码*" field="supplierCode"  />
                <kendo:grid-column title="所在地" field="location"  />
                <kendo:grid-column title="备注" field="remark"  />
                <kendo:grid-column title="创建时间" field="createTime"  format="{0:yyyy-MM-dd HH:mm:ss}" />
              </kendo:grid-columns>
              <kendo:grid-dataBound>
                <script>
                  function onDataBound(arg) {
                  }
                </script>
              </kendo:grid-dataBound>
              <kendo:dataSource serverPaging="true" serverFiltering="true" serverSorting="true">
                <kendo:dataSource-schema data="content" total="totalElements">
                  <kendo:dataSource-schema-model>
                    <kendo:dataSource-schema-model-fields>
                      <kendo:dataSource-schema-model-field name="createTime" type="date"/>
                    </kendo:dataSource-schema-model-fields>
                  </kendo:dataSource-schema-model>
                </kendo:dataSource-schema>
                <kendo:dataSource-transport>
                  <kendo:dataSource-transport-read url="pageSuppliers.do" type="POST" contentType="application/json"/>
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
