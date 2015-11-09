var DIMENSION = 4;
var NUM_OF_SCRAMBLES = 60;
var SECRET_CODE = "dudeigiveup";
var secretIndex = 0;
var divMenu = document.getElementById("divMenu");
var divGame = document.getElementById("divGame");
var divWin1 = document.getElementById("divWin1");
var divWin2 = document.getElementById("divWin2");
var divPuzzle = document.getElementById("divPuzzle");
var arrow = document.getElementById("arrow");
var theStyleSheet = document.getElementById("theStyleSheet");
var audioOpenning = new Audio("beginning.mp3");
var audioGame = new Audio("game.mp3");
var audioWin1 = new Audio("win-1.mp3");
var audioWin2 = new Audio("win-2.mp3");
var audioHydraulic = new Audio("hydraulic.mp3");
var audioExplosion = new Audio("explosion.mp3");
var audioSwoosh = new Audio("swoosh.mp3");
var tiles = [];
var grid = [];
var prevSwap = -2;
var mode = 0;
var vX = [];
var vY = [];
var ro = [];
var winAnimationTimer;
var WINNING_STRING = "CONGRATULATIONS! YOU HAVE FINALLY FINISHED THE PUZZLE. "
		+ "PLANKTON HAS FINALLY BEEN ABLE TO STEAL THE KRABBY PATTY SECRET "
		+ "FORMULA HE HAS BEEN DREAMING OF. HE CAN INITIATE THE NEXT STEP "
		+ "ON HIS ROUTE TO DOMINATE BIKINI BOTTOM.\n\n P.S. IT'S BEEN A GOOD "
		+ "COLLABORATION HUMAN. IT'S TIME FOR YOU TO BOW DOWN BEFORE ME! "
		+ "\n*SQUEAKY EVIL LAUGHS*";
var winningStringInterval = 100;
audioOpenning.play();
audioOpenning.loop = true;
audioGame.loop = true;
audioWin2.loop = true;
var txt;
var nMoves = 0;
var score = 0;
var name= "";
var endTime = 0;
var timer;
var outp = 0;

function play() {
	document.getElementById("playButton").onclick = "";
	setAttributePrefix(divGame, "animation", "moveDown 1.3s ease-out forwards");
	setAttributePrefix(divMenu, "animation", "moveUp 1.3s forwards")
	setTimeout(newGame, 2600);
	setTimeout(function() {
		divGame.style.display = "block";
		audioHydraulic.currentTime = 0;
		audioHydraulic.play();
	}, 1300);
	audioOpenning.pause();
	audioHydraulic.play();
}

function getIndexOnGrid(element) {
	return parseInt(element.className.substring(
			element.className.indexOf("cell_") + "cell_".length));
}

function timer() {
	timer = 0;
    inter = setInterval(function () {
		if (mode == 1) {
			timer++;
			outp = padZeroes(parseInt(timer / 60))
					+ ":" + padZeroes(timer % 60);
			document.getElementById("timer").innerHTML = outp;
		}
    }, 1000);
}

function padZeroes(num) {
	return "00".substring(("" + num).length) + num;
}
function tileClick(event) {
	if (mode == 1) {
		arrow.style.display = "none";
		var indexOnGrid = getIndexOnGrid(event);
		var temp = grid[indexOnGrid];
		if (indexOnGrid % DIMENSION < DIMENSION - 1) {
			if (checkAndSwap(indexOnGrid, indexOnGrid + 1)) {
				revalidate();
			}
		}
		if (indexOnGrid % DIMENSION > 0) {
			if (checkAndSwap(indexOnGrid, indexOnGrid - 1)) {
				revalidate();
			}
		}
		if (parseInt(indexOnGrid / DIMENSION) < DIMENSION - 1) {
			if (checkAndSwap(indexOnGrid, indexOnGrid + DIMENSION)) {
				revalidate();
			}
		}
		if (parseInt(indexOnGrid / DIMENSION) > 0) {
			if (checkAndSwap(indexOnGrid, indexOnGrid - DIMENSION)) {
				revalidate();
			}
		}
		checkWin();
	}
}

function checkAndSwap (index1, index2) {
	if (grid[index2] == -1) {
		var temp = grid[index1];
		grid[index1] = grid[index2];
		grid[index2] = temp;
		return true;
	}
	return false;
}

function checkWin() {
	for (var i = 0; i < DIMENSION * DIMENSION - 1; i++) {
		if (grid[i] != i) return;
	}
	setTimeout(win, 600);
}

