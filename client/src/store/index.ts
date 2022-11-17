import { createStore } from 'vuex';
import PocketBase, { Record } from 'pocketbase';
import { ACT_CATEGORIES_LIST, ACT_LOGIN, ACT_TRANSACTIONS_LIST } from './actions';
import { MUT_CATEGORIES_SET, MUT_TRANSACTIONS_SET } from './mutations';

const client = new PocketBase('/');

const COLLECTION_TRANSACTIONS = 'transactions';
const COLLECTION_CATEGORIES = 'categories';

export default createStore({
  state: {
    transactions: [],
    categories: [],
  },
  getters: {
  },
  mutations: {
    [MUT_TRANSACTIONS_SET](state, payload: any) {
      state.transactions = payload;
    },
    [MUT_CATEGORIES_SET](state, payload: any) {
      state.categories = payload;
    },
  },
  actions: {
    [ACT_LOGIN]({commit}, payload: any) {
      return client.users.authViaEmail(payload.username, payload.password)
      .then(() => {
        this.dispatch(ACT_CATEGORIES_LIST)
      });
    },
    [ACT_TRANSACTIONS_LIST]({commit}, payload: any) {
      return client.records.getFullList(COLLECTION_TRANSACTIONS, undefined, {})
        .then((data) => commit(MUT_TRANSACTIONS_SET, data));
    },
    [ACT_CATEGORIES_LIST]({commit}, payload: any) {
      return client.records.getFullList(COLLECTION_CATEGORIES, undefined, {})
        .then((data) => commit(MUT_CATEGORIES_SET, data));
    },
  },
  modules: {
  }
})
