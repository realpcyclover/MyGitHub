<%-- 
    Document   : SellerProfile
    Created on : Jun 20, 2022, 10:02:01 PM
    Author     : BachDuc
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
        <title>Admin Template</title>
        <link href="../dist/css/style.min.css" rel="stylesheet">
        <link href="../myCss/Mycss.css" rel="stylesheet">
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    </head>

    <body>
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Main wrapper - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full" data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
            <!-- ============================================================== -->
            <!-- Topbar header - style you can find in pages.scss -->
            <!-- ============================================================== -->
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin5">
                        <!-- ============================================================== -->
                        <!-- Logo -->
                        <!-- ============================================================== -->
                        <a class="navbar-brand" href="Home">
                            <!-- Logo icon -->
                            <b class="logo-icon">
                                <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                                <!-- Dark Logo icon -->
                                <img src="assets/images/bee.png" alt="homepage" class="dark-logo" />
                                <!-- Light Logo icon -->
                                <img style="width: 50px;height: 50px;" src="assets/images/bee.png" alt="homepage" class="light-logo" />
                            </b>
                            <!--End Logo icon -->
                            <!-- Logo text -->
                            <span class="logo-text">
                                <!-- dark Logo text -->
                                <img style="width: 150px;height: 60px;" src="assets/images/losip.png" alt="homepage" class="dark-logo" />
                                <!-- Light Logo text -->    
                                <img style="width: 150px;height: 60px;" src="assets/images/losip.png" class="light-logo" alt="homepage" />
                            </span>
                        </a>

                        <!-- ============================================================== -->
                        <!-- End Logo -->
                        <!-- ============================================================== -->
                        <!-- This is for the sidebar toggle which is visible on mobile only -->
                        <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="Home"><i class="ti-menu ti-close"></i></a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                        <!-- ============================================================== -->
                        <!-- toggle and nav items -->
                        <!-- ============================================================== -->
                        <ul class="navbar-nav float-left mr-auto">
                            <!-- ============================================================== -->
                            <!-- Search -->
                            <!-- ============================================================== -->
                        </ul>
                        <!-- ============================================================== -->
                        <!-- Right side toggle and nav items -->
                        <!-- ============================================================== -->
                        <ul class="navbar-nav float-right">
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="assets/images/users/1.jpg" alt="user" class="rounded-circle" width="31"></a>
                                <div class="dropdown-menu dropdown-menu-right user-dd animated">
                                    <a class="dropdown-item" href="Proflie"><i class="ti-user m-r-5 m-l-5"></i> My Profile</a>
                                    <a class="dropdown-item" href="VNPAY/vnpayindex.jsp"><i class="ti-wallet m-r-5 m-l-5"></i> My Balance:balance</a>
                                    <a class="dropdown-item" href="VNPAY/vnpayindex.jsp"><i class="ti-wallet m-r-5 m-l-5"></i> ReCharge</a>
                                    <a class="dropdown-item" href="javascript:void(0)"><i class="ti-email m-r-5 m-l-5"></i> Inbox</a>
                                    <a class="dropdown-item" href="Logout"><i class="ti-email m-r-5 m-l-5"></i> Log out</a>
                                </div>
                            </li>
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- ============================================================== -->
            <!-- End Topbar header -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <aside class="left-sidebar" data-sidebarbg="skin6">
                <!-- Sidebar scroll-->
                <div class="scroll-sidebar">
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav">
                        <ul id="sidebarnav">
                            <!-- User Profile-->
                            <li>
                                <!-- User Profile-->
                                <div class="user-profile d-flex no-block dropdown m-t-20">
                                    <div class="user-pic"><img src="assets/images/users/1.jpg" alt="users" class="rounded-circle" width="40" /></div>
                                    <div class="user-content hide-menu m-l-10">
                                        <a href="javascript:void(0)" class="" id="Userdd" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <h5 class="m-b-0 user-name font-medium">Ho va Ten <i class="fa fa-angle-down"></i></h5>
                                            <span class="op-5 user-email">Gmail </span>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="Userdd">
                                            <a class="dropdown-item" href="javascript:void(0)"><i class="ti-user m-r-5 m-l-5"></i> My Profile</a>
                                            <a class="dropdown-item" href="javascript:void(0)"><i class="ti-wallet m-r-5 m-l-5"></i> My Balance</a>
                                            <a class="dropdown-item" href="javascript:void(0)"><i class="ti-email m-r-5 m-l-5"></i> Inbox</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="javascript:void(0)"><i class="ti-settings m-r-5 m-l-5"></i> Account Setting</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="javascript:void(0)"><i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- End User Profile-->
                            </li>
                            <li class="p-15 m-t-10"><a href="javascript:void(0)" class="btn btn-block create-btn text-white no-block d-flex align-items-center"><i class="fa fa-plus-square"></i> <span class="hide-menu m-l-5">Create New</span> </a></li>
                            <!-- User Profile-->
                            <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="Proflie" aria-expanded="false"><i class="mdi mdi-view-dashboard"></i><span class="hide-menu">Profile</span></a></li>
                            <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="ManagerProduct" aria-expanded="false"><i class="mdi mdi-account-network"></i><span class="hide-menu">Manager Product</span></a></li>
                            <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="ManagerOrder" aria-expanded="false"><i class="mdi mdi-border-all"></i><span class="hide-menu">Manager Order</span></a></li>
                            <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="ManagerShop" aria-expanded="false"><i class="mdi mdi-face"></i><span class="hide-menu">Manager Shop</span></a></li>
                            <li class="sidebar-item"> <a class="sidebar-link waves-effect waves-dark sidebar-link" href="Cart" aria-expanded="false"><i class="mdi mdi-file"></i><span class="hide-menu">My Cart</span></a></li>
                            <li class="text-center p-40 upgrade-btn">
                                <a href="https://wrappixel.com/templates/xtremeadmin/" class="btn btn-block btn-danger text-white" target="blank">Upgrade to Pro</a>
                            </li>
                        </ul>

                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <!-- ============================================================== -->
            <!-- End Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page wrapper  -->
            <!-- ============================================================== -->
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <div class="page-breadcrumb">
                    <div class="row align-items-center">
                        <div class="col-5">
                            <h4 class="page-title">Profile Page</h4>
                            <div class="d-flex align-items-center">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                        <div class="col-7">
                            <div class="text-right upgrade-btn">
                                <a href="https://wrappixel.com/templates/xtremeadmin/" class="btn btn-danger text-white" target="blank">Upgrade to Pro</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Container fluid  -->
                <!-- ============================================================== -->
                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->
                    <!-- Row -->
                    <div class="row">
                        <!-- Column -->
                        <div class="col-lg-4 col-xlg-3 col-md-5">
                            <div class="card">
                                <div class="card-body">
                                    <center class="m-t-30"> <img src="${imgprofile}" class="rounded-circle" width="150" />
                                        <h4 class="card-title m-t-10">Ten</h4>
                                        <h6 class="card-subtitle">User Profile</h6>
                                        <div class="row text-center justify-content-md-center">
                                            <div class="col-4"><a href="javascript:void(0)" class="link"><i class="icon-people"></i> <font class="font-medium">254</font></a></div>
                                            <div class="col-4"><a href="javascript:void(0)" class="link"><i class="icon-picture"></i> <font class="font-medium">54</font></a></div>
                                        </div>
                                    </center>
                                </div>
                                <div>
                                    <hr> </div>
                                <div class="card-body"> <small class="text-muted">Email address </small>
                                    <h6>${acf.getGmailAccount()}</h6> <small class="text-muted p-t-30 db">Phone</small>
                                    <h6>${acf.getPhoneNumber()}</h6> <small class="text-muted p-t-30 db">Address</small>
                                    <table class="styled-table">
                                        <tr>
                                            <th>City</th>
                                            <th>District</th>
                                            <th>Street</th>
                                            <th></th>
                                        </tr>
                                        <c:forEach var="adcitem" items="${adc}">
                                            <tr>
