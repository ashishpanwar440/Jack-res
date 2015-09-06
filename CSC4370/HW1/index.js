var EASTER_EGG = "lifeispotato";
var currentChar = 0;
window.onkeypress = function(event) {
	if (String.fromCharCode(event.which) == EASTER_EGG.charAt(currentChar)) {
		currentChar++;
	} else {
		currentChar = 0;
	}
	if (currentChar == EASTER_EGG.length) window.location.href = "easteregg.html";
}
