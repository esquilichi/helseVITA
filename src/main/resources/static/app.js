document.addEventListener("DOMContentLoaded", function () {
    loadItems();
})

function createPaciente() {

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var email = document.getElementById('email').value;
    var dni = document.getElementById('dni').value;
    var name = document.getElementById('name').value;
    var surname1 = document.getElementById('surname1').value;
    var surname2 = document.getElementById('surname2').value;
    var age = document.getElementById('age').value;

    var item =
        {
            "username": username,
            "password": password,
            "email": email,
            "dni": dni,
            "name": name,
            "surname1": surname1,
            "surname2": surname2,
            "age": age
        };

    var client = new XMLHttpRequest();
    client.responseType = "json";
    client.addEventListener("load", function () {
        console.log(this.response);
        console.log(client.status);

        loadItems();
    });

    client.open("POST", "/api/patients");

    client.setRequestHeader("Content-type", "application/json");
    var body = JSON.stringify(item);
    client.send(body);

}

function loadItems() {

    alert("Eres precioso no cambies :p");

}

function createSanitario() {

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var email = document.getElementById('email').value;
    var dni = document.getElementById('dni').value;
    var role = document.getElementById('role').value;
    var name = document.getElementById('name').value;
    var surname1 = document.getElementById('surname1').value;
    var surname2 = document.getElementById('surname2').value;
    var age = document.getElementById('age').value;

    var item =
        {
            "username": username,
            "password": password,
            "email": email,
            "dni": dni,
            "role": role,
            "name": name,
            "surname1": surname1,
            "surname2": surname2,
            "age": age

        };

    var client = new XMLHttpRequest();
    client.responseType = "json";
    client.addEventListener("load", function () {
        console.log(this.response);
        console.log(client.status);

        loadItems();
    });

    client.open("POST", "/api/healthPersonnel");

    client.setRequestHeader("Content-type", "application/json");
    var body = JSON.stringify(item);
    client.send(body);

}

function addItemsToPage(response) {
    console.log("Ha funcionado");
    /*var itemsElem = document.getElementById('items');

    itemsElem.innerHTML = '';

    for(var item of response){
        var username = item.username;
        var li = document.createElement('li');
        itemsElem.appendChild(li);

        var text = document.createTextNode(username);
        li.appendChild(text);
    }	*/
}

/* 
function search(){
	alert("hola");
	var checkbox1=document.getElementById("Username");
	var checkbox2=document.getElementById("Email");
	var checkbox3=document.getElementById("Dni");
	alert("1");
	if (checkbox1.checked){
		window.location.href("/searchUsername");
		client.open("GET", "/searchUsername?input=0&username=" + text);
	}
	else if (checkbox2.checked){
		window.location.href("/searchEmail");
		client.open("GET", "/searchEmail?input=1&email=" + text);
	}
	else if (checkbox3.checked){
		window.location.href("/searchDni");
		client.open("GET", "/searchDni?input=2&dni=" + text);
	}
	

  }*/