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

   
$weight = $_GET['weight'];  
$start_time = $_GET['start_time'];  
$end_time = $_GET['end_time']; 
$androidid = $_GET['androidid'];
$upload_time = $_GET['upload_time'];


$sql = "insert into user_weight_sleep(`user_id`,`weight`,`start_time`,`end_time`,`upload_time`) values ('$androidid','$weight','$start_time','$end_time','$upload_time')";
$stmt = mysqli_prepare($conn,$sql);
mysqli_stmt_execute($stmt);
//exec("/root/anaconda3/bin/python draw_weight_sleep.py {$androidid}");
echo "yes";

$rs = ['code' => 0, 'msg' => 'ok', 'data' => true]


?>