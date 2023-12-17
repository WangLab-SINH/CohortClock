<?php
header("Content-type:text/html;charset=utf-8");
		

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
    
    require_once('Connection.php');
    
    
    $usr_name = $_GET['nickname'];  
		$passward = $_GET['password'];  
		$androidid = $_GET['androidid']; 
    
		
		$sql1 = "select passward from user_table_final where user_name = '$usr_name'";
    $stmt = mysqli_prepare($conn,$sql1);
		mysqli_stmt_execute($stmt);
		mysqli_stmt_bind_result($stmt, $name);
		$result = 0;
		while(mysqli_stmt_fetch($stmt))
		{
			//echo $name;
			if($name == $passward)
			{
				$result = 1;
			}
		}
		if($result == 1)
		{
			echo "yes";
		}
		else
		{
			echo "no";
		
		}
		
		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true];

    //echo "hello";

 ?>