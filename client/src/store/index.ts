import { createStore } from 'vuex';
import PocketBase, { Record } from 'pocketbase';
import { ACT_LOGIN, ACT_TRANSACTIONS_LIST } from './actions';
import { MUT_TRANSACTIONS_SET } from './mutations';

const client = new PocketBase('/');

export default createStore({
  state: {
    transactions: [],
  },
  getters: {
  },
  mutations: {
    [MUT_TRANSACTIONS_SET](state, payload: any) {
      state.transactions = payload;
    },
  },
  actions: {
    [ACT_LOGIN]({commit}, payload: any) {
      return client.users.authViaEmail(payload.username, payload.password);
    },
    [ACT_TRANSACTIONS_LIST]({commit}, payload: any) {
      return client.records.getFullList('transactions', undefined, {})
        .then((data) => commit(MUT_TRANSACTIONS_SET, data));
    },
  },
  modules: {
  }
})
