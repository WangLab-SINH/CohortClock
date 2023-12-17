<?php  
header('Content-Type:text/html;charset=utf-8');/*ÉèÖÃphp±àÂëÎªutf-8*/
/* 
 * Following code will list all the items 
 */  
   
// array for JSON response  
exec("/root/anaconda3/bin/python json_input.py 'f2b9cbe508dfaa2d' 2>&1", $python_result, $res);

//foreach($python_result as $value)
//{
//	echo $value;
//}
//echo $python_result;
$response = array();  
   
// include db connect class  
//require_once __DIR__ . '/db_connect.php';  
   
// connecting to db  
//$db = new DB_CONNECT();  
   
// get all items from items table  
//$result = mysql_query("SELECT *FROM items WHERE type='1'") or die(mysql_error());  
// check for empty result  

    // looping through all results  
    // items node  
    $response["items"] = array();  
   

        // temp user array  
        $items = array();  
        $items["what"] = ["what1","what2","what3"];  
        $items["when"] = "when1";  
        $items["where"] = "where1";  
        $items["detail"] = "detail1";
        $items["posttime"] = "posttime1";		
        $items["contact"] = "contact1";	
        $items["week"] = $python_result;
        $items["weekend"] = $python_result;

        // push single items into final response array  
        array_push($response["items"], $items);  
     
    // success  
    $response["success"] = 1;  
   
    // echoing JSON response  
    echo json_encode($response,JSON_UNESCAPED_UNICODE); 

   
    // echo JSON  
    //echo json_encode($response,JSON_UNESCAPED_UNICODE);  

?>