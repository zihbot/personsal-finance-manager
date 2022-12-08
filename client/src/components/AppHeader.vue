<template>
    <div class="header">
        <div
            v-if="type !== 'desktop'"
            class="hamburger-menu-icon"
            @click="menuOpen = !menuOpen"
        >
            <span class="fa-solid fa-bars"></span>
        </div>
        <nav v-if="type === 'desktop' || menuOpen" :class="'nav-' + type">
            <router-link to="/transactions">Transactions</router-link>
            <router-link to="/category-edit">Categories</router-link>
            <router-link to="/">Logout</router-link>
        </nav>
    </div>
</template>

<script setup lang="ts">
import { useScreen } from '@/methods/screen';
import { ref } from 'vue';

const { type, orientation } = useScreen();
const menuOpen = ref(false);
</script>

<style scoped lang="scss">
@import '@/helpers.scss';

.header {
    background-color: var(--app-primary-color);
}
.hamburger-menu-icon {
    @include flex-center-container;
    color: var(--app-text-inverse-color);
    height: 4rem;
    width: 4rem;
    font-size: 2rem;
    margin-left: auto;
}
nav {
    padding: 30px;
    a {
        padding: 1rem;
        font-weight: bold;
        color: var(--app-text-inverse-color);
    }
    &.nav-mobile,
    &.nav-tablet {
        position: absolute;
        background-color: var(--app-primary-color);
        top: calc(4rem - 1px);
        left: 0;
        width: 100%;
        box-sizing: border-box;
        display: flex;
        flex-direction: column;
        z-index: 100;
    }
}
</style>
