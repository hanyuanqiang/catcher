<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/15
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function deleteUser(id,label) {
        layer.confirm('你确定要删除['+label+']吗？', {
            btn: ['是的','点错了'], //按钮
            icon:3
        }, function(){
            $.post("${pageContext.request.contextPath}/user/delete.do",{id:id},function (result) {
                if(result.success){
                    $("#tr_user_"+id).remove();
                    layer.msg('['+label+']删除成功', {
                        time: 3000, //3s后自动关闭
                    });
                }else{
                    layer.msg('['+label+']删除失败', {
                        time: 3000, //3s后自动关闭
                        btn: ['知道了']
                    });
                }
            },"json");
        }, function(){

        });
    }
</script>

<div>

    <div class="panel panel-default" style="margin-top: 20px;">
        <div class="panel-heading">
            <h3 class="panel-title">
                <p style="float: right;margin-right: 30px;"><a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/user/preSave.do">添加用户</a></p>
                <p>&nbsp;&nbsp;设置&nbsp;&nbsp;/&nbsp;&nbsp;用户列表</p>
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr align="center">
                    <td>序号</td>
                    <td>姓名</td>
                    <td>账号</td>
                    <td>生日</td>
                    <td>邮箱</td>
                    <td>电话</td>
                    <td>性别</td>
                    <td>职位</td>
                    <td>创建时间</td>
                    <td>是否管理员</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${userList}" var="user" varStatus="index">
                    <tr align="center" id="tr_user_${user.id}">
                        <td>${index.index+1}</td>
                        <td>${user.label}</td>
                        <td>${user.account}</td>
                        <td>
                            <fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td>${user.gender}</td>
                        <td>${user.post}</td>
                        <td>
                            <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd hh:mm"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.isAdmin == true}">是</c:when>
                                <c:otherwise>否</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a type="button" class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/user/preSave.do?id=${user.id}">修改</a>
                            <button type="button" class="btn btn-danger btn-xs" onclick="deleteUser(${user.id},'${user.label}')">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
