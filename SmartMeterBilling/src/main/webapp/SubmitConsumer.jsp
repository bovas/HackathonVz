<%
String smID=request.getParameter("sm_identifier");
String firstname=request.getParameter("firstname");
String lastname=request.getParameter("lastname");
String addr1=request.getParameter("address1");
String addr2=request.getParameter("address2");
String city=request.getParameter("city");
String pincode=request.getParameter("pincode");
System.out.println(smID+"__"+firstname);
out.println("{'success':true}");
%>