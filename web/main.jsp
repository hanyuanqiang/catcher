<%--
  Created by IntelliJ IDEA.
  User: genius
  Date: 2017/3/14
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="en" class="cye-disabled cye-nm" style="overflow: hidden;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>catcher缺陷管理系统</title>

    <!--bootstrap相关样式 start-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.3.7/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-table-master/dist/bootstrap-table.min.css">
    <!--bootstrap相关样式 start-->

    <!-- 模板样式 start -->
    <link href="${pageContext.request.contextPath}/template/css/jquery.gritter.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/template/css/style(1).css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/template/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/template/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/template/css/zabuto_calendar.css" rel="stylesheet">
    <!-- 模板样式 start -->

    <!-- froala_editor 的样式 start  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/font-awesome-4.7.0/css/font-awesome.css">
    <link href="${pageContext.request.contextPath}/static/froala_editor/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/static/froala_editor/css/froala_style.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/froala_editor.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/froala_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/code_view.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/colors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/emoticons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/image_manager.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/image.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/line_breaker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/char_counter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/video.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/fullscreen.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/froala_editor/css/plugins/file.css">
    <!-- froala_editor 的样式 end  -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/Easy-Responsive-Tabs/css/easy-responsive-tabs.css">

    <!--  必须的js文件，包括froala_editor也要用到 -->
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN.min.js"></script>

    <script src="${pageContext.request.contextPath}/static/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>

    <!--froala editor 脚本 start-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/froala_editor.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/froala_editor.pkgd.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/align.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/code_beautifier.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/code_view.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/colors.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/draggable.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/emoticons.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/font_size.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/font_family.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/image.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/image_manager.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/file.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/line_breaker.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/link.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/lists.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/paragraph_format.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/paragraph_style.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/video.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/table.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/url.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/entities.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/char_counter.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/inline_style.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/save.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/plugins/fullscreen.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/froala_editor/js/languages/zh_cn.js"></script>
    <!--froala editor 脚本 start-->

</head>

<body>