<%--                                                int  idAddressAccount ;--%>
<%--                                                @Column(name = "gmailAccount")--%>
<%--                                                String gmailAccount ;--%>
<%--                                                @Column(name = "IDCityAddress")--%>
<%--                                                String IDCityAddress ;--%>
<%--                                                @Column(name = "IDDistrictAddress")--%>
<%--                                                String IDDistrictAddress  ;--%>
<%--                                                @Column(name = "StreetAddress")--%>
<%--                                                String  StreetAddress ;--%>
                                                <td>${adcitem.getIDCityAddress()}</td>
                                                <td>${adcitem.getIDDistrictAddress()}</td>
                                                <td>${adcitem.getStreetAddress()}</td>
                                                <td>
                                                    <a style="display:block;"href="/AccountInfo/Delete/${adcitem.getIdAddressAccount()}">
                                                    Delete
                                                    </a>
                                                    <input type="submit" value="Update" onclick="
                                                    document.getElementById('adccity').value='${adcitem.getIDCityAddress()}';
                                                    document.getElementById('adcdistrict').value='${adcitem.getIDDistrictAddress()}';
                                                     document.getElementById('adcstreet').value='${adcitem.getStreetAddress()}';
                                                      document.getElementById('editadcsubmit').value='${adcitem.getIdAddressAccount()}';
                                                       document.getElementById('editadcsubmit').style.display ='inline-block;';
                                                       document.getElementById('edittitle').value ='Edited:'+'${adcitem.getIdAddressAccount()}';
