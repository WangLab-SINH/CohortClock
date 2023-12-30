<?php  
header('Content-Type:text/html;charset=utf-8');/*ÉèÖÃphp±àÂëÎªutf-8*/   
#exec("/root/anaconda3/bin/python draw_weight_sleep.py '71ae4741a56800f9'");
exec("/root/anaconda3/bin/python draw_weight_sleep.py 'f2b9cbe508dfaa2d'");
exec("/root/anaconda3/bin/python echart2.py 'f2b9cbe508dfaa2d'");

?>