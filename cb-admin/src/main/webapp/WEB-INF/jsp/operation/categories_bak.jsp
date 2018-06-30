<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>分类管理</title>
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
            <li><a href="#">商品管理</a></li>
            <li class="active">分类管理</li>
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
            <h2>分类管理</h2>
          </div>
          <div class="pull-right">
            <div class="dropdown">
              <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-comments"></i>
                <span class="indicator-dot">3</span>
              </a>

              <div role="menu" class="dropdown-menu pull-center ext-dropdown-comments">
                <div class="ext-dropdown-header">
                  <i class="fa fa-comments"></i>
                  <h5>Comments</h5>
                  <a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>
                  <span class="indicator-dot">3</span>
                </div>
                <div class="ext-dropdown-comments-content">
                  <div>
                    <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
                    <a href="#">Karma, a good thing</a>
                    <ul>
                      <li><span>Posted by:</span> <a href="#">Steven</a></li>
                      <li><span>Date:</span> Dec 11, 2012</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Read</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <img src="../images/users/user-4.jpg" alt="" class="avatar"/>
                    <a href="#">A simple, fast way to reduce stress</a>
                    <ul>
                      <li><span>Posted by:</span> <a href="#">Linda</a></li>
                      <li><span>Date:</span> Dec 3, 2012</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Read</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <img src="../images/users/user-6.jpg" alt="" class="avatar"/>
                    <a href="#">Blog: karma and revenge</a>
                    <ul>
                      <li><span>Posted by:</span> <a href="#">Debra Hopper</a></li>
                      <li><span>Date:</span> Nov 18, 2012</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Read</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <span>No new comments</span>
                </div>
                <div class="ext-dropdown-footer">
                  <p>Updated: 11/12/2012 - 14:12</p>
                  <a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>
                </div>
              </div>
            </div>
            <!-- End .dropdown -->
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
            <div class="dropdown">
              <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-inbox"></i>
                <span class="indicator-dot">3</span>
              </a>

              <div role="menu" class="dropdown-menu pull-right ext-dropdown-inbox">
                <div class="ext-dropdown-header">
                  <h5>Inbox</h5>
                  <a href="#" class="btn btn-default btn-sm delete-master"><i class="fa fa-trash-o"></i></a>
                  <span class="indicator-dot">3</span>
                </div>
                <div class="ext-dropdown-inbox-content">
                  <div>
                    <a href="#">He did you get my new blog post?</a>
                    <ul class="nav">
                      <li><span>Send by:</span> <a href="#">Debra Hopper</a></li>
                      <li><span>Date:</span> Dec 12, 2012 - 14:03:09</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Reply</a> -
                        <a href="#">Read</a> -
                        <a href="#">Spam</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <a href="#">I really love your karma theme</a>
                    <ul class="nav">
                      <li><span>Send by:</span> <a href="#">Nathan Rupertson</a></li>
                      <li><span>Date:</span> Dec 3, 2012 - 22:44:12</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Reply</a> -
                        <a href="#">Read</a> -
                        <a href="#">Spam</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <div>
                    <a href="#">Feedback of a happy customer</a>
                    <ul class="nav">
                      <li><span>Send by:</span> <a href="#">Steven Watson</a></li>
                      <li><span>Date:</span> Dec 11, 2012 - 10:53:59</li>
                      <li>
                        <span>Actions:</span>
                        <a href="#">Reply</a> -
                        <a href="#">Read</a> -
                        <a href="#">Spam</a> -
                        <a href="#" class="delete">Delete</a>
                      </li>
                    </ul>
                  </div>
                  <span>No new emails</span>
                </div>
                <div class="ext-dropdown-footer">
                  <div class="progress bar-small">
                    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                      <span class="sr-only">60% Complete</span>
                    </div>
                  </div>
                  <p>60%</p>
                  <a href="#" class="btn btn-default btn-sm"><i class="fa fa-caret-right"></i></a>
                </div>
              </div>
            </div>
            <!-- End .dropdown -->
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

        <div class="spacer-10"></div>
        <div class="jquery-layout-wrapper" style="height:600px;">
          <div id="jquery-layout-1" class="jquery-layout">
            <div class="ui-layout-west">
              <div class="inner-padding">
                <div class="sidebar-module">
                  <ul class="easyfiletree">
                    <li class="eft-open"><i class="fa fa-folder-open"></i>Folder 1
                      <ul>
                        <li><i class="fa fa-file-text"></i>Item 1.1</li>
                        <li><i class="fa fa-file"></i>Item 1.2</li>
                        <li><i class="fa fa-link"></i><a href="#">Item 1.3 (link)</a></li>
                      </ul>
                    </li>
                    <li class="eft-open"><i class="fa fa-folder-open"></i>Folder 2
                      <ul>
                        <li class="eft-open"><i class="fa fa-folder-open"></i>Subfolder 2.1
                          <ul>
                            <li><i class="fa fa-file"></i>File 2.1.1</li>
                            <li><i class="fa fa-picture-o"></i>File 2.1.2</li>
                          </ul>
                        </li>
                        <li><i class="fa fa-file"></i>File 2.2</li>
                      </ul>
                    </li>
                    <li class="eft-closed"><i class="fa fa-folder"></i>Folder 3 (closed)
                      <ul>
                        <li><i class="fa fa-file"></i>File 3.1</li>
                      </ul>
                    </li>
                    <li><i class="fa fa-link"></i><a href="#">File 4 (link)</a></li>
                    <li><i class="fa fa-file-text"></i>File 5</li>
                    <li><i class="fa fa-file"></i>File 6</li>
                  </ul>
                </div><!-- End .sidebar-module -->
              </div>
            </div>


            <div class="ui-layout-center">
              <div class="spacer-10"></div>
              <ul class="ext-tabs">
                <li class="active">
                  <a href="#content-tab-6-a">详情</a>
                </li>
                <li>
                  <a href="#content-tab-6-b">修改</a>
                </li>
                <li>
                  <a href="#content-tab-6-c">新建</a>
                </li>
              </ul><!-- End .ext-tabs -->
              <div class="tab-content">
                <div class="spacer-10"></div>
                <div id="content-tab-6-a" class="tab-pane active">
                  <fieldset>
                    <legend>分类详情</legend>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>分类名称：</label>
                      </div>
                      <div class="col-sm-9">
                        <p>鞋类</p>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>编码：</label>
                      </div>
                      <div class="col-sm-9">
                        <p>001</p>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>父分类名称：</label>
                      </div>
                      <div class="col-sm-9">
                        <p>鞋类</p>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>排序：</label>
                      </div>
                      <div class="col-sm-9">
                        <p>1</p>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>创建时间：</label>
                      </div>
                      <div class="col-sm-9">
                        <p>2015-12-5 18:00</p>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>是否启用：</label>
                      </div>
                      <div class="col-sm-9">
                        <p>是</p>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>备注：</label>
                      </div>
                      <div class="col-sm-9">
                        <p>&nbsp;</p>
                      </div>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                      <div class="col-sm-12">
                        <button class="btn btn-primary pull-right" type="button">删除</button>
                      </div>
                    </div>
                  </fieldset>

                </div>
                <div id="content-tab-6-b" class="tab-pane">
                  <fieldset>
                    <legend>修改分类</legend>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>分类名称：</label>
                      </div>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" required="true" name="code" value="鞋类" aria-required="true">
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>编码：</label>
                      </div>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" required="true" name="code" value="001" aria-required="true">
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>父分类名称：</label>
                      </div>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" required="true" name="code" value="鞋类" aria-required="true">
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>排序：</label>
                      </div>
                      <div class="col-sm-9">
                        <select class="form-control">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                        </select>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>创建时间：</label>
                      </div>
                      <div class="col-sm-9">
                        <kendo:dateTimePicker id="startTime" name="startTime" format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd','HH:mm:ss']" culture="zh-CN"></kendo:dateTimePicker>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>是否启用：</label>
                      </div>
                      <div class="col-sm-9">
                        <div class="inline-labels">
                          <label><input type="radio" value="" name="popular"><span></span>是</label>
                          <label><input type="radio" checked="checked" value="" name="popular"><span></span>否</label>
                        </div>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>备注：</label>
                      </div>
                      <div class="col-sm-9">
                        <textarea class="form-control"></textarea>
                      </div>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                      <div class="col-sm-3"></div>
                      <div class="col-sm-9">
                        <button class="btn btn-primary pull-right" type="button">提交</button>
                      </div>
                    </div>
                  </fieldset>
                </div>
                <div id="content-tab-6-c" class="tab-pane">
                  <fieldset>
                    <legend>新建分类</legend>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>分类名称：</label>
                      </div>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" required="true" name="code" value="" aria-required="true">
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>编码：</label>
                      </div>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" required="true" name="code" value="" aria-required="true">
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>父分类名称：</label>
                      </div>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" required="true" name="code" value="" aria-required="true">
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>排序：</label>
                      </div>
                      <div class="col-sm-9">
                        <select class="form-control">
                          <option>1</option>
                          <option>2</option>
                          <option>3</option>
                        </select>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>创建时间：</label>
                      </div>
                      <div class="col-sm-9">
                        <kendo:dateTimePicker id="endTime" name="endTime" format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd','HH:mm:ss']" culture="zh-CN"></kendo:dateTimePicker>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>是否启用：</label>
                      </div>
                      <div class="col-sm-9">
                        <div class="inline-labels">
                          <label><input type="radio" value="" name="popular"><span></span>是</label>
                          <label><input type="radio" checked="checked" value="" name="popular"><span></span>否</label>
                        </div>
                      </div>
                    </div>
                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>价格段：</label>
                      </div>
                      <div class="col-sm-9">
                        <select class="form-control" required="true" name="priceSeg" aria-required="true">
                          <option>0-99</option>
                          <option>100-199</option>
                          <option>200-299</option>
                          <option>300-399</option>
                          <option>不限</option>
                        </select>
                      </div>
                    </div>

                    <div class="spacer-10"></div>
                    <div class="row">
                      <div class="col-sm-3">
                        <label>备注：</label>
                      </div>
                      <div class="col-sm-9">
                        <textarea class="form-control"></textarea>
                      </div>
                    </div>
                    <div class="spacer-30"></div>
                    <hr>
                    <div class="spacer-30"></div>
                    <div class="row">
                      <div class="col-sm-3"></div>
                      <div class="col-sm-9">
                        <button class="btn btn-primary pull-right" type="button">提交</button>
                      </div>
                    </div>
                  </fieldset>

                </div>
              </div><!-- End .tab-content -->
            </div>

          </div>
        </div><!-- End .jquery-layout-wrapper -->

        <div class="spacer-30"></div>
        <div class="hr-totop"><span>Top</span></div>
        <div class="spacer-30"></div>

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
