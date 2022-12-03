<template>
    <div v-for="(transactions, date) in groupedTransactions" :key="date">
        {{ date }}
        <transaction-list-item
            v-for="transaction in transactions"
            :key="transaction.id"
            :transaction="transaction"
            class="item action"
            @click="edit(transaction.id)"
        ></transaction-list-item>
    </div>
</template>

<script setup lang="ts">
import { computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { ACT_TRANSACTIONS_LIST } from '../store/actions';
import TransactionListItem from '../components/TransactionListItem.vue';
import { useRouter } from 'vue-router';
import { format } from 'date-fns';

const store = useStore();
const router = useRouter();

const groupedTransactions = computed(() =>
    store.state.transactions.reduce((rv, tr) => {
        (rv[format(new Date(tr.time || 0), 'yyyy-MM-dd')] =
            rv[format(new Date(tr.time || 0), 'yyyy-MM-dd')] ?? []).push(tr);
        return rv;
    }, {})
);

onMounted(() => {
    store.dispatch(ACT_TRANSACTIONS_LIST);
});

function edit(id: string) {
    router.push({ path: '/transaction-edit', query: { id } });
}
</script>

<style scoped lang="scss">
.item {
    margin: 0.5rem 0;
}
</style>
