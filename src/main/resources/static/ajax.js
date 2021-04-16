

function loadItemsSanitario(objeto){
	var id = objeto.id -1;
	console.log("La id es " + id);
    var client = new XMLHttpRequest();
    client.responseType = "json";
    client.addEventListener("load", function(){
    	addItemsToPage(this.response);    	
    });
    
    client.open("GET", "/api/healthPersonnel/" + id);
    
    client.send();
		
}

function loadItemsPaciente(objeto){
	var id = objeto.id-1;
	console.log("La id es " + id);
    var client = new XMLHttpRequest();
    client.responseType = "json";
    client.addEventListener("load", function(){
    	addItemsToPage(this.response);    	
    });
    
    client.open("GET", "/api/patients/" + id);
    
    client.send();
		
}

function addItemsToPage(response){
	console.log(response);
	var li = document.createElement("li");
	var br = document.createElement("br");
	var br2 = document.createElement("br");
	var email = document.createTextNode(response.email);
	var password = document.createTextNode(response.password);
	var dni = document.createTextNode(response.dni);
	li.className = "list-group-item";
	li.appendChild(email);
	li.appendChild(br2);
	li.appendChild(password);
	li.appendChild(br);
	li.appendChild(dni);
	

	var lista = document.getElementById(response.id + 1);
	lista.appendChild(li);
	console.log(li);

	/*for(var item of response){
		var username = item.username;
		var li = document.createElement('li');
		itemsElem.appendChild(li);
    	
    	var text = document.createTextNode(username);
    	li.appendChild(text);  		
	}	*/
}
