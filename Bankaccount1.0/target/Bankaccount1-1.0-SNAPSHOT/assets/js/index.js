function save(id,name,family,Father_name,nc_number,cc_number,create_date){
    alert(id);
    fetch("http://localhost:80/admin",{
        method:'POST',
        body:JSON.stringify({id:id, Name:name, Family:family,nc_number:nc_number,father_name:Father_name,cc_number:cc_number,create_date:create_date})

    })
        .then((response) => response.json())
        .then((data) => console.log(data));
    alert("This person is saved")
}

function remove(id){
    alert(id);
    fetch("http://localhost:80/admin",{
        method:'DELETE',
        body:JSON.stringify({id:id})
    })
        .then((response) => response.json())
        .then((data) => console.log(data));
    alert("This person is deleted")
}

function edit(id,cc_number){
    fetch("http://localhost:80/admin",{
        method:'GET',
        body:{id:id,cc_number:cc_number}
    })
        .then((response) => response.json())
        .then((data) => console.log(data));
    alert("This person is edited")
}