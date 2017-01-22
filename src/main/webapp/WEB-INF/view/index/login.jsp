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

    <title>Login</title>

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

    <form class="form-signin" action="/myquartz/login" method="post">
    
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">Sign In</h1>
            <img src="assets/images/login-logo.png" alt=""/>
        </div>
        
        <div class="login-wrap">
            <input type="text" name="account" class="form-control" placeholder="account" autofocus>
            <input type="password" name="password" class="form-control" placeholder="password">

            <button class="btn btn-lg btn-login btn-block" type="submit">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                Not a member yet?
                <a class="" href="/myquartz/signup">
                    Signup
                </a>
            </div>
            
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Forgot Password?</a>
                </span>
            </label>
        </div>
        
    </form>
    
   <!-- Modal -->
   <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h4 class="modal-title">Forgot Password ?</h4>
               </div>
               <div class="modal-body">
                   <p>Enter your e-mail address below to reset your password.</p>
                   <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

               </div>
               <div class="modal-footer">
                   <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                   <button class="btn btn-primary" type="button">Submit</button>
               </div>
           </div>
       </div>
   </div>
   <!-- modal -->

</div>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="assets/js/jquery-1.10.2.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/modernizr.min.js"></script>

<script src="common/js/common.js"></script>
<script >
	$(function() {
		/* 
		$(".form-signin").submit(function() {
            var account = $("account").val();
            var msg = "";
            var password = $("password").val();
            if (isBlank(account) && isBlank(password)) {
				msg = "登录账户和密码不能为空";
			} else if (isBlank(account)) {
				msg = "登录账户不能为空";
			} else if (isBlank(password)) {
				msg = "登录密码不能为空";
			}
            if (notBlank(msg)) {
	            alert(msg.trim());
			}
        });
         */
        $("#pause").click(function() {
            var jobName = $(this).siblings("td.jobName").val();
            var jobGroup = $(this).siblings("td.jobGroup").val();
            alert(jobGroup);

            $.ajax({
        		type : "POST",
        		url : "myquartz/login",
        		data : "cmdID=" + cmdID,
        		dataType : "json",
        		async : false,
        		success : function(data) {
        			var cardList = data.cardList;
        			var str = "该商品未设置储值会员卡价格!";
        			if (cardList.length > 0) {
        				str = "<table style='width: 230px;text-align: left;'>";
        				$.each(cardList, function(i, items) {
        					str += "<tr><td width='70%'>" + items.Name + "</td>"
        							+ "<td width='30%'>" + items.Price
        							+ "&nbsp;元</td></tr>";
        				})
        				str += "</table>";
        			}
        			$("#cardPrices").html(str);
        		}
        	});
        });
    });
</script>

</body>
</html>
