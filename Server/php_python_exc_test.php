<?php
header("Content-type:text/html;charset=utf-8");
$a = "-a resnet152";
$b = "--pretrained";
$c = "--gpu 0";
echo exec("whoami");
exec("/root/anaconda3/bin/python /var/www/html/deeplearning_photo/cpu_php_input_byturn.py -a resnet152 --pretrained --gpu 0 2>/var/www/html/deeplearning_photo/error.txt",$python_result,$ret);
echo $ret;
print_r($python_result);
 ?> 