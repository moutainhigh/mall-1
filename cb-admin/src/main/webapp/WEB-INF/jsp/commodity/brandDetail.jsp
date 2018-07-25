<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

  <title>品牌详情</title>

  <script type="text/javascript">
    $(document).ready(function() {

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

  <div id="sidebar-sec" class="sidebar">

    <div class="sidebar-sec-top"></div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

    <div class="sidebar-module">
      <form class="input-group">
        <input type="text" name="" class="form-control" placeholder="Type A head..." id="typeahead-sidebar-search"/>

        <div class="input-group-btn">
          <!-- can NOT be used with typeahead
          <a href="#" class="clear-input"><i class="fa fa-times-circle"></i></a>
          -->
          <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
        </div>
      </form>
    </div>
    <!-- End .sidebar-module -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <!-- * Tabs can be removed, if so dont forget * -->
    <!-- * to remove the .tab-pane divs(wrapper). * -->

    <ul class="ext-tabs-sidebar">
      <li class="active">
        <a href="#sidebar-tab-3"><i class="fa fa-group"></i> Users</a>
      </li>
      <li>
        <a href="#sidebar-tab-4"><i class="fa fa-check"></i> ToDo</a>
      </li>
    </ul>
    <!-- End .ext-tabs-sidebar -->
    <div class="tab-content">
      <div id="sidebar-tab-3" class="tab-pane active">

        <!-- ********** -->
        <!-- NEW MODULE -->
        <!-- ********** -->

        <div class="sidebar-module">
          <ul class="mini-list">
            <li>
              <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Steven Watson</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-2.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Maris Bradley</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-3.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Wyatt Brooke</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-4.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Elly Martel</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-5.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Martin Gardenar</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-6.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Debra Hopper</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
            <li>
              <img src="../images/users/user-7.jpg" alt="" class="avatar"/>
              <ul>
                <li><a href="#" class="bold">Nathan Rupertson</a></li>
                <li><a href="#">dummyemail@mail.com</a></li>
              </ul>
            </li>
          </ul>
          <!-- End .mini-list -->
        </div>
        <!-- End .sidebar-module -->
      </div>
      <div id="sidebar-tab-4" class="tab-pane">

        <!-- ********** -->
        <!-- NEW MODULE -->
        <!-- ********** -->

        <div class="sidebar-module">
          <div class="sidebar-todo">
            <div class="sidebar-todo-day">
              <h5>Due Today</h5>
              <ul>
                <li>
                  <label class="line-through">Start project X <input type="checkbox" name="" checked/><span></span></label>
                </li>
                <li>
                  <label>Email the invoice<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Call client T<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Take a coffe break<input type="checkbox" name=""/><span></span></label>
                </li>
              </ul>
            </div>
            <!-- End .sidebar-todo-day -->
            <div class="sidebar-todo-day">
              <h5>Due Tomorrow <span class="indicator-pill">32</span></h5>
              <ul>
                <li>
                  <label>Meeting with client T<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Meeting with client X<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label class="line-through">Buy new apple<input type="checkbox" name="" checked/><span></span></label>
                </li>
              </ul>
            </div>
            <!-- End .sidebar-todo-day -->
            <div class="sidebar-todo-day">
              <h5>Due Next Week</h5>
              <ul>
                <li>
                  <label>Start project T<input type="checkbox" name=""/><span></span></label>
                </li>
                <li>
                  <label>Buy new headphones<input type="checkbox" name=""/><span></span></label>
                </li>
              </ul>
            </div>
            <!-- End .sidebar-todo-day -->
          </div>
          <!-- End .sidebar-todo -->
        </div>
        <!-- End .sidebar-module -->
      </div>
    </div>
    <!-- End .tab-content -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

    <div class="sidebar-module">
      <div class="circular-stats">
        <div class="circular-stats-inner">
          <div class="circular-stats-data">
            <strong>2779</strong>
            <span>+ 31%</span>
          </div>
          <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                 value="31" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
        </div>
      </div>
      <!-- End .circular-stats -->
      <div class="circular-stats-infobox">
        <strong>This day</strong>
        <span>Lorem ipsum</span>
        <a href="#" class="btn btn-default">View</a>
      </div>
      <!-- End .circular-stats-infobox -->
      <div class="spacer-20"></div>
      <div class="circular-stats">
        <div class="circular-stats-inner">
          <div class="circular-stats-data">
            <strong>12899</strong>
            <span>+ 77%</span>
          </div>
          <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                 value="77" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
        </div>
      </div>
      <!-- End .circular-stats -->
      <div class="circular-stats-infobox">
        <strong>This month</strong>
        <span>Lorem ipsum</span>
        <a href="#" class="btn btn-default">View</a>
      </div>
      <!-- End .circular-stats-infobox -->
      <div class="spacer-20"></div>
      <div class="circular-stats">
        <div class="circular-stats-inner">
          <div class="circular-stats-data">
            <strong>82229</strong>
            <span>+ 89%</span>
          </div>
          <input class="knob" data-width="100" data-cursor="false" data-fgColor="#aaaaaa" data-bgColor="#cccccc" data-thickness=".20"
                 value="89" data-readOnly="true" data-angleOffset="-0" data-displayInput="false">
        </div>
      </div>
      <!-- End .circular-stats -->
      <div class="circular-stats-infobox">
        <strong>This year</strong>
        <span>Lorem ipsum</span>
        <a href="#" class="btn btn-default">View</a>
      </div>
      <!-- End .circular-stats-infobox -->
    </div>
    <!-- End .sidebar-module -->
  </div>
  <!-- End #sidebar-sec -->

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
            <li class="active">品牌详情</li>
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
            <h2>品牌详情</h2>
          </div>
          <div class="pull-right">
            <a class="btn btn-default" href="brands.do"><i class="fa fa-reply"></i></a>
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

            <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
            <fieldset>
              <legend>品牌详情</legend>
              <div class="row">
                <div class="inline-labels">
                  <div class="col-sm-2">
                    <label>品牌编码：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.brandNo}
                  </div>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-2">
                    <label>品牌名称：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.brandName}
                  </div>
                  <div class="col-sm-1"></div>
                </div>
              </div>
              <div class="spacer-10"></div>

              <div class="row">
                <div class="inline-labels">

                  <div class="col-sm-2">
                    <label>品牌关键字：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.brandKey}
                  </div>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-2">
                    <label>品牌标题：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.brandTitle}
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
                    <label>SEO关键字：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.seoKey}
                  </div>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-2">
                    <label>SEO标题：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.seoTitle}
                  </div>
                  <div class="col-sm-1"></div>
                </div>
              </div>

              <div class="spacer-10"></div>

              <div class="row">
                <div class="inline-labels">
                  <div class="col-sm-2">
                    <label>品牌英文名称：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.brandEnName}
                  </div>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-2">
                    <label>品牌网址：</label>
                  </div>
                  <div class="col-sm-3">
                    ${brand.website}
                  </div>
                  <div class="col-sm-1"></div>
                </div>
              </div>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>

              <div class="row">
                <div class="col-sm-2">
                  <label>品牌分类：</label>
                </div>
                <div class="col-sm-3">
                  ${brand.category.categoryName}
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-1"></div>
              </div>

              <div class="spacer-10"></div>
              <div class="row">
                <div class="inline-labels">
                  <div class="col-sm-2">
                    <label>是否显示：</label>
                  </div>
                  <div class="col-sm-3">
                    <div class="inline-labels">
                      <c:if test="${brand.display == true}">是</c:if>
                      <c:if test="${brand.display == false}">否</c:if>
                    </div>
                  </div>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-2">
                    <label>是否热门：</label>
                  </div>
                  <div class="col-sm-3">
                    <div class="inline-labels">
                      <c:if test="${brand.hot == true}">是</c:if>
                      <c:if test="${brand.hot == false}">否</c:if>
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
                  <label>品牌图片：</label>
                </div>
                <div class="col-sm-9">
                  <%--图片上传控件--%>
                  <link href="../js/plugins/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
                  <script src="../js/plugins/fileinput/fileinput.min.js" type="text/javascript"></script>
                  <script src="../js/plugins/fileinput/zh.js" type="text/javascript"></script>
                  <script type="text/javascript">
                      $(function(){
                          var initPreview = new Array();//展示元素
                          var initPreviewConfig = new Array();//展示设置
                          //初始化图片上传组件
                          $("#picUrl").fileinput({
                              uploadUrl: "/admin/uploads/uploadFile/COMMODITY.do",
                              showCaption: true,
                              minImageWidth: 50,
                              minImageHeight: 50,
                              showUpload:false, //是否显示上传按钮
                              showRemove :false, //显示移除按钮
                              showPreview :true, //是否显示预览
                              showCaption:false,//是否显示标题
                              browseOnZoneClick: false,//是否显示点击选择文件
                              language: "zh" ,
                              showBrowse : false,
                              maxFileSize : 2000,
                              autoReplace : false,//是否自动替换当前图片，设置为true时，再次选择文件， 会将当前的文件替换掉
                              overwriteInitial: false,//不覆盖已存在的图片
                              browseClass:"btn btn-primary", //按钮样式
                              layoutTemplates:{
                                  actionUpload:'',    //设置为空可去掉上传按钮
                                  actionDelete:''
                              },
                              maxFileCount: 10  //上传的个数
                          });
                          //加载图片
                          var a='${listAttachment}';
                          var json=eval('(' + a + ')')
                          for(var i=0,l=json.length;i<l;i++){
                              initPreview[i]  = json[i].filePath;
                              var config = new Object();
                              config.caption = "";
                              config.url="/admin/uploads/delete/COMMODITY.do";
                              config.key=json[i].timeStr;
                              initPreviewConfig[i]=config;
                              $("#picUrl").fileinput('refresh', {
                                  initialPreview: initPreview,
                                  initialPreviewConfig: initPreviewConfig,
                                  initialPreviewAsData: true
                              });
                              var html='<input name="imgurl" type="hidden" id="'+json.inputId+'" value="'+json[i].filePath+','+json[i].fileName+','+json[i].inputId+'">';
                              $('#imgDiv').html($('#imgDiv').html()+html);
                          }
                      })
                  </script>
                  <input id="picUrl"  name="file" type="file" class="file-loading" accept="image/*" multiple>
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
                <div class="col-sm-2">
                  <label>品牌描述：</label>
                </div>
                <div class="col-sm-9">
                  ${brand.description}
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
                  ${brand.seoDescription}
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
                  ${brand.remark}
                </div>
                <div class="col-sm-1"></div>
              </div>

              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>
              <div class="row">
                <div class="col-sm-12">
                  <div class="btn-group pull-right">
                    <a class="btn btn-default pull-right" href="brands.do">返回</a>
                  </div>
                </div>
              </div>
            </fieldset>
          <div class="spacer-40"></div>
          <div class="hr-totop"><span>Top</span></div>
          <div class="spacer-40"></div>

        </div>
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
