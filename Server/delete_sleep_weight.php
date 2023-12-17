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
   

    $androidid = $_GET['androidid'];
    $user_time = $_GET['user_time'];
    $sql1 = "delete from user_weight_sleep where user_id = '$androidid' and upload_time = '$user_time'";
		//$sql1 = "delete from photo_path where user_name = '$androidid' and user_time = '$user_time'";
		$stmt = mysqli_prepare($conn,$sql1);
		mysqli_stmt_execute($stmt);
		echo "ok";
   

?>