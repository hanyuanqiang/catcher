<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/14
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>catcher缺陷管理系统</title>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.1/themes/icon.css">
    <script src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">

        function openTab(text,url,iconCls){
            if($("#tabs").tabs("exists",text)){
                $("#tabs").tabs("select",text);
            }else{
                var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>";
                $("#tabs").tabs("add",{
                    title:text,
                    iconCls:iconCls,
                    closable:true,
                    content:content
                });
            }
        }
    </script>
</head>
<body>

<div class="easyui-layout" style="width:100%;height:100%;">
    <div data-options="region:'north'" style="height:16%">
        <div style="padding: 0px;margin: 0px;background-color: #E0ECFF;">
            <table style="height: 100%;">
                <tr>
                    <%--<td><img src="images/mainlogo.png"/></td>--%>
                    <td><h1>catcher缺陷管理系统</h1></td>
                    <td valign="bottom">欢迎：${currentUser.userName }&nbsp;『${currentUser.roleName }』</td>
                </tr>
            </table>
        </div>
    </div>


    <div data-options="region:'west',split:true,collapsible:false" title="导航菜单" style="width:20%;padding: 10px;">
        <%--<ul class="easyui-tree">
            <li>
                <span>个人工作台</span>
                <ul>
                    <li data-options="state:'open'">
                        <span>我的项目</span>
                        <ul>
                            <li>
                                <span>我负责的</span>
                            </li>
                            <li>
                                <span>我参与的</span>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span>我的版本</span>
                        <ul>
                            <li>
                                <span>我负责的</span>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span>我的消息</span>
                        <ul>
                            <li>
                                <span>公告</span>
                                <ul>
                                    <li>
                                        <span>已公布</span>
                                    </li>
                                    <li>
                                        <span>未公布</span>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <span>收件箱</span>
                                <ul>
                                    <li>
                                        <span>未读</span>
                                    </li>
                                    <li>
                                        <span>已读</span>
                                    </li>
                                    <li>
                                        <span>回收站</span>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <span>发件箱</span>
                                <ul>
                                    <li>
                                        <span>未发送</span>
                                    </li>
                                    <li>
                                        <span>已发送</span>
                                    </li>
                                    <li>
                                        <span>回收站</span>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>

            <li>
                <span>项目</span>
                <ul>
                    <li data-options="state:'closed'">
                        <span>Photos</span>
                    </li>
                </ul>
            </li>

            <li>
                <span>版本</span>
                <ul>
                    <li data-options="state:'closed'">
                        <span>Photos</span>
                    </li>
                </ul>
            </li>

            <li>
                <span>缺陷</span>
                &lt;%&ndash;<ul>
                    <li data-options="state:'closed'">
                        <span>Photos</span>
                        <ul>
                            <li>
                                <span>Company</span>
                            </li>
                        </ul>
                    </li>
                </ul>&ndash;%&gt;
            </li>

            <li>
                <span>设置</span>
                &lt;%&ndash;<ul>
                    <li data-options="state:'closed'">
                        <span>Photos</span>
                        <ul>
                            <li>
                                <span>Company</span>
                            </li>
                        </ul>
                    </li>
                </ul>&ndash;%&gt;
            </li>
        </ul>--%>

        <ul class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/json-data/tree_data.json'">

        </ul>

    </div>


    <div data-options="region:'center'">
        <div class="easyui-tabs" fit="true" border="false" id="tabs" >
            <div title="首页" data-options="iconCls:'icon-home'">
                <div align="center" style="padding-top: 100px;"><span style="font-size: 40px;color: red;">欢迎使用</span></div>
            </div>
        </div>
    </div>



    <div data-options="region:'south',split:true" style="height:35px;" align="center">
        <div style="margin-top: 7px;">版权所有 2017 hanyuanqinag <a href="https://hanyuanqiang.github.io" target="_blank">挪威司机</a></div>
    </div>
</div>

<%--<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">west content</div>
<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
<div data-options="region:'center',title:'Center'"></div>--%>

</body>
</html>
