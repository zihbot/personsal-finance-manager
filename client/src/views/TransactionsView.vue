<template>
    <transaction-list-item
        v-for="transaction in transactions"
        :key="transaction.id"
        :transaction="transaction"
        class="item"
    ></transaction-list-item>
</template>

<script setup lang="ts">
import { computed, onMounted } from "vue";
import { useStore } from "vuex";
import { ACT_TRANSACTIONS_LIST } from "../store/actions";
import TransactionListItem from "../components/TransactionListItem.vue";

const store = useStore();

const transactions = computed(() => store.state.transactions);

onMounted(() => {
    store.dispatch(ACT_TRANSACTIONS_LIST);
});
</script>

<style scoped lang="scss">
.item {
    margin: .5rem 0;
}
</style>
