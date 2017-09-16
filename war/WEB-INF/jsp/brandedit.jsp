<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
<head>
  <title><fmt:message key="title"/></title>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/displaytag.css"/>
  <style>
  	.error { color: red; }
  </style>
  <script>
  <!--
  function doSubmit( dest ) 
  {
  	document.brandForm.action = dest;
  	document.brandForm.submit();
  }
  -->
  </script>
</head>
<body>
<h1><fmt:message key="brand.heading"/></h1>

	<c:url var="brandsUrl" value="/brands"/>
	<c:url var="deleteUrl" value="/brands/delete"/>
	

    <display:table name="${brands}" decorator="au.com.tla.flight.web.ManufacturerDecorator">
  		<display:column property="link" title="Vendor" />
  		<display:column property="website" autolink="true" />
  	</display:table>
  
  	<p/>	
  	<p/>
  
  <form:errors path="*" cssClass="error" />
  
  <form:form name="brandForm" action="${brandsUrl}" method="post" commandName="manufacturer">
  <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    <tr>
      <td align="left" width="20%">Manufacturer:</td>
      <td width="20%">
      	<c:if test="${empty manufacturer.name}">
      		<form:input path="name"/>
        </c:if>
        <c:if test="${not empty manufacturer.name}">
			<form:input path="name" readonly="true"/>
        </c:if>
      <!-- this bit sorta works, removes manufacturer when disabled
        <form:input path="name"/>
        -->
      </td>
      <td> <form:errors path="name" cssClass="error"/> </td>
    </tr>
    <tr>
      <td align="left" width="20%">Website:</td>
      <td>
          <form:input path="website"/>
      </td>
      <td> <form:errors path="website" cssClass="error"/> </td>
    </tr>
  </table>
  <br>
  <c:if test="${empty manufacturer.name}">
  	<input type="button" value="Add" onclick="doSubmit('${brandsUrl}')" />
  </c:if>
  <c:if test="${not empty manufacturer.name}">
  	<input type="button" value="Save" onclick="doSubmit('${brandsUrl}/update')" />
  	<input type="button" value="Delete" onclick="doSubmit('${deleteUrl}')" />
  </c:if>
  </form:form>
<a href="<c:url value="/brands"/>">Home</a>
</body>
</html>