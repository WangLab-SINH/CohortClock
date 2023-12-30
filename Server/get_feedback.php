<?php
header("Content-type:text/html;charset=utf-8");
		function random_char($length = 8,$chars = null){
		  if( empty($chars) ){
		    $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
		  }
		  $chars = str_shuffle($chars);
		  $num = $length < strlen($chars) - 1 ? $length:str_len($chars) - 1;
		  return substr($chars,0,$num);
		}

		$host = "rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com"; //Host
		$username = "chiyuhao";  //User
		$password = "Mll950223";      //Password
		$database = "apis";// Database Name
		//creating a new connection object using mysqli 
		//$conn = new mysqli($host, $username, $password, $database) or die("Database connection failed.") ;
		$conn = mysqli_connect($host, $username, $password, $database);
		if (mysqli_connect_errno($conn)) 
		{ 
    	die("Á¬½Ó MySQL Ê§°Ü: " . mysqli_connect_error()); 
		}
		else
		{
			//echo "success";
		}
    $android = $_GET['android'];  
		$user_name = $_GET['user_name'];  
		$feedback = $_GET['feedback']; 
		$email = $_GET['email'];
		
		
		$sql = "insert into feedback_table (`android`,`user_name`, `feedback`, `email`)values('$android','$user_name','$feedback','$email')";
		$stmt = mysqli_prepare($conn,$sql);
		mysqli_stmt_execute($stmt);
	  
    echo "ok";



 ?>