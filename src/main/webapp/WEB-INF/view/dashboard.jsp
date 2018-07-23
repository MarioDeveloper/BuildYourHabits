<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>

    <!-- Bootstrap core CSS -->
    <spring:url value="/resources/Theme/assets/css/bootstrap.css" var="bootstrap_core"/>
    <link href="${bootstrap_core}" rel="stylesheet"/>

    <!--external css-->
    <spring:url value="/resources/Theme/assets/font-awesome/css/font-awesome.css" var="font_awesome"/>
    <link href="${font_awesome}" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <spring:url value="/resources/Theme/assets/css/style.css" var="style_css"/>
    <link href="${style_css}" rel="stylesheet"/>

    <spring:url value="/resources/Theme/assets/css/style-responsive.css" var="style_responsive"/>
    <link href="${style_responsive}" rel="stylesheet"/>

    <spring:url value="/resources/Theme/assets/css/to-do.css" var="to_do"/>
    <link href="${to_do}" rel="stylesheet"/>

    <style>
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


        #header #main-content #footer #body

        @viewport {
            zoom: 1.0;
        }

    </style>
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
        <c:url value="/" var="dashboard"/>
        <a href="${dashboard}" class="logo" type="button"><b>BUDUJ SWOJE NAWYKI</b></a>

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
                    <c:url value="/" var="dashboard"/>
                    <a class="active" href="${dashboard}" type="button"><i
                            class="fa fa-dashboard"></i><span>&nbsp;Dashboard</span></a>
                </li>

                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-th"></i>
                        <span>Zaplanuj wydarzenie</span>
                    </a>
                    <ul class="sub">
                        <c:url value="/oneTimeEvent" var="oneTimeEvent"/>
                        <li><a href="${oneTimeEvent}" type="button"><b>Jednorazowe</b></a></li>
                        <c:url value="/reccuringEvent" var="reccuringEvent"/>
                        <li><a href="${reccuringEvent}" type="button"><b>Cykliczne</b></a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <c:url value="/ranking" var="ranking"/>
                    <a href="${ranking}" type="button"><i class="fa fa-bar-chart-o"></i><span>&nbsp;Ranking</span></a>
                </li>
                <li class="sub-menu">
                    <c:url value="/history" var="history"/>
                    <a href="${history}" type="button"><i class="fa fa-book"></i><span>&nbsp;Historia</span></a>
                </li>
                <li class="sub-menu">
                    <c:url value="/challengeEvent" var="challenge"/>
                    <a href="${challenge}" type="button"><i class="glyphicon glyphicon-fire" aria-hidden="true"></i><span>&nbsp;Rzuć wyzwanie</span></a>
                </li>
                <%--<hr width="220" align="left">--%>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-cogs"></i>
                        <span>Ustawienia konta</span>
                    </a>
                    <ul class="sub">
                        <c:url var="showFormForUpdateUserPersonalData" value="showFormForUpdateUserPersonalData"/>
                        <li><a href="<c:out value="${showFormForUpdateUserPersonalData}"/> " type="button"><b>Edytuj dane
                            osobowe</b></a></li>

                        <c:if test="${loggedUser.image != null}">
                            <c:url var="showFormForUpdateUserImage" value="showFormForUpdateUserImage"/>
                            <li><a href="<c:out value="${showFormForUpdateUserImage}"/>" type="button"><b>Edytuj zdjęcie</b></a></li>
                        </c:if>
                    </ul>
                </li>
                    <li class="sub-menu">
                        <c:url value="/logout" var="logout"/>
                        <a href="${logout}" type="button"><i class="fa fa-briefcase" aria-hidden="true"></i><span>&nbsp;Wyloguj się</span></a>
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
                                               onchange="this.form.submit()" type="button" />
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <img src="data:image/jpeg;base64,${userImage}" style="width: 100%; height: auto">
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
                                <div class="row" style="margin-left: 0;margin-right: 0;">
                                    <div class="col-md-3" style="padding: 0">
                                        <div class="alert alert-success text-center" style="width: 150px; color: white">

                                            <h5><b>${loggedUser.level.description}</b></h5></div>
                                    </div>
                                    <div class="col-md-4" style="top: 0px;">

                                        <span style="margin-left: 80%" class="badge bg-warning">Poleć aplikację znajomym</span>
                                        <div style="margin-left: 96%; margin-top: 5%" class="fb-share-button" data-href="https://budujswojenawyki.pl" data-layout="button" data-size="large" data-mobile-iframe="true"><a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fbudujswojenawyki.pl%2F&amp;src=sdkpreparse" class="fb-xfbml-parse-ignore">Udostępnij</a></div>

                                    </div>
                                </div>

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

                                                <a href="${performOneTimeEvent}" class="btn btn-success btn-xs" type="button"><i
                                                        class="fa fa-check"></i></a>
                                                <a href="${updateOneTimeEvent}" class="btn btn-primary btn-xs" type="button"><i
                                                        class="fa fa-pencil"></i></a>

                                                <a href="" class="btn btn-danger btn-xs triggerRemove" type="button"><i
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
                                                                   class="btn btn-danger" >Zadanie
                                                                    niewykonane</a>
                                                                <a href="${deleteOneTimeEven}"
                                                                   class="btn btn-primary" >Anuluj
                                                                    wydarzenie</a>
                                                                <button type="button" class="btn btn-default"
                                                                        data-dismiss="modal" type="button">Wyjdź
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

                                                <a href="${performRecurringEvent}" class="btn btn-success btn-xs" type="button"><i
                                                        class="fa fa-check"></i></a>

                                                <a href="${updateRecurringEvent}" class="btn btn-primary btn-xs" type="button"><i
                                                        class="fa fa-pencil"></i></a>

                                                <a href="" class="btn btn-danger btn-xs triggerRemove" type="button"><i
                                                        class="fa fa-trash-o "></i></a>

                                                <div class="modal fade" tabindex="-1" role="dialog"
                                                     aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-hidden="true" type="button">&times;
                                                                </button>
                                                                <h4 class="modal-title" id="customModal">Wybierz jedną
                                                                    z akcji</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a href="${failRecurringEvent}" type="button"
                                                                   class="btn btn-danger" >Zadanie niewykonane</a>

                                                                <a href="${skipRecurringEvent}" class="btn btn-primary" type="button">Pomiń
                                                                    wydarzenie</a>

                                                                <a href="${cancelOtherRecurringEvents}"
                                                                   class="btn btn-primary" type="button">Anuluj pozostałe
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



                    <!-- COMPLEX Challenge Events List -->

                    <div class="row">

                        <div class="col-md-12">

                            <div class="content-panel">
                                <table class="table table-striped table-advance table-hover">
                                    <h4>Aktualne wyzwania</h4>
                                    <thead>
                                    <tr>
                                        <th><i class=""></i> Tytuł</th>
                                        <th class=""><i class=""></i> Termin wykonania</th>
                                        <th><i class=""></i> Opis</th>
                                        <th><i class=""></i> Stopień trudności</th>
                                        <th><i class=""></i> Przeciwnik</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="tempChallengeEvent" items="${challengeEventList}">

                                        <c:url var="performChallengeEvent" value="/performChallengeEvent">
                                            <c:param name="eventId"
                                                     value="${tempChallengeEvent.id}"/>
                                        </c:url>

                                        <c:url var="acceptChallengeEvent" value="/acceptChallengeEvent">
                                            <c:param name="eventId"
                                                     value="${tempChallengeEvent.id}"/>
                                        </c:url>

                                        <c:url var="rejectChallengeEvent" value="/rejectChallengeEvent">
                                            <c:param name="eventId"
                                                     value="${tempChallengeEvent.id}"/>
                                        </c:url>

                                        <c:url var="failChallengeEvent" value="/failChallengeEvent">
                                            <c:param name="eventId"
                                                     value="${tempChallengeEvent.id}"/>
                                        </c:url>

                                        <tr>
                                            <td>${tempChallengeEvent.title}</td>

                                            <td>
                                                <fmt:formatDate value="${tempChallengeEvent.plannedDate}"
                                                                pattern="dd/MM/yy - HH:mm"/>
                                            </td>
                                            <td>${tempChallengeEvent.description}</td>
                                            <td>${tempChallengeEvent.difficultyLevel}</td>
                                            <td>${tempChallengeEvent.firstName} ${tempChallengeEvent.lastName}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${tempChallengeEvent.confirmed == true}">
                                                        <a href="${performChallengeEvent}" class="btn btn-success btn-xs" type="button"><i
                                                                class="fa fa-check"></i></a>
                                                        <a href="${failChallengeEvent}" class="btn btn-danger btn-xs" type="button"><i
                                                                class="fa fa-trash-o "></i></a>
                                                    </c:when>
                                                    <c:when test="${tempChallengeEvent.confirmed == null && tempChallengeEvent.userInitiatorId == loggedUser.id}">
                                                        <span class="badge bg-info">Oczekuje na zatwierdzenie</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="btn-group">
                                                            <a href="${acceptChallengeEvent}" class="btn btn-success" type="button">Akceptuje</a>
                                                        </div>
                                                        <div class="btn-group">
                                                            <a href="${rejectChallengeEvent}" class="btn btn-danger" type="button"'>Odrzucam</a>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>


                                                <div class="modal fade" tabindex="-1" role="dialog"
                                                     aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal"
                                                                        aria-hidden="true">&times;
                                                                </button>
                                                                <h4 class="modal-title" id="customModal2">Wybierz jedną
                                                                    z akcji</h4>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a href="${failRecurringEvent}" type="button"
                                                                   class="btn btn-primary"  >Zadanie niewykonane</a>

                                                                <a href="${skipRecurringEvent}" class="btn btn-primary" type="button">Pomiń
                                                                    wydarzenie</a>

                                                                <a href="${cancelOtherRecurringEvents}"
                                                                   class="btn btn-primary" type="button">Anuluj pozostałe
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
                                <span class="badge bg-inverse"><i class="fa fa-clock-o"></i></span>
                            </div>
                            <div class="details">
                                <p>
                                    <muted>${tempNotificationList.time} temu</muted>
                                    <br/>
                                    <a href="#" >${tempNotificationList.firstName} ${tempNotificationList.lastName}</a>
                                    wypełnił(a) zadanie i zdobył(a) ${tempNotificationList.experiencePoint} pkt doświadczenia.<br/>
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
<script src="${task_js}" type="text/javascript"></script>

