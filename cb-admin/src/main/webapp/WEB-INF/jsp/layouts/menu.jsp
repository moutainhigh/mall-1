
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready(function(){
        $(".sidebar-nav-v2 ul").find("li").click(function(){
            $(".sidebar-nav-v2 ul li").removeClass();
            $(this).addClass("page-arrow active-page");
        });
    });
</script>
<div id="sidebar-tab-1" class="tab-pane <c:if test="${empty param.active}">active</c:if> clearfix">
    <c:set var="navUrl" value="${pageContext.request.requestURI}"/>
    <div class="sidebar-module">
        <nav class="sidebar-nav-v2">
            <ul>
                <li >
                    <a href="../console/dashboard.do"><i class="fa fa-home"></i>首页
                        <%--<span class="indicator-pill">32</span>--%>
                    </a>
                </li>
                <c:forEach var="resc" items="${userRescs}">
                    <c:if test="${resc.type=='MENU'}">
                        <li  <c:if test="${navUrl.contains(resc.value)}">class="page-arrow active-page"</c:if>>
                            <a href="#"><i class="${resc.cssClass}"></i> ${resc.name} <i class="fa fa-caret-left pull-right"></i></a>

                            <c:if test="${resc.hasChildrenMenu}">

                                <ul <c:if test="${navUrl.contains(resc.value)}">style="display: block"</c:if>>
                                    <c:forEach var="childResc" items="${resc.children}">
                                        <li>
                                            <a href="${childResc.value}">${childResc.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </li>
                    </c:if>
                </c:forEach>

            </ul>
        </nav>
    </div>
</div>
