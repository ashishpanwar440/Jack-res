<html>
<head>
    <meta charset="utf-8" />
    <title>Assignment 3</title>
</head>
<body>
	<style>
		td {
			border: 1px solid;
		}
	</style>
    <?php
		function printArrayIntoTable($daArray) {
			echo '<table style="width: 300px; height: 300px; border: 1px solid">';
				foreach ($daArray as $daRow) {
				echo '<tr>';
				foreach ($daRow as $daCell) {
					echo '<td>'.$daCell.'</td>';
				}
				echo '</tr>';
			}
			echo '</table>';
		}
		$daArray = array(
			array('Chama Gaucha', 40.50),
			array('Aviva by Kameel', 15.00),
			array('Bone’s Restaurant', 65.00),
			array('Umi Sushi Buckhead', 40.50),
			array('Fandangles', 30.00),
			array('Capital Grille', 60.50),
			array('Canoe', 35.50),
			array('One Flew South', 21.00),
			array('Fox Bros. BBQ', 15.00),
			array('South City Kitchen Midtown', 29.00)
		);
		printArrayIntoTable($daArray);
		echo '<br/>After price sort:<br/><br/>';
		$daPrice = array();
		for ($i = 0; $i < sizeof($daArray); $i++) {
			$daPrice[$i] = $daArray[$i][1];
		}
		array_multisort($daPrice, $daArray);
		printArrayIntoTable($daArray);
		echo '<br/>After name sort:<br/><br/>';
		$daNames = array();
		for ($i = 0; $i < sizeof($daArray); $i++) {
			$daNames[$i] = $daArray[$i][0];
		}
		array_multisort($daNames, $daArray);
		printArrayIntoTable($daArray);
    ?>
</body>
</html>