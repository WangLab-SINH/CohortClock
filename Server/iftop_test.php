<?php
header("Content-type:text/html;charset=utf-8");

	$result1 = shell_exec('cat /sys/class/net/eth0/statistics/rx_bytes 2>&1');
	sleep(1);
	$result2 = shell_exec('cat /sys/class/net/eth0/statistics/rx_bytes 2>&1');

	echo (int)$result2 - (int)$result1;

 ?>