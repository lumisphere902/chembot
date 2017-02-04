function asyncSimpleSearch(string) {
var xhr = new XMLHttpRequest();
xhr.open("POST", "http://www.chemspider.com/Search.asmx/AsyncSimpleSearch", true);
xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xhr.onreadystatechange = function() {//Call a function when the state changes.
    if(http.readyState == 4 && http.status == 200) {
        alert(http.responseText);
    }
}
xhr.send(params);
}
