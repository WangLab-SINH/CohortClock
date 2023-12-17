<?php
$host = "rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com"; //Host
$username = "chiyuhao";  //User
$password = "Mll950223";      //Password
$database = "apis";// Database Name
 
//creating a new connection object using mysqli 
//echo "success112";
$conn = new mysqli($host, $username, $password, $database) or die("Database connection failed.") ;
?>