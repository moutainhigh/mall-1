<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<li class="eft-open">
  <i class="fa fa-folder-open"></i>
  <c:choose>
    <c:when test="${instanceUnderCatalog=='spec'}">
      <a href="javascript:window.location.href='catalogSpecs.do?catalogId=${catalogItem.id}';">
          ${catalogItem.text}
      </a>
    </c:when>
    <c:when test="${instanceUnderCatalog=='catalogAttributeGroup'}">
      <a href="javascript:{ $('#catalogId').val(${catalogItem.id});reloadGridFilters('grid')};">
          ${catalogItem.text}
      </a>
    </c:when>
    <c:otherwise>
      <a>
          ${catalogItem.text}
      </a>
    </c:otherwise>
  </c:choose>

  <c:if test="${not empty catalogItem.items}">
    <ul>
      <c:forEach var="catalogItem" items="${catalogItem.items}">
        <c:set var="catalogItem" value="${catalogItem}" scope="request"/>
        <jsp:include page="catalogTree.jsp"/>
      </c:forEach>
    </ul>
  </c:if>
</li>