<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Proposal List</title>
</head>
<body>
	<div class="nav">
		<span class="menuButton"><g:link uri="/" class="home">Home</g:link></span>
		<span class="menuButton" style="display: none" id='showMenuBtn'><a class="showMenu" href="#" onclick="showMenu()">Show Menu</a></span>
	</div>
	<div class="body">
		<div id='pageTitle'>
			<span id='pageTitleText'>Proposal List</span>
		</div>
		<g:if test="${flash.message}"><div class="message">${flash.message}</div></g:if>
		<h2>Replacing ${oldPersonInstance} with ${newPersonInstance}</h2>
		<g:form action="replaceParticipant" method="post">
			<g:hiddenField name="oldPersonId" value="${oldPersonInstance.id}" />
			<g:hiddenField name="newPersonId" value="${newPersonInstance.id}" />
			<div class="list">
				<table id='proposalList'>
					<thead>
						<tr>
							<th>Do Replacement</th>
							<th>Proposal #</th>
							<th>Title</th>
							<th>Mission</th>
							<th>Cycle</th>
							<th>Admin</th>
							<th>PI</th>
							<th>Contact</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${proposalList}" status="i" var="proposalInstance">
							<g:set var='isPi' value='0'/>
							<g:set var='isAdmin' value='0'/>
							<g:set var='isContact' value='0'/>
							<g:each in="${proposalInstance.participants}" var="pp">
								<g:if test="${pp.person.id == oldPersonInstance.id}">
									<g:if test="${pp.isPrincipalInvestigator}"><g:set var='isPi' value='1'/></g:if>
									<g:if test="${pp.isAdministrativePrincipalInvestigator}"><g:set var='isAdmin' value='1'/></g:if>
									<g:if test="${pp.isContact}"><g:set var='isContact' value='1'/></g:if>
								</g:if>
							</g:each>
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td><g:checkBox name="propId.${proposalInstance.id}" value="${true}"/></td>
								<td>${proposalInstance.number}</td>
								<td>${fieldValue(bean:proposalInstance, field:'title')}</td>
								<td>${fieldValue(bean:proposalInstance, field:'mission')}</td>
								<td>${fieldValue(bean:proposalInstance, field:'cycle')}</td>
								<td class="${isPi=='1' ? 'bigCheckCentered' : ''}"></td>
								<td class="${isAdmin=='1' ? 'bigCheckCentered' : ''}"></td>
								<td class="${isContact=='1' ? 'bigCheckCentered' : ''}"></td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="buttons">
					<span class="button"><span class='checkAll' onclick='setAll(true);'>Check All</span></span>
					<span class="button"><span class='checkNone' onclick='setAll(false);'>Clear All</span></span>
					<span class="button"><g:actionSubmit class="save" action="replaceParticipant" value="Replace"/></span>
				</div>
			</div>
		</g:form>
	</div>
	<script type="text/javascript">
		function setAll(flag) {
			$("input[type='checkbox']").each(function(n,s) {s.checked = flag;});
		}
	</script>
</body>
</html>
