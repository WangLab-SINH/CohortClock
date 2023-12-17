<?php
header("Content-type:text/html;charset=utf-8");

          echo "The file has been successfully uploaded!";
          $a = "-a resnet152";
					$b = "--pretrained";
					$c = "--gpu 0";
					$imgname = "CkW87q3f200311.jpg";
					//$d = $imgname;
					exec("/root/anaconda3/bin/python cpu_php_input.py -a resnet152 --pretrained --gpu 0 CkW87q3f200311.jpg", $output);
					print_r($output)
					//echo "end";
					//exec('/root/anaconda3/bin/python /root/deeplearning/food/cpu_php_input.py -a resnet152 --pretrained --gpu 0 CkW87q3f200311.jpg' , $output);
					//var_dump($output);
    			




 ?>