<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/22
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!--把下面代码加到<head>与</head>之间-->
    <style type="text/css">

        .projectStatus{
            padding:20px;
        }

        .projectStatus td{
            font-family:verdana;
            cursor:default;
            font-size:17px;
            vertical-align: top;
        }
        .projectStatus a{
            font-size:15px;
            color:#006699;
            line-height:160%;
            text-decoration:none;
        }
        .projectStatus a:link{
            font-size:15px;
            color:#006699;
            line-height:160%;
            text-decoration:none;
        }
        .projectStatus a:active{
            font-size:15px;color:#990000;
            line-height:160%;
        }
        .projectStatus a:hover{
            font-size:15px; color:#3366ff;
            line-height:160%;
            text-decoration:none;
        }
        .projectStatus li{
            list-style-type:square;
            margin:0px;
            padding:0px;
            height:23px;
            margin-top: 10px;
            margin-left:-10px;
        }
        .projectStatus li span{
            color:black;
            /*cursor:hand;*/
            text-decoration:none;
            /*border:1px solid #F1F1F1;*/
            margin:0px;
            padding-left:1px;
            padding-right:0px;
            padding-top:0px;
            padding-bottom:0px;
            position:relative;
            top:0px;
            left:-1px;
        }
        /*.projectStatus .liOver{
            background-color:#dddddd;
            border:1px solid #000000;
        }
        .projectStatus .liout{
            background-color:#f1f1f1;
            border:1px solid #f1f1f1;
        }
        .projectStatus li span.lidown{
            background-color:#00ff00;
            border:1px solid #999999;
        }*/

        .subTable{
            width: 350px;
            height: 250px;
            background-color: white;
        }

        .parentTable{
            background-color: #FFFFE0;
        }

        .time{
            color:#CCC;
            font-size: 13px;
        }
    </style>

