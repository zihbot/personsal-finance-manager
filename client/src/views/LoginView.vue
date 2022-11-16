<template>
    <div class="center login-container">
        <input name="username" v-model="username" />
        <input name="password" type="password" v-model="password" />
        <button @click="login">Login</button>
    </div>
</template>

<script lang="ts" setup>
import router from '@/router';
import { ref } from 'vue';
import { useStore } from 'vuex';

const username = ref('');
const password = ref('');
const store = useStore();

async function login() {
    await store.dispatch('login', {
        username: username.value,
        password: password.value
    });
    router.push({path: '/about'});
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