<!-- js placed at the end of the document so the pages load faster -->
<spring:url value="/resources/Theme/assets/js/jquery.js" var="jquery"/>
<script src="${jquery}"></script>

<spring:url value="/resources/Theme/assets/js/bootstrap.min.js" var="bootstrap_min_js"/>
<script src="${bootstrap_min_js}"></script>

<spring:url value="/resources/Theme/assets/js/jquery.dcjqaccordion.2.7.js" var="dcjq"/>
<script src="${dcjq}" type="text/javascript" class="include"></script>

<spring:url value="/resources/Theme/assets/js/jquery.scrollTo.min.js" var="scrollTo_min_js"/>
<script src="${scrollTo_min_js}"></script>

<spring:url value="/resources/Theme/assets/js/jquery.sparkline.js" var="sparkline"/>
<script src="${sparkline}"></script>

<!--common script for all pages-->
<spring:url value="/resources/Theme/assets/js/common-scripts.js" var="common_scripts"/>
<script src="${common_scripts}"></script>

<div id="fb-root"></div>
<script>
    (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = 'https://connect.facebook.net/pl_PL/sdk.js#xfbml=1&version=v3.0&appId=440306113072008&autoLogAppEvents=1';
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


    $('.triggerRemove').click(function (e) {
        e.preventDefault();
        var modal = $(this).parent().find('.modal');
        modal.modal();
    });


</script>




</body>
</html>
