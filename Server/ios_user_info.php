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
    $user_name = $_FILES['user_name']['name'];
    echo $user_name;
    $temp_array = explode('_', $user_name);
    $sex = $temp_array[0];
    $weight = $temp_array[1];
    $height = $temp_array[2];
    $diabete = $temp_array[3];
    $workdate = $temp_array[4];
    $disease = $temp_array[5];
		$androidid = $temp_array[6];
		echo "\n";
		echo $sex;
		echo $weight;
		echo $height;
		echo $diabete;
		echo $workdate;
		echo $disease;
		echo $androidid;
		$sql1 = "select user_id from user_info where user_id = '$androidid'";
    $stmt = mysqli_prepare($conn,$sql1);
		mysqli_stmt_execute($stmt);
		mysqli_stmt_bind_result($stmt, $name);
		$result = 0;
		while(mysqli_stmt_fetch($stmt))
		{
			//echo $name;
			$result = 1;
		}
		if($result == 1)
		{
			#echo "yes";
			$sql = "UPDATE  user_info SET  `sex` = '$sex', weight = '$weight', height = '$height', diabete = '$diabete', workdate = '$workdate', disease = '$disease' WHERE  user_id = '$androidid'";
		$stmt = mysqli_prepare($conn,$sql);
		mysqli_stmt_execute($stmt);
		}
		else
		{
			echo "yes";
		$sql = "insert into user_info(`user_id`,`sex`,`weight`,`height`,`diabete`,`workdate`,`disease`) values ('$androidid','$sex','$weight','$height','$diabete','$workdate','$disease')";
		$stmt = mysqli_prepare($conn,$sql);
		mysqli_stmt_execute($stmt);
		}
		
		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true];

    //echo "hello";

 ?>