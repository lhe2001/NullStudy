<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>
 <%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title><tiles:insertAttribute name="title" /></title>
  <link rel="stylesheet" href="resources/css/simple.css" />
  <script src="resources/css/main.js"></script>
  <style>
  	#body {
  		margin-top:100px;
  	}
  </style>
  </head>
    <body>
         <tiles:insertAttribute name="header"/>
          <tiles:insertAttribute name="side"/> 
          <div id="body">
          <tiles:insertAttribute name="body"/>
          </div>
          <tiles:insertAttribute name="footer"/>
  </body>
</html>