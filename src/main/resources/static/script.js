let num = 3;

let data = [
    {
        "id" : 1,
        "todo" : "123",
        "stat" : ""
    },
    {
        "id" : 2,
        "todo" : "456",
        "stat" : ""
    }
];

function completeToDoList(elem){
    console.log(elem)
    if(elem.className == ""){
        data.filter(list => list.id == elem.id)[0].stat = "complete";
    } else{
        data.filter(list => list.id == elem.id)[0].stat = "";
    }
}

function deleteToDoList(elem){
    let idx = data.findIndex(list => list.id == elem.id);
    console.log(idx);
    data.splice(idx,idx + 1);;
}

function addToDoList(elem){
    let list = elem.firstElementChild.value;
    if(list === "") return;
    elem.firstElementChild.value = "";
    
    data.push({
        "id" : num++,
        "todo" : list,
        "stat" : ""
    })
    
}

function showToDoList(){
    document.querySelector(".todos").innerHTML = "";
    for(list of data){
        let li = document.createElement("li");
        li.id = list.id;
        li.className = list.stat;
        li.textContent = list.todo;
        document.querySelector(".todos").append(li);
    }
    
}

document.querySelector("form").addEventListener('submit',(event) => {
    event.preventDefault();
    console.log(event.target)
    addToDoList(event.target);
    showToDoList();
});

window.oncontextmenu = function(){
    return false;
};

document.querySelector(".todos").addEventListener('mousedown', (event) =>{
    if((event.button === 2)||(event.which ===3)){
        deleteToDoList(event.target);
        console.log(event.target)
    } else{
        completeToDoList(event.target);
    }
    showToDoList();
});

showToDoList();