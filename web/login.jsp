<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/10
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>欢迎登陆</title>
      <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

      <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap-theme.min.css">
      <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
      <script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>

      <script>

          function addBug() {
              $.post("${pageContext.request.contextPath}/addData/addBug.do");
              alert("添加成功");
          }

          function addAdmin() {
              $.post("${pageContext.request.contextPath}/addData/addAdmin.do");
              alert("添加成功");
          }

          function addUsers() {
              $.post("${pageContext.request.contextPath}/addData/addUsers.do");
              alert("添加成功");
          }

      </script>
  </head>
  <body>

  <h1 align="center" style="margin-top: 30px;">欢迎登陆catcher缺陷管理系统</h1>

  <div  align="center">

    <div style="width: 300px;height: 200px;margin-top: 60px;">

      <form class="form-horizontal" action="${pageContext.request.contextPath}/user/login.do" method="post">
        <div class="form-group">
          <label for="account" class="col-sm-3 control-label">账户</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" id="account" placeholder="account" name="account">
          </div>
        </div>
        <div class="form-group">
          <label for="password" class="col-sm-3 control-label">密码</label>
          <div class="col-sm-9">
            <input type="password" class="form-control" id="password" placeholder="password" name="password">
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-8">
            <div class="checkbox">
              <label>

                <input type="checkbox"> 记住密码
              </label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-8">
            <button type="submit" class="btn btn-default">登陆</button>
          </div>
        </div>
      </form>

    </div>
  </div>
  <input type="button" value="添加bug" onclick="addBug()">
  <input type="button" value="添加超级管理员" onclick="addAdmin()">
  <input type="button" value="添加一些用户" onclick="addUsers()">
  <a href="${pageContext.request.contextPath}/test/froala_editor_html.do">froala_editor.html</a>
  <a href="${pageContext.request.contextPath}/test/froala_editor_jsp.do">froala_editor.jsp</a>
  <a href="${pageContext.request.contextPath}/test/umditor_jsp.do">umditor.jsp</a>
  </body>
</html>
