import { createStore } from 'vuex';
import PocketBase from 'pocketbase';
import {
    ACT_CATEGORIES_CREATE,
    ACT_CATEGORIES_DELETE,
    ACT_CATEGORIES_EDIT,
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
        async [ACT_LOGIN]({ commit, dispatch }, payload: any) {
            return client
                .collection(COLLECTION_USERS)
                .authWithPassword(payload.username, payload.password)
                .then((data) => {
                    commit(MUT_USER_SET, data.record.id);
                    dispatch(ACT_CATEGORIES_LIST);
                });
        },
        async [ACT_TRANSACTIONS_LIST]({ commit }) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .getFullList(undefined, { sort: '-time' })
                .then((data) => commit(MUT_TRANSACTIONS_SET, data));
        },
        async [ACT_TRANSACTIONS_CREATE]({ state }, payload: any) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .create({ user: state.userId, ...payload });
        },
        async [ACT_TRANSACTIONS_EDIT]({ state }, payload: any) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .update(payload.id, { user: state.userId, ...payload });
        },
        async [ACT_TRANSACTIONS_DELETE](_, payload: any) {
            return client
                .collection(COLLECTION_TRANSACTIONS)
                .delete(payload.id);
        },
        async [ACT_CATEGORIES_LIST]({ commit }) {
            return client
                .collection(COLLECTION_CATEGORIES)
                .getFullList(undefined, {})
                .then((data) => commit(MUT_CATEGORIES_SET, data));
        },
        async [ACT_CATEGORIES_CREATE]({ dispatch, state }, payload: any) {
            return client
                .collection(COLLECTION_CATEGORIES)
                .create({ user: state.userId, ...payload })
                .then(() => dispatch(ACT_CATEGORIES_LIST));
        },
        async [ACT_CATEGORIES_EDIT]({ dispatch, state }, payload: any) {
            return client
                .collection(COLLECTION_CATEGORIES)
                .update(payload.id, { user: state.userId, ...payload })
                .then(() => dispatch(ACT_CATEGORIES_LIST));
        },
        async [ACT_CATEGORIES_DELETE]({ dispatch }, payload: any) {
            return client
                .collection(COLLECTION_CATEGORIES)
                .delete(payload.id)
                .then(() => dispatch(ACT_CATEGORIES_LIST));
        },
    },
    modules: {},
});
