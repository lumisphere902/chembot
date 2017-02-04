function asyncSimpleSearch(string) {
var xhr = new XMLHttpRequest();
var params = "query=" + string + "&token=7966ac5e-6bc7-4158-8e37-55173d463ba7";
xhr.open("POST", "http://www.chemspider.com/Search.asmx/AsyncSimpleSearch", true);
xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xhr.onreadystatechange = function() {//Call a function when the state changes.
    if(http.readyState == 4 && http.status == 200) {
        alert(http.responseText);
    }
}
xhr.send();
}
