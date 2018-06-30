<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.yunxin.cb.mail.EmailManager" %>
<%@ page import="com.yunxin.cb.mall.entity.Customer" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.File" %>
<%--
  Created by IntelliJ IDEA.
  User: gonglei
  Date: 16/2/26
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext() );
  EmailManager emailManager = (EmailManager) ac.getBean("emailManager");
  String[] to={"589109@qq.com"};
  String[] cc={"56290999@qq.com"};
  Customer customer=new Customer();
  customer.setAccountName("fsafdsafdsafds");
  Map<String,Object> map=new HashMap<>();
  map.put("customer", customer);
  String fileName="一个美女.jpg";
  File file=new File(pageContext.getServletContext().getRealPath("/images/Snip20160216_1.png"));
  emailManager.sendMail(to,cc,"测试发邮件",map,"findPassword.ftl",fileName,file);
%>
</body>
</html>
