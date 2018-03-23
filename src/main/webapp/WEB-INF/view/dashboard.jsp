<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Habitica</title>

    <%-- Progress bar--%>
    <%--<spring:url value="/resources/LoadingBar/loading-bar.css" var="bar_css" />--%>
    <%--<link href="${bar_css}" rel="stylesheet" type="text/css" />--%>

    <!-- Bootstrap core CSS -->
    <spring:url value="/resources/Theme/assets/css/bootstrap.css" var="bootstrap_core"/>
    <link href="${bootstrap_core}" rel="stylesheet"/>

    <!--external css-->
    <spring:url value="/resources/Theme/assets/font-awesome/css/font-awesome.css" var="font_awesome"/>
    <link href="${font_awesome}" rel="stylesheet"/>

    <spring:url value="/resources/Theme/assets/css/zabuto_calendar.css" var="zabuto"/>
    <link href="${zabuto}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/Theme/assets/js/gritter/css/jquery.gritter.css" var="gritter"/>
    <link href="${gritter}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/Theme/assets/lineicons/style.css" var="style"/>
    <link href="${style}" rel="stylesheet" type="text/css"/>

    <!-- Custom styles for this template -->
    <spring:url value="/resources/Theme/assets/css/style.css" var="style_css"/>
    <link href="${style_css}" rel="stylesheet"/>

    <spring:url value="/resources/Theme/assets/css/style-responsive.css" var="style_responsive"/>
    <link href="${style_responsive}" rel="stylesheet"/>

    <spring:url value="/resources/MDB Free/css/mdb.min.css" var="mdb_min"/>
    <link href="${mdb_min}" rel="stylesheet"/>

    <spring:url value="/resources/Theme/assets/css/to-do.css" var="to_do"/>
    <link href="${to_do}" rel="stylesheet"/>


    <%--<spring:url value="/resources/assets/bootstrap/css/bootstrap.min.css" var="min_css" />--%>
    <%--<link href="${min_css}" rel="stylesheet" />--%>


    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>

    <style>
        .polaroid {
            width: 250px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            text-align: center;
        }

        .data-image {
            margin: 30px 0px;
        }

        .padding {
            margin: 10px 0px;
        }

        .myFooter {
            position: relative;
            right: 0;
            bottom: 0;
            left: 10.95%;
            width: 89.05%;
        }

    </style>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<section id="container">
    <!-- **********************************************************************************************************************************************************
    TOP BAR CONTENT & NOTIFICATIONS
    *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
        <!--logo start-->
        <c:url value="/dashboard" var="dashboard"/>
        <a href="${dashboard}" class="logo"><b>CODZIENNE NAWYKI</b></a>
        <!--logo end-->
        <div class="top-menu">
            <ul class="nav pull-right top-menu">
                <li><a class="logout">Logout</a></li>
            </ul>
        </div>
    </header>
    <!--header end-->

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">

                <%--MIEJSCE NA LOGO HABITICA --%>
                <%--<p class="centered"><a href="profile.html"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>--%>
                <%--<h5 class="centered">Marcel Newman</h5>--%>

                <li class="mt">
                    <c:url value="/dashboard" var="dashboard"/>
                    <a class="active" href="${dashboard}"><i
                            class="fa fa-dashboard"></i><span>&nbsp;Dashboard</span></a>
                </li>

                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-th"></i>
                        <span>Zaplanuj wydarzenie</span>
                    </a>
                    <ul class="sub">
                        <c:url value="/oneTimeEvent" var="oneTimeEvent"/>
                        <li><a href="${oneTimeEvent}"><b>Jednorazowe</b></a></li>
                        <c:url value="/reccuringEvent" var="reccuringEvent"/>
                        <li><a href="${reccuringEvent}"><b>Cykliczne</b></a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <c:url value="/ranking" var="ranking"/>
                    <a href="${ranking}"><i class="fa fa-bar-chart-o"></i><span>&nbsp;Ranking</span></a>
                </li>
                <li class="sub-menu">
                    <c:url value="/history" var="history"/>
                    <a href="${history}"><i class="fa fa-book"></i><span>&nbsp;Historia</span></a>
                </li>
                <li class="sub-menu">
                    <c:url value="/awards" var="rewards"/>
                    <a href="${rewards}"><i class="fa fa-briefcase"
                                            aria-hidden="true"></i><span>&nbsp;Nagrody</span></a>
                </li>
                <%--<hr width="220" align="left">--%>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-cogs"></i>
                        <span>Ustawienia konta</span>
                    </a>
                    <ul class="sub">
                        <c:url value="/addPhoto" var="addPhoto"/>
                        <li><a href="${addPhoto}"><b>Dodaj zdjęcie</b></a></li>
                        <c:url value="/changePassword" var="changePassword"/>
                        <li><a href="${changePassword}"><b>Zmień hasło</b></a></li>
                    </ul>
                </li>
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

            <div class="row">
                <div class="col-lg-9 main-chart">

                    <div class="row mt">

                        <div class="col-md-4 col-sm-4 mb">

                            <div class="view overlay polaroid ">
                                <img src="/resources/images/snake.jpg " class="img-fluid " alt="6">
                                <a>
                                    <div class="mask waves-effect waves-light rgba-white-slight"></div>
                                </a>
                            </div>

                        </div><!-- /col-md-4-->


                        <div class="col-md-8 col-sm-4  mb">
                            <div class="showback">
                                <h4> Punkty życia</h4>
                                <div class="progress progress-striped active ">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="1"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 85%">85
                                        <span class="sr-only">45% Complete</span>
                                    </div>
                                </div>


                                <h4> Punkty doświadczenia</h4>
                                <div class="progress progress-striped active ">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="1" aria-valuemin="0"
                                         aria-valuemax="100" style="width: 100%">100
                                        <span class="sr-only">45% Complete</span>
                                    </div>
                                </div>

                                <h4> Aktualny poziom: <b>2</b></h4>
                                <div class="alert alert-success text-center" style="width: 150px;">

                                    <h5><b>AMATOR</b></h5></div>


                            </div><!-- /showback -->

                        </div><!-- /col-md-4 -->
                    </div><!-- /row -->


                    <!-- COMPLEX TO DO LIST -->
                    <div class="row mt">
                        <div class="col-md-12">
                            <section class="task-panel tasks-widget">
                                <div class="panel-heading">
                                    <div class="pull-left"><h5><i class="fa fa-tasks"></i> Lista wydarzeń jednorazowych
                                    </h5></div>
                                    <br>
                                </div>
                                <div class="panel-body">
                                    <div class="task-content">

                                        <ul class="task-list">
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Dashgum - Admin Panel Theme</span>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Extensive collection of plugins</span>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Free updates always, no extra fees.</span>
                                                    <%--<span class="badge bg-success">2 Days</span>--%>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">More features coming soon</span>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Hey, seriously, you should buy this Dashboard</span>
                                                    <div class="pull-right">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </section>
                        </div><!-- /col-md-12-->
                    </div><!-- /row -->

                    <!-- COMPLEX TO DO LIST -->
                    <div class="row mt">
                        <div class="col-md-12">
                            <section class="task-panel tasks-widget">
                                <div class="panel-heading">
                                    <div class="pull-left"><h5><i class="fa fa-tasks"></i> Lista wydarzeń cyklicznych
                                    </h5></div>
                                    <br>
                                </div>
                                <div class="panel-body">
                                    <div class="task-content">

                                        <ul class="task-list">
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Dashgum - Admin Panel Theme</span>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Extensive collection of plugins</span>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Free updates always, no extra fees.</span>
                                                    <%--<span class="badge bg-success">2 Days</span>--%>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">More features coming soon</span>
                                                    <div class="pull-right hidden-phone">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="task-title">
                                                    <span class="task-title-sp">Hey, seriously, you should buy this Dashboard</span>
                                                    <div class="pull-right">
                                                        <button class="btn btn-success btn-xs"><i
                                                                class=" fa fa-check"></i></button>
                                                        <button class="btn btn-primary btn-xs"><i
                                                                class="fa fa-pencil"></i></button>
                                                        <button class="btn btn-danger btn-xs"><i
                                                                class="fa fa-trash-o "></i></button>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </section>
                        </div><!-- /col-md-12-->
                    </div><!-- /row -->

                </div><!-- /col-lg-9 END SECTION MIDDLE -->


                <!-- **********************************************************************************************************************************************************
                RIGHT SIDEBAR CONTENT
                *********************************************************************************************************************************************************** -->

                <div class="col-lg-3 ds">
                    <!--COMPLETED ACTIONS DONUTS CHART-->
                    <h3>NOTIFICATIONS</h3>

                    <!-- First Action -->
                    <div class="desc">
                        <div class="thumb">
                            <span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
                        </div>
                        <div class="details">
                            <p>
                                <muted>2 Minutes Ago</muted>
                                <br/>
                                <a href="#">James Brown</a> subscribed to your newsletter.<br/>
                            </p>
                        </div>
                    </div>
                    <!-- Second Action -->
                    <div class="desc">
                        <div class="thumb">
                            <span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
                        </div>
                        <div class="details">
                            <p>
                                <muted>3 Hours Ago</muted>
                                <br/>
                                <a href="#">Diana Kennedy</a> purchased a year subscription.<br/>
                            </p>
                        </div>
                    </div>
                    <!-- Third Action -->
                    <div class="desc">
                        <div class="thumb">
                            <span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
                        </div>
                        <div class="details">
                            <p>
                                <muted>7 Hours Ago</muted>
                                <br/>
                                <a href="#">Brandon Page</a> purchased a year subscription.<br/>
                            </p>
                        </div>
                    </div>
                    <!-- Fourth Action -->
                    <div class="desc">
                        <div class="thumb">
                            <span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
                        </div>
                        <div class="details">
                            <p>
                                <muted>11 Hours Ago</muted>
                                <br/>
                                <a href="#">Mark Twain</a> commented your post.<br/>
                            </p>
                        </div>
                    </div>
                    <!-- Fifth Action -->
                    <div class="desc">
                        <div class="thumb">
                            <span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
                        </div>
                        <div class="details">
                            <p>
                                <muted>18 Hours Ago</muted>
                                <br/>
                                <a href="#">Daniel Pratt</a> purchased a wallet in your store.<br/>
                            </p>
                        </div>
                    </div>
                </div><!-- /col-lg-3 -->
            </div>
            <! --/row -->
        </section>
    </section>

    <!--main content end-->
    <!--footer start-->
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center">
            2018 - MarioDeveloper
        </div>
    </footer>


    <!--footer end-->
