<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/19
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function deleteUser(id,label) {
        layer.confirm('你确定要删除['+label+']这个项目吗？', {
            btn: ['是的','点错了'], //按钮
            icon:3
        }, function(){
            $.post("${pageContext.request.contextPath}/project/delete.do",{id:id},function (result) {
                if(result.success){
                    $("#tr_project_"+id).remove();
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
                &nbsp;&nbsp;项目&nbsp;&nbsp;/&nbsp;&nbsp;所有项目
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr align="center">
                    <td>序号</td>
                    <td>项目名称</td>
                    <td>项目负责人</td>
                    <td>计划开始时间</td>
                    <td>计划结束时间</td>
                    <td>风险状况</td>
                    <td>创建者</td>
                    <td>创建时间</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${projectList}" var="project" varStatus="index">
                    <tr align="center" id="tr_project_${project.id}">
                        <td>${index.index+1}</td>
                        <td>${project.label}</td>
                        <td>${project.owner.label}</td>
                        <td>${project.planStartDate}</td>
                        <td>${project.planEndDate}</td>
                        <td>${project.riskStatus}</td>
                        <td>${project.creator.label}</td>
                        <td><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <a type="button" class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/project/preSave.do?id=${project.id}">修改</a>
                            <button type="button" class="btn btn-danger btn-xs" onclick="deleteUser(${project.id},'${project.label}')">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>