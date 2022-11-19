<template>
    <transaction-list-item
        v-for="transaction in transactions"
        :key="transaction.id"
        :transaction="transaction"
        class="item action"
        @click="edit(transaction.id)"
    ></transaction-list-item>
</template>

<script setup lang="ts">
import { computed, onMounted } from "vue";
import { useStore } from "vuex";
import { ACT_TRANSACTIONS_LIST } from "../store/actions";
import TransactionListItem from "../components/TransactionListItem.vue";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter();

const transactions = computed(() => store.state.transactions);

onMounted(() => {
    store.dispatch(ACT_TRANSACTIONS_LIST);
});

function edit(id: string) {
    router.push({path: '/transaction-edit', query: {id}})
}
</script>

<style scoped lang="scss">
.item {
    margin: .5rem 0;
}
</style>
