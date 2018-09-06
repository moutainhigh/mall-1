<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside id="sidebar-main" class="sidebar">
    <jsp:include page="../layouts/sidebar_logo.jsp"/>
    <ul class="ext-tabs-sidebar">
        <li>
            <a href="#sidebar-tab-1"><i class="fa fa-bars"></i> 导航</a>
        </li>
        <li class="active">
            <a href="#sidebar-tab-2"><i class="fa fa-folder"></i> 栏目</a>
        </li>
    </ul>
    <div class="tab-content">
        <jsp:include page="../layouts/menu.jsp"/>
        <div id="sidebar-tab-2" class="tab-pane clearfix">
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
            </div>
        </div>
    </div>
    <div class="sidebar-line"><!-- A seperator line --></div>

    <div class="sidebar-heading" data-module-toggle="false">
        <h3>
            Milestones
            <small class="text-muted">/module</small>
            <a href="#" class="pull-right"><i class="fa fa-gear"></i></a>
        </h3>
    </div>


    <div class="sidebar-module">
        <div class="progress-project">
            <div class="progress-project-header">
                <h5>订单完成率</h5><span>44<span class="text-muted">/55</span></span>
            </div>
            <div class="progress bar-small">
                <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
                    <span class="sr-only">90% Complete</span>
                </div>
            </div>
        </div>
        <!-- End .progress-project -->
        <div class="spacer-20"></div>
        <div class="progress-project">
            <div class="progress-project-header">
                <h5>Project Beta</h5><span>4<span class="text-muted">/10</span></span>
            </div>
            <div class="progress bar-small">
                <div class="progress-bar" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%;">
                    <span class="sr-only">20% Complete</span>
                </div>
            </div>
        </div>
        <!-- End .progress-project -->
        <div class="spacer-20"></div>
        <div class="progress-project">
            <div class="progress-project-header">
                <h5>Project Zeta</h5><span>54<span class="text-muted">/88</span></span>
            </div>
            <div class="progress bar-small">
                <div class="progress-bar" role="progressbar" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100" style="width: 78%;">
                    <span class="sr-only">78% Complete</span>
                </div>
            </div>
        </div>
        <!-- End .progress-project -->
        <div class="spacer-20"></div>
        <div class="progress-project">
            <div class="progress-project-header">
                <h5>Project Tango</h5><span>4<span class="text-muted">/16</span></span>
            </div>
            <div class="progress bar-small">
                <div class="progress-bar" role="progressbar" aria-valuenow="28" aria-valuemin="0" aria-valuemax="100" style="width: 28%;">
                    <span class="sr-only">28% Complete</span>
                </div>
            </div>
        </div>
        <!-- End .progress-project -->
        <div class="spacer-20"></div>
        <div class="progress-project">
            <div class="progress-project-header">
                <h5>Project Charlie</h5><span>50<span class="text-muted">/88</span></span>
            </div>
            <div class="progress bar-small">
                <div class="progress-bar" role="progressbar" aria-valuenow="66" aria-valuemin="0" aria-valuemax="100" style="width: 66%;">
                    <span class="sr-only">66% Complete</span>
                </div>
            </div>
        </div>
        <!-- End .progress-project -->
    </div>
    <!-- End .sidebar-module -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <div class="sidebar-heading">
        <h3>
            Calendar
            <small class="text-muted">/module</small>
        </h3>
    </div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

    <div class="sidebar-module">
        <div class="csscalendar-mini">
            <table>
                <caption>January 2012</caption>
                <thead>
                <tr>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                    <th>Sun</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="off"><a href="">26</a></td>
                    <td class="off"><a href="#">27</a></td>
                    <td class="off"><a href="#">28</a></td>
                    <td class="off"><a href="#">29</a></td>
                    <td class="off"><a href="#">30</a></td>
                    <td class="off"><a href="#">31</a></td>
                    <td><a href="#">1</a></td>
                </tr>
                <tr>
                    <td><a href="#">2</a></td>
                    <td><a href="#">3</a></td>
                    <td class="cal-app"><a href="#" class="tooltip-top" title="12:05 - Client X">4</a></td>
                    <td><a href="#">5</a></td>
                    <td><a href="#">6</a></td>
                    <td><a href="#">7</a></td>
                    <td><a href="#">8</a></td>
                </tr>
                <tr>
                    <td><a href="#">9</a></td>
                    <td><a href="#">10</a></td>
                    <td><a href="#">11</a></td>
                    <td><a href="#">12</a></td>
                    <td><a href="#">13</a></td>
                    <td><a href="#">14</a></td>
                    <td><a href="#">15</a></td>
                </tr>
                <tr>
                    <td><a href="#">16</a></td>
                    <td><a href="#">17</a></td>
                    <td><a href="#">18</a></td>
                    <td><a href="#">19</a></td>
                    <td class="cal-app"><a href="#" class="tooltip-top" title="16:15 - Launch website">20</a></td>
                    <td><a href="#">21</a></td>
                    <td><a href="#">22</a></td>
                </tr>
                <tr>
                    <td><a href="#">23</a></td>
                    <td><a href="#">24</a></td>
                    <td><a href="#">25</a></td>
                    <td><a href="#">26</a></td>
                    <td><a href="#">27</a></td>
                    <td class="active"><a href="#">28</a></td>
                    <td><a href="#">29</a></td>
                </tr>
                <tr>
                    <td><a href="#">30</a></td>
                    <td><a href="#">31</a></td>
                    <td class="off"><a href="#">1</a></td>
                    <td class="off"><a href="#">2</a></td>
                    <td class="off"><a href="#">3</a></td>
                    <td class="off"><a href="#">4</a></td>
                    <td class="off"><a href="#">5</a></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</aside>
