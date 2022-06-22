import auth from "@/services/auth";
import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Account from "../views/Account.vue";
import NewTransaction from "../views/NewTransaction.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Login",
    component: Login,
    meta: {
      allowAll: true
    }
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/accounts",
    name: "Account",
    component: Account,
  },
  {
    path: "/new-transaction",
    name: "NewTransaction",
    component: NewTransaction,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  if (!to.meta?.allowAll && !auth.isLoggedIn()) {
    next({ path: '/' });
  } else {
    next()
  }
});

export default router;
