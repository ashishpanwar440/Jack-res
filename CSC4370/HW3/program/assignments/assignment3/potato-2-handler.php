<html>
<head>
    <meta charset="utf-8" />
    <title>Puhtaytoe</title>
</head>
<body>
	<?php
		$yearOld = date('Y') - $_POST['birthYear'];
		echo 'Greeting '.$_POST['titul'].' '.$_POST['lname'].
			'.<br/> I believe your hood '.$_POST['address'].
			'<br/> has been tampered with. What would a '
			.$yearOld.'-year-old like you do '.$_POST['fname'].'?';
		
	?>
</body>
</html>