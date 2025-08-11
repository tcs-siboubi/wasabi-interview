function togglemenu(id) {
	var menudiv = $('#mh_' + id)[0];
	var openMenus = new Array();

	document.cookie = "expandedMenu=;path=/proper;";

	$('div.menuBody').each(function(ndx,item) {
		if ($(item).is(':visible') && item != menudiv) {
			openMenus.push($(item).attr('menuid'));
		}
	});

	if (menudiv != null) {
		if ($(menudiv).is(':visible')) {
			$('#mh_' + id).slideUp(500);
			$('#mhci_' + id).show();
			$('#mhei_' + id).hide();
		} else {
			openMenus.push($(menudiv).attr('menuid'));
			$('#mh_' + id).slideDown(500);
			$('#mhci_' + id).hide();
			$('#mhei_' + id).show();
		}
	}
	
	document.cookie = "openMenuItems=" + openMenus.join(',') + ";path=/proper;"
}
function toggleAllMenus() {
	if ($('#menuAllCollapsed').is(':visible')) {
		document.cookie = "expandedMenu=T;path=/proper;";
		$('div.menuBody').each(function(ndx,item) {
			$(item).show();
		});
		$('img.menuCollapsed').each(function(ndx,item) {
			$(item).hide();
		});
		$('img.menuExpanded').each(function(ndx,item) {
			$(item).show();
		});
		$('#menuAllCollapsed').hide();
		$('#menuAllExpanded').show();
	} else {
		document.cookie = "expandedMenu=;path=/proper;";
		document.cookie = "openMenuItems=;path=/proper;"
		$('div.menuBody').each(function(ndx,item) {
			$(item).hide();
		});
		$('img.menuCollapsed').each(function(ndx,item) {
			$(item).show();
		});
		$('img.menuExpanded').each(function(ndx,item) {
			$(item).hide();
		});
		$('#menuAllCollapsed').show();
		$('#menuAllExpanded').hide();
	}
}
function initMenu() {
	if (document.cookie.indexOf("expandedMenu=T") != -1) {
		$('div.menuBody').each(function(ndx, item) {
			$(item).show();
		});
		$('img.menuCollapsed').each(function(ndx,item) {
			$(item).hide();
		});
		$('img.menuExpanded').each(function(ndx,item) {
			$(item).show();
		});
		$('#menuAllCollapsed').hide();
		$('#menuAllExpanded').show();
	} else if (document.cookie.indexOf("openMenuItems=") != -1) {
		var c_start = document.cookie.indexOf("openMenuItems=") + 14;
		var c_end = document.cookie.indexOf(";", c_start);
		if (c_end == -1)
			c_end = document.cookie.length;
		var openMenuItems = document.cookie.substring(c_start, c_end).split(",");
		$('div.menuBody').each(function(ndx,item) {
			if (jQuery.inArray($(item).attr('menuid'),openMenuItems) != -1) {
				$(item).show();
				var id = $(item).attr('id');
				id = id.replace('mh_', 'mhci_');
				$('#'+id).hide();
				id = id.replace('mhci_', 'mhei_');
				$('#'+id).show();
			}
		});
	}
    checkShowMenuCookie();
}

function hideMenu() {
    $('#sidebar').hide('drop',null, 1000, function() { expandContent();setShowMenuCookie('false'); });
}
function showMenu() {
    $('#sidebar').show('drop',null, 500, function() { contractContent();setShowMenuCookie('true'); });
}
function expandContent() {
	if ($('#showMenuBtn').length != 0) $('#showMenuBtn').show();
	$('#content').css({ cssFloat : 'left', width : '1000px' });
}
function contractContent() {
	if ($('#showMenuBtn').length != 0) $('#showMenuBtn').hide();
	$('#content').css({ cssFloat : 'right', width : '792px' });
}
function setShowMenuCookie(value) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + 1);
	document.cookie = "showMenu=" + value + ";expires="
			+ exdate.toGMTString() + ";path=/proper";
}
function getShowMenuCookie() {
	if (document.cookie.length > 0) {
		var c_start = document.cookie.indexOf("showMenu=");
		if (c_start != -1) {
			c_start = c_start + 9;
			var c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1)
				c_end = document.cookie.length;
			return document.cookie.substring(c_start, c_end);
		}
	}
	return "true";
}
function checkShowMenuCookie() {
	var showMenu = getShowMenuCookie();
	if (showMenu == 'false') {
		if ($('#showMenuBtn').length != 0) {
			$('#showMenuBtn').show();
			$('#sidebar').hide();
			$('#content').css({ cssFloat : 'left', width : '1000px' });
		}
	} else {
		if ($('#showMenuBtn').length != 0) $('#showMenuBtn').hide();
		$('#sidebar').show();
		$('#content').css({ cssFloat : 'right', width : '792px' });
	}
}
