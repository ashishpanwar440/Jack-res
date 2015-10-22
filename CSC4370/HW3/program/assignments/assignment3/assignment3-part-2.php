<html>
<head>
    <meta charset="utf-8" />
    <title>Assignment 3</title>
</head>
<body>
    <?php
		$month = array('January', 'February', 'March', 'April',
				'May', 'June', 'July', 'August',
				'September', 'October', 'November', 'December');
		for ($i = 0; $i < sizeof($month); $i++) {
			echo $month[$i].'<br/>';
		}
		echo "<br/>Part 2<br/><br/>";
		sort($month);
		for ($i = 0; $i < sizeof($month); $i++) {
			echo $month[$i].'<br/>';
		}
		echo "<br/>Part 3<br/><br/>";
		$month = array('January', 'February', 'March', 'April',
				'May', 'June', 'July', 'August',
				'September', 'October', 'November', 'December');
		foreach ($month as $theMonth) {
			echo $theMonth.'<br/>';			
		}
		echo "<br/>Part 3-2<br/><br/>";		
		sort($month);
		foreach ($month as $theMonth) {
			echo $theMonth.'<br/>';			
		}
    ?>
</body>
</html>