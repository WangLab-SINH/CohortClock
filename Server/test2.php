<?php
header("Content-type:text/html;charset=utf-8");
		echo "hello";
		$output =  exec("/root/anaconda3/bin/python echart2.py '143d785c051eaeb9'");
		echo $output;
		echo "end";



 ?>