function win() {
	mode = -1;
	for (var i = 0; i < tiles.length; i++) {
		var centerX = divPuzzle.offsetWidth / 2;
		var centerY = divPuzzle.offsetHeight / 2;
		var tileX = tiles[i].offsetLeft + tiles[i].offsetWidth / 2;
		var tileY = tiles[i].offsetTop + tiles[i].offsetHeight / 2;
		var angle = Math.atan2(tileY - centerY, tileX - centerX);
		var distance = Math.sqrt((tileY - centerY) * (tileY - centerY)
				+ (tileX - centerX) * (tileX - centerX));
		vY[i] = Math.sin(angle) * 36 + Math.random() * 10 - 5;
		vX[i] = Math.cos(angle) * 46 + Math.random() * 10 - 5;
		ro[i] = 0;
		setAttributePrefix(tiles[i], "transition", "");
	}
	winAnimationTimer = setInterval(winAnimation, 25, 0);
	setAttributePrefix(divWin1, "transition", "all .8s");
	setAttributePrefix(divWin2, "transition", "all 2s");
	audioExplosion.play();
	setTimeout(function() {
		setAttributePrefix(divGame, "transition", "all 1.4s");
		divGame.style.opacity = 0;
		audioGame.pause();
		setTimeout(function() {
			divGame.style.display = "none";
			divWin1.style.opacity = 1;
			setAttributePrefix(divWin1, "transform", "translateX(-50%) scale(1, 1)");
			audioWin1.play();
			getFileFromServer("score.xml");
			setTimeout(function() {
				divWin1.style.opacity = 0;
				setTimeout(function() {
					divWin2.style.opacity = 1;
					audioWin2.play();
					setTimeout(function() {
						var name = prompt("Congratulations! Please enter your name to be included on the high score board!");
						txt = txt.replace("<players>",'');
						txt = txt.replace("</players>",'');
						txt = txt.trim();
						score = parseInt(100000 / (endTime + nMoves + 1));
						var s ="<players>\n\n" + txt + "\n\n<player>\n<time>" + outp + "</time>\n<moves>" +nMoves + "</moves>\n<name>" + name + "</name>\n<score>" + score + "</score>\n</player>\n" + "\n</players>";
						sendFile(s);
						winTextAnimation(0);
					}, 2000);
				}, 1400);
			}, 5600);
		}, 2000);
	}, 4000);
}

function winTextAnimation(textIndex) {
	if (textIndex <= WINNING_STRING.length) {
		document.getElementById("winText").innerText = 
				WINNING_STRING.substring(0, textIndex);
		setTimeout(function() {winTextAnimation(textIndex + 1)}, winningStringInterval);
	}
}

function fastForwardString() {
	winningStringInterval = 20;
}

function winAnimation() {
	for (var i = 0; i < tiles.length; i++) {
		var realLeft = tiles[i].offsetLeft + divPuzzle.offsetLeft;
		var realTop = tiles[i].offsetTop + divPuzzle.offsetTop;
		ro[i] += vX[i] * .8;
		if (Math.abs(vY[i]) > 5
			|| realTop + tiles[i].offsetHeight < divGame.offsetHeight - 100) {
			tiles[i].style.left = tiles[i].offsetLeft + vX[i] + "px";
			tiles[i].style.top = tiles[i].offsetTop + vY[i] + "px";
			setAttributePrefix(tiles[i], "transform", "rotate(" + ro[i] +"deg)");
		}
		if (realTop + tiles[i].offsetHeight >= divGame.offsetHeight - 100
			&& vY[i] > 0 
			|| realTop  <= 60 
			&& vY[i] < 0) {
			vY[i] = -vY[i] * .60;
			vX[i] = vX[i] * .60;
			if (Math.abs(vY[i]) < 5) vY[i] = 0;
		}
		if (realLeft + tiles[i].offsetWidth >= divGame.offsetWidth - 50
			&& vX[i] > 0 
			|| realLeft <= 60 
			&& vX[i] < 0) {
			vX[i] = -vX[i] * .60;
		}
		if (realTop + tiles[i].offsetHeight <= divGame.offsetHeight - 100) {
			vY[i] += 1;
		}
	}
}

function scrambleItUp() {
	scramble();
	revalidate();
	if (++scrambleCount >= NUM_OF_SCRAMBLES) {
		for (var i = 0; i < tiles.length; i++) {
			setAttributePrefix(tiles[i], "transition", "all .3s");
		}
		mode = 1;
		timer();
	} else {
		setTimeout(scrambleItUp, nextCountDown(scrambleCount));
	}
}

