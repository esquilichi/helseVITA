document.addEventListener("DOMContentLoaded", function(){
	loadItems();
})

function createPaciente() {

	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var email = document.getElementById('email').value;
	var dni = document.getElementById('dni').value;


	var item = 
	{ 
	"username": username, 
	"password":password,
	"email":email,
	"dni":dni 
	};
		
	var client = new XMLHttpRequest();
	client.responseType = "json";
	client.addEventListener("load", function() {
		console.log(this.response);
		console.log(client.status);
		
		loadItems();
	});

	client.open("POST", "/api/patients");

	client.setRequestHeader("Content-type", "application/json");
	var body = JSON.stringify(item);
	client.send(body);

}

function loadItems(){
	
    var client = new XMLHttpRequest();
    client.responseType = "json";
    client.addEventListener("load", function(){
    	addItemsToPage(this.response);    	
    });
    
    client.open("GET", "/api");
    
    client.send();
		
}

function createSanitario() {

	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var email = document.getElementById('email').value;
	var dni = document.getElementById('dni').value;


	var item = 
	{ 
	"username": username, 
	"password":password,
	"email":email,
	"dni":dni 
	};
		
	var client = new XMLHttpRequest();
	client.responseType = "json";
	client.addEventListener("load", function() {
		console.log(this.response);
		console.log(client.status);
		
		loadItems();
	});

	client.open("POST", "/api/healthPersonnel");

	client.setRequestHeader("Content-type", "application/json");
	var body = JSON.stringify(item);
	client.send(body);

}

function loadItems(){
	
    var client = new XMLHttpRequest();
    client.responseType = "json";
    client.addEventListener("load", function(){
    	addItemsToPage(this.response);    	
    });
    
    client.open("GET", "/api");
    
    client.send();
		
}


function addItemsToPage(response){
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
