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
	
    $sql1 = "select `user_name`,`user_time`,`photo_name`, `photo_type`, `photo_cal`, `photo_kind`,  `workday`, `weekend` from photo_path_new where user_name = '$androidid'";
if ($stmt = $conn->prepare($sql1))
{
    // 处理打算执行的SQL命令
    $stmt->execute();
 
    // 执行SQL语句
    $stmt->store_result();

 
    // 输出查询的记录个数
    $stmt->bind_result($user_name, $user_time, $photo_url, $photo_type, $photo_cal, $photo_kind, $workday, $weekend);
    echo $user_time;
 
    // 当查询结果绑定到变量中
    while ($stmt->fetch())
    {
        // 逐条从MySQL服务取数据
        printf ("%s;%s;%s;%s;%s;%s;%s;%s<", $user_name, $user_time, $photo_url, $photo_type, $photo_cal, $photo_kind, $workday, $weekend);     //格式化结果输出
    }
 
    $stmt->close();
    //释放mysqli_stmt对象占用的资源
}
 
$conn->close();
#	}else{
#		echo "no";
#		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true]
#	}

?>