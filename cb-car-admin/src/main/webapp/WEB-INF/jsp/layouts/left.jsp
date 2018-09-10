<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside id="sidebar-main" class="sidebar">
    <jsp:include page="sidebar_logo.jsp"/>
    <div class="sidebar-line"></div>
    <ul class="ext-tabs-sidebar">
        <li class="active">
            <a href="#sidebar-tab-1"><i class="fa fa-bars"></i> 导航</a>
        </li>
    </ul>
    <div class="tab-content">
        <jsp:include page="../layouts/default.jsp"/>
        <jsp:include page="../layouts/menu.jsp"/>
    </div>
</aside>
