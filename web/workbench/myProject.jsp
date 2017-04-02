<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/4/1
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>

    <div class="panel panel-default" style="margin-top: 20px;">
        <div class="panel-heading">
            <h3 class="panel-title">
                <p>&nbsp;&nbsp;个人工作台&nbsp;&nbsp;/&nbsp;&nbsp;我的项目</p>
            </h3>
        </div>
        <div class="panel-body">
            <div id="parentHorizontalTab" readonly="readonly">
                <ul class="resp-tabs-list hor_1">
                    <li>我负责的</li>
                    <li>我参与的</li>
                </ul>
                <div class="resp-tabs-container hor_1">
                    <div>
                        <%--显示我负责的项目列表--%>
                            <table id="IOwner"></table>
                    </div>
                    <div>
                        <%--显示我负责的项目列表--%>
                        <table id="IJoin"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="IOwnerToolbar" hidden>
        <button type="button" class="btn btn-danger btn-sm" onclick="deleteIOwnerProject()">删除</button>
    </div>

    <div id="IJoinToolbar" hidden>
        <button type="button" class="btn btn-danger btn-sm" onclick="deleteIJoinProject()">删除</button>
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
        });
    });


    $('#IOwner').bootstrapTable({
        method:'post',      //使用post方法要用下面的contentType
        contentType:'application/x-www-form-urlencoded',
        url:'${pageContext.request.contextPath}/project/list_json.do',
        columns: [{
            checkbox:true,
        },{
            align:'center',
            title: '序号',
            formatter:function(value,row,index){
                return index+1;
            }
        },{
            align:'center',
            field: 'label',
            title: '项目名称',
            formatter:function (value,row,index) {
                return '<a href="${pageContext.request.contextPath}/project/preSave.do?id='+row.id+'">'+value+'</a>'
            }
        },{
            align:'center',
            field: 'createTime',
            title: '创建时间'
        },{
            align:'center',
            field: 'owner.label',
            title: '项目负责人'
        },{
            align:'center',
            field: 'riskStatus',
            title: '风险状态',
            formatter:function (value,row,index) {
                if(value == '正常'){
                    return "<span>正常&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #13d438;'></i>";
                }else if(value == '警惕'){
                    return "<span>警惕&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #dcc72b;'></i>";
                }else if (value == '危险'){
                    return "<span>危险&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #d40e18;'></i>";
                }else{
                    return null;
                }
            }
        }],
//        striped:true,   //隔行变色
        pagination: true,
        paginationLoop:false,
        pageSize:10,
        pageList:[10,20],
        height:550,
        toolbar:'#IOwnerToolbar',
        search:true,    //启用搜索框
//        searchOnEnterKey:true,
        showRefresh:true,
        sidePagination:'server',    //服务端分页,后台必须返回rows，total这两个参数
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                label:params.search,     //查询的内容
                tag:'iOwner'
                /*search:params.search,
                sort:params.sort,
                order:params.order,
                pageSize:params.pageSize,   //每页记录数
                pageNumber:params.pageNumber,   //要查询的页码
                searchText:params.searchText,
                sortName:params.sortName,
                sortOrder:params.sortOrder,*/
//              sdate: $("#stratTime").val(),
            };
            return temp;
        }
    });

    $('#IJoin').bootstrapTable({
        method:'post',      //使用post方法要用下面的contentType
        contentType:'application/x-www-form-urlencoded',
        url:'${pageContext.request.contextPath}/project/list_json.do?',
        columns: [{
            checkbox:true,
        },{
            align:'center',
            title: '序号',
            formatter:function(value,row,index){
                return index+1;
            }
        },{
            align:'center',
            field: 'label',
            title: '项目名称',
            formatter:function (value,row,index) {
                return '<a href="${pageContext.request.contextPath}/project/preSave.do?id='+row.id+'">'+value+'</a>'
            }
        },{
            align:'center',
            field: 'createTime',
            title: '创建时间'
        },{
            align:'center',
            field: 'owner.label',
            title: '项目负责人'
        },{
            align:'center',
            field: 'riskStatus',
            title: '风险状态',
            formatter:function (value,row,index) {
                if(value == '正常'){
                    return "<span>正常&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #13d438;'></i>";
                }else if(value == '警惕'){
                    return "<span>警惕&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #dcc72b;'></i>";
                }else if (value == '危险'){
                    return "<span>危险&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #d40e18;'></i>";
                }else{
                    return null;
                }
            }
        }],
//        striped:true,   //隔行变色
        pagination: true,
        paginationLoop:false,
        pageSize:10,
        pageList:[10,20],
        height:550,
        toolbar:'#IJoinToolbar',
        search:true,    //启用搜索框
//        searchOnEnterKey:true,
        showRefresh:true,
        sidePagination:'server',    //服务端分页,后台必须返回rows，total这两个参数
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                label:params.search,     //查询的内容
                tag:'iJoin'
                /*search:params.search,
                 sort:params.sort,
                 order:params.order,
                 pageSize:params.pageSize,   //每页记录数
                 pageNumber:params.pageNumber,   //要查询的页码
                 searchText:params.searchText,
                 sortName:params.sortName,
                 sortOrder:params.sortOrder,*/
//              sdate: $("#stratTime").val(),
            };
            return temp;
        }
    });

    $("#IOwnerToolbar").show();
    $("#IJoinToolbar").show();

    function deleteIOwnerProject() {
        var projects = $('#IOwner').bootstrapTable('getSelections');
        var ids;
        if(projects.length > 0){
            $.each(projects,function (n,value) {
                if(n==0){
                    ids = value.id+'';
                }else{
                    ids = ids + ','+value.id;
                }
            });
            $.post("${pageContext.request.contextPath}/project/delete.do",{ids:ids},function (result) {
                if(result.success){
                    layer.msg('删除成功', {
                        time: 3000, //3s后自动关闭
                    });
                    $('#IOwner').bootstrapTable('refresh');
                }else{
                    layer.msg('删除失败', {
                        time: 3000, //3s后自动关闭
                        btn: ['知道了']
                    });
                }
            },"json");
        }else{
            layer.alert('请选择要删除的项目', {icon: 7});
        }
    }
    function deleteIJoinProject() {
        var projects = $('#IJoin').bootstrapTable('getSelections');
        var ids;
        if(projects.length > 0){
            $.each(projects,function (n,value) {
                if(n==0){
                    ids = value.id+'';
                }else{
                    ids = ids + ','+value.id;
                }
            });
            $.post("${pageContext.request.contextPath}/project/delete.do",{ids:ids},function (result) {
                if(result.success){
                    layer.msg('删除成功', {
                        time: 3000, //3s后自动关闭
                    });
                    $('#IJoin').bootstrapTable('refresh');
                }else{
                    layer.msg('删除失败', {
                        time: 3000, //3s后自动关闭
                        btn: ['知道了']
                    });
                }
            },"json");
        }else{
            layer.alert('请选择要删除的项目', {icon: 7});
        }
    }
</script>