function nextCountDown(progress) {
	var x = progress * 3 / NUM_OF_SCRAMBLES;
	x -= 2;
	var y = Math.pow(Math.E, -(x*x));
	var nextCount = (1.04 - y) * 200;
	for (var i = 0; i < tiles.length; i++) {
		setAttributePrefix(tiles[i], "transition", "all " + nextCount / 1000 + "s");
	}
	return nextCount;
}

function scramble() {
	for (var i = 0; i < grid.length; i++) {
		if (grid[i] == -1){
			var toSwap = -1;
			while (toSwap == -1) {
				toSwap = parseInt(Math.random() * 3.99);
				if (toSwap == 0 && i % DIMENSION + 1 >= DIMENSION) toSwap = -1;
				if (toSwap == 1 && parseInt((i + DIMENSION) / DIMENSION) >= DIMENSION) toSwap = -1;
				if (toSwap == 2 && i % DIMENSION - 1 < 0) toSwap = -1;
				if (toSwap == 3 && i - DIMENSION < 0) toSwap = -1;
				if (toSwap == prevSwap) toSwap = -1;
			}
			if (toSwap == 0) {
				checkAndSwap(i + 1, i);
				prevSwap = 2;
			}
			if (toSwap == 1) {
				checkAndSwap(i + DIMENSION, i);
				prevSwap = 3;
			}
			if (toSwap == 2) {
				checkAndSwap(i - 1, i);
				prevSwap = 0;
			}
			if (toSwap == 3) {
				checkAndSwap(i - DIMENSION, i);
				prevSwap = 1;
			}
		}
	}
}

function newGame() {
	audioGame.play();
	var k = 0;
	theStyleSheet.innerHTML = "";
	for (var i = 0; i < DIMENSION * DIMENSION; i++) {
		var x = parseInt(i % DIMENSION) * 100;
		var y = parseInt(i / DIMENSION) * 100;
		theStyleSheet.innerHTML += ".cell_" + i
		+ "{left: " + x + "px; top: " + y + "px}";
	}
	for (var i = 0; i < DIMENSION; i++) {
		for (var j = 0; j < DIMENSION; j++) {
			if (i != DIMENSION - 1 || j != DIMENSION - 1) {
				grid[k] = k;
				var cell = document.createElement("td");
				tiles[k] = document.createElement("div");
				tiles[k].className = "tile";
				var tinyDiv = document.createElement("div");
				tinyDiv.innerHTML = k + 1;
				tinyDiv.className = "tileText";
				tiles[k].className += " cell_" + k ;
				tiles[k].style.cursor = "pointer";
				tiles[k].id = k;
				tiles[k].onclick = function() {tileClick(this)};
				tiles[k].onmouseover = function() {tileHover(this)};
				tiles[k].onmouseout = function() {tileOut(this)};
				var posX = -j * 100;
				var posY = -i * 100;
				tiles[k].style.backgroundPosition = posX + "px " + posY + "px";
				tiles[k].appendChild(tinyDiv);
				divPuzzle.appendChild(tiles[k]);
				k++;
			}
		}
	}
	grid[DIMENSION * DIMENSION - 1] = -1;
	for (var i = 0; i < tiles.length; i++) {
		var centerX = divPuzzle.offsetWidth / 2 - tiles[i].offsetWidth / 2;
		var centerY = divPuzzle.offsetHeight / 2 - tiles[i].offsetHeight / 2;
		var deltaX = centerX - tiles[i].offsetLeft;
		var deltaY = centerY - tiles[i].offsetTop;
		tiles[i].style.transform = "translate(" + deltaX + "px, " + deltaY + "px) scale(0, 0)";
		setAttributePrefix(tiles[i], "transform",
			"translate(" + deltaX + "px, " + deltaY + "px) scale(0, 0)");
		tiles[i].style.opacity = 0;
	}
	initTiles(0);
}

