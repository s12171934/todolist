<template>
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
    updateTodo(id) {
      this.axios.put(`/api/todo/${id}`).then((res) => {
        this.todos.filter((todo) => todo.id == id)[0].done = res.data;
      });
    },
    deleteTodo(id) {
      this.axios.delete(`/api/todo/${id}`).then((res) => {
        if (res.data === "success") {
          this.todos.splice(
            this.todos.indexOf(this.todos.find((todo) => todo.id == id)),
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