<section id="container">
    <!-- **********************************************************************************************************************************************************
    TOP BAR CONTENT & NOTIFICATIONS
    *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
        <div class="sidebar-toggle-box">
            <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
        </div>
        <!--logo start-->
        <a href="#" class="logo" has_check_key_word="true"><b>catcher缺陷管理系统</b></a>
        <!--logo end-->

        <div class="top-menu">
            <ul class="nav pull-right top-menu">
                <li><a class="logout" href="#" has_check_key_word="true">注销</a></li>
            </ul>
        </div>
    </header>
    <!--header end-->

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse " tabindex="5000" style="overflow: hidden; outline: none;" style="font-size: 30px;">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">

                <li class="sub-menu dcjq-parent-li">
                    <a href="javascript:;" class="dcjq-parent" has_check_key_word="true">
                        <i class="fa fa-home"></i>
                        <span>个人工作台</span>
                        <span class="dcjq-icon"></span>
                    </a>
                    <ul class="sub" style="display: none;">
                        <li><a href="${pageContext.request.contextPath}/project/myProject.do" has_check_key_word="true">我的项目</a></li>
                        <li><a href="#" has_check_key_word="true">我的版本</a></li>
                        <li><a href="#" has_check_key_word="true">我的缺陷</a></li>
                        <li><a href="#" has_check_key_word="true">我的消息</a></li>
                    </ul>
                </li>

                <li class="sub-menu dcjq-parent-li">
                    <a href="javascript:;" class="dcjq-parent" has_check_key_word="true">
                        <i class="fa fa-desktop"></i>
                        <span>项目</span>
                        <span class="dcjq-icon"></span>
                    </a>
                    <ul class="sub" style="display: none;">
                        <li><a href="${pageContext.request.contextPath}/project/status.do" has_check_key_word="true">项目概况</a></li>
                        <li><a href="${pageContext.request.contextPath}/project/list.do" has_check_key_word="true">所有项目</a></li>
                        <li><a href="${pageContext.request.contextPath}/project/preSave.do" has_check_key_word="true">新增项目</a></li>
                    </ul>
                </li>

                <li class="sub-menu dcjq-parent-li">
                    <a href="javascript:;" class="dcjq-parent" has_check_key_word="true">
                        <i class="fa fa-cogs"></i>
                        <span>版本</span>
                        <span class="dcjq-icon"></span>
                    </a>
                    <ul class="sub" style="display: none;">
                        <li><a href="${pageContext.request.contextPath}/version/list.do" has_check_key_word="true">所有版本</a></li>
                        <li><a href="${pageContext.request.contextPath}/version/preSave.do" has_check_key_word="true">新建版本</a></li>
                    </ul>
                </li>
                <li class="sub-menu dcjq-parent-li">
                    <a href="javascript:;" class="dcjq-parent" has_check_key_word="true">
                        <i class="fa fa-bug"></i>
                        <span>缺陷</span>
                        <span class="dcjq-icon"></span>
                    </a>
                    <ul class="sub" style="display: none;">
                        <li><a href="${pageContext.request.contextPath}/bug/list.do" has_check_key_word="true">所有缺陷</a></li>
                        <li><a href="${pageContext.request.contextPath}/bug/preSave.do" has_check_key_word="true">新建缺陷</a></li>
                    </ul>
                </li>
                <li class="sub-menu dcjq-parent-li">
                    <a href="${pageContext.request.contextPath}/sysActivity/list.do" class="dcjq-parent" has_check_key_word="true">
                        <i class="fa fa-eye-slash"></i>
                        <span>活动记录</span>
                        <span class="dcjq-icon"></span>
                    </a>
                    <%--<ul class="sub" style="display: none;">
                        <li><a href="http://www.alvarez.is/demo/gridgum/dashgum/form_component.html" has_check_key_word="true">Form Components</a></li>
                        <li><a href="http://www.alvarez.is/demo/gridgum/dashgum/advanced_form_components.html" has_check_key_word="true">Advanced Components</a></li>
                        <li><a href="http://www.alvarez.is/demo/gridgum/dashgum/form_validation.html" has_check_key_word="true">Form Validation</a></li>
                    </ul>--%>
                </li>

                <c:if test="${currentUser.isAdmin == true}">
                    <li class="sub-menu dcjq-parent-li">
                        <a href="javascript:;" class="dcjq-parent" has_check_key_word="true">
                            <i class="fa fa-bookmark"></i>
                            <span>系统</span>
                            <span class="dcjq-icon"></span>
                        </a>
                            <ul class="sub" style="display: none;">
                                <li><a href="${pageContext.request.contextPath}/user/list.do" has_check_key_word="true">用户管理</a></li>
                            </ul>
                    </li>
                </c:if>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <div>
                <c:if test="${subPage == null}">
                    <h1 style="color: red;margin-top: 20px;" align="center">欢迎登陆</h1>
                </c:if>
                <c:if test="${subPage != null}">
                    <jsp:include page="${subPage}"/>
                </c:if>
            </div>
        </section>
    </section>
</section>

<!-- template中多出来的js文件，到最后如果没出现什么问题则删除掉 start-->
<%--<script src="${pageContext.request.contextPath}/template/js/bootstrap.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/template/js/jquery.sparkline.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/template/js/jquery.gritter.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/template/js/gritter-conf.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/template/js/Chart.js"></script>--%>
<!-- template中多出来的js文件，到最后如果没出现什么问题则删除掉 end-->

<script class="include" type="text/javascript" src="${pageContext.request.contextPath}/template/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="${pageContext.request.contextPath}/template/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/template/js/jquery.scrollTo.min.js"></script>
<script src="${pageContext.request.contextPath}/template/js/common-scripts.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/Easy-Responsive-Tabs/js/easyResponsiveTabs.js"></script>

</body>
</html>
