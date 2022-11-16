import { createStore } from 'vuex';
import PocketBase from 'pocketbase';

const client = new PocketBase('/');

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
    login({commit}, payload: any) {
      return client.users.authViaEmail(payload.username, payload.password);
    }
  },
  modules: {
  }
})
