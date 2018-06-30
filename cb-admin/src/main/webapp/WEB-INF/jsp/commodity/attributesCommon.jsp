<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/7
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="checkboxtoggle-2">

  <!-- * data-asf-time = seconds, data-asf-expireafter = minutes * -->
  <fieldset>
    <legend>商品属性选择</legend>

    <c:forEach items="${attributeGroups}" var="attrGroup" varStatus="agStatus">

      <c:choose>
        <c:when test="${(agStatus.index+1) % 2 ==0}">
          <div class="row">
            <div class="col-sm-2">
              <label>${attrGroup.groupName}：</label>
            </div>
            <div class="col-sm-9">
              <c:choose>
                <c:when test="${attrGroup.word == true}">
                  <c:forEach items="${attrGroup.attributes}" var="attr" varStatus="aStatus">
                    <input type="checkbox" value="${attr.attributeId}"/>${attr.attributeName}
                  </c:forEach>
                </c:when>
                <c:otherwise>
                  <c:forEach items="${attrGroup.attributes}" var="attr" varStatus="aStatus">
                    <input type="checkbox" value="${attr.attributeId}"/><img src="${attr.propPic}">${attr.attributeName}<img>
                  </c:forEach>
                </c:otherwise>
              </c:choose>
            </div>
            <div class="col-sm-1"></div>
          </div>
          <div class="spacer-30"></div>
          <hr>
          <div class="spacer-30"></div>
        </c:when>
        <c:otherwise>
          <div class="row">
            <div class="col-sm-2">
              <label>${attrGroup.groupName}：</label>
            </div>
            <div class="col-sm-9">
              <c:choose>
                <c:when test="${attrGroup.word == true}">
                  <c:forEach items="${attrGroup.attributes}" var="attr" varStatus="aStatus">
                    <input type="checkbox" value="${attr.attributeId}"/>${attr.attributeName}
                  </c:forEach>
                </c:when>
                <c:otherwise>
                  <c:forEach items="${attrGroup.attributes}" var="attr" varStatus="aStatus">
                    <input type="checkbox" value="${attr.attributeId}"/><img src="${attr.propPic}">${attr.attributeName}<img>
                  </c:forEach>
                </c:otherwise>
              </c:choose>
            </div>
            <div class="col-sm-1"></div>
          </div>
          <div class="spacer-10"></div>
        </c:otherwise>
      </c:choose>

    </c:forEach>

    <div class="spacer-30"></div>
    <hr>
    <div class="spacer-30"></div>
    <div class="row">
      <div class="col-sm-12">
        <div class="btn-group pull-right">
          <button id="saveBtn" class="btn btn-default" type="submit"><i class="fa fa-save"></i>&nbsp;保&nbsp;存&nbsp;</button>
          <button type="reset" class="btn btn-default"><i class="fa fa-reply"></i>&nbsp;重&nbsp;置&nbsp;</button>
        </div>
      </div>
    </div>
  </fieldset>
</form>

