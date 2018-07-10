<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Ranking</title>

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

    <spring:url value="/resources/Theme/assets/js/fancybox/jquery.fancybox.css" var="fancybox"/>
    <link href="${fancybox}" rel="stylesheet"/>

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
                    <a href="${ranking}" class="active"><i class="fa fa-bar-chart-o"></i><span>&nbsp;Ranking</span></a>
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
        <section class="wrapper site-min-height">

            <div class="row">
                <div class="col-lg-9 main-chart">


                    <div class="content-panel">
                        <table class="table table-striped table-advance table-hover">
                            <h4>Ranking</h4>

                            <c:url var="loadByCity" value="/getUsersByCriteria">
                                <c:param name="criteriaParam" value="${currentUser.city}"/>
                            </c:url>

                            <c:url var="loadWomen" value="/getUsersByCriteria">
                                <c:param name="criteriaParam" value="K"/>
                            </c:url>

                            <c:url var="loadMen" value="/getUsersByCriteria">
                                <c:param name="criteriaParam" value="M"/>
                            </c:url>

                            <c:url var="loadAllUser" value="/ranking"/>

                            <div class="showback">
                                <div class="btn-group btn-group-justified">
                                    <div class="btn-group">
                                        <a href="${loadByCity}" class="btn btn-primary btn-round">Twoje Miasto</a>
                                    </div>
                                    <div class="btn-group">
                                        <a href="${loadWomen}" class="btn btn-primary">Kobiety</a>
                                    </div>
                                    <div class="btn-group">
                                        <a href="${loadMen}" class="btn btn-primary">Mężczyźni</a>
                                    </div>
                                    <div class="btn-group">
                                        <a href="${loadAllUser}" class="btn btn-primary btn-round">Wszyscy</a>
                                    </div>
                                </div>
                            </div><!--/showback -->

                            <div class="col-lg-4 col-md-4 col-sm-4 mb col-sm-offset-4">

                                <div class="white-panel pn">
                                    <div class="white-header">
                                        <h4><b>Najlepszy użytkownik</b></h4>
                                    </div>

                                    <c:choose>
                                        <c:when test="${images[0] == null}">
                                            <p class="img-circle badge bg-info" width="80">Dodaj zdjęcie</p>
                                        </c:when>
                                        <c:otherwise>
                                            <div>
                                                <p><img src="data:image/jpeg;base64,${images[0]}" class="img-circle"
                                                        width="95"></p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <%--<p><img src="/resources/images/snake.jpg" class="img-circle" width="80"></p>--%>
                                    <p><b><h4>${userList[0].firstName} ${userList[0].lastName}</h4></b></p>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <p class="large mt">Poziom</p>
                                            <span class="badge">${userList[0].level.id}</span>
                                        </div>
                                        <div class="col-md-6">
                                            <p class="large mt">Doświadczenie</p>
                                            <span class="badge">${userList[0].experience}</span>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <thead>
                            <tr>
                                <th>#</th>
                                <th><i class="glyphicon glyphicon-camera"></i> Zdjęcie</th>
                                <th>Imię</th>
                                <th>Nazwisko</th>
                                <th>Miasto</th>
                                <th>Poziom</th>
                                <th>Punkty doświadczenia</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>


                            <c:forEach var="tempUserList" items="${userList}" varStatus="loop">
                                <c:if test="${not loop.first}">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${images[loop.index] == '-' }">
                                                -
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc">
                                                    <div class="project-wrapper">
                                                        <div class="project">
                                                            <div class="photo-wrapper">
                                                                <div class="photo">
                                                                    <a class="fancybox" href="data:image/jpeg;base64,${images[loop.index]}" style="height: 500px; widows:500px;"><img class="img-responsive img-circle" src="data:image/jpeg;base64,${images[loop.index]}" alt="" width="80" height="80"/></a>
                                                                </div>
                                                                <div class="overlay"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div><!-- col-lg-4 -->
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${tempUserList.firstName}</td>
                                    <td>${tempUserList.lastName}</td>
                                    <td>${tempUserList.city}</td>
                                    <td>${tempUserList.level.id}</td>
                                    <td>${tempUserList.experience}</td>
                                </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /content-panel -->

                </div>


                <!-- **********************************************************************************************************************************************************
                  RIGHT SIDEBAR CONTENT
                  *********************************************************************************************************************************************************** -->

                <div class="col-lg-3 ds">
                    <!--COMPLETED ACTIONS DONUTS CHART-->
                    <h3>POWIADOMIENIA</h3>

                    <c:forEach var="tempNotificationList" items="${notificationList}">

                        <div class="desc">
                            <div class="thumb">
                                <span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
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

<spring:url value="/resources/Theme/assets/js/fancybox/jquery.fancybox.js" var="fancybox_js"/>
<script src="${fancybox_js}"></script>



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
    $(function() {
        //    fancybox
        jQuery(".fancybox").fancybox();
    });

</script>

<script>
    //custom select box

    $(function(){
        $("select.styled").customSelect();
    });

</script>


</body>
</html>
