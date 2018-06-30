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

  <title>供应商详情</title>
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
            <li><a href="#">供应商管理</a></li>
            <li><a href="#">供应商查询</a></li>
            <li class="active">供应商详情</li>
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
            <h2>供应商详情</h2>
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
          <ul class="ext-tabs">
            <li class="active">
              <a href="#content-tab-6-a">基本信息</a>
            </li>
            <li>
              <a href="#content-tab-6-b">账号</a>
            </li>
            <li>
              <a href="#content-tab-6-c">其他</a>
            </li>
          </ul><!-- End .ext-tabs -->
          <div class="tab-content">
            <div id="content-tab-6-a" class="tab-pane active">
              <div class="spacer-20"></div>
              <div class="row">
                <div class="col-sm-3">
                  <label>类型：</label>企业
                </div>
                <div class="col-sm-3">
                  <label>名称：</label>A科技公司
                </div>
                <div class="col-sm-3">
                  <label>机构编码：</label>1001
                </div>
                <div class="col-sm-3">
                  <label>法人：</label>admin
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <label>营业执照：</label><img src="../images/supplier/zhizhao.jpg" width="200px;">
                </div>
                <div class="col-sm-9">
                  <label>所在地：</label>广东省深圳市宝安区西乡
                </div>
              </div>

              <div class="row">
                <div class="col-sm-3">
                  <label>联系人：</label>admin
                </div>
                <div class="col-sm-3">
                  <label>手机：</label>18699999999
                </div>
                <div class="col-sm-3">
                  <label>固定电话：</label>0755-99999999
                </div>
                <div class="col-sm-3">
                </div>
              </div>
              <div class="row">
                <div class="col-sm-9">
                  <label>联系人地址：</label>广东省深圳市宝安区西乡
                </div>
                <div class="col-sm-3">
                  <label>邮编：</label>518000
                </div>
              </div>
              <div class="row">
                <div class="col-sm-9">
                  <label>备注：</label>
                </div>
              </div>
            </div>
            <div id="content-tab-6-b" class="tab-pane">
              <div class="toolbar responsive-helper">
                <form>
                  <div class="pull-left">

                    <div class="toolbar-field">
                      <strong>账号:</strong>
                    </div>
                    <div class="toolbar-field">
                      <input type="text" class="form-control" placeholder="请输入账号"/>
                    </div>
                    <div class="toolbar-field">
                      <strong>手机号:</strong>
                    </div>
                    <div class="toolbar-field">
                      <input type="text" class="form-control" placeholder="请输入手机号"/>
                    </div>
                  </div><!-- End .pull-left -->
                  <div class="pull-right">
                    <div class="toolbar-field">
                      <button type="button" class="btn btn-default"><i class="fa fa-search"></i>查询</button>
                      <button type="button" class="btn btn-default">清空</button>
                    </div>
                  </div><!-- End .pull-right -->
                </form>
              </div><!-- End .toolbar -->

              <div class="spacer-10"></div>
              <div class="toolbar responsive-helper">
                <header>
                  <div class="pull-left">
                    <h3>账号列表</h3>
                  </div>
                  <div class="pull-right">
                    <div class="dropdown">
                      <a href="#" data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                        操作
                        <i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="dropdown-menu ext-dropdown-icons-right pull-right" role="menu">
                        <li>
                          <a href="#"><i class="fa fa-trash-o"></i>新增</a>
                        </li>
                        <li>
                          <a href="#"><i class="fa fa-trash-o"></i>修改</a>
                        </li>
                        <li>
                          <a href="#"><i class="fa fa-trash-o"></i>删除</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </header>
              </div>
              <table class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th class="th-square" scope="col">
                    <label><input type="checkbox" class="checkbox-master"><span></span></label>
                  </th>
                  <th scope="col" data-rt-column="code">账号</th>
                  <th scope="col" data-rt-column="name">用户名</th>
                  <th scope="col" data-rt-column="name">手机</th>
                  <th scope="col" data-rt-column="address">Email</th>
                  <th scope="col" data-rt-column="address">性别</th>
                  <th scope="col" data-rt-column="address">创建时间</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td><label><input type="checkbox"><span></span></label></td>
                  <td>admin</td>
                  <td>admin</td>
                  <td>18688888888</td>
                  <td>admin@qq.com</td>
                  <td>男</td>
                  <td>2015-10-01 18:00</td>
                </tr>
                <tr>
                  <td><label><input type="checkbox"><span></span></label></td>
                  <td>admin</td>
                  <td>admin</td>
                  <td>18688888888</td>
                  <td>admin@qq.com</td>
                  <td>男</td>
                  <td>2015-10-01 18:00</td>
                </tr>
                <tr>
                  <td><label><input type="checkbox"><span></span></label></td>
                  <td>admin</td>
                  <td>admin</td>
                  <td>18688888888</td>
                  <td>admin@qq.com</td>
                  <td>男</td>
                  <td>2015-10-01 18:00</td>
                </tr>
                <jsp:include page="../common/common_page.jsp"/>
                </tbody>
              </table>
            </div>
            <div id="content-tab-6-c" class="tab-pane">



            </div>
          </div><!-- End .tab-content -->
          <div class="spacer-40"></div>
          <div class="hr-totop"><span>Top</span></div>
          <div class="spacer-10"></div>

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
