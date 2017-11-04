<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="entries" required="true" type="java.util.List"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${entries}" var="entry" varStatus="status">
  <myTags:displayReference entry="${entry}" index="${status.index}"/>
  <myTags:displayText entry="${entry}" />
  <c:if test="${entry.pathPart}"><myTags:recurse entries="${entry.entries}" /></c:if>
</c:forEach>