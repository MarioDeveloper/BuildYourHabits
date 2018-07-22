<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Rejestracja</title>

	<!-- CSS -->
	<spring:url value="/resources/assets/bootstrap/css/bootstrap.min.css" var="minCss" />
	<spring:url value="/resources/assets/font-awesome/css/font-awesome.min.css" var="awesome" />
	<spring:url value="/resources/assets/css/form-elements.css" var="fe" />
	<spring:url value="/resources/assets/css/style.css" var="style" />

	<link href="${minCss}" rel="stylesheet" />
	<link href="${awesome}" rel="stylesheet" />
	<link href="${fe}" rel="stylesheet" />
	<link href="${style}" rel="stylesheet" />

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

			<div class="row" id="">
				<div class="col-sm-6 col-sm-offset-3 text">
					<c:if test="${param.success != null}">
                        <c:url value="/" var="loginPage"/>
                        <div class="alert alert-info">Dziękujęmy za zarejestrowanie się do naszej aplikacji :) <a
                                href="${loginPage}"> Zaloguj się! </a></div>
					</c:if>
				</div>
			</div>


			<div class="row" id="topdiv">
				<div class="col-sm-offset-3 col-sm-6">

					<div class="form-box" id="bottomdiv">
						<div class="form-top">
							<div class="form-top-left">
                                <h3>Zarejestruj się teraz</h3>
                                <p>Wypełnij poniższy formularz, aby uzyskać stały dostęp do tej wspaniałej
                                    aplikacji:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form:form action="/registration" modelAttribute="user" method="post" enctype="utf8"
									   role="form" class="registration-form">
								<div class="form-group">
									<label class="sr-only" for="form-first-name">First name</label>
									<form:input path="firstName" type="text" name="form-first-name"
                                                placeholder="Imię..." class="form-first-name form-control"
                                                id="form-first-name" required="required"/>
									<form:errors path="firstName" cssClass="text-danger"/>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-last-name">Last name</label>
									<form:input path="lastName" type="text" name="form-last-name"
                                                placeholder="Nazwisko..." class="form-last-name form-control"
                                                required="required"/>
									<form:errors path="lastName" cssClass="text-danger"/>
								</div>
                                <div class="form-group">
                                    <label class="sr-only" for="form-last-name">City</label>
                                    <form:input path="city" type="text" name="form-last-name"
                                                placeholder="Miasto..." class="form-last-name form-control"
                                                required="required"/>
                                    <form:errors path="city" cssClass="text-danger"/>
                                </div>

                                <div class="form-group">
                                    <label class="sr-only" for="form-last-name">Gender</label>
                                    <form:select path="gender" type="text" name="form-last-name"
                                                 class="form-last-name form-control" required="required">
                                        <form:option value="Wybierz płeć..."/>
                                        <form:option value="Kobieta"/>
                                        <form:option value="Mężczyzna"/>
                                    </form:select>
                                    <form:errors path="gender" cssClass="text-danger"/>
                                </div>

                                <div class="form-group">
									<label class="sr-only" for="form-email">Email</label>
                                    <form:input path="email" name="form-email" placeholder="Email..."
                                                class="form-email form-control" id="form-email" required="required"/>
									<form:errors path="email" cssClass="text-danger"/>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-last-name">Password</label>
									<form:input path="password" type="password" name="password"
                                                placeholder="Hasło..." class="form-last-name form-control"
                                                id="form-password" required="required"/>
									<form:errors path="password" cssClass="text-danger"/>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-last-name">Confirm password</label>
									<form:input path="matchingPassword" type="password" name="confirm-password"
                                                placeholder="Powtórz hasło..." class="form-last-name form-control"
                                                id="form-confirm-password" required="required"/>
									<form:errors path="matchingPassword" cssClass="text-danger"/>
								</div>
                                <button type="submit" class="btn">Zarejestruj mnie!</button>
							</form:form>
						</div>
					</div>

                    <div class="social-login">
                        <h3>...lub jeśli masz już konto:</h3>
                        <div class="social-login-buttons">
                            <c:url value="/" var="loginPage"/>
                            <a href="${loginPage}" class="btn btn-link-1 btn-link-1-google-plus"> Zaloguj się! </a>
                        </div>
                    </div>

                </div>
			</div>
		</div>
	</div>


    <!-- Javascript -->
	<spring:url value="/resources/assets/js/jquery-1.11.1.min.js" var="jquery" />
	<spring:url value="/resources/assets/bootstrap/js/bootstrap.min.js" var="bootstrapMin" />
	<spring:url value="/resources/assets/js/jquery.backstretch.min.js" var="backstretchMin" />
	<spring:url value="/resources/assets/js/scripts.js" var="scriptJs" />

	<script src="${jquery}"></script>
	<script src="${bootstrapMin}"></script>
	<script src="${backstretchMin}"></script>
	<script src="${scriptJs}"></script>

	<%--<spring:url value="/resources/assets/js/placeholder.js" var="placeholder" />--%>
	<%--<script src="${placeholder}"></script>--%>

</body>

</html>