<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
