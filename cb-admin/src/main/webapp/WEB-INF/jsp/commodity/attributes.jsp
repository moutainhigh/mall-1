<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">                       <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>属性</title>
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
            <li><a href="#">属性管理</a></li>
            <li class="active">属性</li>
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
            <h2>属性</h2>
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
          <div class="toolbar responsive-helper">
            <form>
              <div class="pull-left">
                <div class="toolbar-field">
                  <strong>属性名称:</strong>
                </div>
                <div class="toolbar-field">
                  <input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" type="text" class="form-control" placeholder="请输入属性名称"/>
                </div>
                <div class="toolbar-field">
                  <strong>属性组名称:</strong>
                </div>
                <div class="toolbar-field">
                  <input onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'')" type="text" class="form-control" placeholder="请输入属性组名称"/>
                </div>
                <div class="toolbar-field">
                  <strong>规格属性:</strong>
                </div>
                <div class="toolbar-field">
                  <select class="form-control simpleselect">
                    <option>全部</option>
                    <option>是</option>
                    <option>否</option>
                  </select>
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
                <h3>属性列表</h3>
              </div>
              <div class="pull-right">
                <div class="dropdown">
                  <a href="#" data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                    操作
                    <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="dropdown-menu ext-dropdown-icons-right pull-right" role="menu">
                    <li>
                      <a href="../attribute/attributeDetail.jsp"><i class="fa fa-info-circle"></i>详情</a>
                    </li>
                    <li>
                      <a href="../attribute/addAttribute.jsp"><i class="fa fa-plus"></i>新增</a>
                    </li>
                    <li>
                      <a href="../attribute/editAttribute.jsp"><i class="fa fa-edit"></i>修改</a>
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
              <th scope="col" data-rt-column="code">属性名称</th>
              <th scope="col" data-rt-column="code">属性组名称</th>
              <th scope="col" data-rt-column="name">是否规格属性</th>
              <th scope="col" data-rt-column="startTime">创建时间</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td><label><input type="checkbox"><span></span></label></td>
              <td>衣服属性</td>
              <td>衣服属性</td>
              <td>是</td>
              <td>2015-10-01 18:00</td>
            </tr>
            <tr>
              <td><label><input type="checkbox"><span></span></label></td>
              <td>衣服属性</td>
              <td>衣服属性</td>
              <td>是</td>
              <td>2015-10-01 18:00</td>
            </tr>
            <tr>
              <td><label><input type="checkbox"><span></span></label></td>
              <td>衣服属性</td>
              <td>衣服属性</td>
              <td>是</td>
              <td>2015-10-01 18:00</td>
            </tr>
            <tr>
              <td><label><input type="checkbox"><span></span></label></td>
              <td>衣服属性</td>
              <td>衣服属性</td>
              <td>是</td>
              <td>2015-10-01 18:00</td>
            </tr>
            <jsp:include page="../common/common_page.jsp"/>
            </tbody>
          </table>
        </div>

        <div class="spacer-30"></div>
        <div class="hr-totop"><span>Top</span></div>
        <div class="spacer-10"></div>

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
