<?php  
header('Content-Type:text/html;charset=utf-8');/*����php����Ϊutf-8*/   
$host = "rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com"; //Host
$username = "chiyuhao";  //User
$password = "Mll950223";      //Password
$database = "apis";// Database Name
//creating a new connection object using mysqli 
//$conn = new mysqli($host, $username, $password, $database) or die("Database connection failed.") ;
$conn = mysqli_connect($host, $username, $password, $database);
if (mysqli_connect_errno($conn)) 
{ 
	die("���� MySQL ʧ��: " . mysqli_connect_error()); 
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
if (function_exists("fastcgi_finish_request")) { // yii��yafĬ�ϲ���������������ϴ˾伴�ɣ�ǰ�����õ�fpm��
    fastcgi_finish_request(); // ��Ӧ���, �������ص�ǰ��,�ر�����
}
sleep(2);
ignore_user_abort(true);// �ڹر����Ӻ󣬼�������php�ű�
set_time_limit(0);
$sql = "select `data_time`, `index_value` from body_index where `phone_id` = '$phone_id' and `index_name` ='$index_name'";
		if ($stmt = $conn->prepare($sql))
{
    // �������ִ�е�SQL����
    $stmt->execute();
 
    // ִ��SQL���
    $stmt->store_result();

 
    // �����ѯ�ļ�¼����
    $stmt->bind_result($data_time, $index_value);
	
    $stmt->close();
    //�ͷ�mysqli_stmt����ռ�õ���Դ
}




?>