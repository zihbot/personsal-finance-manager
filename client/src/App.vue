<template>
    <nav v-if="router.currentRoute.value?.fullPath !== '/'">
        <router-link to="/">Login</router-link>
    </nav>
    <div id="main-container">
        <router-view />
    </div>
</template>

<script setup lang="ts">
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
