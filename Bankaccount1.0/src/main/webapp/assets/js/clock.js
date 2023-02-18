function clockRuner(){
    var d = new Date();
    var hour = d.getHours();
    var minute = d.getMinutes();
    var second = d.getSeconds();

    var p = 'AM';
    if (hour > 12){
        hour -= 12;
        p='PM';
    }

    if ( hour < 10){
        hour = "0" + hour;
    }

    if ( minute < 10){
        minute = "0" + minute;
    }

    if ( second < 10){
        second = "0" + second;
    }

    var clock = hour + ":" + minute + ":" + second + " " + p //15:15:30
    document.getElementById('MyClockDisplay').textContent= clock;
    setTimeout(clockRuner,1000);
}
clockRuner();