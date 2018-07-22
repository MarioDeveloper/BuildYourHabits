<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Buduj swoje nawyki</title>

    <spring:url value="/resources/assets/bootstrap/css/bootstrap.min.css" var="minCss"/>
    <spring:url value="/resources/assets/font-awesome/css/font-awesome.min.css" var="awesome"/>
    <spring:url value="/resources/assets/css/form-elements.css" var="fe"/>
    <spring:url value="/resources/assets/css/style.css" var="style"/>

    <link href="${minCss}" rel="stylesheet"/>
    <link href="${awesome}" rel="stylesheet"/>
    <link href="${fe}" rel="stylesheet"/>
    <link href="${style}" rel="stylesheet"/>

    <style>
        #topdiv {
            margin-bottom: 0px;
        }

        #bottomdiv {
            margin-top: 20px;
        }
    </style>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">

            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 text">
                    <h1><strong>Buduj</strong> swoje nawyki</h1>
                </div>
            </div>
        </div>

        <div class="row" id="">
            <div class="col-sm-6 col-sm-offset-3 text">
                <c:if test="${param.logout != null}">
                    <div class="alert alert-info">Zostałeś pomyślnie wylogowany</div>
                </c:if>
            </div>
        </div>

        <div class="row" id="topdiv">
            <div class="col-sm-offset-4 col-sm-4">

                <div class="form-box" id="bottomdiv">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Zaloguj się do naszej aplikacji</h3>
                            <p>Wpisz email oraz hasło, aby się zalogować:</p>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">Niepoprawny email lub hasło</div>
                            </c:if>
                        </div>
                        <div class="form-top-right">
                            <%--<i class="fa fa-key"></i>--%>
                            <i class="fa fa-key" aria-hidden="true"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form"
                              action="<c:url value='/j_security_check?${_csrf.parameterName}=${_csrf.token} }' />"
                              method="POST" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name='j_username' placeholder="Email..."
                                       class="form-username form-control" id="form-username" required="required">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name='j_password' placeholder="Hasło..."
                                       class="form-password form-control" id="form-password" required="required">
                            </div>
                            <button id='button1' type="submit" class="btn">Zaloguj się!</button>
                        </form>
                    </div>
                </div>

                <div class="social-login">
                    <h3>...albo stwórz własne konto:</h3>
                    <div class="social-login-buttons">
                        <c:url value="/registration" var="registration"/>
                        <a href="${registration}" class="btn btn-link-1 btn-link-1-google-plus"> Zarejestruj
                            się! </a>
                    </div>
                </div>


                <div class="fb-share-button" data-href="http://budujswojenawyki.pl" data-layout="button"
                     data-title="title of your post" data-desc="description of your post"></div>

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </div>
    </div>
</div>


<!-- Javascript -->
<spring:url value="/resources/assets/js/jquery-1.11.1.min.js" var="jquery"/>
<spring:url value="/resources/assets/bootstrap/js/bootstrap.min.js" var="bootstrapMin"/>
<spring:url value="/resources/assets/js/jquery.backstretch.min.js" var="backstretchMin"/>
<spring:url value="/resources/assets/js/scripts.js" var="scriptJs"/>

<script src="${jquery}"></script>
<script src="${bootstrapMin}"></script>
<script src="${backstretchMin}"></script>
<script src="${scriptJs}"></script>

<script>
    $('#button1').on('click', function () {
        var button = this;
        var form = $('#form1');
        if (form.valid()) {
            setTimeout(function () {
                button.attr('disabled', '');
            });
        }
    });

</script>

</body>
</html>