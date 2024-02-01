function completeToDoList(elem){
    if(elem.className == ""){
        elem.className = "complete";
    } else{
        elem.className = "";
    }
    
}

function deleteToDoList(elem){
    elem.remove();
}

function addToDoList(elem){
    let list = elem.firstElementChild.value;
    elem.firstElementChild.value = "";
    let li = document.createElement("li");
    li.innerHTML = list;
    document.querySelector(".todos").append(li);
    
}

document.querySelector("form").addEventListener('submit',(event) => {
    event.preventDefault();
    console.log(event.target)
    addToDoList(event.target);
})

window.oncontextmenu = function(){
    return false;
};

document.querySelector(".todos").addEventListener('mousedown', (event) =>{
    if((event.button === 2)||(event.which ===3)){
        deleteToDoList(event.target);
        console.log(event.target);
    } else{
        console.log(event.target.className)
        completeToDoList(event.target);
        console.log(event.target);
    }
})