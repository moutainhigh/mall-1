<%@ page import="com.yunxin.cb.security.SecurityConstants" %>
<%@ page import="com.yunxin.cb.console.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar-logo">
  <a href="../index.do" id="logo-big"><h1>水晶球</h1><h2> 后台管理系统</h2></a>
</div>
<div class="sidebar-module">
  <div class="sidebar-profile">
    <img src="../images/users/user-1.jpg" alt="" class="avatar"/>
    <%--<span class="indicator-dot">2</span>--%>
    <ul class="sidebar-profile-list">
      <li><h3>Hi, ${sessionScope.loginSession.userName}</h3></li>
      <li> <a href="<c:url value="../logout" />">退出</a></li>
    </ul>
  </div>
</div>
<div class="sidebar-line"></div>
