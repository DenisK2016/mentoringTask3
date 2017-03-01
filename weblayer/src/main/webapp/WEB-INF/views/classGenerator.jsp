<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Title</title>
</head>
<body>

	<table border="2">
			<tr>
				<th><a href="addcar/"><input type="submit" value="Car interface generate" /></a></th>
				<th align="left">${car}</th>
			</tr>
			<tr>
				<td><a href="audi/"><input type="submit" value="Audi generate" /></a></td>
				<td align="left">${audi}</td>
				<td><a href="startAudi/"><input type="submit" value="Start" /><br>${audiEngineStart }</td>
				<td><a href="stopAudi/"><input type="submit" value="Stop" /><br>${audiEngineStop }</td>
			</tr>
			<tr>
				<td><a href="vw/"><input type="submit" value="VW generate" /></a></td>
				<td align="left">${vw}</td>
				<td><a href="startVW/"><input type="submit" value="Start" /><br>${vwEngineStart }</td>
				<td><a href="stopVW/"><input type="submit" value="Stop" /><br>${vwEngineStop }</td>
			</tr>
</html>