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
    $file_path = "uploads/";
    $usr_name = $_GET['androidid'];
    $usr_time = $_GET['time'];
    $food_cal = $_GET['food_type'];
    $food_kind = "0";
    $str_temp = explode('.', $ori_name);
    //print_r($str_temp);
    $imgname = "temp_food.jpg";

    $file_path = $file_path . basename($imgname);
		$sql = "insert into photo_path (`user_name`,`user_time`,`photo_path`, `photo_name`, `photo_type`, `photo_cal`, `photo_kind`)values('$usr_name','$usr_time','$file_path','$imgname','food','$food_cal','$food_kind')";
		$stmt = mysqli_prepare($conn,$sql);
		mysqli_stmt_execute($stmt);
    			

 ?>