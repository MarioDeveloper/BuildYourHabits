<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Habitica Login Page</title>

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
			.error {
				padding: 15px;
				margin-bottom: 20px;
				border: 1px solid transparent;
				border-radius: 4px;
				color: #a94442;
				background-color: #f2dede;
				border-color: #ebccd1;
			}

			.msg {
				padding: 15px;
				margin-bottom: 20px;
				border: 1px solid transparent;
				border-radius: 4px;
				color: #31708f;
				background-color: #d9edf7;
				border-color: #bce8f1;
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
                                   <h1><strong>Habitica</strong> Login Form</h1>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-offset-4 col-sm-4">
                        	
                        	<div class="form-box">
	                        	<div class="form-top">
	                        		<div class="form-top-left">
	                        			<h3>Login to our site</h3>
	                            		<p>Enter username and password to log on:</p>


										<c:if test="${not empty param.logout}">
											<div class="msg">Logout successfully</div>
										</c:if>

										<c:if test="${not empty param.logout}">
											<div class="error">Invalid username or password</div>
										</c:if>

	                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-key"></i>
	                        		</div>
	                            </div>
	                            <div class="form-bottom">
				                    <form role="form" action="<c:url value='/j_security_check?${_csrf.parameterName}=${_csrf.token} }' />" method="POST" class="login-form">
				                    	<div class="form-group">
				                    		<label class="sr-only" for="form-username">Username</label>
				                        	<input type="text" name='j_username' placeholder="Username..." class="form-username form-control" id="form-username">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-password">Password</label>
				                        	<input type="password" name='j_password' placeholder="Password..." class="form-password form-control" id="form-password">
				                        </div>
				                        <button type="submit" class="btn">Log in!</button>
				                    </form>
			                    </div>
		                    </div>
		                
		                	<div class="social-login">
	                        	<h3>...or login with:</h3>
	                        	<div class="social-login-buttons">
		                        	<a class="btn btn-link-1 btn-link-1-facebook" href="#">
		                        		<i class="fa fa-facebook"></i> Facebook
		                        	</a>
		                        	<a class="btn btn-link-1 btn-link-1-twitter" href="#">
		                        		<i class="fa fa-twitter"></i> Twitter
		                        	</a>
		                        	<a class="btn btn-link-1 btn-link-1-google-plus" href="#">
		                        		<i class="fa fa-google-plus"></i> Google Plus
		                        	</a>
									<input type="hidden"
										   name="${_csrf.parameterName}"
										   value="${_csrf.token}"/>
	                        </div>
	                    </div>
					</div>
				</div>
			</div>
		</div>

        <%--<footer>--%>
        <%--</footer>--%>

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