</section>

<!--script for this page-->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<spring:url value="/resources/Theme/assets/js/tasks.js" var="task_js"/>
<script src="${task_js}" type="text/javascript"/>

<!-- js placed at the end of the document so the pages load faster -->
<spring:url value="/resources/Theme/assets/js/jquery.js" var="jquery"/>
<script src="${jquery}"></script>

<spring:url value="/resources/Theme/assets/js/jquery-1.8.3.min.js" var="jquery_min_js"/>
<script src="${jquery_min_js}"></script>

<spring:url value="/resources/Theme/assets/js/bootstrap.min.js" var="bootstrap_min_js"/>
<script src="${bootstrap_min_js}"></script>

<spring:url value="/resources/Theme/assets/js/jquery.dcjqaccordion.2.7.js" var="dcjq"/>
<script src="${dcjq}" type="text/javascript" class="include"></script>

<spring:url value="/resources/Theme/assets/js/jquery.scrollTo.min.js" var="scrollTo_min_js"/>
<script src="${scrollTo_min_js}"></script>

<spring:url value="/resources/Theme/assets/js/jquery.nicescroll.js" var="nicescroll"/>
<script src="${nicescroll}" type="text/javascript"></script>

<spring:url value="/resources/Theme/assets/js/jquery.sparkline.js" var="sparkline"/>
<script src="${sparkline}"></script>

