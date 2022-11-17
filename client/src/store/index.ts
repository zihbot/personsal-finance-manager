import { createStore } from 'vuex';
import PocketBase, { Record, User } from 'pocketbase';
import { ACT_CATEGORIES_LIST, ACT_LOGIN, ACT_TRANSACTIONS_CREATE, ACT_TRANSACTIONS_LIST } from './actions';
import { MUT_CATEGORIES_SET, MUT_TRANSACTIONS_SET, MUT_USER_SET } from './mutations';

const client = new PocketBase('/');

const COLLECTION_TRANSACTIONS = 'transactions';
const COLLECTION_CATEGORIES = 'categories';

export default createStore({
  state: {
    user: null as User | null,
    transactions: [],
    categories: [],
  },
  getters: {
  },
  mutations: {
    [MUT_USER_SET](state, payload: any) {
      state.user = payload;
    },
    [MUT_TRANSACTIONS_SET](state, payload: any) {
      state.transactions = payload;
    },
    [MUT_CATEGORIES_SET](state, payload: any) {
      state.categories = payload;
    },
  },
  actions: {
    [ACT_LOGIN]({commit, dispatch}, payload: any) {
      return client.users.authViaEmail(payload.username, payload.password)
      .then((data) => {
        commit(MUT_USER_SET, data.user);
        dispatch(ACT_CATEGORIES_LIST);
      });
    },
    [ACT_TRANSACTIONS_LIST]({commit}, payload: any) {
      return client.records.getFullList(COLLECTION_TRANSACTIONS, undefined, {})
        .then((data) => commit(MUT_TRANSACTIONS_SET, data));
    },
    [ACT_TRANSACTIONS_CREATE]({commit, state}, payload: any) {
      return client.records.create(COLLECTION_TRANSACTIONS, {user: state.user?.id, ...payload});
    },
    [ACT_CATEGORIES_LIST]({commit}, payload: any) {
      return client.records.getFullList(COLLECTION_CATEGORIES, undefined, {})
        .then((data) => commit(MUT_CATEGORIES_SET, data));
    },
  },
  modules: {
  }
})
