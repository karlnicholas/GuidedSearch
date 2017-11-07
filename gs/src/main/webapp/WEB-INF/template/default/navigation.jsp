<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function() {
  // Setup drop down menu
  $('.dropdown-toggle').dropdown();
  // Fix input element click problem
  $('.dropdown input, .dropdown label').click(function(e) {
    e.stopPropagation();
  });
});
</script>
<nav id="navigation" class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="navbar-header">
    <a href="${urlBuilder.homeUrl(viewModel)}" class="navbar-brand" >Guided Search</a>
    <form class="navbar-form navbar-left form-horizontal" role="form" method="post">
      <input type="text" class="form-control" name="ntm" value="<c:out value="${viewModel.term}"/>" placeholder="Search">
      <div class="btn-group dropdown" >
        <button type="submit" class="btn btn-default">Submit</button>
        <button class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
        <div class="dropdown-menu container" style="width: 350px; padding: 15px">
            <div class="row">
            <label for="inAll" class="control-label col-sm-4">All&nbsp;Of:&nbsp;&nbsp;</label>
            <div class="col-sm-4"><input type="text" class="form-control" name="inAll" value="<c:out value="${inAll}"/>" id="inAll" /></div>
            </div>
            <div class="row">
            <label for="inNot" class="control-label col-sm-4">None&nbsp;Of:&nbsp;&nbsp;</label>
            <div class="col-sm-4"><input type="text" class="form-control" name="inNot" value="<c:out value="${inNot}"/>" id="inNot" /></div>
            </div>
            <div class="row">
            <label for="inAny" class="control-label col-sm-4">Any&nbsp;Of:&nbsp;&nbsp;</label>
            <div class="col-sm-4"><input type="text" class="form-control" name="inAny" value="<c:out value="${inAny}"/>" id="inAny" /></div>
            </div>
            <div class="row">
            <label for="inExact" class="control-label col-sm-4">Exact&nbsp;Phrase:&nbsp;&nbsp;</label>
            <div class="col-sm-4"><input type="text" class="form-control" name="inExact" value="<c:out value="${inExact}"/>" id="inExact" /></div>
            </div>
            <div class="row">
            <label for="submit" class="control-label col-sm-4"></label>
            <div class="col-sm-4"><button type="submit" class="form-control" id="submit">Submit</button></div>
            </div>
        </div>
      </div>
      <button type="submit" name="cl" class="btn">Clear</button>
      <c:choose>
        <c:when test="${viewModel.fragments == true}">
          <button type="submit" name="to" class="btn btn-info">Fragments</button>
        </c:when>
        <c:when test="${viewModel.fragments == false && not empty viewModel.term}">
          <button type="submit" name="to" class="btn">Fragments</button>
        </c:when>
        <c:when test="${viewModel.fragments == false && empty viewModel.term}">
          <button type="submit" name="to" class="btn" disabled="disabled">Fragments</button>
        </c:when>
      </c:choose>
      <input type="hidden" name="fs" value="${viewModel.fragments}" />
    </form>
  </div>
  <div class="navbar-right">
    <ul class="nav navbar-nav">
      <li class="dropdown"><a href="#" class="dropdown-toggle navbar-brand" data-toggle="dropdown">Applications <span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
          <li><a href="http://op-op.b9ad.pro-us-east-1.openshiftapps.com">Court Opinions</a></li>
          <li><a href="http://gs-op.b9ad.pro-us-east-1.openshiftapps.com">Guided Search</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>