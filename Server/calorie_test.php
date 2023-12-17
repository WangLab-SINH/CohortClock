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
   
    			echo "test";
    			$python_result2 = exec("/root/anaconda3/bin/python calorie_classification_cpu.py 'f2b9cbe508dfaa2d' '2oNpH65X200725.jpg'");
					$sql = "UPDATE photo_path SET photo_cal = '$python_result2'  WHERE user_name = 'f2b9cbe508dfaa2d' and user_time = '2020.7.25-16:51:33' and photo_name = '2oNpH65X200725.jpg'";
    			echo $python_result2;
    			





 ?>