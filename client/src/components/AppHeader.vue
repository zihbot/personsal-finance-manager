<template>
    <nav v-if="router.currentRoute.value?.fullPath !== '/'">
        <router-link to="/transactions">Transactions</router-link>
        <router-link to="/category-edit">Categories</router-link>
        <router-link to="/">Logout</router-link>
    </nav>
</template>

<script setup lang="ts">
import { useScreen } from '@/methods/screen';
import router from '@/router';

const { type, orientation } = useScreen();
</script>

<style scoped lang="scss">
nav {
    padding: 30px;

    a {
        padding: 1rem;
        font-weight: bold;
        color: #2c3e50;

        &.router-link-exact-active {
            color: #42b983;
        }
    }
}
</style>