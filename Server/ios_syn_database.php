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
   

    $androidid_input = $_GET['androidid'];

    
    $androidid_query = $androidid_input;
    
    $sql2 = "select `user_name`,`androidid` from user_table_final";
    if ($stmt = $conn->prepare($sql2))
{
    // �������ִ�е�SQL����
    $stmt->execute();
 
    // ִ��SQL���
    $stmt->store_result();

 
    // �����ѯ�ļ�¼����
    $stmt->bind_result($user_name, $androidid);

 
    // ����ѯ����󶨵�������
    while ($stmt->fetch())
    {
    	//echo strval($user_name);
    	if(strcmp(strval($user_name), strval($androidid_input))==0)
    	{
    		$androidid_query = $androidid;
    		//echo $androidid_query;
    		break;
    	}
    	
    	
       
    }
 		
    $stmt->close();
    //�ͷ�mysqli_stmt����ռ�õ���Դ
}

//echo $androidid_query;
$sql1 = "select `user_name`,`user_time`,`photo_name`, `photo_type`, `photo_cal`, `photo_kind`,  `workday`, `weekend` from photo_path where user_name = '$androidid_query'";
if ($stmt1 = $conn->prepare($sql1))
{
    // �������ִ�е�SQL����
    $stmt1->execute();
 
    // ִ��SQL���
    $stmt1->store_result();

 
    // �����ѯ�ļ�¼����
    $stmt1->bind_result($user_name, $user_time, $photo_url, $photo_type, $photo_cal, $photo_kind, $workday, $weekend);
 
    // ����ѯ����󶨵�������
    while ($stmt1->fetch())
    {
        // ������MySQL����ȡ����
        printf ("%s;%s;%s;%s;%s;%s;%s;%s<", $user_name, $user_time, $photo_url, $photo_type, $photo_cal, $photo_kind, $workday, $weekend);     //��ʽ��������
    }
 
    $stmt1->close();
    //�ͷ�mysqli_stmt����ռ�õ���Դ
}

    

	
   
 
$conn->close();
#	}else{
#		echo "no";
#		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true]
#	}

?>