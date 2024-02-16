import { createWebHistory, createRouter } from "vue-router";
import TodoList from "../components/TodoList";
import LoginPage from "../components/LoginPage";
import SignUp from "../components/SignUp";

const routes = [
  {
    path: "/",
    component: LoginPage,
  },
  {
    path: "/todos",
    component: TodoList,
  },
  {
    path: "/login",   
    component: LoginPage,
  },
  {
    path: "/signup",
    component: SignUp,
  },
]; //라우팅 패스, 컴포넌트 등 정의

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export { router };
