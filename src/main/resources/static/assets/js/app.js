document.addEventListener("DOMContentLoaded", function () {
	loadItems();
})

function createPaciente() {

	getJSessionId();

	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var email = document.getElementById('email').value;
	var dni = document.getElementById('dni').value;
	var name = document.getElementById('name').value;
	var surname1 = document.getElementById('surname1').value;
	var surname2 = document.getElementById('surname2').value;
	var age = document.getElementById('age').value;
	var csrf_token  = document.getElementById('_csrf').content;
	var csrf_header = document.getElementById('_csrf_header').content;

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
		alert(this.response);
		console.log(client.status);

		loadItems();
	});

	client.open("POST", "/api/patients");
	client.setRequestHeader(csrf_header,csrf_token);
	client.setRequestHeader("Content-type", "application/json");
	var body = JSON.stringify(item);
	client.send(body);

}

function loadItems() {
	console.log("Sigue funcionando la página, no has roto nada");
}

function createSanitario() {

	getJSessionId();

	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var email = document.getElementById('email').value;
	var dni = document.getElementById('dni').value;
	var role = document.getElementById('role').value;
	var name = document.getElementById('name').value;
	var surname1 = document.getElementById('surname1').value;
	var surname2 = document.getElementById('surname2').value;
	var age = document.getElementById('age').value;

	var csrf_token  = document.getElementById('_csrf').content;
	var csrf_header = document.getElementById('_csrf_header').content;

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
	client.setRequestHeader(csrf_header,csrf_token);
	client.setRequestHeader("Content-type", "application/json");
	var body = JSON.stringify(item);
	client.send(body);

}

	function submitQuestion(){
		var texto = CKEDITOR.instances.editor.getData();
		var nombre = document.getElementById('name').value;
		var asunto = document.getElementById('subject').value;
		var email = document.getElementById('email').value;
		var csrf_token  = document.getElementById('_csrf').content;
		var csrf_header = document.getElementById('_csrf_header').content;
		
		var item ={
			"cosa": texto,
			"answer": null,
			"personName":nombre,
			"email":email,
			"subject":asunto
		};
		
		var client = new XMLHttpRequest();
		client.responseType = "json";
		client.addEventListener("load", function(){
			console.log(this.response);
			console.log(client.status);
		});

		client.open("POST", "/api/preguntas");
		client.setRequestHeader(csrf_header,csrf_token);
		client.setRequestHeader("Content-type", "application/json");
		var body = JSON.stringify(item);
		client.send(body);
	}

	function submitAnswer(){
		var texto = CKEDITOR.instances.editor.getData();
		var id = document.getElementById('id_question').value;
		var csrf_token  = document.getElementById('_csrf').content;
		var csrf_header = document.getElementById('_csrf_header').content;

		
		var item ={
			"answer": texto
		};
		
		var client = new XMLHttpRequest();
		client.responseType = "json";
		client.addEventListener("load", function(){
			console.log(this.response);
			console.log(client.status);
		});

		client.open("PATCH", "/api/preguntas/" + id);
		client.setRequestHeader(csrf_header,csrf_token);
		client.setRequestHeader("Content-type", "application/json");
		var body = JSON.stringify(item);
		alert(body);
		client.send(body);
	}
	

function getJSessionId(){
	var jsId = document.cookie.match(/JSESSIONID=[^;]+/);
    if(jsId != null) {
        if (jsId instanceof Array)
            jsId = jsId[0].substring(11);
        else
            jsId = jsId.substring(11);
    }
    return jsId;
}

function addItemsToPage(response) {
	console.log("Ha funcionado");
}

function newPatient() {
    var selectedValue = document.getElementById("list").value;
	var idHealthPersonnel =document.getElementsByName("id_HealthPersonnel").value;
	var csrf_token  = document.getElementById('_csrf').content;
	var csrf_header = document.getElementById('_csrf_header').content;
	var item ={
		"id1":selectedValue,
		"id2":idHealthPersonnel
	};


    var client = new XMLHttpRequest();
	client.responseType = "json";
	client.addEventListener("load", function () {
		console.log(this.response);
		console.log(client.status);

		loadItems();
	});

	client.open("POST", "/api/setNewPatient");
	client.setRequestHeader(csrf_header,csrf_token);
	client.setRequestHeader("Content-type", "application/json");
	var body = JSON.stringify(item);
	client.send(body);

}