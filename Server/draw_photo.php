<?php
header("Content-type:text/html;charset=utf-8");
		function random_char($length = 8,$chars = null){
		  if( empty($chars) ){
		    $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
		  }
		  $chars = str_shuffle($chars);
		  $num = $length < strlen($chars) - 1 ? $length:str_len($chars) - 1;
		  return substr($chars,0,$num);
		}

		$host = "rm-8vb33gt140m5236h743480.mysql.zhangbei.rds.aliyuncs.com"; //Host
		$username = "chiyuhao";  //User
		$password = "Mll950223";      //Password
		$database = "apis";// Database Name
		 
		
		$conn = mysqli_connect($host, $username, $password, $database);
		if (mysqli_connect_errno($conn)) 
		{ 
    	die("���� MySQL ʧ��: " . mysqli_connect_error()); 
		}
		else
		{
			echo "success";
		}
    $file_path = "uploads/";
    $image = $_FILES['uploaded_file'];
    require_once('Connection.php');
    $ori_name = $_FILES['uploaded_file']['name'];
    $temp_string = $_FILES['usr_name']['name'];
    $temp_array = explode('_', $temp_string);
    $usr_name = $temp_array[0];
    $usr_time = $temp_array[1];
    $str_temp = explode('.', $ori_name);
    $imgname = random_char(8).date('ymd').'.'.$str_temp[1];

    $file_path = $file_path . basename($imgname);
		$sql = "insert into photo_path (`user_name`,`user_time`,`photo_path`, `photo_name`, `photo_type`)values('$usr_name','$usr_time','$file_path','$imgname','food')";
		$stmt = mysqli_prepare($conn,$sql);
    mysqli_stmt_execute($stmt);

		
    $file = "uploads/" .$_FILES['uploaded_file']['name'];
    
    
    exec("/root/anaconda3/bin/python /root/deeplearning/food/dotplot.py {$usr_name}");
    
    
		if(!move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
          echo "An error has occurred moving the uploaded file.<BR>";
          echo "Please ensure that if safe_mode is on that the " . "UID PHP is using matches the file.";
          exit;
     } else {
          echo "The file has been successfully uploaded!";
          $a = "-a resnet152";
					$b = "--pretrained";
					$c = "--gpu 0";
					//$d = $imgname;
					$python_result = exec("/root/anaconda3/bin/python /root/deeplearning/food/cpu_php_input.py {$a} {$b} {$c} {$imgname}");
					echo $python_result;

          $sql = "insert into photo_path (`user_name`,`user_time`,`photo_path`, `photo_name`, `photo_type`)values('$usr_name','$usr_time','$file_path','$imgname','$python_result')";
			    $stmt = mysqli_prepare($conn,$sql);
    			mysqli_stmt_execute($stmt);
    			
     }
    //move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path);
    chmod($file,0777);
    echo "name".$_FILES['uploaded_file']['name']."<br/>";
		echo "type".$_FILES['uploaded_file']['type']."<br/>";
		echo "size".$_FILES['uploaded_file']['size']."<br/>";



 ?>