<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="ie ie6 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 7]> <html class="ie ie7 lte9 lte8 lte7 no-js"> <![endif]-->
<!--[if IE 8]> <html class="ie ie8 lte9 lte8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie ie9 lte9 no-js"> <![endif]-->
<!--[if gt IE 9]> <html class="no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>编辑品牌</title>

  <script type="text/javascript">
    $(document).ready(function() {

      $("#validateSubmitForm").validationEngine({
        autoHidePrompt: true, scroll: false, showOneMessage: true,
        onValidationComplete: function (form, valid) {
          if (valid) {
            var defaultPicPath = $('input:radio[name="picPath"]:checked').val();
            if (defaultPicPath == null) {
              bootbox.alert("请至少选择一张品牌默认图片!");
              return false;
            } else {
              return true;
            }
          }
        }
      });

    });


  </script>
</head>
<body>


<!-- ********************************************
     * SIDEBAR MAIN:                            *
     *                                          *
     * the part which contains the main         *
     * navigation, logo, search and more...     *
     * (parts can be in both sidebars).         *
     ******************************************** -->

<jsp:include page="../layouts/left.jsp"/>
<!-- End aside -->

<!-- ********************************************
     * SIDEBAR SEC:                             *
     *                                          *
     * the part which contains things like      *
     * calendar, users, lists, blocks and       *
     * much more.                               *
     ******************************************** -->

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
          <li><a href="#">商品管理</a></li>
          <li><a href="#">品牌管理</a></li>
          <li class="active">编辑品牌</li>
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
          <h2>编辑品牌</h2>
        </div>
        <div class="pull-right">
          <a class="btn btn-default " href="brands.do"><i class="fa fa-reply"></i></a>
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
        <form:form id="validateSubmitForm" cssClass="form-horizontal" action="editBrand.do" method="post" commandName="brand" >
          <form:hidden path="brandId"/>
          <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
          <fieldset>
            <legend>编辑品牌</legend>
            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>品牌编码：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input path="brandNo" type="text" cssClass="form-control validate[required,minSize[2]]" maxlength="32"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>品牌名称：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="brandName" maxlength="128"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>
            <div class="spacer-10"></div>

            <div class="row">
              <div class="inline-labels">

                <div class="col-sm-2">
                  <label>品牌关键字：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="brandKey" maxlength="32"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>品牌标题：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control validate[required,minSize[2]]" path="brandTitle" maxlength="128"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>SEO关键字：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control" path="seoKey" maxlength="255"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>SEO标题：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control" path="seoTitle" maxlength="255"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>品牌英文名称：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control" path="brandEnName" maxlength="128"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>品牌网址：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass="form-control" path="website" maxlength="255"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">

              <div class="col-sm-2">
                <label>运营分类：<span class="asterisk">*</span></label>
              </div>
              <div class="col-sm-3">
                <form:hidden id="categoryId" path="category.categoryId"/>
                <div class="input-group">
                  <form:input id="categoryName" readonly="true" cssClass="form-control validate[required]" path="category.categoryName" maxlength="32"/>
                  <span class="input-group-btn">
                      <button id="categoryNameBtn" class="btn btn-default" type="button">选择</button>
                  </span>
                </div>
              </div>

              <div class="col-sm-1"></div>
              <div class="col-sm-2">
                <label>是否启用：<span class="asterisk">*</span></label>
              </div>
              <div class="col-sm-3">
                <div class="inline-labels">
                  <form:radiobutton path="enabled" value="1"/>是
                  <form:radiobutton path="enabled" value="0"/>否
                </div>
              </div>
              <div class="col-sm-1"></div>
            </div>

            <div class="spacer-10"></div>
            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>是否显示：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:radiobutton path="display" value="1"/>是
                    <form:radiobutton path="display" value="0"/>否
                  </div>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>是否热门：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:radiobutton path="hot" value="1"/>是
                    <form:radiobutton path="hot" value="0"/>否
                  </div>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>

            <div class="row">
              <div class="col-sm-2">
                <label>品牌描述：</label>
              </div>
              <div class="col-sm-9">
                <form:textarea cssClass="form-control" path="description" maxlength="512"></form:textarea>
              </div>
              <div class="col-sm-1"></div>
            </div>
            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>
            <div class="row">
              <div class="col-sm-2">
                <label>SEO描述：</label>
              </div>
              <div class="col-sm-9">
                <form:textarea cssClass="form-control" path="seoDescription" maxlength="512"></form:textarea>
              </div>
              <div class="col-sm-1"></div>
            </div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>
            <div class="row">
              <div class="col-sm-2">
                <label>备注</label>
              </div>
              <div class="col-sm-9">
                <form:textarea cssClass="form-control" path="remark" maxlength="255"></form:textarea>
              </div>
              <div class="col-sm-1"></div>
            </div>

            <div class="spacer-10"></div>
            <div class="inner-padding">
              <div class="row">
                <div class="col-sm-2">
                </div>
                <div class="col-sm-3">
                  <a id="chooseImageBtn" class="btn btn-default">添加图片</a>
                </div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                </div>
                <div class="col-sm-9">
                  <table id="imageTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                      <th class="text-center" width="120">默认封面图片</th>
                      <th scope="col">图片预览</th>
                      <th class="text-center" width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="imageTr-1">
                      <td class='text-center'><input type='radio' name='picPath' value='${brand.picPath}' cssClass='form-control' <c:if test="${!empty brand.picPath}">checked="true"</c:if>/></td>
                      <td><img src='..${PIC_PATH}${brand.picPath}' style='max-height:120px'/></td>
                      <td><a type='button' title='删除' class='btn btn-default' href='javascript:removeAttribute(-1)'><i class='fa fa-minus-circle'></i></a></td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
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

      var idIndex = 0;

      $('#chooseImageBtn').click(function (e) {
        $('#imageDialog').modal().css({
          width: 'auto'
        });

        e.preventDefault();
      });

      function chooseImage() {
        var imagePath = $("#imageFrame")[0].contentWindow.getSelectedPath();
        if (imagePath != null) {
          var newRow = "<tr id='imageTr" + idIndex + "'><td class='text-center'><input type='radio' name='picPath' value='" + imagePath + "'/></td><td><img src='.."+PIC_PATH + imagePath + "' style='max-height:120px'/></td><td class='text-center'><a type='button' title='删除' class='btn btn-default' href='javascript:removeImage(" + idIndex + ")'><i class='fa fa-minus-circle'></i></a></td></tr>";
          $("#imageTable tr:last").after(newRow);
          idIndex++;
          $('#imageDialog').modal("hide");
        } else {
          alert("请选择图片");
        }
      }

      function removeImage(indx) {
        $("#imageTr" + indx).remove();
      }
    </script>
  </div>
</div>

<div class="modal fade" id="categoryDialog" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择运营分类</h4>
      </div>
      <div class="modal-body">
        <div class="row" style="margin-top:-20px;margin-bottom:-20px">
          <div class="sidebar-module">
            <kendo:treeView name="treeview" select="onSelect">
              <kendo:dataSource data="${categoryTree}">
              </kendo:dataSource>
            </kendo:treeView>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>&nbsp;关闭</button>
        <button class="btn btn-primary pull-right" onclick="chooseCategory();"><i class="fa fa-check"></i>&nbsp;确认</button>
      </div>
    </div>
    <script type="application/javascript">
      var categoryId = 0;
      var categoryName = "";
      $('#categoryNameBtn').click(function (e) {
        $('#categoryDialog').modal();
        e.preventDefault();
      });

      function onSelect(e) {
        var data = $('#treeview').data('kendoTreeView').dataItem(e.node);
        categoryId = data.id;
        categoryName = data.text;
      }

      function chooseCategory() {
        $('#categoryDialog').modal("hide");
        $("#categoryId").val(categoryId);
        $("#categoryName").val(categoryName);
        //loadSpecs(catalogId);
      }
    </script>
  </div>
</div>

</body>
</html>
