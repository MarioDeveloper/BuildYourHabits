<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
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

    <%--<spring:url value="/resources/MDB Free/css/mdb.min.css" var="mdb_min"/>--%>
    <%--<link href="${mdb_min}" rel="stylesheet"/>--%>

    <spring:url value="/resources/Theme/assets/css/to-do.css" var="to_do"/>
    <link href="${to_do}" rel="stylesheet"/>


    <%--<spring:url value="/resources/assets/bootstrap/css/bootstrap.min.css" var="min_css" />--%>
    <%--<link href="${min_css}" rel="stylesheet" />--%>



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

        .btn-file {
            position: relative;
            overflow: hidden;
        }

        .btn-file input[type=file] {
            position: absolute;
            top: 0;
            right: 0;
            min-width: 100%;
            min-height: 100%;
            font-size: 100px;
            text-align: right;
            filter: alpha(opacity=0);
            opacity: 0;
            outline: none;
            background: white;
            cursor: inherit;
            display: block;
        }

        .upload-btn-wrapper input[type=file] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
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
        <%--<div class="top-menu">--%>
        <%--<ul class="nav pull-right top-menu">--%>
        <%--<li><a class="logout">Logout</a></li>--%>
        <%--</ul>--%>
        <%--</div>--%>
    </header>
    <!--header end-->

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">

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

                        <c:if test="${loggedUser.image != null}">
                            <c:url var="showFormForUpdateUserImage" value="showFormForUpdateUserImage"/>
                            <li><a href="<c:out value="${showFormForUpdateUserImage}"/>"><b>Edytuj zdjęcie</b></a></li>
                        </c:if>
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
                <div class="col-lg-9 main-chart">

                    <div class="row mt">

                        <div class="col-md-4 col-sm-4 mb">
                            <c:choose>
                                <c:when test="${loggedUser.image == null}">
                                    <form method="POST" action="${pageContext.request.contextPath}/upload"
                                          enctype="multipart/form-data">
                                        <div class="col-sm-offset-3">
                                            <label class="badge bg-info" style="text-align: center"><h5>Dodaj swoje
                                                zdjęcie</h5></label>
                                        </div>
                                        <input type="file" name="file" class="form-control"
                                               onchange="this.form.submit()">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <img src="data:image/jpeg;base64,${userImage}" width="300" height="290">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div><!-- /col-md-4-->

                        <div class="col-md-8 col-sm-4  mb">
                            <div class="showback">
                                <h4> Punkty życia</h4>
                                <div class="progress progress-striped active ">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="1"
                                         aria-valuemin="0" aria-valuemax="100"
                                         style="width: ${loggedUser.life}%">${loggedUser.life}
                                        <span class="sr-only">100% Complete</span>
                                    </div>
                                </div>


                                <h4> Punkty doświadczenia</h4>
                                <div class="progress progress-striped active ">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="1" aria-valuemin="0"
                                         aria-valuemax="100"
                                         style="width: ${loggedUser.experience}%">${loggedUser.experience}
                                        <span class="sr-only">45% Complete</span>
                                    </div>
                                </div>

                                <h4> Aktualny poziom: <b>${loggedUser.level.id}</b></h4>
                                <div class="alert alert-success text-center" style="width: 150px;">

                                    <h5><b>${loggedUser.level.description}</b></h5></div>


                            </div><!-- /showback -->

                        </div><!-- /col-md-4 -->
                    </div><!-- /row -->


                    <!-- COMPLEX TO DO LIST -->

                    <div class="row">

                        <div class="col-md-12">

                            <div class="content-panel">
                                <table class="table table-striped table-advance table-hover">
                                    <h4>Wydarzenia jednorazowe</h4>
                                    <thead>
                                    <tr>
                                        <th><i class=""></i> Tytuł</th>
                                        <th class=""><i class=""></i> Termin wykonania
                                        </th>
                                        <th><i class=""></i> Opis</th>
                                        <th><i class=" "></i> Stopień trudności</th>
                                        <%--<th></th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="tempOneTimeEventList" items="${oneTimeEventList}">

                                        <c:url var="performOneTimeEvent" value="/performOneTimeEvent">
                                            <c:param name="eventId" value="${tempOneTimeEventList.id}"/>
                                        </c:url>

                                        <c:url var="updateOneTimeEvent" value="/showFormForUpdateOneTimeEvent">
                                            <c:param name="eventId" value="${tempOneTimeEventList.id}"/>
                                        </c:url>

                                        <c:url var="deleteOneTimeEven" value="/deleteOneTimeEvent">
                                            <c:param name="eventId" value="${tempOneTimeEventList.id}"/>
                                        </c:url>

                                        <c:url var="failOneTimeEvent" value="/failOneTimeEvent">
                                            <c:param name="eventId" value="${tempOneTimeEventList.id}"/>
                                        </c:url>

                                        <tr>
                                            <td>${tempOneTimeEventList.title}</td>
                                            <td>
                                                <fmt:formatDate value="${tempOneTimeEventList.plannedDate}"
                                                                pattern="dd/MM/yy - HH:mm"/>
                                            </td>
                                            <td>${tempOneTimeEventList.description}</td>
                                            <td>${tempOneTimeEventList.difficultyLevel}</td>
                                            <td>

                                                <a href="${performOneTimeEvent}" class="btn btn-success btn-xs"><i
                                                        class="fa fa-check"></i></a>
                                                <a href="${updateOneTimeEvent}" class="btn btn-primary btn-xs"><i
                                                        class="fa fa-pencil"></i></a>

                                                <a href="" class="btn btn-danger btn-xs triggerRemove"><i
                                                        class="fa fa-trash-o "></i></a>

                                                <div class="modal fade" tabindex="-1" role="dialog"
                                                     aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-hidden="true">&times;
                                                                </button>
                                                                <h4 class="modal-title" id="myModalLabel">Wybierz jedną
                                                                    z akcji</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a href="${failOneTimeEvent}" type="button"
                                                                   class="btn btn-primary">Zadanie
                                                                    niewykonane</a>
                                                                <a href="${deleteOneTimeEven}"
                                                                   class="btn btn-primary">Anuluj
                                                                    wydarzenie</a>
                                                                <button type="button" class="btn btn-default"
                                                                        data-dismiss="modal">Wyjdź
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div><!-- /content-panel -->


                        </div>


                    </div><!-- /row -->

                    <!-- COMPLEX Recurring Events List -->

                    <div class="row">

                        <div class="col-md-12">

                            <div class="content-panel">
                                <table class="table table-striped table-advance table-hover">
                                    <h4>Wydarzenia cykliczne</h4>
                                    <thead>
                                    <tr>
                                        <th><i class=""></i> Tytuł</th>
                                        <th class=""><i class=""></i> Zaplanowany
                                            termin
                                        </th>
                                        <th class=""><i class=""></i> Ostatni termin
                                        </th>
                                        <th><i class=""></i> Powtarzane</th>
                                        <th><i class=""></i> Opis</th>
                                        <th><i class=""></i> Stopień trudności</th>
                                        <%--<th></th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="tempRealizationRecurringEvent"
                                               items="${realizationRecurringEventList}">

                                        <c:url var="performRecurringEvent" value="/performRecurringEvent">
                                            <c:param name="eventId"
                                                     value="${tempRealizationRecurringEvent.recurringEvent.id}"/>
                                        </c:url>

                                        <c:url var="updateRecurringEvent" value="/showFormForUpdateRecurringEvent">
                                            <c:param name="eventId"
                                                     value="${tempRealizationRecurringEvent.recurringEvent.id}"/>
                                        </c:url>

                                        <c:url var="failRecurringEvent" value="/failRecurringEvent">
                                            <c:param name="eventId"
                                                     value="${tempRealizationRecurringEvent.recurringEvent.id}"/>
                                        </c:url>

                                        <c:url var="skipRecurringEvent" value="/skipRecurringEvent">
                                            <c:param name="eventId"
                                                     value="${tempRealizationRecurringEvent.recurringEvent.id}"/>
                                        </c:url>

                                        <c:url var="cancelOtherRecurringEvents" value="/cancelOtherRecurringEvents">
                                            <c:param name="eventId"
                                                     value="${tempRealizationRecurringEvent.recurringEvent.id}"/>
                                        </c:url>


                                        <tr>
                                            <td>${tempRealizationRecurringEvent.recurringEvent.title}</td>

                                            <td>
                                                <fmt:formatDate value="${tempRealizationRecurringEvent.plannedDate}"
                                                                pattern="dd/MM/yy - HH:mm"/>
                                            </td>
                                            <td>
                                                <fmt:formatDate
                                                        value="${tempRealizationRecurringEvent.recurringEvent.finishDate}"
                                                        pattern="dd/MM/yy - HH:mm"/>
                                            </td>
                                            <td>
                                                Co ${tempRealizationRecurringEvent.recurringEvent.frequency} ${tempRealizationRecurringEvent.recurringEvent.frequencyUnit}
                                            </td>
                                            <td>${tempRealizationRecurringEvent.recurringEvent.description}</td>
                                            <td>${tempRealizationRecurringEvent.recurringEvent.difficultyLevel}</td>
                                            <td>

                                                <a href="${performRecurringEvent}" class="btn btn-success btn-xs"><i
                                                        class="fa fa-check"></i></a>

                                                <a href="${updateRecurringEvent}" class="btn btn-primary btn-xs"><i
                                                        class="fa fa-pencil"></i></a>

                                                <a href="" class="btn btn-danger btn-xs triggerRemove"><i
                                                        class="fa fa-trash-o "></i></a>

                                                <div class="modal fade" tabindex="-1" role="dialog"
                                                     aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-hidden="true">&times;
                                                                </button>
                                                                <h4 class="modal-title" id="customModal">Wybierz jedną
                                                                    z akcji</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a href="${failRecurringEvent}" type="button"
                                                                   class="btn btn-primary">Zadanie niewykonane</a>

                                                                <a href="${skipRecurringEvent}" class="btn btn-primary">Pomiń
                                                                    wydarzenie</a>

                                                                <a href="${cancelOtherRecurringEvents}"
                                                                   class="btn btn-primary">Anuluj pozostałe
                                                                    wydarzenia</a>

                                                                <button type="button" class="btn btn-default"
                                                                        data-dismiss="modal">Wyjdź
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div><!-- /content-panel -->


                        </div>


                    </div><!-- /row -->

                </div><!-- /col-lg-9 END SECTION MIDDLE -->


                <!-- **********************************************************************************************************************************************************
                RIGHT SIDEBAR CONTENT
                *********************************************************************************************************************************************************** -->

                <div class="col-lg-3 ds">
                    <!--COMPLETED ACTIONS DONUTS CHART-->
                    <h3>POWIADOMIENIA</h3>

                    <c:forEach var="tempNotificationList" items="${notificationList}">

                        <div class="desc">
                            <div class="thumb">
                                <span class="badge bg-warning"><i class="fa fa-clock-o"></i></span>
                            </div>
                            <div class="details">
                                <p>
                                    <muted>${tempNotificationList.time} temu</muted>
                                    <br/>
                                    <a href="#">${tempNotificationList.firstName} ${tempNotificationList.lastName}</a>
                                    wypełnił zadanie i zdobył ${tempNotificationList.experiencePoint} pkt doświadczenia.<br/>
                                </p>
                            </div>
                        </div>
                    </c:forEach>

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

<script type="text/javascript">

    $('.triggerRemove').click(function (e) {
        e.preventDefault();
        var modal = $(this).parent().find('.modal');
        modal.modal(); // to otwiera modal
    });


</script>


</body>
</html>
