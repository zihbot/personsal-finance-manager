<template>
    <div class="transaction">{{props.transaction.value}}</div>
</template>

<script setup lang="ts">
const props = defineProps(['transaction']);
</script>

<style scoped lang="scss">
.transaction {
    width: 100%;
    padding: 1rem;
    box-sizing: border-box;
    background-color: var(--app-light-background);
    border-radius: 2rem;
}
</style>