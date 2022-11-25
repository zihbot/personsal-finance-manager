import { createStore } from 'vuex';
import PocketBase from 'pocketbase';
import {
    ACT_CATEGORIES_LIST,
    ACT_LOGIN,
    ACT_TRANSACTIONS_CREATE,
    ACT_TRANSACTIONS_DELETE,
    ACT_TRANSACTIONS_EDIT,
    ACT_TRANSACTIONS_LIST,
} from './actions';
import {
    MUT_CATEGORIES_SET,
    MUT_TRANSACTIONS_SET,
    MUT_USER_SET,
} from './mutations';

const client = new PocketBase('/');

const COLLECTION_USERS = 'users';
const COLLECTION_TRANSACTIONS = 'transactions';
const COLLECTION_CATEGORIES = 'categories';

export default createStore({
    state: {
        userId: null,
        transactions: [],
        categories: [],
    },
    getters: {},
    mutations: {
        [MUT_USER_SET](state, payload: any) {
            state.userId = payload;
        },
        [MUT_TRANSACTIONS_SET](state, payload: any) {
            state.transactions = payload;
        },
        [MUT_CATEGORIES_SET](state, payload: any) {
            state.categories = payload;
        },
    },
    actions: {
        [ACT_LOGIN]({ commit, dispatch }, payload: any) {
            return client
                .collection(COLLECTION_USERS)
                .authWithPassword(payload.username, payload.password)
                .then((data) => {
                    commit(MUT_USER_SET, data.record.id);
                    dispatch(ACT_CATEGORIES_LIST);
                });
        },
        [ACT_TRANSACTIONS_LIST]({ commit }, payload: any) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .getFullList(undefined, { sort: '-created' })
                .then((data) => commit(MUT_TRANSACTIONS_SET, data));
        },
        [ACT_TRANSACTIONS_CREATE]({ commit, state }, payload: any) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .create({ user: state.userId, ...payload });
        },
        [ACT_TRANSACTIONS_EDIT]({ commit, state }, payload: any) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .update(payload.id, { user: state.userId, ...payload });
        },
        [ACT_TRANSACTIONS_DELETE]({ commit, state }, payload: any) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .delete(payload.id);
        },
        [ACT_CATEGORIES_LIST]({ commit }, payload: any) {
            return client
                .collection(COLLECTION_CATEGORIES)
                .getFullList(undefined, {})
                .then((data) => commit(MUT_CATEGORIES_SET, data));
        },
    },
    modules: {},
});
