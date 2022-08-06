//グローバル？
var displayTime;

window.addEventListener('load', function() {
	time();
	setInterval('time()',1000);
});

function time(){
    var now = new Date();
    displayTime = now.toLocaleTimeString();
    var n = document.getElementById("time");
    if (n) {
	    n.innerHTML = displayTime;
	}
}