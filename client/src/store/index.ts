import { createStore } from 'vuex';
import PocketBase from 'pocketbase';
import { ACT_LOGIN, ACT_TRANSACTIONS_LIST } from './actions';

const client = new PocketBase('/');

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
    [ACT_LOGIN]({commit}, payload: any) {
      return client.users.authViaEmail(payload.username, payload.password);
    },
    [ACT_TRANSACTIONS_LIST]({commit}, payload: any) {
      return client.records.getFullList('transactions', undefined, {});
    },
  },
  modules: {
  }
})
