<template>
    <app-header></app-header>
    <div id="main-container">
        <router-view />
    </div>
    <app-floating-action-button
        v-if="
            !router.currentRoute.value?.fullPath?.startsWith?.(
                '/transaction-edit'
            ) && router.currentRoute.value?.fullPath !== '/'
        "
        class="fab-page"
        :icon="'fa-plus'"
        @click="router.push('transaction-edit')"
    ></app-floating-action-button>
</template>

<script setup lang="ts">
import AppFloatingActionButton from './components/AppFloatingActionButton.vue';
import AppHeader from './components/AppHeader.vue';
import router from './router';
import store from './store';

router.beforeEach((to, from) => {
    if (store.state.userId === null && to.name !== 'login')
        return { name: 'login' };
});
</script>

<style lang="scss">
@import 'styles.scss';

#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
}

#main-container {
    padding: 2rem;
    box-sizing: border-box;
}
</style>
