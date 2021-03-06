<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Historia</title>

    <%--DATE TIME PICKER RESOURCES--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <%-- Datetimepicker language--%>
    <spring:url value="/resources/Theme/assets/js/moment.js" var="momento"/>
    <script src="${momento}"></script>
    <spring:url value="/resources/Theme/assets/js/pl.js" var="pl_js"/>
    <script src="${pl_js}"></script>

    <%--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>--%>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker-standalone.css">
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/js/bootstrap-datetimepicker.min.js"></script>

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

    <%--<spring:url value="/resources/MDB Free/css/mdb.min.css" var="mdb_min" />--%>
    <%--<link href="${mdb_min}" rel="stylesheet" />--%>

    <spring:url value="/resources/Theme/assets/css/to-do.css" var="to_do"/>
    <link href="${to_do}" rel="stylesheet"/>

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
                    <a href="${dashboard}"><i class="fa fa-dashboard"></i><span>&nbsp;Dashboard</span></a>
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
                    <a href="${history}" class="active"><i class="fa fa-book"></i><span>&nbsp;Historia</span></a>
                </li>
                <li class="sub-menu">
                    <c:url value="/rewards" var="rewards"/>
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
                        <c:url var="showFormForUpdateUserPersonalData" value="showFormForUpdateUserPersonalData"/>
                        <li><a href="<c:out value="${showFormForUpdateUserPersonalData}"/>"><b>Edytuj dane
                            osobowe</b></a></li>
                    </ul>
                </li>
                    <li class="sub-menu">
                        <c:url value="/logout" var="logout"/>
                        <a href="${logout}"><i class="fa fa-briefcase"
                                               aria-hidden="true"></i><span>&nbsp;Wyloguj się</span></a>
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
                <div class="col-lg-12 main-chart">

                    <div class="content-panel">
                        <table class="table table-striped table-advance table-hover">
                            <div class="alert alert-info"><h4><b>Historia wydarzeń jednorazowych</b></h4></div>
                            <thead>
                            <tr>
                                <th>
                                    <div style="text-align: center"><h4><b> Tytuł </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Zaplanowany termin </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Termin realizacji </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Opis </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Stopień trudności </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Punkty życia </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Zdobyte doświadczenie </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Status </b></h4></div>
                                </th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="tempOneTimeEventList" items="${oneTimeEventList}" varStatus="loop">

                                <tr>
                                    <td><p style="text-align:center"> ${tempOneTimeEventList.title} </p></td>
                                    <td>
                                        <p style="text-align:center">
                                            <fmt:formatDate value="${tempOneTimeEventList.plannedDate}"
                                                            pattern="dd/MM/yy - HH:mm"/>
                                        </p>
                                    </td>
                                    <td>
                                        <p style="text-align:center">
                                            <fmt:formatDate value="${tempOneTimeEventList.realizationDate}"
                                                            pattern="dd/MM/yy - HH:mm"/>
                                        </p>
                                    </td>
                                    <td><p style="text-align:center"> ${tempOneTimeEventList.description} </p></td>
                                    <td><p style="text-align:center"> ${tempOneTimeEventList.difficultyLevel} </p></td>

                                    <c:choose>
                                        <c:when test="${tempOneTimeEventList.done =='true'}">
                                            <td><p style="text-align:center"> - </p></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><p style="text-align:center"><span
                                                    class="badge bg-important">${tempOneTimeEventList.life} </span></p>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${tempOneTimeEventList.done =='true'}">
                                            <td><p style="text-align:center"><span
                                                    class="badge bg-info">+${tempOneTimeEventList.experience}</span></p>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><p style="text-align:center"> - </p></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${tempOneTimeEventList.done =='true'}">
                                            <td><span class="label label-success label-mini">Wykonane</span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="label label-danger">Niewykonane</span></td>
                                        </c:otherwise>
                                    </c:choose>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /content-panel -->


                </div>

                <div class="col-lg-12 main-chart">

                    <div class="content-panel">
                        <table class="table table-striped table-advance table-hover">
                            <div class="alert alert-info"><h4><b>Historia wydarzeń cyklicznych</b></h4></div>
                            <thead>
                            <tr>
                                <th>
                                    <div style="text-align: center"><h4><b> Tytuł </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Zaplanowany termin </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Termin realizacji </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Opis </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Stopień trudności </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Punkty życia </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Zdobyte doświadczenie </b></h4></div>
                                </th>
                                <th>
                                    <div style="text-align: center"><h4><b> Status </b></h4></div>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="tempRealizationRecurringEventList" items="${realizationRecurringEventList}"
                                       varStatus="loop">

                                <tr>
                                    <td>
                                        <p style="text-align:center"> ${tempRealizationRecurringEventList.recurringEvent.title} </p>
                                    </td>
                                    <td>
                                        <p style="text-align:center">
                                            <fmt:formatDate value="${tempRealizationRecurringEventList.plannedDate}"
                                                            pattern="dd/MM/yy - HH:mm"/>
                                        </p>
                                    </td>
                                    <td>
                                        <p style="text-align:center">
                                            <fmt:formatDate value="${tempRealizationRecurringEventList.realizationDate}"
                                                            pattern="dd/MM/yy - HH:mm"/>
                                        </p>
                                    </td>
                                    <td>
                                        <p style="text-align:center"> ${tempRealizationRecurringEventList.recurringEvent.description} </p>
                                    </td>
                                    <td>
                                        <p style="text-align:center"> ${tempRealizationRecurringEventList.recurringEvent.difficultyLevel} </p>
                                    </td>

                                    <c:choose>
                                        <c:when test="${tempRealizationRecurringEventList.done =='true'}">
                                            <td><p style="text-align:center"> - </p></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><p style="text-align:center"><span
                                                    class="badge bg-important">${tempRealizationRecurringEventList.life}
                                            </p></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${tempRealizationRecurringEventList.done =='true'}">
                                            <td><p style="text-align:center"><span
                                                    class="badge bg-info">+${tempRealizationRecurringEventList.experience}</span>
                                            </p></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><p style="text-align:center"> - </p></td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${tempRealizationRecurringEventList.done =='true'}">
                                            <td><span class="label label-success label-mini">Wykonane</span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="label label-danger">Niewykonane</span></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /content-panel -->
                </div>
            </div>
        </section>
    </section>
</section>

<!--script for this page-->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<spring:url value="/resources/Theme/assets/js/tasks.js" var="task_js"/>
<script src="${task_js}" type="text/javascript"/>

<!-- js placed at the end of the document so the pages load faster -->
<spring:url value="/resources/Theme/assets/js/jquery.js" var="jquery"/>
<script src="${jquery}"></script>

<%--<spring:url value="/resources/Theme/assets/js/jquery-1.8.3.min.js" var="jquery_min_js" />--%>
<%--<script src="${jquery_min_js}"></script>--%>

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
