<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside id="sidebar-main" class="sidebar">

    <jsp:include page="sidebar_logo.jsp"/>

    <div class="sidebar-line"><!-- A seperator line --></div>

    <ul class="ext-tabs-sidebar">
        <li class="active">
            <a href="#sidebar-tab-1"><i class="fa fa-bars"></i> 导航</a>
        </li>
        <li>
            <a href="#sidebar-tab-2"><i class="fa fa-folder"></i> Folders</a>
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
            <!-- End .sidebar-module -->
        </div>
    </div>
    <!-- End .tab-content -->

    <div class="sidebar-line"><!-- A seperator line --></div>

    <div class="sidebar-heading" data-module-toggle="false">
        <h3>
            统计项
            <%--<small class="text-muted">/module</small>--%>
            <%--<a href="#" class="pull-right"><i class="fa fa-gear"></i></a>--%>
        </h3>
    </div>

    <!-- ********** -->
    <!-- NEW MODULE -->
    <!-- ********** -->

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

</aside>
