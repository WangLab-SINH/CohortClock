<?php
if($_SERVER['REQUEST_METHOD'] =='POST'){
    $image = $_POST['image'];
    require_once('/deeplearning_photo/Connection.php');
    
    $sql = "INSERT INTO images (image) VALUES (?)";
    $stmt = mysqli_prepare($con,$sql);
    echo '{"error"}';
    
    mysqli_stmt_bind_param($stmt,"s",$image);
    mysqli_stmt_execute($stmt);
    
    $check = mysqli_stmt_affected_rows($stmt);
    if($check == 1){
        echo "Image Uploaded Successfully";
    }else{
        echo "Error Uploading Image";
    }
        mysqli_close($con);
} else {
	  echo "no request";
    echo "Error";
}
?>