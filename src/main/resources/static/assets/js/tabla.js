function edad(){
    let input, filter, table, tr, th, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value;
    table = document.getElementById("myTable");
    tr  = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++){
            th = tr[i].getElementsByTagName("th")[5];
            if (th){
                txtValue = th.textContent || th.innerText;
                if (txtValue.localeCompare(filter) !== 0){
                    if (tr[i].style.display === ""){
                        tr[i].style.display = "";
                    }else if (tr[i].style.display === "none"){
                        tr[i].style.display = "none";
                        tr[i].id = "0";
                    }

                }else {
                    tr[i].style.display = "none";
                }
            }
        }

}

function cambia_nombre(){
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

function chulo(){
    let input;
    input = document.getElementById("miInputChulo");
    alert(input.value);
}

function cambia_fecha(){
    let input, table, tr, th, i, txtValue, year, month, day, hour, minute, temp;
    input = document.getElementById("fecha");

    year = input.subSequence(0,4);
    month = input.subSequence(5,7);
    day = input.subSequence(8,10);
    hour = input.subSequence(11,13);
    minute = input.subSequence(16,17);

    table = document.getElementById("myTable");
    tr  = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++){
        th = tr[i].getElementsByTagName("th")[1];
        if (th){
            txtValue = th.textContent || th.innerText;
            if (txtValue.indexOf(input) > -1){
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