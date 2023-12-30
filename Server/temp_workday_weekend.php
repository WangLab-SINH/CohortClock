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

		$current_time = date('Y-m-d h:i:s', time());
		$myfile = fopen("save_date.txt", "r");
		$last_time = fread($myfile, filesize("save_date.txt"));
		fclose($myfile);
		echo $current_time;
		echo $last_time;
		$date1 = strtotime($last_time);
		$date2 = strtotime($current_time);
		$diff = $date2 - $date1;
		echo $diff;
		if($diff < 1800){
			echo "no";
		}
		
			
?>