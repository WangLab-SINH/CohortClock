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
   
    $user_name = $_GET['user_name'];  
    $user_group = $_GET['user_group'];  
    $phone_id = $_GET['phone_id'];  
    $upload_time = $_GET['upload_time']; 
    $data_time = $_GET['data_time'];	
    $index_value = $_GET['index_value'];
    $index_name = $_GET['index_name'];
	


		$sql = "insert into body_index(`user_name`,`user_group`,`phone_id`,`upload_time`,`data_time`,`index_value`,`index_name`) values ('$user_name','$user_group','$phone_id','$upload_time','$data_time','$index_value','$index_name')";
		$stmt = mysqli_prepare($conn,$sql);
		mysqli_stmt_execute($stmt);

		
		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true];
#	}else{
#		echo "no";
#		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true]
#	}
if (function_exists("fastcgi_finish_request")) { // yii或yaf默认不会立即输出，加上此句即可（前提是用的fpm）
    fastcgi_finish_request(); // 响应完成, 立即返回到前端,关闭连接
}
sleep(2);
ignore_user_abort(true);// 在关闭连接后，继续运行php脚本
set_time_limit(0);
$sql = "select `data_time`, `index_value` from body_index where `phone_id` = '$phone_id' and `index_name` ='$index_name'";
		if ($stmt = $conn->prepare($sql))
{
    // 处理打算执行的SQL命令
    $stmt->execute();
 
    // 执行SQL语句
    $stmt->store_result();

 
    // 输出查询的记录个数
    $stmt->bind_result($data_time, $index_value);
	
    $stmt->close();
    //释放mysqli_stmt对象占用的资源
}




?>