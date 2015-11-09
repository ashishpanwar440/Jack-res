<?php

if(!empty($_POST['data'])){
$file = fopen("score.xml", 'w');//creates new file
$data = $_POST['data'];
fwrite($file, $data);
fclose($file);
}

?>
