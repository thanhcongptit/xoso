/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//ham kiem tra so theo keyup
function isNumber(evt){
     var charCode = (evt.which) ? evt.which : event.keyCode
     //console.info(charCode);
     if (charCode > 31 && (charCode < 48 || charCode > 57))
     return false;
 
     return true;
}

//ngan su kien pate
function onPate(evt){    
    return false;
}


function getCurrHHMMSS() {
    var currentdate = new Date();
    var currentHH = (currentdate.getHours() < 10 ? '0' + currentdate.getHours() : currentdate.getHours());
    var currentMM = (currentdate.getMinutes() < 10 ? '0' + currentdate.getMinutes() : currentdate.getMinutes());
    var currentSS = (currentdate.getSeconds() < 10 ? '0' + currentdate.getSeconds() : currentdate.getSeconds());
    return parseInt(currentHH + '' + currentMM + '' + currentSS);
}

function countDownMB() {    
    var date = new Date();    
    date.setMinutes(15);
    date.setHours(18);
    date.setSeconds(0);
      
    
    var target_date = date.getTime();
    var days, hours, minutes, seconds;

    var current_date = new Date().getTime();
    var seconds_left = (target_date - current_date) / 1000;

    // do some time calculations
    days = parseInt(seconds_left / 86400);
    seconds_left = seconds_left % 86400;

    hours = ""+parseInt(seconds_left / 3600);
    seconds_left = seconds_left % 3600;

    minutes =""+ parseInt(seconds_left / 60);
    seconds =""+parseInt(seconds_left % 60);
    
    
    
    if(hours>=0 && minutes>=0 && seconds>=0){        
        if(hours<10){hours="0"+hours;}

        if(minutes<10){minutes="0"+minutes;}

        if(seconds<10){seconds="0"+seconds;}                 
        
        $('#mbhour').html(hours);
        $('#mbminu').html(minutes);
        $('#mbsecond').html(seconds);
        
        t = setTimeout(function () {            
            countDownMB();
        }, 1000);
        
    }        
}

function countDownMT() {    
    var date = new Date();    
    date.setMinutes(15);
    date.setHours(17);
    date.setSeconds(0);
      
    
    var target_date = date.getTime();
    var days, hours, minutes, seconds;

    var current_date = new Date().getTime();
    var seconds_left = (target_date - current_date) / 1000;

    // do some time calculations
    days = parseInt(seconds_left / 86400);
    seconds_left = seconds_left % 86400;

    hours = ""+parseInt(seconds_left / 3600);
    seconds_left = seconds_left % 3600;

    minutes =""+ parseInt(seconds_left / 60);
    seconds =""+parseInt(seconds_left % 60);
    
    
    
    if(hours>=0 && minutes>=0 && seconds>=0){        
        if(hours<10){hours="0"+hours;}

        if(minutes<10){minutes="0"+minutes;}

        if(seconds<10){seconds="0"+seconds;}                 
        
        $('#mthour').html(hours);
        $('#mtminu').html(minutes);
        $('#mtsecond').html(seconds);
        
        t = setTimeout(function () {            
            countDownMT();
        }, 1000);
        
    }        
}

function countDownMN() {    
    var date = new Date();    
    date.setMinutes(15);
    date.setHours(16);
    date.setSeconds(0);
      
    
    var target_date = date.getTime();
    var days, hours, minutes, seconds;

    var current_date = new Date().getTime();
    var seconds_left = (target_date - current_date) / 1000;

    // do some time calculations
    days = parseInt(seconds_left / 86400);
    seconds_left = seconds_left % 86400;

    hours = ""+parseInt(seconds_left / 3600);
    seconds_left = seconds_left % 3600;

    minutes =""+ parseInt(seconds_left / 60);
    seconds =""+parseInt(seconds_left % 60);
    
    
    
    if(hours>=0 && minutes>=0 && seconds>=0){        
        if(hours<10){hours="0"+hours;}

        if(minutes<10){minutes="0"+minutes;}

        if(seconds<10){seconds="0"+seconds;}                 
        
        $('#mnhour').html(hours);
        $('#mnminu').html(minutes);
        $('#mnsecond').html(seconds);
//        document.getElementById('mnhour').innerHTML=hours;
//        document.getElementById('mnminu').innerHTML=minutes;
//        document.getElementById('mnsecond').innerHTML=seconds;
        
        t = setTimeout(function () {            
            countDownMN();
        }, 1000);
        
    }        
}