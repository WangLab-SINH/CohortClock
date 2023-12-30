<?php
header("Content-type:text/html;charset=utf-8");
echo "hello";
system("whoami");
$out = array();
$ret = array();
    exec("/root/anaconda3/bin/python test_echarts.py");

?>