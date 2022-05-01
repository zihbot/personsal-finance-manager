import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import NewTransaction from "../views/NewTransaction.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/newTransaction",
    name: "NewTransaction",
    component: NewTransaction,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
