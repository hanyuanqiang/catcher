<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/23
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/Easy-Responsive-Tabs/css/easy-responsive-tabs.css">
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/Easy-Responsive-Tabs/js/easyResponsiveTabs.js"></script>
<style>
    .tbpd {
        padding:5px;
    }
    .a_user:hover{color:#3366cc!important;text-decoration:none;  }

    #container {
        width: 940px;
        margin: 0 auto;
    }
    @media only screen and (max-width: 768px) {
        #container {
            width: 90%;
            margin: 0 auto;
        }
    }
</style>
<div id="parentHorizontalTab">
    <ul class="resp-tabs-list hor_1">
        <li>基本信息</li>
        <li>附件</li>
    </ul>
    <div class="resp-tabs-container hor_1">
        <div>
            <table style="width: 1100px;">
                <tr>
                    <td colspan="1" class="tbpd" align="right" style="width: 100px;">版本名称</td>
                    <td colspan="3" class="tbpd"><input type="text" class="form-control" name="label" value="${version.label}"></td>
                </tr>
                <tr>
                    <td colspan="4" class="tbpd">
                        <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">保存</button>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <div>该面板用于上传附件</div>
        </div>
    </div>
</div>

<!--Plug-in Initialisation-->
<script type="text/javascript">
    $(document).ready(function() {
        //Horizontal Tab
        $('#parentHorizontalTab').easyResponsiveTabs({
            type: 'default', //Types: default, vertical, accordion
            width: 'auto', //auto or any width like 600px
            fit: true, // 100% fit in a container
            tabidentify: 'hor_1', // The tab groups identifier
            activate: function(event) { // Callback function if tab is switched
                var $tab = $(this);
                var $info = $('#nested-tabInfo');
                var $name = $('span', $info);
                $name.text($tab.text());
                $info.show();
            }
        });

        // Child Tab
        $('#ChildVerticalTab_1').easyResponsiveTabs({
            type: 'vertical',
            width: 'auto',
            fit: true,
            tabidentify: 'ver_1', // The tab groups identifier
            activetab_bg: '#fff', // background color for active tabs in this group
            inactive_bg: '#F5F5F5', // background color for inactive tabs in this group
            active_border_color: '#c1c1c1', // border color for active tabs heads in this group
            active_content_border_color: '#5AB1D0' // border color for active tabs contect in this group so that it matches the tab head border
        });

        //Vertical Tab
        $('#parentVerticalTab').easyResponsiveTabs({
            type: 'vertical', //Types: default, vertical, accordion
            width: 'auto', //auto or any width like 600px
            fit: true, // 100% fit in a container
            closed: 'accordion', // Start closed if in accordion view
            tabidentify: 'hor_1', // The tab groups identifier
            activate: function(event) { // Callback function if tab is switched
                var $tab = $(this);
                var $info = $('#nested-tabInfo2');
                var $name = $('span', $info);
                $name.text($tab.text());
                $info.show();
            }
        });
    });
</script>