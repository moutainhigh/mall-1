<%@ taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>编辑优惠券</title>
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
            <li><a href="#">优惠券管理</a></li>
            <li class="active">编辑优惠券</li>
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
            <h2>编辑优惠券</h2>
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
        <div class="inner-padding">
          <form data-asf-expireafter="1" data-asf-time="10">

            <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
            <fieldset>
              <legend>编辑优惠券</legend>
              <div class="row">
                <div class="col-sm-2">
                  <label>优惠券编码：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <input type="text" class="form-control" required="true" name="code" aria-required="true">
                </div>
                <div class="col-sm-2">
                  <label>优惠券名称：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <input type="text" class="form-control" required="true" name="name" aria-required="true">
                </div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>优惠券面值：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <input type="text" class="form-control" required="true" name="name" aria-required="true">
                </div>
                <div class="col-sm-2">
                  <label>最低消费：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <input type="text" class="form-control" required="true" name="name" aria-required="true">
                </div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>优惠券类型：<span class="asterisk">*</span></label>
                </div>
                <div class="col-sm-3">
                  <select class="form-control" required="" name="client">
                    <option value="">-- Select option --</option>
                    <option value="no">No</option>
                    <option value="yes">Yes</option>
                  </select>
                </div>
                <div class="col-sm-2">
                  <label>优惠券图片：</label>
                </div>
                <div class="col-sm-3">
                  <div class="fileupload fileupload-new" data-provides="fileupload">
                    <input type="hidden" value="" name=""/>
                    <div class="input-group">
                      <div class="uneditable-input">
                        <span class="fileupload-preview"/>
                      </div>
                        <span class="btn btn-default btn-file">
                        <span class="fileupload-new">选择文件</span>
                        <span class="fileupload-exists">重选</span>
                        <input type="file" name=""/>
                        </span>
                      <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">删除</a>
                    </div>
                  </div>
                  <div class="helper-text-box">
                  </div>
                </div>
              </div>
              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>开始时间</label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <kendo:dateTimePicker id="startTime" name="startTime" format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd','HH:mm:ss']" culture="zh-CN"></kendo:dateTimePicker>
                  </div>
                </div>
                <div class="col-sm-2">
                  <label>结束时间</label>
                </div>
                <div class="col-sm-3">
                  <div class="inline-labels">
                    <kendo:dateTimePicker id="endTime" name="endTime" format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd','HH:mm:ss']" culture="zh-CN"></kendo:dateTimePicker>
                  </div>
                </div>
              </div>
              <div class="spacer-30"></div>
              <hr>
              <div class="spacer-30"></div>
              <div class="row">
                <div class="col-sm-2">
                  <label>备注</label>
                </div>
                <div class="col-sm-10">
                  <textarea class="form-control" name="remark" aria-required="true"></textarea>
                </div>
              </div>
              <div class="spacer-10"></div>
              <div class="row">
                <div class="col-sm-12">

                  <button class="btn btn-default pull-right" type="reset">返回</button>
                  <button class="btn btn-primary pull-right" type="submit">提交</button>
                </div>
              </div>
            </fieldset>
          </form>
          <div class="spacer-30"></div>
          <div class="hr-totop"><span>Top</span></div>
          <div class="spacer-30"></div>

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
