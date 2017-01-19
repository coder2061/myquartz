<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>Register</title>

    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body">

<div class="container">

    <form class="form-signin" action="/myquartz/register">
    
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">Register</h1>
            <img src="assets/images/login-logo.png" alt=""/>
        </div>

        <div class="login-wrap">
            <p>Enter your personal details below</p>
            <input type="text" placeholder="Full Name" class="form-control" autofocus>
            <input type="text" placeholder="Address" class="form-control">
            <input type="text" placeholder="Email" class="form-control">
            <input type="text" placeholder="City/Town" class="form-control">
            
            <div class="radios">
                <label for="radio-01" class="label_radio col-lg-6 col-sm-6">
                    <input type="radio" checked="" value="1" id="radio-01" name="sample-radio"> Male
                </label>
                <label for="radio-02" class="label_radio col-lg-6 col-sm-6">
                    <input type="radio" value="1" id="radio-02" name="sample-radio"> Female
                </label>
            </div>

            <p> Enter your account details below</p>
            <input type="text" placeholder="User Name" class="form-control">
            <input type="password" placeholder="Password" class="form-control">
            <input type="password" placeholder="Re-type Password" class="form-control">
            
            <label class="checkbox">
                <input type="checkbox" value="agree this condition"> I agree to the Terms of Service and Privacy Policy
            </label>
            
            <button type="submit" class="btn btn-lg btn-login btn-block">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                Already Registered.
                <a href="/myquartz/signin" class="">
                    Login
                </a>
            </div>
        </div>

    </form>

</div>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="assets/js/jquery-1.10.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/modernizr.min.js"></script>

</body>
</html>
