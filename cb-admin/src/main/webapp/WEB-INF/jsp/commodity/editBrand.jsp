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
          var flag=${brand.hot};
          if(flag){
              $("#sort").show();
          }else {
              $("#sort").hide();
          }
      });
      function changeOtherMatter(flag) {
          if(flag){
              $("#sort").show();
          }else {
              $("#sort").hide();
          }
      }
      function submitForm() {
          var flag = $("input[name='otherMatter']:checked").val();
          if(flag=="false"){
              $("#hotSort").val(0);
          }
          return true;
      }
      function test($event) {
          console.log($event.value);
          var brandNo = $("#brandNo").val();
          var brandName = $("#brandName").val();
          //光标离开，ajax发送请求
          $.ajax({
              url:"checkBrandNoAndBrandName.do",
              type:"get",
              data:{"brandNo":brandNo,
                  "brandName":brandName
              },
              success:function(data){
                  //判断再显示
                  if(data == "failure"){
                      bootbox.alert("编码或名称重复请重新输入");
                      return false;
                  }
              },
              error:function(data){
                  if(data.responseText == "failure"){
                      bootbox.alert("编码或名称重复请重新输入");
                      return false;
                  }
              }
          });
      }

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
        <form:form id="validateSubmitForm" cssClass="form-horizontal" action="editBrand.do" method="post" commandName="brand" onsubmit="return submitForm();">
          <form:hidden path="brandId"/>
          <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
          <fieldset>
            <legend>编辑品牌</legend>
            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>品牌编码：</label>
                </div>
                <div class="col-sm-3">
                  <form:input path="brandNo" id="brandNo" type="text" onblur="test(this)" cssClass=" form-control validate[required,minSize[2]]" maxlength="32"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>品牌名称：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" id="brandName" onblur="test(this)" cssClass=" form-control validate[required,minSize[2]]" path="brandName" maxlength="5"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>
            <div class="spacer-10"></div>

            <div class="row">
              <div class="inline-labels">

                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>品牌关键字：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass=" form-control validate[required,minSize[2]]" path="brandKey" maxlength="32"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>品牌标题：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass=" form-control validate[required,minSize[2]]" path="brandTitle" maxlength="128"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>SEO关键字：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass=" form-control validate[required,minSize[2]]" path="seoKey" maxlength="255"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>SEO标题：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass=" form-control validate[required,minSize[2]]" path="seoTitle" maxlength="255"/>
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
                  <form:input type="text" cssClass=" form-control" path="brandEnName" maxlength="128"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label>品牌网址：</label>
                </div>
                <div class="col-sm-3">
                  <form:input type="text" cssClass=" form-control" path="website" maxlength="255"/>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>

            <div class="spacer-10"></div>

            <div class="row">

              <div class="col-sm-2">
                <label><span class="asterisk">*</span>运营分类：</label>
              </div>
              <div class="col-sm-3">
                <form:hidden id="categoryId" path="category.categoryId"/>
                <div class="input-group">
                  <form:input id="categoryName" readonly="true" cssClass=" form-control validate[required]" path="category.categoryName" maxlength="32"/>
                  <span class="input-group-btn">
                      <button id="categoryNameBtn" class="btn btn-default" type="button">选择</button>
                  </span>
                </div>
              </div>

              <div class="col-sm-1"></div>
              <div class="col-sm-2">
                <label><span class="asterisk">*</span>是否启用：</label>
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
                  <label><span class="asterisk">*</span>是否显示：</label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:radiobutton path="display" value="1"/>是
                    <form:radiobutton path="display" value="0"/>否
                  </div>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                  <label><span class="asterisk">*</span>是否热门：</label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <form:radiobutton path="hot" name="otherMatter" id="otherMatter" value="1" onclick="changeOtherMatter(true);"/>是
                    <form:radiobutton path="hot" name="otherMatter" id="otherMatter" value="0" onclick="changeOtherMatter(false);"/>否
                  </div>
                </div>
                <div class="col-sm-1"></div>
              </div>
            </div>
            <div class="row" id="sort" style="display: none;">
              <div class="inline-labels">
                <div class="col-sm-2">
                  <label>热门排序：<span class="asterisk"></span></label>
                </div>
                <div class="col-sm-3">
                  <div>
                    <form:select path="sort" cssClass=" form-control" id="hotSort">
                      <form:option value="0">0</form:option>
                      <form:option value="1">1</form:option>
                      <form:option value="2">2</form:option>
                      <form:option value="3">3</form:option>
                      <form:option value="4">4</form:option>
                      <form:option value="5">5</form:option>
                      <form:option value="6">6</form:option>
                      <form:option value="7">7</form:option>
                      <form:option value="8">8</form:option>
                    </form:select>
                  </div>
                </div>
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
                <form:textarea cssClass=" form-control" path="description" maxlength="512"></form:textarea>
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
                <form:textarea cssClass=" form-control" path="seoDescription" maxlength="512"></form:textarea>
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
                <form:textarea cssClass=" form-control" path="remark" maxlength="255"></form:textarea>
              </div>
              <div class="col-sm-1"></div>
            </div>

            <div class="spacer-10"></div>

            <div class="spacer-30"></div>
            <hr>
            <div class="spacer-30"></div>
            <div class="row">
              <div class="col-sm-2">
                <label><span class="asterisk">*</span>图片:</label>
              </div>
              <div class="col-sm-9">
                  <%--图片上传控件--%>
                    <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                    <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                    <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                    <script type="text/javascript">
                    $(function(){
                        $("#validateSubmitForm").validationEngine({
                            autoHidePrompt: true, scroll: false, showOneMessage: true,
                            onValidationComplete: function (form, valid) {
                                if (valid) {
                                    var defaultPicPath = $('input[name="imgurl"]');
                                    if (defaultPicPath.size()==0) {
                                        bootbox.alert("请至少选择一张图片!");
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        });
                        var initPreview = new Array();//展示元素
                        var initPreviewConfig = new Array();//展示设置
                        //初始化图片上传组件
                        $("#picUrl").fileinput({
                            uploadUrl: "/admin/uploads/uploadFile/BRAND.do",
                            showCaption: true,
                            minImageWidth: 50,
                            minImageHeight: 50,
                            showUpload:true, //是否显示上传按钮
                            showRemove :false, //显示移除按钮
                            showPreview :true, //是否显示预览
                            showClose: false,
                            showCaption:false,//是否显示标题
                            browseOnZoneClick: true,//是否显示点击选择文件
                            language: "zh" ,
                            showBrowse : false,
                            maxFileSize : 2000,
                            allowedFileExtensions: ["jpg", "png", "gif"],
                            autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                            overwriteInitial: false,//不覆盖已存在的图片
                            browseClass:"btn btn-primary", //按钮样式
                            // layoutTemplates:{
                            //     actionUpload:''    //设置为空可去掉上传按钮
                            // },
                            maxFileCount: 10  //上传的个数
                        }).on("fileuploaded", function (event, data) {
                            var response = data.response;
                            //添加url到隐藏域
                            var html='<input name="imgurl" type="hidden" id="'+response.timeStr+'" value="'+response.url+','+response.fileName+','+response.timeStr+'">';
                            $('#imgDiv').html($('#imgDiv').html()+html);
                            //上传完成回调
                            var index=0;
                            if(initPreview.length>0 ){
                                index=initPreview.length;
                            }
                            initPreview[index]  = response.url;
                            var config = new Object();
                            config.caption = "";
                            config.url="/admin/uploads/delete/BRAND.do";
                            config.key=response.timeStr;
                            initPreviewConfig[index]=config;
                            $("#picUrl").fileinput('refresh', {
                                initialPreview: initPreview,
                                initialPreviewConfig: initPreviewConfig,
                                initialPreviewAsData: true
                            });
                            $(".btn-default").attr("disabled",false);

                        }).on("filepredelete", function(jqXHR) {
                            var abort = true;
                            if (confirm("确定要删除吗？(删除后不会恢复)")) {
                                abort = false;
                            }
                            return abort;
                        }).on('filedeleted', function(event, id) {
                            $("#"+id).remove();
                            for (var i=0;i<initPreview.length;i++)
                            {
                                if(initPreview[i].indexOf(id) != -1){
                                    initPreview.splice(i)
                                    initPreviewConfig.splice(i)
                                }
                            }
                        }).on('filebatchselected', function (event, files) {//选中文件事件
                            $(".kv-file-upload").click();
                        });
                        //加载图片
                        var a='${listAttachment}';
                        var json=eval('(' + a + ')')
                        for(var i=0,l=json.length;i<l;i++){
                            initPreview[i]  = json[i].filePath;
                            var config = new Object();
                            config.caption = "";
                            config.url="/admin/uploads/delete/BRAND.do";
                            config.key=json[i].inputId;
                            initPreviewConfig[i]=config;
                            $("#picUrl").fileinput('refresh', {
                                initialPreview: initPreview,
                                initialPreviewConfig: initPreviewConfig,
                                initialPreviewAsData: true
                            });
                            var html='<input name="imgurl" type="hidden" id="'+json[i].inputId+'" value="'+json[i].filePath+','+json[i].fileName+','+json[i].inputId+'">';
                            $('#imgDiv').html($('#imgDiv').html()+html);
                        }
                    })
                </script>
                <input id="picUrl" name="file" type="file" class="file-loading" accept="image/*" multiple>
                <div id="imgDiv">
                </div>
                  <%--图片上传控件结束--%>
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
                  <button onclick="clearInput('form-control')" type="button" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
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