<!--common script for all pages-->
<spring:url value="/resources/Theme/assets/js/common-scripts.js" var="common_scripts"/>
<script src="${common_scripts}"></script>

<spring:url value="/resources/Theme/assets/js/gritter/js/jquery.gritter.js" var="jquery_gritter"/>
<script src="${jquery_gritter}" type="text/javascript"></script>

<spring:url value="/resources/Theme/assets/js/gritter-conf.js" var="gritter_conf"/>
<script src="${gritter_conf}" type="text/javascript"></script>

<!--script for this page-->
<spring:url value="/resources/Theme/assets/js/sparkline-chart.js" var="sparkline_js"/>
<script src="${sparkline_js}"></script>

<spring:url value="/resources/Theme/assets/js/zabuto_calendar.js" var="zabuto_js"/>
<script src="${zabuto_js}" type="text/javascript"></script>

<spring:url value="/resources/LoadingBar/loading-bar.js" var="loading_bar"/>
<script src="${loading_bar}" type="text/javascript"></script>

<spring:url value="/resources/Theme/assets/js/chart-master/Chart.js" var="chart"/>
<script src="${chart}"></script>


<%--<script type="text/javascript">--%>
<%--$(document).ready(function () {--%>
<%--var unique_id = $.gritter.add({--%>
<%--// (string | mandatory) the heading of the notification--%>
<%--title: 'Welcome to Dashgum!',--%>
<%--// (string | mandatory) the text inside the notification--%>
<%--text: 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo. Free version for <a href="http://blacktie.co" target="_blank" style="color:#ffd777">BlackTie.co</a>.',--%>
<%--// (string | optional) the image to display on the left--%>
<%--image: 'assets/img/ui-sam.jpg',--%>
<%--// (bool | optional) if you want it to fade out on its own or just sit there--%>
<%--sticky: true,--%>
<%--// (int | optional) the time you want it to be alive for before fading out--%>
<%--time: '',--%>
<%--// (string | optional) the class name you want to apply to that specific message--%>
<%--class_name: 'my-sticky-class'--%>
<%--});--%>

<%--return false;--%>
<%--});--%>
<%--</script>--%>

<script type="application/javascript">
    $(document).ready(function () {
        $("#date-popover").popover({html: true, trigger: "manual"});
        $("#date-popover").hide();
        $("#date-popover").click(function (e) {
            $(this).hide();
        });

        $("#my-calendar").zabuto_calendar({
            action: function () {
                return myDateFunction(this.id, false);
            },
            action_nav: function () {
                return myNavFunction(this.id);
            },
            ajax: {
                url: "show_data.php?action=1",
                modal: true
            },
            legend: [
                {type: "text", label: "Special event", badge: "00"},
                {type: "block", label: "Regular event",}
            ]
        });
    });


    function myNavFunction(id) {
        $("#date-popover").hide();
        var nav = $("#" + id).data("navigation");
        var to = $("#" + id).data("to");
        console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }
</script>


</body>
</html>
