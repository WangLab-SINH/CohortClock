<?php  
header('Content-Type:text/html;charset=utf-8');/*设置php编码为utf-8*/   
$host = "rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com"; //Host
$username = "chiyuhao";  //User
$password = "Mll950223";      //Password
$database = "apis";// Database Name
//creating a new connection object using mysqli 
//$conn = new mysqli($host, $username, $password, $database) or die("Database connection failed.") ;
$conn = mysqli_connect($host, $username, $password, $database);
if (mysqli_connect_errno($conn)) 
{ 
	die("连接 MySQL 失败: " . mysqli_connect_error()); 
}
else
{
	//echo "success";
}

require_once('Connection.php');
/* 
 * Following code will create a new product row 
 * All product details are read from HTTP GET Request 
 */  
   
// array for JSON response  
$response = array();  
   
// check for required fields  
#if (isset($_GET['sex']) && isset($_GET['weight']) && isset($_GET['height']) && isset($_GET['diabete'])&& isset($_GET['workdate'])&& isset($_GET['disease'])&& isset($_GET['androidid'])) 
#{  
   
    $sex = $_GET['sex'];  
    $weight = $_GET['weight'];  
    $height = $_GET['height'];  
    $diabete = $_GET['diabete']; 
    $workdate = $_GET['workdate'];	
    $disease = $_GET['disease'];
    $androidid = $_GET['androidid'];
	
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
			echo "yes";
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
		
		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true]
#	}else{
#		echo "no";
#		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true]
#	}

?>