<template>
    <div class="transaction">
        <transaction-category :category-id="props.transaction.category"></transaction-category>
        {{props.transaction.value}}
    </div>
</template>

<script setup lang="ts">
import TransactionCategory from './TransactionCategory.vue';

const props = defineProps(['transaction']);
</script>

<style scoped lang="scss">
.transaction {
    width: 100%;
    padding: 1rem;
    box-sizing: border-box;
    background-color: var(--app-light-background);
    border-radius: 2rem;

    display: flex;
    align-items: center;
    gap: 1rem;
}
</style>