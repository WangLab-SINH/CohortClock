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
		
		function deldir($path){
    //如果是目录则继续
    if(is_dir($path)){
        //扫描一个文件夹内的所有文件夹和文件并返回数组
        $p = scandir($path);
        foreach($p as $val){
            //排除目录中的.和..
            if($val !="." && $val !=".."){
                //如果是目录则递归子目录，继续操作
                if(is_dir($path.'/'.$val)){
                    //子目录中操作删除文件夹和文件
                    deldir($path.'/'.$val.'/');
                    //目录清空后删除空文件夹
                    @rmdir($path.'/'.$val.'/');
                }else{
                    //如果是文件直接删除
                    unlink($path.'/'.$val);
                }
            }
        }
    }
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
		 if(!move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
          //echo "An error has occurred moving the uploaded file.<BR>";
          //echo "Please ensure that if safe_mode is on that the " . "UID PHP is using matches the file.";
          exit;
     } else {
     	echo $imgname;
    }
		
		
		
		
$rs = ['code' => 0, 'msg' => 'ok', 'data' => true];
set_time_limit(0);
ob_end_clean();
ob_start();
echo $imgname;// 输出结果到前端
$size = ob_get_length();
ob_end_flush();
flush();
if (function_exists("fastcgi_finish_request")) { // yii或yaf默认不会立即输出，加上此句即可（前提是用的fpm）
    fastcgi_finish_request(); // 响应完成, 立即返回到前端,关闭连接
}
sleep(2);
ignore_user_abort(true);// 在关闭连接后，继续运行php脚本
set_time_limit(0);
		
		
		$current_time = date('Y-m-d h:i:s', time());
		if(file_exists("save_date.txt")){
			$myfile = fopen("save_date.txt", "r");
			$last_time = fread($myfile, filesize("save_date.txt"));
			fclose($myfile);
			$date1 = strtotime($last_time);
			$date2 = strtotime($current_time);
			$diff = $date2 - $date1;
			if($diff > 1800){
						$old_file_path = $file_path;
		$file_path = 'total_photo_byturn/';
		$new_file_path = $file_path . basename($imgname);
		copy($old_file_path, $new_file_path);
		$arr = scandir($file_path);
		$file_num = count($arr) - 2;
		if($file_num >= 1){
			$target_dir = 'temp_photo_byturn/train/0';
			$source_file_dir = 'total_photo_byturn';
			for($i = 2; $i < $file_num + 2; $i ++){
				
				$newFilePath = $target_dir.DIRECTORY_SEPARATOR.basename($arr[$i]);
				$sourcePath = $source_file_dir.DIRECTORY_SEPARATOR.basename($arr[$i]);
				rename($sourcePath,$newFilePath);
			}
			$a = "-a resnet152";
			$b = "--pretrained";
			$c = "--gpu 0";
			//$d = $imgname;
			$python_result = exec("/root/anaconda3/bin/python cpu_php_input_byturn.py {$a} {$b} {$c}");
			$new_result_array = explode(';', $python_result);
			for($x = 0; $x < count($new_result_array); $x++){
				$temp_result_array = explode(':', $new_result_array[$x]);
				$temp_imagename = $temp_result_array[0];
				$temp_imagefood = $temp_result_array[1];
				//echo $temp_imagename;
				//echo $temp_imagefood;
				$sql = "UPDATE photo_path SET photo_type = '$temp_imagefood' WHERE photo_name = '$temp_imagename'";
		    $stmt = mysqli_prepare($conn,$sql);
  			mysqli_stmt_execute($stmt);
			}
			
			
			
			$python_result2 = exec("/root/anaconda3/bin/python calorie_classification_cpu_byturn.py");
			$temp_array2 = explode(';', $python_result2);
			for($x = 0; $x < count($temp_array2); $x++){
				$temp_result_array = explode(':', $temp_array2[$x]);
				$temp_imagename = $temp_result_array[0];
				//echo $temp_imagename;
				$temp_imagecal = explode(',', $temp_result_array[1])[0];
				$temp_imagekind = explode(',', $temp_result_array[1])[1];
				//echo $temp_imagename;
				//echo $temp_imagecal;
				//echo $temp_imagekind;
				$sql1 = "UPDATE photo_path SET photo_cal = '$temp_imagecal' WHERE photo_name = '$temp_imagename'";
		    $stmt1 = mysqli_prepare($conn,$sql1);
  			mysqli_stmt_execute($stmt1);
  			
  			$sql2 = "UPDATE photo_path SET photo_kind = '$temp_imagekind' WHERE photo_name = '$temp_imagename'";
		    $stmt2 = mysqli_prepare($conn,$sql2);
  			mysqli_stmt_execute($stmt2);
			}
			
			$python_result3 = exec("/root/anaconda3/bin/python echart_to_phone_byturn.py");
			$temp_array3 = explode('<', $python_result3);
			for($x = 0; $x < count($temp_array3); $x++){
				$temp_result_array = explode(':', $temp_array3[$x]);
				$temp_imagename = $temp_result_array[0];
				$temp_result_array1 = explode(';', $temp_result_array[1]);
				$temp_workday = $temp_result_array1[0];
				$temp_weekend = $temp_result_array1[1];
				$sql2 = "UPDATE photo_path SET workday = '$temp_workday', weekend = '$temp_weekend' WHERE photo_name = '$temp_imagename'";
		    $stmt2 = mysqli_prepare($conn,$sql2);
  			mysqli_stmt_execute($stmt2);
			}
	
			deldir($target_dir);		
			$myfile = fopen("save_date.txt", "w");
			fwrite($myfile, $current_time);
			$last_time = fread($myfile, filesize("save_date.txt"));
			fclose($myfile);
		}
			}
		}else{
			$myfile = fopen("save_date.txt", "w");
			fwrite($myfile, $current_time);
			$last_time = fread($myfile, filesize("save_date.txt"));
			fclose($myfile);
		}
		

		$old_file_path = $file_path;
		$file_path = 'total_photo_byturn/';
		$new_file_path = $file_path . basename($imgname);
		copy($old_file_path, $new_file_path);
		$arr = scandir($file_path);
		$file_num = count($arr) - 2;
		if($file_num >= 5){
			$target_dir = 'temp_photo_byturn/train/0';
			$source_file_dir = 'total_photo_byturn';
			for($i = 2; $i < $file_num + 2; $i ++){
				
				$newFilePath = $target_dir.DIRECTORY_SEPARATOR.basename($arr[$i]);
				$sourcePath = $source_file_dir.DIRECTORY_SEPARATOR.basename($arr[$i]);
				rename($sourcePath,$newFilePath);
			}
			$a = "-a resnet152";
			$b = "--pretrained";
			$c = "--gpu 0";
			//$d = $imgname;
			$python_result = exec("/root/anaconda3/bin/python cpu_php_input_byturn.py {$a} {$b} {$c}");
			$new_result_array = explode(';', $python_result);
			for($x = 0; $x < count($new_result_array); $x++){
				$temp_result_array = explode(':', $new_result_array[$x]);
				$temp_imagename = $temp_result_array[0];
				$temp_imagefood = $temp_result_array[1];
				//echo $temp_imagename;
				//echo $temp_imagefood;
				$sql = "UPDATE photo_path SET photo_type = '$temp_imagefood' WHERE photo_name = '$temp_imagename'";
		    $stmt = mysqli_prepare($conn,$sql);
  			mysqli_stmt_execute($stmt);
			}
			
			
			
			$python_result2 = exec("/root/anaconda3/bin/python calorie_classification_cpu_byturn.py");
			$temp_array2 = explode(';', $python_result2);
			for($x = 0; $x < count($temp_array2); $x++){
				$temp_result_array = explode(':', $temp_array2[$x]);
				$temp_imagename = $temp_result_array[0];
				//echo $temp_imagename;
				$temp_imagecal = explode(',', $temp_result_array[1])[0];
				$temp_imagekind = explode(',', $temp_result_array[1])[1];
				//echo $temp_imagename;
				//echo $temp_imagecal;
				//echo $temp_imagekind;
				$sql1 = "UPDATE photo_path SET photo_cal = '$temp_imagecal' WHERE photo_name = '$temp_imagename'";
		    $stmt1 = mysqli_prepare($conn,$sql1);
  			mysqli_stmt_execute($stmt1);
  			
  			$sql2 = "UPDATE photo_path SET photo_kind = '$temp_imagekind' WHERE photo_name = '$temp_imagename'";
		    $stmt2 = mysqli_prepare($conn,$sql2);
  			mysqli_stmt_execute($stmt2);
			}
			
			$python_result3 = exec("/root/anaconda3/bin/python echart_to_phone_byturn.py");
			$temp_array3 = explode('<', $python_result3);
			for($x = 0; $x < count($temp_array3); $x++){
				$temp_result_array = explode(':', $temp_array3[$x]);
				$temp_imagename = $temp_result_array[0];
				$temp_result_array1 = explode(';', $temp_result_array[1]);
				$temp_workday = $temp_result_array1[0];
				$temp_weekend = $temp_result_array1[1];
				$sql2 = "UPDATE photo_path SET workday = '$temp_workday', weekend = '$temp_weekend' WHERE photo_name = '$temp_imagename'";
		    $stmt2 = mysqli_prepare($conn,$sql2);
  			mysqli_stmt_execute($stmt2);
			}
	
			deldir($target_dir);		
		}


          




 ?>