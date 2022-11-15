<template>
    <div class="center login-container">
        <input name="username" v-model="username" />
        <input name="password" v-model="password" />
        <button @click="login">Login</button>
    </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const username = ref('');
const password = ref('');

function login() {
    console.log('LOF')
}
</script>

<style scoped lang="scss">
.center {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
.login-container {
    width: 100%;
    max-width: 400px;
    max-height: 300px;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    justify-content: center;
}
</style>
