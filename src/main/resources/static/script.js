let api = 'http://localhost:8080/api/todo';

let fetchMovieInfo = async (url) => fetch(url).then((res) => res.json());

async function completeToDoList(elem){
    let id = elem.id;
    await fetch(api,{
        method:'PUT',
        headers:{
            'Content-Type': 'text;charset=utf-8'
        },
        body:id
    });
}

async function deleteToDoList(elem){
    let id = elem.id;
    await fetch(api,{
        method:'DELETE',
        headers:{
            'Content-Type': 'text;charset=utf-8'
        },
        body:id
    });
    
}

async function addToDoList(elem){
    let list = elem.firstElementChild.value;
    if(list === "") return;
    elem.firstElementChild.value = "";
    await fetch(api,{
        method:'POST',
        headers:{
            'Content-Type': 'text;charset=utf-8'
        },
        body:list
    });
    
}

async function showToDoList(){
    let data = await fetch(api).then((res) => res.json());
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