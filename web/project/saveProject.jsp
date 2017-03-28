<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/16
  Time: 14:59
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
                &nbsp;&nbsp;项目&nbsp;&nbsp;/&nbsp;&nbsp;添加项目
            </h3>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/project/save.do" method="post" id="myForm" enctype="multipart/form-data">
                <input type="text" value="${project.id}" name="id" hidden>
                <input type="text" value="${project.creator.id}" name="creator.id" hidden>
                <input type="text" value="${project.riskValue}" name="riskValue" hidden>

                <table style="width: 1100px;" border="1">
                    <tr>
                        <td colspan="1" class="tbpd" align="right" style="width: 100px;">项目名称</td>
                        <td colspan="3" class="tbpd"><input type="text" class="form-control" name="label" value="${project.label}"></td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">计划开始时间</td>
                        <td colspan="1" class="tbpd">
                            <fmt:formatDate value="${project.planStartDate}" var="fplanStartDate" pattern="yyyy-MM-dd"/>
                            <input type="text" class="form-control" name="planStartDate" value="${fplanStartDate}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                        </td>
                        <td colspan="1" class="tbpd" align="right">计划结束时间</td>
                        <td colspan="1" class="tbpd">
                            <fmt:formatDate value="${project.planEndDate}" var="fplanEndDate" pattern="yyyy-MM-dd"/>
                            <input type="text" class="form-control" name="planEndDate" value="${fplanEndDate}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">项目描述</td>
                        <td colspan="3" class="tbpd">
                            <div>
                                <input type="text" name="description" hidden id="description_" value='${project.description}'>
                                <textarea id="editor" style="width: inherit">${project.description}</textarea>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="1" class="tbpd" align="right">项目负责人</td>
                        <td colspan="1" class="tbpd">

                            <div class="input-group" style="width: 250px;">
                                <div class="form-control" aria-describedby="sizing-addon2">
                                    <div class="plus-tag tagbtn clearfix" id="myTags" style="height: 23px;">
                                        <p id="ownerTag" hidden></p>
                                        <script>
                                            <c:if test="${project!=null && project.owner!=null}">
                                            $("#ownerTag").after('<a title="${project.owner.label}" href="javascript:void(0);" id="a_owner" class="a_user"><span>${project.owner.label}</span><em onclick="removeOwner()"></em> </a>');
                                            $("#ownerTag").after('<input type="text" hidden value="${project.owner.id}" name="owner.id" id="input_owner">');
                                            </c:if>
                                        </script>
                                    </div>
                                </div>
                                <span class="input-group-addon" id="sizing-addon2" onclick="addOwner()">
                                    <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                </span>
                            </div>

                        </td>
                        <td colspan="1" class="tbpd" align="right">项目状态</td>
                        <td colspan="1" class="tbpd">
                            <select class="form-control" name="status">
                                <option ${project.status=='进行中'?'selected':''}>进行中</option>
                                <option ${project.status=='已完成'?'selected':''}>已完成</option>
                                <option ${project.status=='已归档'?'selected':''}>已归档</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">项目成员</td>
                        <td colspan="3" class="tbpd">

                            <div class="input-group" style="width: 1000px;height: 100px;">
                                <div class="form-control" aria-describedby="sizing-addon2" style="height: inherit">
                                    <div class="plus-tag tagbtn clearfix">
                                        <p id="membersTag" hidden></p>
                                        <c:if test="${project!=null && project.members != null}">
                                            <script>
                                                <c:forEach items="${project.members}" var="member">
                                                $("#membersTag").after('<a title="${member.label}" href="javascript:void(0);" id="a_member_${member.id}" class="a_user"><span>${member.label}</span><em onclick="removeMember(${member.id})"></em> </a>');
                                                $("#membersTag").after('<input type="text" hidden value="${member.id}" name="members[${member.id}].id" id="input_member_${member.id}">');
                                                </c:forEach>
                                            </script>
                                        </c:if>
                                    </div>
                                </div>
                                <span class="input-group-addon" id="sizing-addon2" onclick="addMembers()">
                                    <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                </span>
                            </div>


                            <%--<table>
                                <tr>
                                    <td>
                                        <div class="form-control" style="height: 100px;width: 900px;">
                                            <div class="plus-tag tagbtn clearfix">
                                                <p id="membersTag" hidden></p>
                                                <c:if test="${project!=null && project.members != null}">
                                                    <script>
                                                        <c:forEach items="${project.members}" var="member">
                                                            $("#membersTag").after('<a title="${member.label}" href="javascript:void(0);" id="a_member_${member.id}" class="a_user"><span>${member.label}</span><em onclick="removeMember(${member.id})"></em> </a>');
                                                            $("#membersTag").after('<input type="text" hidden value="${member.id}" name="members[${member.id}].id" id="input_member_${member.id}">');
                                                        </c:forEach>
                                                    </script>
                                                </c:if>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="button" class="btn btn-info btn-sm" value="添加成员" onclick="addMembers()"/>
                                    </td>
                                </tr>
                            </table>--%>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" class="tbpd" align="right">项目附件</td>
                        <td colspan="3" class="tbpd">
                            <table>
                                <tr>
                                    <td>
                                        <input type="file" size="30" name="file"/>
                                        <input type="text" name="attachment" hidden value="${project.attachment}">
                                    </td>
                                    <td>
                                        <c:if test='${project != null && project.attachment != null} && project.attachment != ""'>
                                            ${project.attachment}<a href="javascript:void(0);" onclick="removeAttachment()">x</a>
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


    <div id="members_table_div" hidden>
        <table id="select_members_table"></table>
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
            width:1000
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
    function addMembers() {
        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
            $('#select_members_table').bootstrapTable('load',result);
        },"json");

        //页面层
        layer.open({
            title:"添加用户",
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['650px', '510px'], //宽高
            scrollbar: false,
            content: $("#members_table_div"),
            btn:['确定', '关闭'],
            yes:function (index,layero) {
                var users = $('#select_members_table').bootstrapTable('getSelections');
                if(users.length > 0){
                    $.each(users,function (n,value) {
                        $("#a_member_"+value.id).remove();
                        $("#input_member_"+value.id).remove();
                        $("#membersTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_member_'+value.id+'" class="a_user"><span>'+value.label+'</span><em onclick="removeMember('+value.id+')"></em> </a>');
                        $("#membersTag").after('<input type="text" hidden value="'+value.id+'" name="members['+value.id+'].id" id="input_member_'+value.id+'">');
                    });
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }else{
                    layer.alert('请选择用户', {icon: 7});
                }
            }
        });
    }

    function removeMember(id) {
        $("#a_member_"+id).remove();
        $("#input_member_"+id).remove();
    }

    /*初始化选择项目成员表格*/
    $('#select_members_table').bootstrapTable({
        columns: [{
            checkbox:true
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

