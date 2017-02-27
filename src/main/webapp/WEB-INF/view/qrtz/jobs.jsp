<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>Jobs</title>

  <!--dynamic table-->
  <link href="./../assets/js/advanced-datatable/css/demo_page.css" rel="stylesheet" />
  <link href="./../assets/js/advanced-datatable/css/demo_table.css" rel="stylesheet" />
  <link href="./../assets/js/data-tables/DT_bootstrap.css" rel="stylesheet" />

  <link href="./../assets/css/style.css" rel="stylesheet">
  <link href="./../assets/css/style-responsive.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="./../assets/js/html5shiv.js"></script>
  <script src="./../assets/js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header">

<section>
    <%@ include file="../assets/left_side.html" %>
    
    <!-- main content start-->
    <div class="main-content" >

        <%@ include file="../assets/header_section.html" %>

        <!-- page heading start-->
        <div class="page-heading">
            <h3>
                Jobs
            </h3>
            <ul class="breadcrumb">
                <li>
                    <a href="#">Home</a>
                </li>
                <li>
                    <a href="#">Qrtz</a>
                </li>
                <li class="active">Jobs</li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
        <div class="row">
        <div class="col-sm-12">
        <section class="panel">
	        <header class="panel-heading">
	            Jobs
	            <span class="tools pull-right">
	                <a href="javascript:;" class="fa fa-chevron-down"></a>
	                <a href="javascript:;" class="fa fa-times"></a>
	             </span>
	        </header>
	        <div class="panel-body">
		        <div class="adv-table">
			        <table class="display table table-bordered table-striped" id="dynamic-table">
				        <thead>
					        <tr>
					            <th>任务名称</th>
					            <th>任务分组</th>
					            <th>corn表达式</th>
					            <th>调度类</th>
					            <th>操作</th>
					        </tr>
				        </thead>
				        <tbody>
				        	<c:forEach var="item" items="${jobs}">
						        <tr class="gradeX">
						            <td>${item.jobName}</td>
						            <td>${item.jobGroup}</td>
						            <td>${item.cronExpression}</td>
						            <td>${item.jobClassName}</td>
						            <td>
									  	<button class="btn btn-default" type="button"><i class="fa fa-plus"></i> create</button>
                                        <button class="btn btn-success" type="button"><i class="fa fa-edit"></i> modify</button>
                                        <button class="btn btn-primary" type="button"><i class="fa fa-square"></i> pause</button>
                                        <button class="btn btn-info" type="button"><i class="fa fa-refresh"></i> resume</button>
                                        <button class="btn btn-warning " type="button"><i class="fa fa-times"></i> delete</button>
                                        <button class="btn btn-danger " type="button"><i class="fa fa-times-circle"></i> clear</button>
						            </td>
						        </tr>
					        </c:forEach>
				        </tbody>
				        <tfoot>
					        <tr>
					            <th>JobName</th>
					            <th>JobGroup</th>
					            <th>CronExpression</th>
					            <th>JobClassName</th>
					            <th>Operation</th>
					        </tr>
				        </tfoot>
			        </table>
		        </div>
	        </div>
        </section>
        </div>
        </div>
        </div>
        <!--body wrapper end-->

        <%@ include file="../assets/footer.html" %>


    </div>
    <!-- main content end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="./../assets/js/jquery-1.10.2.min.js"></script>
<script src="./../assets/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="./../assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="./../assets/js/bootstrap.min.js"></script>
<script src="./../assets/js/modernizr.min.js"></script>
<script src="./../assets/js/jquery.nicescroll.js"></script>

<!--dynamic table-->
<script src="./../assets/js/advanced-datatable/js/jquery.dataTables.js"></script>
<script src="./../assets/js/data-tables/DT_bootstrap.js"></script>

<!--dynamic table initialization -->
<script src="./../assets/js/dynamic_table_init.js"></script>

<!--common scripts for all pages-->
<script src="./../assets/js/scripts.js"></script>

<script>
	$(function() {
        $("#pause").click(function() {
            var jobName = $(this).siblings("td.jobName").val();
            var jobGroup = $(this).siblings("td.jobGroup").val();
            alert(jobGroup)
        });
    });
</script>
</body>
</html>
