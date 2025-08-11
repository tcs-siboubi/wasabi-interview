<%@ page import="edu.stsci.proper.Person" %>
<html>
<head>
<asset:stylesheet src="workQueue.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Replace Investigator</title>
</head>
<body>
<div class="nav">
	<span class="menuButton"><g:link uri="/" class="home">Home</g:link></span>
</div>
<div class="body">
<div id='pageTitle'>
	<span id='pageTitleText'>Replace Investigator</span>
</div>
<g:if test="${flash.message}">
	<div class="message">
	${flash.message}
	</div>
</g:if>
<g:if test="${flash.errors}">
	<div class="errors">
	<ul>
		<g:each var="err" in="${flash.errors}">
			<li>${err}</li>
		</g:each>
	</ul>
	</div>
</g:if>
<g:form action="getReplacementList" method="post">
	<div class="dialog">
	<table>
		<tbody>
			<tr class="prop">
				<td valign="top" class="name"><label for="oldParticipant">Current Investigator</label></td>
				%{-- NOTE: In the real application, this is an autocomplete field backed by a separate API. For this exercise, we've simplified it to a dropdown containing all people. --}%
				<td valign="top" class="value"><g:select name="oldParticipant" from="${Person.list(sort: 'sortName')}" optionKey="id" optionValue="sortName" value="${params?.oldParticipant}" noSelection="['': '[Choose a person]']"/></td>
			</tr>
			<tr class="prop">
				<td valign="top" class="name"><label for="newParticipant">Replacement Investigator</label></td>
				%{-- NOTE: In the real application, this is an autocomplete field backed by a separate API. For this exercise, we've simplified it to a dropdown containing all people. --}%
				<td valign="top" class="value"><g:select name="newParticipant" from="${Person.list(sort: 'sortName')}" optionKey="id" optionValue="sortName" value="${params?.newParticipant}" noSelection="['': '[Choose a person]']"/></td>
			</tr>
		</tbody>
	</table>
	</div>
	<div class="buttons">
		<span class="button"><g:actionSubmit class="find" action="getReplacementList" value="Show Matching Proposals" /></span>
	</div>
</g:form>
</div>
</body>
</html>
