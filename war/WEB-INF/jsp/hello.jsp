<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> ${model.now}</p>
    
    <h3>Manufacturers</h3>
    <!--<c:forEach items="${model.brands}" var="brand">
      	<c:out value="${brand.name}"/> <c:out value="${brand.website}"/> <br/>
    </c:forEach>
    -->
    <display:table name="${model.brands}">
    	<display:column property="name" />
  		<display:column property="website" />
  	</display:table>
  	
  	<p/>
  	
  	<a href="<c:url value="brandedit.htm"/>">Edit Manufacturers</a>
  </body>
</html>