">
                                                </td>
<%--                                                /Delete/{adid}--%>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                    <!--insert address-->


                                    <div class="col-md-6 mb-4">
                                        <div class="col-md-6 mb-4">
                                            <div id="edittitle">Edit:</div>
                                            <form:form action="AccountInfo" modelAttribute="AccountAdress" method="GET">
                                                City:<form:select id="adccity" path="IDCityAddress" onchange="this.form.submit()" >
                                                    <c:forEach var="cityitem" items="${city}">
                                                        <form:option value="${cityitem.getIDCityAddress()}" label="${cityitem.getNameCityAddress()}"/>
                                                    </c:forEach>
                                                </form:select>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                                District:<form:select id="adcdistrict" path="IDDistrictAddress">
                                                    <c:forEach var="districitem" items="${distric}">
                                                    <form:option value="${districitem.getIDDistrictAddress()}" label="${districitem.getNameDistrictAddress()}"/>
                                                    </c:forEach>
                                                </form:select>

                                    </div>
                                    <div  class="form-outline mb-4">
<%--                                        <input  type="text" name="Adressregister" value="" class="form-control form-control-lg" />--%>
                                        <form:input id="adcstreet" path="StreetAddress" value="" type="text" class="form-control form-control-lg" />
                                        <label class="form-label" >Address</label>
                                    </div>

                                    <button style="display: inline-block;" type="submit" name="insertadress" onclick="document.getElementById('adin').value='submited'" id="adin" value="submit" class="btn btn-warning btn-lg ms-2">Submit</button>
                                    <button  style="display: inline-block;"  id="editadcsubmit" type="submit" name="editadress" onclick="document.getElementById('adin').value='editadc'" id="adin" value="Update" class="btn btn-warning btn-lg ms-2">Update</button>
                                    </form:form>

                                    <!--insert adress-->
                                    <br/>
                                </div>
                            </div>
                        </div>
                        <!-- Column -->
                        <!-- Column -->
                        <div class="col-lg-8 col-xlg-9 col-md-7">
                            <div class="card">
                                <div class="card-body">
                                    <form:form method="POST"
                                               action="/AccountInfo/Update"
                                               modelAttribute="acountinfo">
                                        <label class="col-md-12">First Name</label>
                                        <div class="col-md-12">
<%--                                            <input type="text" name="fname" form="updateinfo" value="${acf.getFname()}" placeholder="" class="form-control form-control-line">--%>
                                            <form:input path="Fname" type="text"
                                                          value="${acf.getFname()}" placeholder="" class="form-control form-control-line"/>
                                        </div>
                                        <label class="col-md-12">Last Name</label>
                                        <div class="col-md-12">
<%--                                            <input type="text" name="lname" form="updateinfo" value="${acf.getLname()}" placeholder="" class="form-control form-control-line">--%>
                                            <form:input path="Lname" type="text"
                                                          value="${acf.getLname()}" placeholder="" class="form-control form-control-line"/>
                                        </div>
                                        <label for="example-email" class="col-md-12">Email</label>
                                        <div class="col-md-12">
                                            <input readonly="" type="text" name="email" form="updateinfo" value="${acf.getGmailAccount()}" placeholder="" class="form-control form-control-line" name="example-email" id="example-email">
