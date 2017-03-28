<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/15
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function updatePassword(id) {
        layer.prompt({title: '输入新密码，并确认', formType: 1}, function(pass, index){
            layer.close(index);
            $.post("${pageContext.request.contextPath}/user/updatePassword.do",{id:id,password:pass},
                function(result){
                    if(result.success){
                        $("#passTag").val(result.password);
                        layer.msg("密码已成功修改");
                    }
                },"json");
        });
    }
</script>

<div>
    <div class="panel panel-default" style="margin-top: 20px;">
        <div class="panel-heading">
            <h3 class="panel-title">
                &nbsp;&nbsp;设置&nbsp;&nbsp;/&nbsp;&nbsp;编辑用户
            </h3>
        </div>
        <div class="panel-body">
            <div style="width: 70%" align="center">
                <form action="${pageContext.request.contextPath}/user/save.do" method="post">

                    <table class="table" align="center">
                        <tr>
                            <td align="right">
                                <label for="account">账号</label>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${user != null}">
                                        <input type="text" class="form-control" id="account" name="account" value="${user.account}" readonly>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" class="form-control" id="account" name="account" value="${user.account}">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td align="right">
                                <label for="email">邮箱</label>
                            </td>
                            <td>
                                <input type="text" class="form-control" id="email" name="email" value="${user.email}">
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label for="birth">出生日期</label>
                            </td>
                            <td>
                                <input type="text" class="form-control" id="birth" name="birth" value="${user.birth}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                            </td>
                            <td align="right">
                                <label for="label">姓名</label>
                            </td>
                            <td>
                                <input type="text" class="form-control" id="label" name="label" value="${user.label}">
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label for="phone">电话</label>
                            </td>
                            <td>
                                <input type="text" class="form-control" name="phone" id="phone" value="${user.phone}">
                            </td>
                            <td align="right">
                                <label>职位</label>
                            </td>
                            <td>
                                <select class="form-control" name="post">
                                    <option ${user.post=='开发工程师'?'selected':''}>开发工程师</option>
                                    <option ${user.post=='测试工程师'?'selected':''}>测试工程师</option>
                                    <option ${user.post=='需求工程师'?'selected':''}>需求工程师</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">
                                <label>是否为管理员</label>
                            </td>
                            <td>
                                <label class="radio-inline">
                                    <input type="radio" name="isAdmin" id="isAdmintrue" value="true" ${user.isAdmin == true?'checked':''}> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isAdmin" id="isAdminfalse" value="false" ${user.isAdmin == false?'checked':''}> 否
                                </label>
                            </td>
                            <td align="right">
                                <label>性别</label>
                            </td>
                            <td>
                                <label class="radio-inline">
                                    <input type="radio" name="gender" id="male" value="男" ${user.gender=='男'?'checked':''}> 男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="gender" id="female" value="女" ${user.gender=='女'?'checked':''}> 女
                                </label>
                            </td>
                        </tr>
                        <tr>

                            <c:choose>
                                <c:when test="${user != null}">
                                    <input type="text" value="${user.id}" name="id" hidden>
                                    <input type="password" name="password" value="${user.password}" hidden id="passTag">
                                    <td align="right">
                                        <label for="createTime">创建时间</label>
                                    </td>
                                    <td>
                                        <input type="text" class="form-control" id="createTime" name="createTime" value="${user.createTime}" disabled>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td align="right">
                                        <label for="password">密码</label>
                                    </td>
                                    <td>
                                        <input type="password" class="form-control" id="password" name="password" value="${user.password}">
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </table>
                    <p align="right">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <c:if test="${user != null}">
                            <button type="button" class="btn btn-info" onclick="updatePassword(${user.id})">重置密码</button>
                        </c:if>
                    </p>
                </form>
            </div>
        </div>
    </div>

</div>
