<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Habitica Registration Page</title>

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
					<h1><strong>Habitica</strong> Registration Form</h1>
				</div>
			</div>

			<div class="row" id="">
				<div class="col-sm-6 col-sm-offset-3 text">
					<c:if test="${param.success != null}">
						<div class="alert alert-info">You've successfully registered to our awesome app!</div>
					</c:if>
				</div>
			</div>


			<div class="row" id="topdiv">
				<div class="col-sm-offset-3 col-sm-6">

					<div class="form-box" id="bottomdiv">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Sign up now</h3>
								<p>Fill in the form below to get instant access:</p>
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
												placeholder="First name..." class="form-first-name form-control"
												id="form-first-name" required="required"/>
									<form:errors path="firstName" cssClass="text-danger"/>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-last-name">Last name</label>
									<form:input path="lastName" type="text" name="form-last-name"
												placeholder="Last name..." class="form-last-name form-control"
												required="required"/>
									<form:errors path="lastName" cssClass="text-danger"/>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-email">Email</label>
									<form:input path="email" type="text" name="form-email" placeholder="Email..."
												class="form-email form-control" id="form-email" required="required"/>
									<form:errors path="email" cssClass="text-danger"/>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-last-name">Password</label>
									<form:input path="password" type="password" name="password"
												placeholder="Password..." class="form-last-name form-control"
												id="form-password" required="required"/>
									<form:errors path="password" cssClass="text-danger"/>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-last-name">Confirm password</label>
									<form:input path="matchingPassword" type="password" name="confirm-password"
												placeholder="Confirm Password..." class="form-last-name form-control"
												id="form-confirm-password" required="required"/>
									<form:errors path="matchingPassword" cssClass="text-danger"/>
								</div>
								<button type="submit" class="btn">Sign me up!</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer>
		<c:url value="/" var="loginPage" />
		<a href="${loginPage}">Login Page</a>
	</footer>

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