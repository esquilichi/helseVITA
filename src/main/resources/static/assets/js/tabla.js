function myFunction(){
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
                tr[i].style.display = "";
            }else {
                tr[i].style.display = "none";
            }
        }
    }
}