<%--                                            <form:input path="gmailAccount" type="text"--%>
<%--                                                        value="${acf.getGmailAccount()}" placeholder="" class="form-control form-control-line"/>--%>
                                        </div>
                                        <label class="col-md-12">Pass Word</label>
                                        <div class="col-md-12">
                                            <input type="text"readonly="" value="${ac.getPasswordaccount()}" class="form-control form-control-line">
                                        </div>
                                        <label class="col-md-12">Phone Number</label>
                                        <div class="col-md-12">
<%--                                            <input type="text" name="phone" form="updateinfo" value="${acf.getPhoneNumber()}" placeholder="" class="form-control form-control-line">--%>
                                            <form:input path="PhoneNumber" type="text"
                                                        value="${acf.getPhoneNumber()}" placeholder="" class="form-control form-control-line"/>
                                        </div>
                                        <label class="col-md-12">Gender</label>
                                        <div class="col-md-12">
                                        <form:select  path="gender">
                                            <form:option   value="${acf.getGender()}" cssStyle="display: none;" > ${acf.getGender()}</form:option>
                                            <form:option value="male"> male</form:option>
                                            <form:option value="female"> female</form:option>
                                        </form:select>
                                        </div>
                                        <div class="col-sm-12">
                                            <button name="updateinfo" value="updateinfo"  type="submit" class="btn btn-success">Update Profile</button>
                                            <div style="margin-top: 12px;color: red;font-size: red;font-family: 24px;"></div>
                                        </div>
                                    </form:form>

                                    <div class="form-group">
                                        <label class="col-md-12">Seller Image</label>
                                        <div class="col-md-12">
                                            <form id="imgupload" action="AccountInfo/Uploadimg" method="post" enctype="multipart/form-data" ></form>
                                            <input class="btn btn-success" form="imgupload"  class="m-t-30" type="file"  accept="image/*" name="imgitem" id="file"  onchange="loadFile(event)" style="display: block;">
                                            <input class="btn btn-secondary"  name="uploadimg" value="Upload_Image" type="submit" form="imgupload" />
                                            <img form="imgupload"  id="output" width="200" />
                                        </div>
                                    </div>
                                    <!--                                    <div class="form-group">
                                                                            <div class="col-sm-12">
                                                                                <button class="btn btn-success">Update Profile</button>
                                                                            </div>
                                                                        </div>-->
                                    <form id="repass" action="Proflie" method="POST"></form>
                                    <div class="form-group">
                                        <label class="col-md-12">NewPass</label>
                                        <div class="col-md-12">
                                            <input type="password" name="new" form="repass"  class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">ReNewPass</label>
                                        <div class="col-md-12">
                                            <input type="password" name="new" form="repass"  class="form-control form-control-line">
                                        </div>
                                    </div>

                                    <div style="color: red;font-size: 12px;"></div>
                                     <div class="col-md-12">
                                            <input type="submit" name="repass" form="repass" class="form-control form-control-line">
                                        </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                </div>
                <!-- Row -->
                <!-- ============================================================== -->
                <!-- End PAge Content -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Right sidebar -->
                <!-- ============================================================== -->
                <!-- .right-sidebar -->
                <!-- ============================================================== -->
                <!-- End Right sidebar -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->
            <footer class="footer text-center">
                Assigment copyright by Group9 
            </footer>
            <!-- ============================================================== -->
            <!-- End footer -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="../assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="../assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="../assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../dist/js/app-style-switcher.js"></script>
    <!--Wave Effects -->
    <script src="../dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="../dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="../dist/js/custom.js"></script>
    <script>
                                                var loadFile = function (event) {
                                                    var image = document.getElementById('output');
                                                    image.src = URL.createObjectURL(event.target.files[0]);
                                                };
                                                var loadFile1 = function (event) {
                                                    var image = document.getElementById('output1');
                                                    image.src = URL.createObjectURL(event.target.files[0]);
                                                };
                                                function Close() {
                                                    document.getElementById('FormEdit').style.display = "none";
                                                }


    </script>
</body>

</html>