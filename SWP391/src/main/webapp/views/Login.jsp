<%-- 
    Document   : Login
    Created on : Jun 4, 2022, 2:25:25 AM
    Author     : BachDuc
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>

            .divider:after,
            .divider:before {
                content: "";
                flex: 1;
                height: 1px;
                background: #eee;
            }
            .h-custom {
                height: calc(100% - 73px);
            }
            @media (max-width: 450px) {
                .h-custom {
                    height: 100%;
                }
            }
        </style>
    </head>
    <body>
        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                             class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">

                        <form:form method="POST"
                                   action="/Account/Login"

                                   modelAttribute="NewAccount">
                            <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                                <p class="lead fw-normal mb-0 me-3">Sign in with</p>
                                <button type="button" class="btn btn-primary btn-floating mx-1">
                                    <i class="fab fa-facebook-f"></i>
                                </button>

                                <button type="button" class="btn btn-primary btn-floating mx-1">
                                    <i class="fab fa-twitter"></i>
                                </button>

                                <button type="button" class="btn btn-primary btn-floating mx-1">
                                    <i class="fab fa-linkedin-in"></i>
                                </button>
                            </div>

                            <div class="divider d-flex align-items-center my-4">
                                <p class="text-center fw-bold mx-3 mb-0">Or</p>
                            </div>


<%--                            <form:form method="POST"--%>
<%--                                       action="/categories/insertcategories"--%>
<%--                                       modelAttribute="categoryv1">--%>
<%--                                Category Name:<form:input path="categoryName"   />--%>
<%--                                <br>--%>
<%--                                &lt;%&ndash;    <div>${categoryv1.getCategoryName()}</div>&ndash;%&gt;--%>
<%--                                Description:<form:input path="description"  />--%>
<%--                                <br>--%>
<%--                                <input type="submit" value="Insert">--%>

<%--                            </form:form>--%>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <form:input path="gmailAccount" value=""    type="text" id="form3Example3" name="gmailAccount" class="form-control form-control-lg"
                                            placeholder="Enter a valid email address"/>
                                <label class="form-label" for="form3Example3">Email address</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-3">
                                <form:input path="passwordaccount"  type="password" id="form3Example4" name="passwordaccount" class="form-control form-control-lg"
                                            placeholder="Enter password" />
                                <label class="form-label" for="form3Example4">Password</label>
                            </div>


                            <div class="d-flex justify-content-between align-items-center">
                                <!-- Checkbox -->
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg" name="btn"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                               <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="../Register"
                                                                                                  class="link-danger">Register</a></p>
                                <p class="small fw-bold mt-2 pt-1 mb-0">You forget mat khau ? <a href="../ForgetPass"
                                                                                                  class="link-danger">You click here</a></p>                                                                  
                                                                                                  
                            </div>

                        </form>
                        </form:form>
                    </div>
                </div>
            </div>
            <div
                class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
                <!-- Copyright -->
                <div class="text-white mb-3 mb-md-0">
                    Copyright © 2020. All rights reserved.
                </div>
                <!-- Copyright -->

                <!-- Right -->
                <div>
                    <a href="#!" class="text-white me-4">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a href="#!" class="text-white me-4">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a href="#!" class="text-white me-4">
                        <i class="fab fa-google"></i>
                    </a>
                    <a href="#!" class="text-white">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                </div>
                <!-- Right -->
            </div>
        </section>
    </body>
</html>