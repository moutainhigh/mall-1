<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>新增活动</title>

  <script type="text/javascript">
    $(document).ready(function() {

      $("#activityCategoryIds").select2();

      $("input:radio[name='limitAmount']:checked").each(function() {
        if($(this).attr('value')!=0){
          $("#amountSizeDiv").show();
        }else{
          $("#amountSizeDiv").hide();
        }
      });

      $("input:radio[name='limitAmount']").change(function() {
        if($(this).attr('value')!=0){
          $("#amountSizeDiv").show();
        }else{
          $("#amountSizeDiv").hide();
        }
      });

      $("#startTime").kendoDateTimePicker({
        format: "yyyy-MM-dd HH:mm:ss",
        value:new Date(),
        min: new Date(),
        culture:"zh-CN",
        parseFormats: ["yyyy-MM-dd", "HH:mm:ss"]
      });

      $("#endTime").kendoDateTimePicker({
        format: "yyyy-MM-dd HH:mm:ss",
        value:kendo.toString(new Date($("#startTime").val()), "yyyy-MM-dd HH:mm:ss"),
        min: kendo.toString(new Date($("#startTime").val()), "yyyy-MM-dd HH:mm:ss"),
        culture:"zh-CN",
        parseFormats: ["yyyy-MM-dd", "HH:mm:ss"]
      });

      $("#ruleIds").select2();
      $("#validateSubmitForm").validationEngine({
        autoHidePrompt: true, scroll: false, showOneMessage: true,
        onValidationComplete: function (form, valid) {
          if (valid) {

            if (null == $("#ruleIds").val() || "" == $("#ruleIds").val()) {
              bootbox.alert("请选择活动规则!");
              return false;
            }

            if (null == $("#startTime").val() || "" == $("#startTime").val()) {
              bootbox.alert("请选择开始时间!");
              return false;
            }

            if (null == $("#endTime").val() || "" == $("#endTime").val()) {
              bootbox.alert("请选择结束时间!");
              return false;
            }

            if ($("#startTime").val() >= $("#endTime").val()) {
              bootbox.alert("结束时间必须大于开始时间，请重新设置!");
              return false;
            }
            if (null == $("#imagePath").val() || "" == $("#imagePath").val()) {
              bootbox.alert("请选择活动图片!");
              return false;
            }
            return true;

          }
        }
      });

    });


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
            <li><a href="#">活动推广</a></li>
            <li><a href="#">活动管理</a></li>
            <li class="active">新增活动</li>
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

      <!-- ********************************************
           * HEADER SEC:                              *
           *                                          *
           * the part which contains the page title,  *
           * buttons and dropdowns.                   *
           ******************************************** -->

      <header id="header-sec">
        <div class="inner-padding">
          <div class="pull-left">
            <h2>新增活动</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default " href="activities.do"><i class="fa fa-reply"></i></a>
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
          <form:form id="validateSubmitForm" action="addActivity.do" cssClass="form-horizontal" method="post" commandName="activity">

            <fieldset>
              <legend>新增活动</legend>

              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 活动编码：</label>
                </div>
                <div class="col-sm-3">
                  <form:input cssClass="form-control validate[required,minSize[2]]" path="activityCode" maxlength="32"/>
                </div>

                <div class="col-sm-2">
                  <label>活动别名：</label>
                </div>
                <div class="col-sm-3">
                  <form:input cssClass="form-control validate[minSize[2]]" path="activityAlias" maxlength="32"/>
                </div>
              </div>
              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 活动名称：</label>
                </div>
                <div class="col-sm-3">
                  <form:input cssClass="form-control validate[required,minSize[2]]" path="activityName" maxlength="128"/>
                </div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 活动规则：</label>
                </div>
                <div class="col-sm-3">
                  <form:select cssClass="simpleselect" id="ruleIds" path="ruleCondition.ruleId" multiple="false" items="${activityRules}" itemLabel="ruleName" itemValue="ruleId"/>
                </div>
              </div>
              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 活动范围：</label>
                </div>
                <div class="col-sm-3">
                  <form:select cssClass="form-control simpleselect validate[required]" path="activityScope">
                    <form:options items="${activityScope}" itemLabel="name"/>
                  </form:select>
                </div>

                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 活动类型：</label>
                </div>
                <div class="col-sm-3">
                  <form:select cssClass="form-control simpleselect validate[required]" path="activityType">
                    <form:options items="${activityType}" itemLabel="name"/>
                  </form:select>
                </div>
              </div>

              <%--<div class="row">--%>
                <%--<div class="col-sm-2">--%>
                  <%--<label>运营分类：<span class="asterisk">*</span></label>--%>
                <%--</div>--%>
                <%--<div class="col-sm-3">--%>
                  <%--&lt;%&ndash;<form:hidden id="categoryId" path="category.categoryId"/>&ndash;%&gt;--%>
                  <%--<div class="input-group">--%>
                    <%--&lt;%&ndash;<form:input id="categoryName" readonly="true" cssClass="form-control validate[required]" path="category.categoryName" maxlength="32"/>&ndash;%&gt;--%>
                    <%--<input id="categoryName" class="form-control"  />--%>
                  <%--<span class="input-group-btn">--%>
                      <%--<button id="categoryNameBtn" class="btn btn-default" type="button">选择</button>--%>
                  <%--</span>--%>
                  <%--</div>--%>
                <%--</div>--%>

                <%--<div class="col-sm-1"></div>--%>
                <%--<div class="col-sm-1"></div>--%>
              <%--</div>--%>

              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label>运营分类：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:select cssClass="simpleselect " id="activityCategoryIds" path="activityCategoryIds"  items="${categoryTree}" itemLabel="categoryName" itemValue="categoryId"/>
                </div>

                <div class="col-sm-1"></div>
                <div class="col-sm-1"></div>
              </div>


              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label>限制参与商品数量：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:radiobutton path="limitAmount" value="1"/>是
                  <form:radiobutton path="limitAmount" value="0"/>否
                </div>
                <div id="amountSizeDiv">
                  <div class="col-sm-2">
                    <label>参与商品数量：</label>
                  </div>
                  <div class="col-sm-3">
                    <form:input path="limitAmountSize" cssClass="form-control validate[custom[number]]" maxlength="4"/>
                  </div>
                </div>

              </div>

              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 开始时间：</label>
                </div>
                <div class="col-sm-3">
                  <form:input path="startTime"/>
                </div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 结束时间：</label>
                </div>
                <div class="col-sm-3">
                  <form:input path="endTime"/>
                </div>
              </div>
              <div class="spacer-10"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span> 活动图片：</label>
                </div>
                <div class="col-sm-3">
                  <a id="chooseImageBtn" class="btn btn-default">选择</a>
                  <form:hidden path="picPath" id="imagePath"/>
                </div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>图片预览：</label>
                </div>
                <div class="col-sm-8">
                  <img id="previewImagePath" src="" style="max-width:600px ">
                </div>
              </div>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>备注：</label>
                </div>
                <div class="col-sm-8">
                  <form:textarea class="form-control" path="remark" maxlength="255"></form:textarea>
                </div>
                <div class="col-sm-1"></div>
              </div>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>

              <div class="row">
                <div class="col-sm-12">
                  <div class="btn-group pull-right">
                    <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
                    <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
                  </div>
                </div>
              </div>
            </fieldset>
          </form:form>

          <div class="spacer-40"></div>
          <div class="hr-totop"><span>Top</span></div>
          <div class="spacer-40"></div>

        </div>
        <!-- End .inner-padding -->
      </div>
      <!-- End .window -->


      <jsp:include page="../layouts/footer.jsp"/>
      <!-- End #footer-main -->
    </div>
    <!-- End #content -->
  </div>
  <!-- End #main -->
  <div class="modal fade" id="imageDialog" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 1100px;height: 600px;">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">选择图片</h4>
        </div>
        <div class="modal-body">
          <iframe id="imageFrame" src="../media/chooseMedias.do" style="width: 100%;height: 500px;border: none"></iframe>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default" data-dismiss="modal">关闭</button>
          <button class="btn btn-primary pull-right" onclick="chooseImage();">确认</button>
        </div>
      </div>
      <script type="application/javascript">
        $('#chooseImageBtn').click(function (e) {
          $('#imageDialog').modal().css({
            width: 'auto'
          });
          e.preventDefault();
        });

        function chooseImage() {
          var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
          if (imagePath != null) {
              $("#previewImagePath").attr("src", ".."+PIC_PATH + imagePath);
              $("#imagePath").val(imagePath);
              $('#imageDialog').modal("hide");
          } else {
            alert("请选择图片");
          }
        }
      </script>
    </div>
  </div>

  <%--<div class="modal fade" id="categoryDialog" tabindex="-1" role="dialog" aria-hidden="true">--%>
    <%--<div class="modal-dialog">--%>
      <%--<div class="modal-content">--%>
        <%--<div class="modal-header">--%>
          <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
          <%--<h4 class="modal-title">选择运营分类</h4>--%>
        <%--</div>--%>
        <%--<div class="modal-body">--%>
          <%--<div class="row" style="margin-top:-20px;margin-bottom:-20px">--%>
            <%--<div class="sidebar-module">--%>
              <%--<kendo:treeView name="treeview" select="onSelect">--%>
                <%--<kendo:dataSource data="${categoryTree}">--%>
                <%--</kendo:dataSource>--%>
              <%--</kendo:treeView>--%>
            <%--</div>--%>
          <%--</div>--%>
        <%--</div>--%>
        <%--<div class="modal-footer">--%>
          <%--<button class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;关闭</button>--%>
          <%--<button class="btn btn-primary pull-right" onclick="chooseCategory();"><i class="fa fa-check"></i>&nbsp;确认</button>--%>
        <%--</div>--%>
      <%--</div>--%>
      <%--<script type="application/javascript">--%>
        <%--var categoryId = 0;--%>
        <%--var categoryName = "";--%>
        <%--$('#categoryNameBtn').click(function (e) {--%>
          <%--$('#categoryDialog').modal();--%>
          <%--e.preventDefault();--%>
        <%--});--%>

        <%--function onSelect(e) {--%>
          <%--var data = $('#treeview').data('kendoTreeView').dataItem(e.node);--%>
          <%--categoryId = data.id;--%>
          <%--categoryName = data.text;--%>
        <%--}--%>

        <%--function chooseCategory() {--%>
          <%--$('#categoryDialog').modal("hide");--%>
          <%--$("#categoryId").val(categoryId);--%>
          <%--$("#categoryName").val(categoryName);--%>
          <%--//loadSpecs(catalogId);--%>
        <%--}--%>
      <%--</script>--%>
    <%--</div>--%>
  <%--</div>--%>

</body>
</html>
