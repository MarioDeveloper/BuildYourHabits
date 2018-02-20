<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<div class="row">
						<div class="col-sm-offset-3 col-sm-6">
                        	
                        	<div class="form-box">
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
				                    <form role="form" action="" method="post" class="registration-form">
				                    	<div class="form-group">
				                    		<label class="sr-only" for="form-first-name">First name</label>
				                        	<input type="text" name="form-first-name" placeholder="First name..." class="form-first-name form-control" id="form-first-name">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-last-name">Last name</label>
				                        	<input type="text" name="form-last-name" placeholder="Last name..." class="form-last-name form-control" id="form-last-name">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-email">Email</label>
				                        	<input type="text" name="form-email" placeholder="Email..." class="form-email form-control" id="form-email">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-last-name">Password</label>
				                        	<input type="password" name="password" placeholder="Password..." class="form-last-name form-control" id="form-password">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-last-name">Confirm password</label>
				                        	<input type="password" name="confirm-password" placeholder="Confirm Password..." class="form-last-name form-control" id="form-confirm-password">
				                        </div>
				                        <button type="submit" class="btn">Sign me up!</button>
				                    </form>
			                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer>
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