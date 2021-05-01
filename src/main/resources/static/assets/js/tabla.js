function edad(){
    let input, filter, table, tr, th, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr  = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++){
        th = tr[i].getElementsByTagName("th")[5];
        if (th){
            txtValue = th.textContent || th.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1){
                if (tr[i].style.display === ""){
                    tr[i].style.display = "";
                }else if (tr[i].style.display === "none"){
                    tr[i].style.display = "none";
                }

            }else {
                tr[i].style.display = "none";
            }
        }
    }
}

function cambia_id(){
    let input, filter, table, tr, th, i, txtValue;
    input = document.getElementById("myInput2");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr  = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++){
        th = tr[i].getElementsByTagName("th")[1];
        if (th){
            txtValue = th.textContent || th.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1){
                if (tr[i].style.display === ""){
                    tr[i].style.display = "";
                }else if (tr[i].style.display === "none"){
                    tr[i].style.display = "none";
                }

            }else {
                tr[i].style.display = "none";
            }
        }
    }
}

function cambia_apellido1(){
    let input, filter, table, tr, th, i, txtValue;
    input = document.getElementById("myInput3");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr  = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++){
        th = tr[i].getElementsByTagName("th")[2];
        if (th){
            txtValue = th.textContent || th.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1){
                if (tr[i].style.display === ""){
                    tr[i].style.display = "";
                }else if (tr[i].style.display === "none"){
                    tr[i].style.display = "none";
                }

            }else {
                tr[i].style.display = "none";
            }
        }
    }
}