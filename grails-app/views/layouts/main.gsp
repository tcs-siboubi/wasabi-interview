<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title><g:layoutTitle default="Proposal/Person Application"/></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">

    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>

    <g:layoutHead/>
</head>

<body>
<div id='page'>
    <g:render template="/common/header"/>
    <g:render template="/common/sidebar"/>
    <div id='content'><g:layoutBody/></div>
    <g:render template="/common/footer"/>
</div>
</body>
</html>
