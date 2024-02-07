let api = "http://localhost:8080/api/todo";

async function getUserId(){
  let data = await fetch(api + "/user").then((res)=>res.json());
  let userName = data.name;
  let spans = document.querySelectorAll(".id");
  for(let span of spans){
    span.innerHTML = userName;
  }
}

async function showToDoList(list) {
  let li = document.createElement("li");
  li.id = list.id;
  li.className = (list.done ? "complete" : "");
  li.textContent = list.content;
  document.querySelector(".todos").append(li);
}

async function showAll() {
  let data = await fetch(api).then((res) => res.json());
  document.querySelector(".todos").innerHTML = "";
  for (list of data) {
    showToDoList(list);
  }
}

async function addToDoList(elem) {
  let list = elem.firstElementChild.value;
  if (list === "") return;
  elem.firstElementChild.value = "";
  let todo = {
    content: list,
  };
  let response = await fetch(api, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(todo),
  }).then(async (res) => await res.json());
  showToDoList(response);
}

async function deleteToDoList(elem) {
  let id = elem.id;
  let response = await fetch(api + "/" + id, {
    method: "DELETE",
  }).then(async (res) => await res.text());
  if (response === "success") document.getElementById(id).remove();
}

async function completeToDoList(elem) {
  let id = elem.id;
  let response = await fetch(api + "/" + id, {
    method: "PUT",
  }).then(async (res) => await res.text());
  console.log(response);
  elem.className = String(response) == "true" ? "complete" : "";
}

document.querySelector("form").addEventListener("submit", (event) => {
  event.preventDefault();
  addToDoList(event.target);
});

window.oncontextmenu = function () {
  return false;
};

document.querySelector(".todos").addEventListener("mousedown", (event) => {
  if (event.button === 2 || event.which === 3) {
    deleteToDoList(event.target);
  } else {
    completeToDoList(event.target);
  }
});

document.querySelector(".logout").addEventListener("click",(event) => {
  location.href = "/logout";
})

showAll();
getUserId();
