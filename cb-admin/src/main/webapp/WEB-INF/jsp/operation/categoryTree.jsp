<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<li class="eft-open">
  <i class="fa fa-folder-open"></i><span data-id="${categoryItem.categoryId}">${categoryItem.categoryName}</span>
  <c:if test="${not empty categoryItem.categories}">
    <ul>
      <c:forEach var="categoryItem" items="${categoryItem.categories}">
        <c:set var="categoryItem" value="${categoryItem}" scope="request"/>
        <jsp:include page="categoryTree.jsp"/>
      </c:forEach>
    </ul>
  </c:if>
</li>