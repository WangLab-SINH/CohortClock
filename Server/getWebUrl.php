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
   

    #$androidid = $_GET['androidid'];

    


    
    
	
    $sql1 = "select `photo_name`, `photo_type`, `photo_cal`, `photo_kind` from photo_path";
if ($stmt = $conn->prepare($sql1))
{
    // �������ִ�е�SQL����
    $stmt->execute();
 
    // ִ��SQL���
    $stmt->store_result();

 
    // �����ѯ�ļ�¼����
    $stmt->bind_result($photo_url, $photo_type, $photo_cal, $photo_kind);

 
    // ����ѯ����󶨵�������
    $output_array = array();
    while ($stmt->fetch())
    {

    	{
    		if($photo_type == 'food')
    		{
    			
    			{
    				if(!in_array($photo_kind, $output_array))
    				{
    					printf ("%s;%s;%s;%s<", $photo_url, $photo_type, $photo_cal, $photo_kind);
    					array_push($output_array, $photo_kind);
    				}
    			}
    		}
    	}
    	

    	
        // ������MySQL����ȡ����
        #printf ("%s;%s;%s;%s<", $photo_url, $photo_type, $photo_cal, $photo_kind);     //��ʽ��������
    }
 
    $stmt->close();
    //�ͷ�mysqli_stmt����ռ�õ���Դ
}
 
$conn->close();
#	}else{
#		echo "no";
#		$rs = ['code' => 0, 'msg' => 'ok', 'data' => true]
#	}

?>