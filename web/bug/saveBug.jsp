<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/23
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jquery-label/css/jq22.css">

<style>
    .tbpd {
        padding:5px;
    }
    .a_commom:hover{color:#3366cc!important;text-decoration:none;  }
</style>

<div>
    <div class="panel panel-default" style="margin-top: 20px;">
        <div class="panel-heading">
            <h3 class="panel-title">
                &nbsp;&nbsp;缺陷&nbsp;&nbsp;/&nbsp;&nbsp;新建缺陷
            </h3>
        </div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath}/bug/save.do" method="post" id="myForm" enctype="multipart/form-data">
                <input type="text" value="${bug.id}" name="id" hidden>
                <input type="text" value="${bug.status}" name="status" hidden>
                <input type="text" value="save" name="action" hidden id="bug_action">
                <%--<c:if test="${bug!=null && bug.processor!=null}">
                    <input type="text" value="${bug.processor.id}" name="processor.id" hidden>
                </c:if>
                <c:if test="${bug!=null && bug.verifier!=null}">
                    <input type="text" value="${bug.verifier.id}" name="verifier.id" hidden>
                </c:if>--%>

                <table border="1" style="width: 100%;">
                    <tr>
                        <td style="width:70%;">
                            <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                            <c:if test="${bug != null}">当前状态：${bug.status}</c:if>
                            <%--<c:if test="${bug == null}">
                                &lt;%&ndash;只有当新建的时候才显示保存按钮&ndash;%&gt;
                                <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">保存</button>
                            </c:if>--%>
                        </div>
                            <div id="parentHorizontalTab" readonly="readonly">
                                <ul class="resp-tabs-list hor_1">
                                    <li>基本信息</li>
                                    <li>附件</li>
                                </ul>
                                <div class="resp-tabs-container hor_1">
                                    <div>
                                        <table border="1" style="width: 100%;">
                                            <tr>
                                                <td colspan="1" class="tbpd" align="right" style="width:100px;">标题</td>
                                                <td colspan="3" class="tbpd" style="width: 75%;"><input type="text" class="form-control" name="label" value="${bug.label}"></td>
                                            </tr>
                                            <tr>
                                                <td colspan="1" class="tbpd" align="right" style="width:25%;">优先级</td>
                                                <td colspan="1" class="tbpd" style="width:25%;">
                                                    <select class="form-control" name="priority">
                                                        <option ${bug.priority=='最高'?'selected':''}>最高</option>
                                                        <option ${bug.priority=='较高'?'selected':''}>较高</option>
                                                        <option ${bug.priority=='一般'?'selected':''}>一般</option>
                                                        <option ${bug.priority=='较低'?'selected':''}>较低</option>
                                                        <option ${bug.priority=='最低'?'selected':''}>最低</option>
                                                    </select>
                                                </td>
                                                <td colspan="1" class="tbpd" align="right" style="width:25%;">严重程度</td>
                                                <td colspan="1" class="tbpd" style="width:25%;">
                                                    <select class="form-control" name="severity">
                                                        <option ${bug.severity=='阻塞'?'selected':''}>阻塞</option>
                                                        <option ${bug.severity=='严重'?'selected':''}>严重</option>
                                                        <option ${bug.severity=='主要'?'selected':''}>主要</option>
                                                        <option ${bug.severity=='次要'?'selected':''}>次要</option>
                                                        <option ${bug.severity=='提示'?'selected':''}>提示</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="1" class="tbpd" align="right">类型</td>
                                                <td colspan="1" class="tbpd">
                                                    <select class="form-control" name="type">
                                                        <option ${bug.type=='实现错误'?'selected':''}>实现错误</option>
                                                        <option ${bug.type=='需求变动'?'selected':''}>需求变动</option>
                                                        <option ${bug.type=='新增需求'?'selected':''}>新增需求</option>
                                                        <option ${bug.type=='性能优化'?'selected':''}>性能优化</option>
                                                    </select>
                                                </td>
                                                <td colspan="1" class="tbpd" align="right">缺陷来源</td>
                                                <td colspan="1" class="tbpd">
                                                    <select class="form-control" name="source">
                                                        <option ${bug.source=='开发人员'?'selected':''}>开发人员</option>
                                                        <option ${bug.source=='测试人员'?'selected':''}>测试人员</option>
                                                        <option ${bug.source=='客户'?'selected':''}>客户</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="1" class="tbpd" align="right">发现阶段</td>
                                                <td colspan="1" class="tbpd">
                                                    <select class="form-control" name="findStage">
                                                        <option ${bug.findStage=='设计阶段'?'selected':''}>设计阶段</option>
                                                        <option ${bug.findStage=='开发阶段'?'selected':''}>开发阶段</option>
                                                        <option ${bug.findStage=='测试阶段'?'selected':''}>测试阶段</option>
                                                        <option ${bug.findStage=='运行阶段'?'selected':''}>运行阶段</option>
                                                    </select>
                                                </td>
                                                <td colspan="1" class="tbpd" align="right">重现频率</td>
                                                <td colspan="1" class="tbpd">
                                                    <select class="form-control" name="frequency">
                                                        <option ${bug.frequency=='必然出现'?'selected':''}>必然出现</option>
                                                        <option ${bug.frequency=='经常出现'?'selected':''}>经常出现</option>
                                                        <option ${bug.frequency=='偶尔出现'?'selected':''}>偶尔出现</option>
                                                        <option ${bug.frequency=='无法重现'?'selected':''}>无法重现</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="1" class="tbpd" align="right">发现问题版本</td>
                                                <td colspan="1" class="tbpd">
                                                    <div class="input-group" style="width: 100%;">
                                                        <div class="form-control" aria-describedby="sizing-addon2">
                                                            <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                                                <p id="raisedVersionTag" hidden></p>
                                                                <script>
                                                                    <c:if test="${bug!=null && bug.raisedVersion!=null}">
                                                                    $("#raisedVersionTag").after('<a title="${bug.raisedVersion.label}" href="javascript:void(0);" id="a_raisedVersion" class="a_commom"><span>${bug.raisedVersion.label}</span><em onclick="removeRaisedVersion()"></em> </a>');
                                                                    $("#raisedVersionTag").after('<input type="text" hidden value="${bug.raisedVersion.id}" name="raisedVersion.id" id="input_raisedVersion">');
                                                                    </c:if>
                                                                </script>
                                                            </div>
                                                        </div>
                                                        <span class="input-group-addon" id="sizing-addon2" onclick="addRaisedVersion()">
                                                            <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td colspan="1" class="tbpd" align="right">解决期限</td>
                                                <td colspan="1" class="tbpd">
                                                    <input type="text" class="form-control" name="deadline" value="${bug.deadline}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="1" class="tbpd" align="right">描述</td>
                                                <td colspan="3" class="tbpd">
                                                    <input type="text" name="description" hidden id="description_" value='${bug.description}'>
                                                    <textarea id="editor" style="width: inherit">${bug.description}</textarea>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div>
                                        <div>该面板用于上传附件</div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td valign="top">
                            <c:if test='${bug.status == null}'><%--新建缺陷时显示--%>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="save" value="save" checked> 保存
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="allocation" value="allocation"> 分配
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="unassign" value="unassign"> 待分配
                                    </label>
                                    <div id="selectSolverDiv" hidden>
                                        <p>选择处理人:</p>
                                        <div class="input-group" style="width: 100%;">
                                            <div class="form-control" aria-describedby="sizing-addon2">
                                                <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                                    <p id="solverTag" hidden></p>
                                                        <%--<script>
                                                            <c:if test="${bug!=null && bug.processor!=null}">
                                                            $("#processorTag").after('<a title="${bug.processor.label}" href="javascript:void(0);" id="a_processor" class="a_user"><span>${bug.processor.label}</span><em onclick="removeProcessor()"></em> </a>');
                                                            $("#processorTag").after('<input type="text" hidden value="${bug.processor.id}" name="processor.id" id="input_processor">');
                                                            </c:if>
                                                        </script>--%>
                                                </div>
                                            </div>
                                            <span class="input-group-addon" id="sizing-addon2" onclick="addSolver()">
                                            <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                        </span>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    $(function(){
                                        $("#save").click(function(){
                                            $("#bug_action").val("save");
                                            removeSolver();
                                            $("#selectSolverDiv").hide();
                                        });
                                        $("#allocation").click(function(){
                                            $("#bug_action").val("commit");
                                            $("#selectSolverDiv").show();
                                        });
                                        $("#unassign").click(function(){
                                            removeSolver();
                                            $("#bug_action").val("unassign");
                                            $("#selectSolverDiv").hide();
                                        });
                                    });

                                    /*添加处理着*/
                                    function addSolver() {
                                        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
                                            $("#select_user_table").bootstrapTable('load',result);
                                        },"json");

                                        //页面层
                                        layer.open({
                                            title:"请选择处理人",
                                            type: 1,
                                            skin: 'layui-layer-rim', //加上边框
                                            area: ['650px', '530px'], //宽高
                                            scrollbar: false,
                                            content: $("#user_table_div"),
                                            btn:['确定', '关闭'],
                                            yes:function (index,layero) {
                                                var users = $("#select_user_table").bootstrapTable("getSelections");
                                                if(users.length == 1){
                                                    $.each(users,function (n,value) {
                                                        $("#a_solver").remove();
                                                        $("#input_solver").remove();
                                                        $("#solverTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_solver"  class="a_commom"><span>'+value.label+'</span><em onclick="removeSolver()"></em> </a>');
                                                        $("#solverTag").after('<input type="text" hidden value="'+value.id+'" name="solver.id" id="input_solver">');
                                                    });
                                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                                }else{
                                                    layer.alert('请选择一个用户', {icon: 7});
                                                }
                                            }
                                        });
                                    }
                                    function removeSolver() {
                                        $("#a_solver").remove();
                                        $("#input_solver").remove();
                                    }

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "创建中"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="save" value="save" checked> 保存
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="allocation" value="allocation"> 分配
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="unassign" value="unassign"> 待分配
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="abandon" value="abandon"> 废弃
                                    </label>

                                    <div id="selectSolverDiv" hidden>
                                        <p>选择处理人:</p>
                                        <div class="input-group" style="width: 100%;">
                                            <div class="form-control" aria-describedby="sizing-addon2">
                                                <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                                    <p id="solverTag" hidden></p>
                                                </div>
                                            </div>
                                            <span class="input-group-addon" id="sizing-addon2" onclick="addSolver()">
                                            <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                        </span>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    $(function(){
                                        $("#save").click(function(){
                                            $("#bug_action").val("save");
                                            removeSolver();
                                            $("#selectSolverDiv").hide();
                                        });
                                        $("#allocation").click(function(){
                                            $("#bug_action").val("commit");
                                            $("#selectSolverDiv").show();
                                        });
                                        $("#unassign").click(function(){
                                            $("#bug_action").val("unassign");
                                            removeSolver();
                                            $("#selectSolverDiv").hide();
                                        });
                                        $("#abandon").click(function(){
                                            $("#bug_action").val("abandon");
                                            removeSolver();
                                            $("#selectSolverDiv").hide();
                                        });
                                    });

                                    /*添加处理人*/
                                    function addSolver() {
                                        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
                                            $("#select_user_table").bootstrapTable('load',result);
                                        },"json");

                                        //页面层
                                        layer.open({
                                            title:"请选择处理人",
                                            type: 1,
                                            skin: 'layui-layer-rim', //加上边框
                                            area: ['650px', '530px'], //宽高
                                            scrollbar: false,
                                            content: $("#user_table_div"),
                                            btn:['确定', '关闭'],
                                            yes:function (index,layero) {
                                                var users = $("#select_user_table").bootstrapTable("getSelections");
                                                if(users.length == 1){
                                                    $.each(users,function (n,value) {
                                                        $("#a_solver").remove();
                                                        $("#input_solver").remove();
                                                        $("#solverTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_solver"  class="a_commom"><span>'+value.label+'</span><em onclick="removeSolver()"></em> </a>');
                                                        $("#solverTag").after('<input type="text" hidden value="'+value.id+'" name="solver.id" id="input_solver">');
                                                    });
                                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                                }else{
                                                    layer.alert('请选择一个用户', {icon: 7});
                                                }
                                            }
                                        });
                                    }
                                    function removeSolver() {
                                        $("#a_solver").remove();
                                        $("#input_solver").remove();
                                    }

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "待分配"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="save" value="save" checked> 保存
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="allocation" value="allocation"> 分配
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="hang" value="hang"> 挂起
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="rtnCreating" value="rtnCreating"> 退回
                                    </label>
                                    <div id="selectSolverDiv" hidden>
                                        <p>选择处理人:</p>
                                        <div class="input-group" style="width: 100%;">
                                            <div class="form-control" aria-describedby="sizing-addon2">
                                                <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                                    <p id="solverTag" hidden></p>
                                                        <%--<script>
                                                            <c:if test="${bug!=null && bug.processor!=null}">
                                                            $("#processorTag").after('<a title="${bug.processor.label}" href="javascript:void(0);" id="a_processor" class="a_user"><span>${bug.processor.label}</span><em onclick="removeProcessor()"></em> </a>');
                                                            $("#processorTag").after('<input type="text" hidden value="${bug.processor.id}" name="processor.id" id="input_processor">');
                                                            </c:if>
                                                        </script>--%>
                                                </div>
                                            </div>
                                            <span class="input-group-addon" id="sizing-addon2" onclick="addSolver()">
                                                <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    $(function(){
                                        $("#save").click(function(){
                                            removeSolver();
                                            $("#bug_action").val("save");
                                            $("#selectSolverDiv").hide();
                                        });
                                        $("#allocation").click(function(){
                                            $("#bug_action").val("commit");
                                            $("#selectSolverDiv").show();
                                        });
                                        $("#hang").click(function(){
                                            removeSolver();
                                            $("#bug_action").val("hang");
                                            $("#selectSolverDiv").hide();
                                        });
                                        $("#rtnCreating").click(function(){
                                            removeSolver();
                                            $("#bug_action").val("rtnCreating");
                                            $("#selectSolverDiv").hide();
                                        });
                                    });

                                    /*添加处理着*/
                                    function addSolver() {
                                        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
                                            $("#select_user_table").bootstrapTable('load',result);
                                        },"json");

                                        //页面层
                                        layer.open({
                                            title:"请选择处理人",
                                            type: 1,
                                            skin: 'layui-layer-rim', //加上边框
                                            area: ['650px', '530px'], //宽高
                                            scrollbar: false,
                                            content: $("#user_table_div"),
                                            btn:['确定', '关闭'],
                                            yes:function (index,layero) {
                                                var users = $("#select_user_table").bootstrapTable("getSelections");
                                                if(users.length == 1){
                                                    $.each(users,function (n,value) {
                                                        $("#a_solver").remove();
                                                        $("#input_solver").remove();
                                                        $("#solverTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_solver"  class="a_commom"><span>'+value.label+'</span><em onclick="removeSolver()"></em> </a>');
                                                        $("#solverTag").after('<input type="text" hidden value="'+value.id+'" name="solver.id" id="input_solver">');
                                                    });
                                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                                }else{
                                                    layer.alert('请选择一个用户', {icon: 7});
                                                }
                                            }
                                        });
                                    }
                                    function removeSolver() {
                                        $("#a_solver").remove();
                                        $("#input_solver").remove();
                                    }

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "已废弃"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="rtnCreating" value="rtnCreating" checked> 重新激活
                                    </label>
                                </div>

                                <script>
                                    $(function(){
                                        $("#rtnCreating").click(function(){
                                            $("#bug_action").val("rtnCreating");
                                        });
                                    });

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "处理中"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="save" value="save" checked> 保存
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="allocation" value="allocation"> 分配
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="hang" value="hang"> 挂起
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="rtnUnassign" value="rtnUnassign"> 退回
                                    </label>
                                    <div id="selectSolverDiv">
                                        <p>当前处理人:</p>
                                        <div class="input-group" style="width: 100%;">
                                            <div class="form-control" aria-describedby="sizing-addon2">
                                                <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                                    <p id="solverTag" hidden></p>
                                                        <script>
                                                            <c:if test="${bug!=null && bug.solver!=null}">
                                                                $("#solverTag").after('<a title="${bug.solver.label}" href="javascript:void(0);" id="a_solver" class="a_commom"><span>${bug.solver.label}</span><em onclick="removeSolver()"></em> </a>');
                                                                $("#solverTag").after('<input type="text" hidden value="${bug.solver.id}" name="solver.id" id="input_solver">');
                                                            </c:if>
                                                        </script>
                                                </div>
                                            </div>
                                            <span class="input-group-addon" id="sizing-addon2" onclick="addSolver()">
                                                <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <div id="selectVerifierDiv" hidden>
                                        <p>选择验证者:</p>
                                        <div class="input-group" style="width: 100%;">
                                            <div class="form-control" aria-describedby="sizing-addon2">
                                                <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                                    <p id="verifierTag" hidden></p>
                                                        <%--<script>
                                                            <c:if test="${bug!=null && bug.processor!=null}">
                                                            $("#processorTag").after('<a title="${bug.processor.label}" href="javascript:void(0);" id="a_processor" class="a_user"><span>${bug.processor.label}</span><em onclick="removeProcessor()"></em> </a>');
                                                            $("#processorTag").after('<input type="text" hidden value="${bug.processor.id}" name="processor.id" id="input_processor">');
                                                            </c:if>
                                                        </script>--%>
                                                </div>
                                            </div>
                                            <span class="input-group-addon" id="sizing-addon2" onclick="addVerifier()">
                                                <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    $(function(){
                                        $("#save").click(function(){
                                            $("#bug_action").val("save");
                                            $("#selectSolverDiv").show();//显示当前处理人
                                            removeVerifier();   //移除验证人
                                            $("#selectVerifierDiv").hide(); //隐藏选择验证人的div
                                        });
                                        $("#allocation").click(function(){
                                            $("#bug_action").val("commit");
                                            $("#selectSolverDiv").hide();
                                            $("#selectVerifierDiv").show();
                                        });
                                        $("#hang").click(function(){
                                            removeSolver();
                                            removeVerifier();
                                            $("#bug_action").val("hang");
                                            $("#selectSolverDiv").hide();
                                            $("#selectVerifierDiv").hide();
                                        });
                                        $("#rtnUnassign").click(function(){
                                            removeSolver();
                                            removeVerifier();
                                            $("#bug_action").val("rtnUnassign");
                                            $("#selectSolverDiv").hide();
                                            $("#selectVerifierDiv").hide();
                                        });
                                    });

                                    /*添加处理人*/
                                    function addSolver() {
                                        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
                                            $("#select_user_table").bootstrapTable('load',result);
                                        },"json");

                                        //页面层
                                        layer.open({
                                            title:"请选择处理人",
                                            type: 1,
                                            skin: 'layui-layer-rim', //加上边框
                                            area: ['650px', '530px'], //宽高
                                            scrollbar: false,
                                            content: $("#user_table_div"),
                                            btn:['确定', '关闭'],
                                            yes:function (index,layero) {
                                                var users = $("#select_user_table").bootstrapTable("getSelections");
                                                if(users.length == 1){
                                                    $.each(users,function (n,value) {
                                                        $("#a_solver").remove();
                                                        $("#input_solver").remove();
                                                        $("#solverTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_solver"  class="a_commom"><span>'+value.label+'</span><em onclick="removeSolver()"></em> </a>');
                                                        $("#solverTag").after('<input type="text" hidden value="'+value.id+'" name="solver.id" id="input_solver">');
                                                    });
                                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                                }else{
                                                    layer.alert('请选择一个用户', {icon: 7});
                                                }
                                            }
                                        });
                                    }
                                    function removeSolver() {
                                        $("#a_solver").remove();
                                        $("#input_solver").remove();
                                    }

                                    /*添加验证者*/
                                    function addVerifier() {
                                        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
                                            $("#select_user_table").bootstrapTable('load',result);
                                        },"json");

                                        //页面层
                                        layer.open({
                                            title:"请选择验证者",
                                            type: 1,
                                            skin: 'layui-layer-rim', //加上边框
                                            area: ['650px', '530px'], //宽高
                                            scrollbar: false,
                                            content: $("#user_table_div"),
                                            btn:['确定', '关闭'],
                                            yes:function (index,layero) {
                                                var users = $("#select_user_table").bootstrapTable("getSelections");
                                                if(users.length == 1){
                                                    $.each(users,function (n,value) {
                                                        $("#a_verifier").remove();
                                                        $("#input_verifier").remove();
                                                        $("#verifierTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_verifier"  class="a_commom"><span>'+value.label+'</span><em onclick="removeVerifier()"></em> </a>');
                                                        $("#verifierTag").after('<input type="text" hidden value="'+value.id+'" name="verifier.id" id="input_verifier">');
                                                    });
                                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                                }else{
                                                    layer.alert('请选择一个用户', {icon: 7});
                                                }
                                            }
                                        });
                                    }

                                    function removeVerifier() {
                                        $("#a_verifier").remove();
                                        $("#input_verifier").remove();
                                    }

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "验证中"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="save" value="save" checked> 保存
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="rtnProcess" value="rtnProcess"> 验证失败
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="close" value="close"> 关闭
                                    </label>
                                    <div id="selectVerifierDiv" hidden>
                                        <p>当前验证人:</p>
                                        <div class="input-group" style="width: 100%;">
                                            <div class="form-control" aria-describedby="sizing-addon2">
                                                <div class="plus-tag tagbtn clearfix" style="height: 23px;">
                                                    <p id="verifierTag" hidden></p>
                                                        <script>
                                                            <c:if test="${bug!=null && bug.processor!=null}">
                                                            $("#verifierTag").after('<a title="${bug.verifier.label}" href="javascript:void(0);" id="a_verifier" class="a_commom"><span>${bug.verifier.label}</span><em onclick="removeProcessor()"></em> </a>');
                                                            $("#verifierTag").after('<input type="text" hidden value="${bug.verifier.id}" name="verifier.id" id="input_verifier">');
                                                            </c:if>
                                                        </script>
                                                </div>
                                            </div>
                                            <span class="input-group-addon" id="sizing-addon2" onclick="addVerifier()">
                                                <i class="fa fa-plus" aria-hidden="true" style="font-size: 18px;cursor:pointer;color:#5EAEE3;font-stretch: normal"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>

                                <script>
                                    $(function(){
                                        $("#save").click(function(){
                                            $("#bug_action").val("save");
                                            $("#selectVerifierDiv").show();
                                        });
                                        $("#rtnProcess").click(function(){
                                            $("#bug_action").val("rtnProcess");
                                            removeVerifier();
                                            $("#selectVerifierDiv").hide();
                                        });
                                        $("#close").click(function(){
                                            removeVerifier();
                                            $("#bug_action").val("close");
                                            $("#selectVerifierDiv").hide();
                                        });

                                    });

                                    /*添加验证者*/
                                    function addVerifier() {
                                        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
                                            $("#select_user_table").bootstrapTable('load',result);
                                        },"json");

                                        //页面层
                                        layer.open({
                                            title:"请选择验证者",
                                            type: 1,
                                            skin: 'layui-layer-rim', //加上边框
                                            area: ['650px', '530px'], //宽高
                                            scrollbar: false,
                                            content: $("#user_table_div"),
                                            btn:['确定', '关闭'],
                                            yes:function (index,layero) {
                                                var users = $("#select_user_table").bootstrapTable("getSelections");
                                                if(users.length == 1){
                                                    $.each(users,function (n,value) {
                                                        $("#a_verifier").remove();
                                                        $("#input_verifier").remove();
                                                        $("#verifierTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_verifier"  class="a_commom"><span>'+value.label+'</span><em onclick="removeVerifier()"></em> </a>');
                                                        $("#verifierTag").after('<input type="text" hidden value="'+value.id+'" name="verifier.id" id="input_verifier">');
                                                    });
                                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                                }else{
                                                    layer.alert('请选择一个用户', {icon: 7});
                                                }
                                            }
                                        });
                                    }

                                    function removeVerifier() {
                                        $("#a_verifier").remove();
                                        $("#input_verifier").remove();
                                    }

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "已挂起"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="save" value="save" checked> 保存
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="unassign" value="unassign"> 待分配
                                    </label>
                                </div>

                                <script>
                                    $(function(){
                                        $("#save").click(function(){
                                            $("#bug_action").val("save");
                                        });
                                        $("#unassign").click(function(){
                                            $("#bug_action").val("unassign");
                                        });
                                    });

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "已关闭"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="rOpen" value="rOpen" checked> 重打开
                                    </label>
                                </div>

                                <script>
                                    $(function(){
                                        $("#rOpen").click(function(){
                                            $("#bug_action").val("rOpen");
                                        });
                                    });

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                            <c:if test='${bug.status == "重打开"}'>
                                <div style="height: 40px;width: 100%;border: 1px rebeccapurple solid">
                                    <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
                                </div>
                                <div>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="save" value="save" checked> 保存
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="inlineRadioOptions" id="rtnProcess" value="rtnProcess" checked> 退回处理中
                                    </label>
                                </div>

                                <script>
                                    $(function(){
                                        $("#save").click(function(){
                                            $("#bug_action").val("save");
                                        });
                                        $("#rtnProcess").click(function(){
                                            $("#bug_action").val("rtnProcess");
                                        });
                                    });

                                    /*提交表单时，把富文本编辑框中的内容取出放到input中*/
                                    function submitForm() {
                                        var desc = $('textarea#editor').froalaEditor('html.get');
                                        $("#description_").val(desc);
                                        $("#myForm").submit();
                                    }
                                </script>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div id="version_table_div" hidden>
        <table id="select_version_table"></table>
    </div>

    <div id="user_table_div" hidden>
        <table id="select_user_table"></table>
    </div>

    <%--<c:if test='${bug.status == "创建中"}'>
        <button type="button" class="btn btn-info" style="float: right" onclick="submitForm()">提交</button>
    </c:if>--%>
    <%--<c:if test='${bug.status == "待分配"}'>

    </c:if>--%>
    <%--<c:if test='${bug.status == "处理中"}'>

    </c:if>--%>
    <%--<c:if test='${bug.status == "验证中"}'>

    </c:if>--%>
    <%--<c:if test='${bug.status == "已废弃"}'>

    </c:if>--%>
    <c:if test='${bug.status == "已关闭"}'>

    </c:if>
    <%--<c:if test='${bug.status == "已挂起"}'>

    </c:if>--%>
