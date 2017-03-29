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
    function deleteBug(id,label) {
        layer.confirm('你确定要删除['+label+']吗？', {
            btn: ['是的','点错了'], //按钮
            icon:3
        }, function(){
            $.post("${pageContext.request.contextPath}/bug/delete.do",{id:id},function (result) {
                if(result.success){
                    $("#tr_bug_"+id).remove();
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
                <p>&nbsp;&nbsp;缺陷&nbsp;&nbsp;/&nbsp;&nbsp;缺陷列表</p>
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr align="center">
                    <td>序号</td>
                    <td>标题</td>
                    <td>创建者</td>
                    <td>创建时间</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${bugList}" var="bug" varStatus="index">
                    <tr align="center" id="tr_bug_${bug.id}">
                        <td>${index.index+1}</td>
                        <td>${bug.label}</td>
                        <td>${bug.creator.label}</td>
                        <td>
                            <fmt:formatDate value="${bug.createTime}" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <a type="button" class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/bug/preSave.do?id=${bug.id}">修改</a>
                            <button type="button" class="btn btn-danger btn-xs" onclick="deleteBug(${bug.id},'${bug.label}')">删除</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
