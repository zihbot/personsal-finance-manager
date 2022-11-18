import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import CategoryEditView from '../views/CategoryEditView.vue'
import LoginView from '../views/LoginView.vue'
import TransactionEditView from '../views/TransactionEditView.vue'
import TransactionsView from '../views/TransactionsView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/transactions',
    name: 'transactions',
    component: TransactionsView
  },
  {
    path: '/transaction-edit',
    name: 'transaction-edit',
    component: TransactionEditView
  },
  {
    path: '/category-edit',
    name: 'category-edit',
    component: CategoryEditView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
