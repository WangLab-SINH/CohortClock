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
    //�����Ŀ¼�����
    if(is_dir($path)){
        //ɨ��һ���ļ����ڵ������ļ��к��ļ�����������
        $p = scandir($path);
        foreach($p as $val){
            //�ų�Ŀ¼�е�.��..
            if($val !="." && $val !=".."){
                //�����Ŀ¼��ݹ���Ŀ¼����������
                if(is_dir($path.'/'.$val)){
                    //��Ŀ¼�в���ɾ���ļ��к��ļ�
                    deldir($path.'/'.$val.'/');
                    //Ŀ¼��պ�ɾ�����ļ���
                    @rmdir($path.'/'.$val.'/');
                }else{
                    //������ļ�ֱ��ɾ��
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