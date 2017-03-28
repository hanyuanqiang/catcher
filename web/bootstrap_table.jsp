<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/17
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Latest compiled and minified CSS -->

<!-- Latest compiled and minified JavaScript -->


<div>

    <table id="tb_departments"></table>
    <script>
       $('#tb_departments').bootstrapTable({
           url: '${pageContext.request.contextPath}/user/list_json.do',
           columns: [{
              checkbox:true
           },{
               field: 'id',
               title: 'Item ID'
           },{
               field: 'account',
               title: '账号名'
           },{
               field: 'label',
               title: '标签'
           }],
           pagination: true,
           height:500,
           paginationLoop:false
       });
    </script>

</div>

