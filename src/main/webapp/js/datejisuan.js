var minute = 1000 * 60;  
var hour = minute * 60;  
var day = hour * 24;  
var halfamonth = day * 15;  
var month = day * 30;  
  
function getDateDiff(setdateTimeStamp){  
    var dateTimeStamp=new Date(setdateTimeStamp);  
    var now = new Date().getTime();  
    var diffValue = now - dateTimeStamp;  
    if(diffValue < 0){  
     //若日期不符则弹出窗口告之  
      return  "结束日期不能小于开始日期！" ;  
     }  
    var monthC =diffValue/month;  
    var weekC =diffValue/(7*day);  
    var dayC =diffValue/day;  
    var hourC =diffValue/hour;  
    var minC =diffValue/minute;  
    if(monthC>=1){  
     result="发表于" + parseInt(monthC) + "个月前";  
     }  
     else if(weekC>=1){  
     result="发表于" + parseInt(weekC) + "周前";  
     }  
     else if(dayC>=1){  
     result="发表于"+ parseInt(dayC) +"天前";  
     }  
     else if(hourC>=1){  
     result="发表于"+ parseInt(hourC) +"个小时前";  
     }  
     else if(minC>=1){  
     result="发表于"+ parseInt(minC) +"分钟前";  
     }else  
     result="刚刚发表";   
     alert(result);  
     return result;  
}  

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}        

function timeStamp2String(time){  
    var datetime = new Date();  
    datetime.setTime(time);  
    var year = datetime.getFullYear();  
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;  
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();  
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();  
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();  
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();  
    return year + "-" + month + "-" + date;
}  