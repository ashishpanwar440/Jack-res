<html>
<head>
    <meta charset="utf-8" />
    <title>Assignment 3</title>
</head>
<body>
    <?php
		function charlieAteItOrNah() {
			return (rand(0, 100) >= 50); //One liner FTW!
		}
		if (charlieAteItOrNah()) {
			echo "Charlie ate my lunch!";
		} else {
			echo "Charlie did not eat my lunch";
		}
    ?>
</body>
</html>