<div class="projectStatus">
    <!--把下面代码加到<body>与</body>之间-->
    <table class="parentTable">
        <tr style="height: 250px;" valign="top">
            <td>
                <table class="subTable" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#999999" bordercolordark="#ffffff">
                    <tr bgcolor="#ccccff" valign="center">
                        <td height="30" align="center"><div style="margin-top: 4px;"><span style="color: black;font-family: 黑体;">危险状态&nbsp;<i class='fa fa-square' style='font-size: 16px;color: rgb(220,62,53);'></i></span></div></td>
                    </tr>
                    <tr>
                        <td>
                            <ul>
                                <c:forEach items="${dangerProjectList}" var="project" begin="0" end="5">
                                    <li>
                                        <span onMouseOver="this.className='liOver'" onMouseOut="this.className='liout'" onMousedown="this.className='lidown'" class="liout">
                                            <a title="${project.label}" <%--target="_blank"--%> href="${pageContext.request.contextPath}/project/preSave.do?id=${project.id}">${project.label}</a>
                                            <span class="time"><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd" type="date"></fmt:formatDate></span>
                                        </span>
                                    </li>
                                </c:forEach>
                                <c:if test="${fn:length(dangerProjectList) > 6}">
                                    <p><a href="${pageContext.request.contextPath}/project/list.do?riskStatus=危险" style="float: right;font-size: 12px;">+更多</a></p>
                                </c:if>
                            </ul>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="20"></td>
            <td>
                <table  class="subTable" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#999999" bordercolordark="#ffffff" >
                    <tr bgcolor="#ccccff">
                        <td height="30" align="center"><div style="margin-top: 4px;"><span style="color: black;font-family: 黑体">警惕状态&nbsp;<i class='fa fa-square' style='font-size: 16px;color: rgb(220,199,43);'></i></span></div></td>
                    </tr>
                    <tr>
                        <td>
                            <ul>
                                <c:forEach items="${warningProjectList}" var="project" begin="0" end="5">
                                    <li>
                                        <span onMouseOver="this.className='liOver'" onMouseOut="this.className='liout'" onMousedown="this.className='lidown'" class="liout">
                                            <a title="${project.label}" <%--target="_blank"--%> href="${pageContext.request.contextPath}/project/preSave.do?id=${project.id}">${project.label}</a>
                                            <span class="time"><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd" type="date"></fmt:formatDate></span>
                                        </span>
                                    </li>
                                </c:forEach>
                                <c:if test="${fn:length(warningProjectList) > 6}">
                                    <p><a href="${pageContext.request.contextPath}/project/list.do?riskStatus=警惕" style="float: right;font-size: 12px;">+更多</a></p>
                                </c:if>
                            </ul>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="20"></td>
            <td>
                <table class="subTable" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#999999" bordercolordark="#ffffff" >
                    <tr bgcolor="#ccccff">
                        <td height="30" align="center"><div style="margin-top: 4px;"><span style="color: black;font-family: 黑体">已延期&nbsp;<i class='fa fa-square' style='font-size: 16px;color: rgb(220,127,11);'></i></span></div></td>
                    </tr>
                    <tr>
                        <td>
                            <ul>
                                <c:forEach items="${delayProjectList}" var="project" begin="0" end="5">
                                    <li>
                                        <span onMouseOver="this.className='liOver'" onMouseOut="this.className='liout'" onMousedown="this.className='lidown'" class="liout">
                                            <a title="${project.label}" <%--target="_blank"--%> href="${pageContext.request.contextPath}/project/preSave.do?id=${project.id}">${project.label}</a>
                                            <span class="time"><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd" type="date"></fmt:formatDate></span>
                                        </span>
                                    </li>
                                </c:forEach>
                                <c:if test="${fn:length(delayProjectList) > 6}">
                                    <p><a href="${pageContext.request.contextPath}/project/list.do?tag=delay" style="float: right;font-size: 12px;">+更多</a></p>
                                </c:if>
                            </ul>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height: 25px;"></tr>
        <tr style="height: 300px;" valign="top">
            <td>
                <table class="subTable" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#999999" bordercolordark="#ffffff" >
                    <tr bgcolor="#ccccff">
                        <td height="30" align="center"><div style="margin-top: 4px;"><span style="color: black;font-family: 黑体">进行中&nbsp;<i class='fa fa-square' style='font-size: 16px;color: rgb(51,220,123);'></i></span></div></td>
                    </tr>
                    <tr>
                        <td>
                            <ul>
                                <c:forEach items="${ingProjectList}" var="project" begin="0" end="5">
                                    <li>
                                        <span onMouseOver="this.className='liOver'" onMouseOut="this.className='liout'" onMousedown="this.className='lidown'" class="liout">
                                            <a title="${project.label}" <%--target="_blank"--%> href="${pageContext.request.contextPath}/project/preSave.do?id=${project.id}">${project.label}</a>
                                            <span class="time"><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd" type="date"></fmt:formatDate></span>
                                        </span>
                                    </li>
                                </c:forEach>
                                <c:if test="${fn:length(ingProjectList) > 6}">
                                    <p><a href="${pageContext.request.contextPath}/project/list.do?status=进行中" style="float: right;font-size: 12px;">+更多</a></p>
                                </c:if>
                            </ul>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="20"></td>
            <td>
                <table class="subTable" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#999999" bordercolordark="#ffffff" >
                    <tr bgcolor="#ccccff">
                        <td height="30" align="center"><div style="margin-top: 4px;"><span style="color: black;font-family: 黑体">已完成&nbsp;<i class='fa fa-square' style='font-size: 16px;color: rgb(10,26,220);'></i></span></div></td>
                    </tr>
                    <tr>
                        <td>
                            <ul>
                                <c:forEach items="${finishProjectList}" var="project" begin="0" end="5">
                                    <li>
                                        <span onMouseOver="this.className='liOver'" onMouseOut="this.className='liout'" onMousedown="this.className='lidown'" class="liout">
                                            <a title="${project.label}" <%--target="_blank"--%> href="${pageContext.request.contextPath}/project/preSave.do?id=${project.id}">${project.label}</a>
                                            <span class="time"><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd" type="date"></fmt:formatDate></span>
                                        </span>
                                    </li>
                                </c:forEach>
                                <c:if test="${fn:length(finishProjectList) > 6}">
                                    <p><a href="${pageContext.request.contextPath}/project/list.do?status=已完成" style="float: right;font-size: 12px;">+更多</a></p>
                                </c:if>
                            </ul>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="20"></td>
            <td>
                <table class="subTable" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#999999" bordercolordark="#ffffff" >
                    <tr bgcolor="#ccccff">
                        <td height="30" align="center"><div style="margin-top: 4px;"><span style="color: black;font-family: 黑体">已归档&nbsp;<i class='fa fa-square' style='font-size: 16px;color: rgb(97,50,165);'></i></span></div></td>
                    </tr>
                    <tr>
                        <td>
                            <ul>
                                <c:forEach items="${doneProjectList}" var="project" begin="0" end="5">
                                    <li>
                                        <span onMouseOver="this.className='liOver'" onMouseOut="this.className='liout'" onMousedown="this.className='lidown'" class="liout">
                                            <a title="${project.label}" <%--target="_blank"--%> href="${pageContext.request.contextPath}/project/preSave.do?id=${project.id}">${project.label}</a>
                                            <span class="time"><fmt:formatDate value="${project.createTime}" pattern="yyyy-MM-dd" type="date"></fmt:formatDate></span>
                                        </span>
                                    </li>
                                </c:forEach>
                                <c:if test="${fn:length(doneProjectList) > 6}">
                                    <p><a href="${pageContext.request.contextPath}/project/list.do?status=已归档" style="float: right;font-size: 12px;">+更多</a></p>
                                </c:if>
                            </ul>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>


