<template>
    <app-header v-if="router.currentRoute.value?.fullPath !== '/'"></app-header>
    <div id="main-container">
        <router-view />
    </div>
    <app-floating-action-button
        v-if="showPlus"
        class="fab-page"
        :icon="'fa-plus'"
        @click="router.push('transaction-edit')"
    ></app-floating-action-button>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import AppFloatingActionButton from './components/AppFloatingActionButton.vue';
import AppHeader from './components/AppHeader.vue';
import router from './router';
import store from './store';

router.beforeEach((to, from) => {
    if (store.state.userId === null && to.name !== 'login')
        return { name: 'login' };
});

const showPlus = computed(() => {
    const path = router.currentRoute.value?.fullPath;
    return path !== '/' && !path?.startsWith?.('/transaction-edit');
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
