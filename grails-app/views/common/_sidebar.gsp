<div id='sidebar'>
    <table>
        <tr>
            <td class='icon' onclick='toggleAllMenus();'>
                <img id='menuAllCollapsed' src="${assetPath(src: 'collapsed.png')}"/>
                <img id='menuAllExpanded' src="${assetPath(src: 'expanded.png')}" style='display:none;'/>
            </td>
            <td class='title' onclick="window.open('${createLink(uri:'/')}','_self');">Menu</td>
            <g:if test="${controllerName == null}">
                <td class='icon'>&nbsp;</td>
            </g:if>
            <g:else>
                <td class='icon' onclick="hideMenu();"><img src="${assetPath(src: 'hide.png')}"/></td>
            </g:else>
        </tr>
    </table>
    <div>
        <div class='menublock'>
            <div class='menuheader' onclick="togglemenu('proposalMenu');">
                <img id='mhci_proposalMenu' class='menuCollapsed' style='display:none' src="${assetPath(src: 'collapsed.png')}"/>
                <img id='mhei_proposalMenu' class='menuExpanded' style='display:inline' src="${assetPath(src: 'expanded.png')}"/>
                Proposals
            </div>
            <div id='mh_proposalMenu' class='menuBody' menuid='PROPOSALS' style='display:block;'>
                <div class='menuitem'>
                    <g:link action="getReplacementNames" controller="proposal">
                        <img src="${assetPath(src: 'link.gif')}"/>
                        Replace Investigator
                    </g:link>
                </div>
            </div>
        </div>
    </div>
</div>
