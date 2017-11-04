<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="entry" required="true" type="gsearch.viewmodel.EntryReference"%>
<%@ attribute name="index" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!entry.pathPart && !entry.sectionText}">
<c:if test="${viewModel.state != 'TERMINATE' }">
  <c:set var="panelDOpen" value="<div class='panel-heading'>" />
  <c:set var="panelDClose" value="</div>" />
</c:if>
${panelDOpen}
  <div class="row panel-title">
    <p>
      <span class="col-xs-1">
        <c:if test="${entry.count > 0 && viewModel.fragments}">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse${index}">
            <span class="glyphicon glyphicon-asterisk"></span></a>
        </c:if>
      </span>
      <a href="${urlBuilder.newPathUrl(viewModel, entry.fullFacet)}">
        <span class="col-xs-3">
          ${entry.displayTitle}
          <c:choose>
          <c:when test="${entry.count > 0}">
            <span class="badge pull-right"><c:out value="${entry.count}" /></span>
          </c:when>
          <c:when test="${entry.count == 0}">&nbsp;</c:when>
          </c:choose>
        </span>
        <span class="col-xs-5">
        ${entry.codeReference.title}
        </span>
        <span class="col-xs-3">§§&nbsp;${entry.codeReference.codeRange}
        </span>
      </a>
    </p>
  </div>
<c:if test="${!empty entry.entries }">
  <div class="panel-collapse collapse" id="collapse${index}">
    <div class="panel-body">
      <pre>
        <c:forEach items="${entry.entries}" var="entryText">
          <c:out value="${highlighter.highlightText(entryText.text, viewModel.term, \"<mark><strong><u>\", \"</u></strong></mark>\")}" escapeXml="false" />
          <br>
        </c:forEach>
      </pre>
    </div>
  </div>
</c:if>
${panelDClose}
</c:if>