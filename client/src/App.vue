<template>
    <nav v-if="router.currentRoute.value?.fullPath !== '/'">
        <router-link to="/transactions">Transactions</router-link>
        <router-link to="/">Logout</router-link>
    </nav>
    <div id="main-container">
        <router-view />
    </div>
    <app-floating-action-button
        v-if="
            router.currentRoute.value?.fullPath !== '/transaction-edit' &&
            router.currentRoute.value?.fullPath !== '/'
        "
        class="fab-page"
        :icon="'fa-plus'"
        @click="router.push('transaction-edit')"
    ></app-floating-action-button>
</template>

<script setup lang="ts">
import AppFloatingActionButton from "./components/AppFloatingActionButton.vue";
import router from "./router";
</script>

<style lang="scss">
@import "styles.scss";

#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
}

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

#main-container {
    padding: 2rem;
    box-sizing: border-box;
}
</style>
