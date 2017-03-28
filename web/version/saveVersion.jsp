<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/22
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jquery-label/css/jq22.css">

<style>
    .tbpd {
        padding:5px;
    }
    .a_user:hover{color:#3366cc!important;text-decoration:none;}
</style>

<div>
    <div class="panel panel-default" style="margin-top: 20px;">
        <div class="panel-heading">
            <h3 class="panel-title">
                &nbsp;&nbsp;版本&nbsp;&nbsp;/&nbsp;&nbsp;添加版本
            </h3>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/version/save.do" method="post" id="myForm" enctype="multipart/form-data">
                <input type="text" value="${version.id}" name="id" hidden>
                <input type="text" value="${version.creator.id}" name="creator.id" hidden>

                <table style="width: 1100px;">
                    <tr>
                        <td colspan="1" class="tbpd" align="right" style="width: 100px;">版本名称</td>
                        <td colspan="3" class="tbpd"><input type="text" class="form-control" name="label" value="${version.label}"></td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">计划发布时间</td>
                        <td colspan="1" class="tbpd">
                            <%--<fmt:formatDate value="${version.planDate}" var="fplanDate" pattern="yyyy-MM-dd"/>--%>
                            <input type="text" class="form-control" name="planDate" value="${version.planDate}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                        </td>
                        <td colspan="1" class="tbpd" align="right">实际发布时间</td>
                        <td colspan="1" class="tbpd">
                            <%--<fmt:formatDate value="${version.actualDate}" var="factualDate" pattern="yyyy-MM-dd"/>--%>
                            <input type="text" class="form-control" name="actualDate" value="${version.actualDate}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">版本描述</td>
                        <td colspan="3" class="tbpd">
                            <div>
                                <input type="text" name="description" hidden id="description_" value='${version.description}'>
                                <textarea id="editor" style="width: inherit">${version.description}</textarea>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="1" class="tbpd" align="right">版本负责人</td>
                        <td colspan="1" class="tbpd">
                            <div class="input-group">
                                <div class="form-control" aria-describedby="sizing-addon2">
                                    <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                        <p id="ownerTag" hidden></p>
                                        <script>
                                            <c:if test="${version!=null && version.owner!=null}">
                                            $("#ownerTag").after('<a title="${version.owner.label}" href="javascript:void(0);" id="a_owner" class="a_user"><span>${version.owner.label}</span><em onclick="removeOwner()"></em> </a>');
                                            $("#ownerTag").after('<input type="text" hidden value="${version.owner.id}" name="owner.id" id="input_owner">');
                                            </c:if>
                                        </script>
                                    </div>
                                </div>

                                <span class="input-group-addon" id="sizing-addon2" onclick="addOwner()">
                                    <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                </span>
                            </div>
                        </td>
                        <td colspan="1" class="tbpd" align="right">版本状态</td>
                        <td colspan="1" class="tbpd">
                            <select class="form-control" name="status">
                                <option ${version.status=='未发布'?'selected':''}>未发布</option>
                                <option ${version.status=='已发布'?'selected':''}>已发布</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">所属项目</td>
                        <td colspan="3" class="tbpd">

                            <div class="input-group" style="width: 100%">
                                <div class="form-control" aria-describedby="sizing-addon2">
                                    <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                        <p id="projectTag" hidden></p>
                                        <script>
                                            <c:if test="${version!=null && version.project!=null}">
                                            $("#projectTag").after('<a title="${version.project.label}" href="javascript:void(0);" id="a_project" class="a_user"><span>${version.project.label}</span><em onclick="removeProject()"></em> </a>');
                                            $("#projectTag").after('<input type="text" hidden value="${version.project.id}" name="project.id" id="input_project">');
                                            </c:if>
                                        </script>
                                    </div>
                                </div>
                                <span class="input-group-addon" id="sizing-addon2" onclick="addProject()">
                                    <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                </span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">项目附件</td>
                        <td colspan="3" class="tbpd">
                            <table>
                                <tr>
                                    <td>
                                        <input type="file" size="30" name="file"/>
                                        <input type="text" name="attachment" hidden value="${version.attachment}">
                                    </td>
                                    <td>
                                        <c:if test='${version != null && version.attachment != null && version.attachment != ""}'>
                                            ${version.attachment}<a href="javascript:void(0);" onclick="removeAttachment()">x</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" class="tbpd">
                            <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">保存</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>


    <div id="project_table_div" hidden>
        <table id="select_project_table"></table>
    </div>

    <div id="owner_table_div" hidden>
        <table id="select_owner_table"></table>
    </div>
</div>

<script>

    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
    function submitForm() {
        var desc = $('textarea#editor').froalaEditor('html.get');
        $("#description_").val(desc);
        $("#myForm").submit();
    }

    /*初始化富文本编辑器*/
    $(function() {
        $('textarea#editor').froalaEditor({
            language:"zh_cn",
            heightMin:300,
            toolbarButtons:['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', '|', 'specialCharacters', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', 'undo', 'redo', 'clearFormatting', 'selectAll', 'html'],
            width:977
        })
    });


    /*添加项目负责人*/
    function addOwner() {
        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
            $("#select_owner_table").bootstrapTable('load',result);
        },"json");

        //页面层
        layer.open({
            title:"添加用户",
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['650px', '510px'], //宽高
            scrollbar: false,
            content: $("#owner_table_div"),
            btn:['确定', '关闭'],
            yes:function (index,layero) {
                var users = $("#select_owner_table").bootstrapTable("getSelections");
                if(users.length == 1){
                    $.each(users,function (n,value) {
                        $("#a_owner").remove();
                        $("#input_owner").remove();
                        $("#ownerTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_owner"  class="a_user"><span>'+value.label+'</span><em onclick="removeOwner()"></em> </a>');
                        $("#ownerTag").after('<input type="text" hidden value="'+value.id+'" name="owner.id" id="input_owner">');
                    });
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }else{
                    layer.alert('请选择一个用户', {icon: 7});
                }
            }
        });
    }

    function removeOwner() {
        $("#a_owner").remove();
        $("#input_owner").remove();
    }

    /*添加项目成员*/
    function addProject() {
        $.post("${pageContext.request.contextPath}/project/list_json.do",function (result) {
            $('#select_project_table').bootstrapTable('load',result);
        },"json");

        //页面层
        layer.open({
            title:"选择所属项目",
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['650px', '510px'], //宽高
            scrollbar: false,
            content: $("#project_table_div"),
            btn:['确定', '关闭'],
            yes:function (index,layero) {
                var users = $('#select_project_table').bootstrapTable('getSelections');
                if(users.length > 0){
                    $.each(users,function (n,value) {
                        $("#a_project").remove();
                        $("#input_project").remove();
                        $("#projectTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_project"  class="a_user"><span>'+value.label+'</span><em onclick="removeProject()"></em> </a>');
                        $("#projectTag").after('<input type="text" hidden value="'+value.id+'" name="project.id" id="input_project">');
                    });
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }else{
                    layer.alert('请选择所属项目', {icon: 7});
                }
            }
        });
    }

    function removeProject() {
        $("#a_project").remove();
        $("#input_project").remove();
    }

    /*初始化选择项目成员表格*/
    $('#select_project_table').bootstrapTable({
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'ID',
            height:10
        },{
            field: 'label',
            title: '项目名称'
        },{
            field: 'createTime',
            title: '创建时间'
        },{
            field: 'creator',
            title: '创建者'
        }],
        pagination: true,
        height:400,
        paginationLoop:false,
        pageSize:10,
        pageList:[10,20]
    });

    /*初始化选择项目负责人表格*/
    $('#select_owner_table').bootstrapTable({
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'ID',
            height:10
        },{
            field: 'account',
            title: '账号名'
        },{
            field: 'label',
            title: '标签'
        }],
        pagination: true,
        height:400,
        paginationLoop:false,
        pageSize:10,
        pageList:[10,20]
        /*rowStyle:function (row,index) {
         return {
         css: {
         color: 'red',
         height:5
         }
         };
         }*/
        /*rowAttributes:function (row,index) {
         return {
         style:'height:20px;border:1px solid blue'
         }
         }*/
    });
</script>


