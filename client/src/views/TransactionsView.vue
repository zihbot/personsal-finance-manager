<template>
    <div></div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import { useStore } from 'vuex';
import { ACT_TRANSACTIONS_LIST } from '../store/actions';

const store = useStore();

onMounted(()=> {
    store.dispatch(ACT_TRANSACTIONS_LIST);
})
</script>

<style scoped lang="scss">
</style>