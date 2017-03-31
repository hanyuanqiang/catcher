<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  SysActivity: genius
  Date: 2017/3/31
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function deleteSysActivity(id) {
        layer.confirm('你确定要删除这条活动记录吗？', {
            btn: ['是的','点错了'], //按钮
            icon:3
        }, function(){
            $.post("${pageContext.request.contextPath}/sysActivity/delete.do",{id:id},function (result) {
                if(result.success){
                    $("#tr_sysActivity_"+id).remove();
                    layer.msg('删除成功', {
                        time: 3000, //3s后自动关闭
                    });
                }else{
                    layer.msg('删除失败', {
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
                <p>&nbsp;&nbsp;活动记录&nbsp;&nbsp;/&nbsp;&nbsp;列表</p>
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr align="center">
                    <td>序号</td>
                    <td>操作模型</td>
                    <td>动作</td>
                    <td>说明</td>
                    <td>执行时间</td>
                    <td>操作人</td>
                    <td>执行</td>
                </tr>
                <c:forEach items="${sysActivityList}" var="sysActivity" varStatus="index">
                    <tr align="center" id="tr_sysActivity_${sysActivity.id}">
                        <td>${index.index+1}</td>
                        <td>${sysActivity.model}</td>
                        <td>${sysActivity.action}</td>
                        <td>${sysActivity.message}</td>
                        <td>
                            <fmt:formatDate value="${sysActivity.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        </td>
                        <td>${sysActivity.owner.label}</td>
                        <td>
                            <button type="button" class="btn btn-danger btn-xs" onclick="deleteSysActivity(${sysActivity.id})">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
