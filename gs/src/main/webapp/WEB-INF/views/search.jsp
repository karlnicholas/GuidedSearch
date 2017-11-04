<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<jsp:useBean id="highlighter" class="gsearch.util.Highlighter" scope="request"/>
<jsp:useBean id="urlBuilder" class="gs.util.URLBuilder" scope="request"/>
<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="body">
<ol class="breadcrumb">
<li><a href="${urlBuilder.homeUrl(viewModel)}" data-toggle="tooltip" data-placement="bottom" title="Home">Home</a></li><myTags:breadcrumb entries="${viewModel.entries }" />
<c:if test="${not empty viewModel.term}">
<li><span class="badge pull-right">${viewModel.totalCount}</span></li>
</c:if>
</ol><div class="container-fluid">
<div class="panel-group" id="accordion">
<div class='panel panel-default'>
<myTags:recurse entries="${viewModel.entries}"/>
</div>
</div>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>