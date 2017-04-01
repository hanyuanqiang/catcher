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
                        <div>显示我参与的项目列表</div>
                    </div>
                </div>
            </div>
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
        });
    });

    /*初始化选择项目成员表格*/
    $('#IOwner').bootstrapTable({
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
            field: 'creator.label',
            title: '创建人'
        }],
//        striped:true,   //隔行变色
        pagination: true,
        paginationLoop:false,
        pageSize:10,
        pageList:[10,20],
        height:500,
        search:true,    //启用搜索框
        searchOnEnterKey:true,
        showRefresh:true,
        sidePagination:'server',    //服务端分页,后台必须返回rows，total这两个参数
        queryParams: function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                /*search:params.search,
                sort:params.sort,
                order:params.order,
                pageSize:params.pageSize,   //每页记录数
                pageNumber:params.pageNumber,   //要查询的页码
                searchText:params.searchText,
                sortName:params.sortName,
                sortOrder:params.sortOrder,*/
                mypara:'test',
//                sdate: $("#stratTime").val(),

            };
            return temp;
        }

    });
</script>