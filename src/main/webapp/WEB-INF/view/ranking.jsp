<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Ranking</title>

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

    <spring:url value="/resources/Theme/assets/js/fancybox/jquery.fancybox.css" var="fancybox"/>
    <link href="${fancybox}" rel="stylesheet"/>
</head>

<body>

<section id="container">
    <!-- **********************************************************************************************************************************************************
    TOP BAR CONTENT & NOTIFICATIONS
    *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
        <!--logo start-->
        <c:url value="/" var="dashboard"/>
        <a href="${dashboard}" class="logo"><b>CODZIENNE NAWYKI</b></a>
        <!--logo end-->
    </header>
    <!--header end-->

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <header class="header black-bg">
        <div class="sidebar-toggle-box">
            <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
        </div>
        <!--logo start-->
        <c:url value="/" var="dashboard"/>
        <a href="${dashboard}" class="logo"><b>BUDUJ SWOJE NAWYKI</b></a>

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
                    <a href="${dashboard}"><i
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
                <li class="sub-menu" class="active">
                    <c:url value="/ranking" var="ranking"/>
                    <a href="${ranking}"><i class="fa fa-bar-chart-o"></i><span>&nbsp;Ranking</span></a>
                </li>
                <li class="sub-menu">
                    <c:url value="/history" var="history"/>
                    <a href="${history}"><i class="fa fa-book"></i><span>&nbsp;Historia</span></a>
                </li>
                <li class="sub-menu">
                    <c:url value="/challengeEvent" var="challenge"/>
                    <a href="${challenge}"><i class="glyphicon glyphicon-fire"
                                              aria-hidden="true"></i><span>&nbsp;Rzuć wyzwanie</span></a>
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
                    <a href="${logout}"><i class="fa fa-briefcase" aria-hidden="true"></i><span>&nbsp;Wyloguj się</span></a>
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
                                        <c:when test="${images[0] == null or images[0] == '-'}">
                                            <p class="img-circle badge bg-info" width="80" >Dodaj zdjęcie</p>
                                        </c:when>
                                        <c:otherwise>
                                            <div>
                                                <p><img src="data:image/jpeg;base64,${images[0]}" class="img-circle"
                                                        width="95" style="max-height: 95px"></p>
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
                                            <c:when test="${images[loop.index] == null or images[loop.index] == '-'}">
                                                <p class="img-circle badge bg-info" width="80">Brak zdjęcia</p>
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
                                <span class="badge bg-inverse"><i class="fa fa-clock-o"></i></span>
                            </div>
                            <div class="details">
                                <p>
                                    <muted>${tempNotificationList.time} temu</muted>
                                    <br/>
                                    <a href="#">${tempNotificationList.firstName} ${tempNotificationList.lastName}</a>
                                    wypełnił(a) zadanie i zdobył(a) ${tempNotificationList.experiencePoint} pkt doświadczenia.<br/>
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

<spring:url value="/resources/Theme/assets/js/fancybox/jquery.fancybox.js" var="fancybox_js"/>
<script src="${fancybox_js}"></script>

<script type="text/javascript">
    $(function() {
        //    fancybox
        jQuery(".fancybox").fancybox();
    });

</script>

</body>
</html>
