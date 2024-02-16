<template>
  <body>
    <h1>todo-list</h1>
    <form id="form" @submit.prevent="insertTodo">
      <input
        v-model="content"
        type="text"
        class="input"
        id="input"
        placeholder="Enter your todo"
        autocomplete="off"
      />

      <ul class="todos" id="todos">
        <Todo
          v-for="todo in todos"
          :key="todo"
          :todo="todo"
          @update="updateTodo"
          @delete="deleteTodo"
        />
      </ul>
    </form>
    <small
      >왼쪽 클릭: 항목 완료 <br />
      오른쪽 클릭: 해당 항목 삭제</small
    >
  </body>
</template>

<script>
import Todo from "./Todo.vue";

export default {
  components: {
    Todo,
  },
  data() {
    return {
      todos: [],
      nextId: 0,
      content: "",
    };
  },
  methods: {
    insertTodo() {
      this.axios
        .post(`/api/todo`, {
          content: this.content,
        })
        .then((res) => {
          this.todos.push(res.data);
        });
      this.content = "";
    },
    updateTodo(todo) {
      this.axios.put(`/api/todo/${todo.id}`).then((res) => {
        todo.done = res.data;
      });
    },
    deleteTodo(todo) {
      this.axios.delete(`/api/todo/${todo.id}`).then((res) => {
        if (res.data === "success") {
          this.todos.splice(
            this.todos.indexOf(todo),
            1
          );
        }
      });
    },
  },
  mounted() {
    this.axios
      .get(`/api/todo`)
      .then((res) => {
        this.todos = res.data;
      })
      .catch((res) => {
        console.error(res);
      });
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;400&display=swap");

* {
  box-sizing: border-box;
}

body {
  color: #444;
  font-family: "Poppins", sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  margin: 0;
}

h1 {
  color: black;
  font-size: 5rem;
  text-align: center;
}

form {
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  max-width: 100%;
  width: 400px;
}

.input {
  border: none;
  color: #444;
  font-size: 2rem;
  padding: 1rem 2rem;
  display: block;
  width: 100%;
}

.input::placeholder {
  color: grey;
}

.input:focus {
  outline-color: skyblue;
}

.todos {
  background-color: #fff;
  padding: 0;
  margin: 0;
  list-style-type: none;
  text-align: left;
}

.todos li {
  border-top: 1px solid #e5e5e5;
  cursor: pointer;
  font-size: 1.5rem;
  padding: 1rem 2rem;
}

.todos li.completed {
  color: #b6b6b6;
  text-decoration: line-through;
}

small {
  color: #b5b5b5;
  margin-top: 3rem;
  text-align: center;
}
</style>