</div>

<script>

    /*$(function() {
        alert("here");
        $("#parentHorizontalTab:has(input)").each(function(){
            $(this).attr("readonly","readonly");
        });
    })*/

    /*初始化富文本编辑器*/
    $(function() {
        $('textarea#editor').froalaEditor({
            language:"zh_cn",
            heightMin:300,
            toolbarButtons:['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', '|', 'specialCharacters', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', 'undo', 'redo', 'clearFormatting', 'selectAll', 'html'],
            /*width:977*/
        })
    });




    /*/!*添加处理着*!/
    function addProcessor() {
        $.post("${pageContext.request.contextPath}/user/list_json.do",function (result) {
            $("#select_user_table").bootstrapTable('load',result);
        },"json");

        //页面层
        layer.open({
            title:"请选择处理人",
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['650px', '530px'], //宽高
            scrollbar: false,
            content: $("#user_table_div"),
            btn:['确定', '关闭'],
            yes:function (index,layero) {
                var users = $("#select_user_table").bootstrapTable("getSelections");
                if(users.length == 1){
                    $.each(users,function (n,value) {
                        $("#a_processor").remove();
                        $("#input_processor").remove();
                        $("#processorTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_processor"  class="a_user"><span>'+value.label+'</span><em onclick="removeProcessor()"></em> </a>');
                        $("#processorTag").after('<input type="text" hidden value="'+value.id+'" name="processor.id" id="input_processor">');
                    });
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }else{
                    layer.alert('请选择一个用户', {icon: 7});
                }
            }
        });
    }

    function removeProcessor() {
        $("#a_processor").remove();
        $("#input_processor").remove();
    }*/

    /*添加发现问题版本*/
    function addRaisedVersion() {
        $.post("${pageContext.request.contextPath}/version/list_json.do",function (result) {
            $('#select_version_table').bootstrapTable('load',result);
        },"json");

        //页面层
        layer.open({
            title:"选择发现该bug的版本",
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['650px', '510px'], //宽高
            scrollbar: false,
            content: $("#version_table_div"),
            btn:['确定', '关闭'],
            yes:function (index,layero) {
                var users = $('#select_version_table').bootstrapTable('getSelections');
                if(users.length > 0){
                    $.each(users,function (n,value) {
                        $("#a_raisedVersion").remove();
                        $("#input_raisedVersion").remove();
                        $("#raisedVersionTag").after('<a title="'+value.label+'" href="javascript:void(0);" id="a_raisedVersion"  class="a_commom"><span>'+value.label+'</span><em onclick="removeRaisedVersion()"></em> </a>');
                        $("#raisedVersionTag").after('<input type="text" hidden value="'+value.id+'" name="raisedVersion.id" id="input_raisedVersion">');
                    });
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }else{
                    layer.alert('请选择所属项目', {icon: 7});
                }
            }
        });
    }

    function removeRaisedVersion() {
        $("#a_raisedVersion").remove();
        $("#input_raisedVersion").remove();
    }

    /*初始化选择项目成员表格*/
    $('#select_version_table').bootstrapTable({
        columns: [{
            radio:true
        },{
            field: 'id',
            title: 'ID',
            height:10
        },{
            field: 'label',
            title: '版本名称'
        },{
            field: 'createTime',
            title: '创建时间'
        },{
            field: 'creator.label',
            title: '创建者'
        }],
        pagination: true,
        height:400,
        paginationLoop:false,
        pageSize:10,
        pageList:[10,20]
    });

    /*初始化选择项目负责人表格*/
    $('#select_user_table').bootstrapTable({
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
            title: '姓名'
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

<!--Plug-in Initialisation-->
<script type="text/javascript">
    $(document).ready(function() {
        //Horizontal Tab
        $('#parentHorizontalTab').easyResponsiveTabs({
            type: 'default', //Types: default, vertical, accordion
            width: 'auto', //auto or any width like 600px
            fit: true, // 100% fit in a container
            tabidentify: 'hor_1', // The tab groups identifier
            /*activate: function(event) { // Callback function if tab is switched
                var $tab = $(this);
                var $info = $('#nested-tabInfo');
                var $name = $('span', $info);
                $name.text($tab.text());
                $info.show();
            }*/
        });
    });
</script>
