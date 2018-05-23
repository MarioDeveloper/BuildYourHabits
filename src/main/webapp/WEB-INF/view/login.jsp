<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>BUDUJ SWOJE NAWYKI</title>

        <!-- CSS -->


		<%--<meta property="og:url"           content="http://budujswojenawyki.pl" />--%>
		<%--<meta property="og:type"          content="website" />--%>
		<%--<meta property="og:title"         content="Your Website Title" />--%>
		<%--<meta property="og:description"   content="Your description" />--%>
		<%--&lt;%&ndash;<meta property="og:image"         content="https://www.your-domain.com/path/image.jpg" />&ndash;%&gt;--%>

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
                    </div>

				<div class="row" id="">
					<div class="col-sm-6 col-sm-offset-3 text">
						<c:if test="${param.logout != null}">
							<div class="alert alert-info">Udane wylogowanie</div>
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


										<%--<c:if test="${param.logout !=null}">--%>
										<%--<div class="alert alert-success">Udane wylogowanie</div>--%>
										<%--</c:if>--%>

										<c:if test="${param.error != null}">
											<div class="alert alert-danger">Niepoprawny email lub hasło</div>
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
											<input type="text" name='j_username' placeholder="Email..."
												   class="form-username form-control" id="form-username">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-password">Password</label>
											<input type="password" name='j_password' placeholder="Hasło..."
												   class="form-password form-control" id="form-password">
				                        </div>
										<button type="submit" class="btn">Zaloguj się!</button>
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


							<div class="fb-share-button" data-href="http://budujswojenawyki.pl" data-layout="button" data-title ="title of your post" data-desc="description of your post" ></div>

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


		<%--FACEBOOK--%>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js" type="text/javascript"></script>
		<div id="fb-root"></div>
		<script>


            (function(d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id)) return;
                js = d.createElement(s); js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=440306113072008&version=v2.0";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));</script>

		<%--FACEBOOK END--%>
<script>

		$( document ).ready(function() {
		$( "#btn" ).click(function() {
		var val=$("#name").val();
		FB.login(function(response)
		{
		if (response.authResponse)
		{
		// Post message to your wall
		var opts = {
		message : val,
		name: 'budujswojenawyki ',
//		picture: 'http://javafreakers.com/wp-content/uploads/2014/04/SDJINFO.png',
		link: 'http://budujswojenawyki.pl',
		description: 'javafreaker for java tutorial ',
		redirect_uri: 'http://budujswojenawyki.pl',
		};

		FB.api('/feed', 'post', opts, function(response)
		{
		if (!response || response.error)
		{
		//alert('Posting error occured');
		}
		else
		{
		// alert('Success - Post ID: ' + response.id);
		}
		if(response){
		//do some task when response come
		}
		// var element = document.getElementById('fb-root');
		//FB.Canvas.showFlashElement(element);
		//FB.XFBML.parse();
		});
		}
		else
		{
		alert('Not logged in');

		}
		}, { scope : 'publish_stream' });
		});
		});
		//}
        </script>
        <div id="fb-root"></div>
        <script>
        window.fbAsyncInit = function() {
            FB.init({
                appId : '440306113072008',
                status : true, // check login status
                cookie : true, // enable cookies to allow the server to access the session
                xfbml : true // parse XFBML
            });
        };

        (function() {
            var e = document.createElement('script');
            e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
            e.async = true;
            document.getElementById('fb-root').appendChild(e);
        }());
		</script>




	</body>
</html>