function tileHover(element) {
	if (mode == 1) {
		var index = getIndexOnGrid(element);
		var direction = movablePiece(index);
		if (direction >= 0) {
			element.className = "movablepiece " + element.className;
			arrow.style.display = "block";
			var arrowRotation = direction * 90 + "deg";
			setAttributePrefix(arrow, "transform", "translate(-50%, -50%) rotate(" + arrowRotation + ")");
			if (direction == 0) {
				arrow.style.left = element.offsetLeft + element.offsetWidth + "px";
				arrow.style.top = element.offsetTop + element.offsetHeight / 2 + "px";
			} else if (direction == 1) {
				arrow.style.left = element.offsetLeft + element.offsetWidth / 2 + "px";
				arrow.style.top = element.offsetTop + element.offsetHeight + "px";
			} else if (direction == 2) {
				arrow.style.left = element.offsetLeft + "px";
				arrow.style.top = element.offsetTop + element.offsetHeight / 2 + "px";
			} else if (direction == 3) {
				arrow.style.left = element.offsetLeft + element.offsetWidth / 2 + "px";
				arrow.style.top = element.offsetTop + "px";
			}
		}
	}
}

function tileOut(element) {
	if (element.className.indexOf("movablepiece ") >= 0) {
		element.className = element.className.substring("movablepiece ".length);
		arrow.style.display = "none";
	}
}

function revalidate() {
	audioSwoosh.currentTime = 0;
	audioSwoosh.play();
	for (var i = 0; i < grid.length; i++) {
		if (grid[i] >= 0) {
			var tileClass = tiles[grid[i]].className;
			tiles[grid[i]].className = tileClass.substring(0, 
					tileClass.indexOf("cell"));
			tiles[grid[i]].className += "cell_" + i;
		}
	}
	if (mode == 1) {
		document.getElementById("counter").innerHTML = "Moves: " + (++nMoves);
	}
}

function initTiles (dominoIndex) {
	if (dominoIndex < tiles.length) {
		setAttributePrefix(tiles[dominoIndex], "transition", "all .5s");
		setAttributePrefix(tiles[dominoIndex], "transform", "translate(0, 0) scale(1, 1)");
		tiles[dominoIndex].style.opacity = 1;
		setTimeout(function() {initTiles(dominoIndex + 1)}, 60);
	} else {
		document.getElementById("scrambleButton").style.display = "inline";
	}
}

function scrambleClick() {
	scrambleCount = 0;
	scrambleCountDown = nextCountDown(0);
	setTimeout(scrambleItUp, 0);
	/*for (var i = 0; i < 500000; i++) {
		scramble();
	}
	revalidate();*/
	document.getElementById("scrambleButton").style.display = "none";
}

function setAttributePrefix(element, att, value) {
	element.style["-webkit-" + att] = value;
	element.style["-moz-" + att] = value;
	element.style["-ms-" + att] = value;
	element.style["-o-" + att] = value;
	element.style[att] = value;
}

function movablePiece(index) {
	if (index % DIMENSION < DIMENSION - 1 && grid[index + 1] == -1) return 0;
	if (index + DIMENSION < DIMENSION * DIMENSION && grid[index + DIMENSION] == -1) return 1;
	if (index % DIMENSION > 0 && grid[index - 1] == -1) return 2;
	if (index - DIMENSION >= 0 && grid[index - DIMENSION] == -1) return 3;
	return -1;
}

function keyPressed(event) {
	if (mode == 1) {
		if (SECRET_CODE.charCodeAt(secretIndex++) == event.charCode) {
			if (secretIndex == SECRET_CODE.length) {
				for (var i = 0; i < grid.length - 1; i++) {
					grid[i] = i;
				}
				grid[grid.length - 1] = -1;
				revalidate();
				checkWin();
			}
		} else {
			secretIndex = 0;
		}
	}
}

function controlMusic(event) {
	var state = !event.checked;
	audioOpenning.muted = state;
	audioGame.muted = state;
	audioWin1.muted = state;
	audioWin2.muted = state;
	audioHydraulic = new Audio("hydraulic.mp3");
	audioExplosion = new Audio("explosion.mp3");
	audioSwoosh = new Audio("swoosh.mp3");
}

function controlSFX(event) {
	var state = !event.checked;
	audioHydraulic.muted = state;
	audioExplosion.muted = state;
	audioSwoosh.muted = state;
}

function sendFile(s) {
	var data = new FormData();
	data.append("data", s);
	var xhr = (window.XMLHttpRequest) ? new XMLHttpRequest() : new activeXObject("Microsoft.XMLHTTP");
	xhr.open('post', 'score.php', true );
	xhr.send(data);
}

function getFileFromServer(url) {
	if (window.XMLHttpRequest) {
   		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
   		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhr.onreadystatechange = function()
	{
		txt = xhr.responseText;	
	};

	xhr.open("GET",url);
	xhr.send();
}