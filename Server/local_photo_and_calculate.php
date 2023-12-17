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
    $file_path = "uploads/";
    $image = $_FILES['uploaded_file'];
    require_once('Connection.php');
    $ori_name = $_FILES['uploaded_file']['name'];
    $temp_string = $_FILES['usr_name']['name'];
    $temp_array = explode('_', $temp_string);
    $usr_name = $temp_array[0];
    $usr_time = $temp_array[1];
    $str_temp = explode('.', $ori_name);
    //print_r($str_temp);
    $imgname = random_char(8).date('ymd').'.'.$str_temp[1];

    $file_path = $file_path . basename($imgname);
    //echo "imagename: " . $imgname;
    //echo "file_path： ". $file_path;
		$sql = "insert into photo_path (`user_name`,`user_time`,`photo_path`, `photo_name`, `photo_type`)values('$usr_name','$usr_time','$file_path','$imgname','food')";
		$stmt = mysqli_prepare($conn,$sql);
		mysqli_stmt_execute($stmt);
	  //exec("/root/anaconda3/bin/python dotplot_new.py {$usr_name}");
	  //exec("/root/anaconda3/bin/python test_echarts.py");
	  //exec("/root/anaconda3/bin/python heatmap.py {$usr_name}");
		//exec("/root/anaconda3/bin/python echart2.py {$usr_name}");
		//$rs = ['code' => 0, 'msg' => 'ok', 'data' => true];
//set_time_limit(0);
//ob_end_clean();
//ob_start();
//$size = ob_get_length();
//header("Content-Length: $size");
//ob_end_flush();
//flush();
//if (function_exists("fastcgi_finish_request")) { // （前提是用的fpm）
//    fastcgi_finish_request(); // 响应完成, 立即返回到前端,关闭连接
//}
//ignore_user_abort(true);// 在关闭连接后，继续运行php脚本
//set_time_limit(0);

		
		
    //$file = "uploads/" .$_FILES['uploaded_file']['name'];
    if(!move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
          //echo "An error has occurred moving the uploaded file.<BR>";
          //echo "Please ensure that if safe_mode is on that the " . "UID PHP is using matches the file.";
          exit;
     } else {
          //echo "The file has been successfully uploaded!";
          $a = "-a resnet152";
					$b = "--pretrained";
					$c = "--gpu 0";
					//$d = $imgname;
					$python_result = exec("/root/anaconda3/bin/python /root/deeplearning/food/cpu_php_input.py {$usr_name} {$a} {$b} {$c} {$imgname}");
					echo $python_result;
          //$sql = "insert into photo_path (`user_name`,`user_time`,`photo_path`, `photo_name`, `photo_type`)values('$usr_name','$usr_time','$file_path','$imgname','$python_result')";
          $sql = "UPDATE photo_path SET photo_type = '$python_result'  WHERE user_name = '$usr_name' and user_time = '$usr_time' and photo_name = '$imgname'";
			    $stmt = mysqli_prepare($conn,$sql);
    			mysqli_stmt_execute($stmt);
    			$python_result1 = exec("/root/anaconda3/bin/python echart_to_phone.py {$usr_name}");
    			echo $python_result1;
    			echo ";";
    			echo $imgname;
    			echo ";";
    			
    			$python_result2 = exec("/root/anaconda3/bin/python calorie_classification_cpu.py {$usr_name} {$imgname}");
    			$temp_array2 = explode(';', $python_result2);
    			$photo_cal = $temp_array2[0];
    			$photo_kind = $temp_array2[1];
    			$sql = "UPDATE photo_path SET photo_cal = '$photo_cal'  WHERE user_name = '$usr_name' and user_time = '$usr_time' and photo_name = '$imgname'";
    			$stmt = mysqli_prepare($conn,$sql);
    			mysqli_stmt_execute($stmt);
    			$sql = "UPDATE photo_path SET photo_kind = '$photo_kind'  WHERE user_name = '$usr_name' and user_time = '$usr_time' and photo_name = '$imgname'";
    			$stmt = mysqli_prepare($conn,$sql);
    			mysqli_stmt_execute($stmt);
    			
    			echo $python_result2;
    			echo ";";
    			
    			$python_result3 = exec("/root/anaconda3/bin/python get_diet_type_cpu.py {$usr_name}");
    			echo $python_result3;
    			
     }
    //move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path);
    //chmod($file,0777);
    //echo "name".$_FILES['uploaded_file']['name']."<br/>";
		//echo "type".$_FILES['uploaded_file']['type']."<br/>";
		//echo "size".$_FILES['uploaded_file']['size']."<br/>";



 ?>