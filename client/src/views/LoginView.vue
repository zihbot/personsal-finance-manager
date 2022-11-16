<template>
    <div class="center login-container">
        <app-input name="username" v-model="username" />
        <app-input name="password" type="password" v-model="password" />
        <app-button @click="login" class="primary">Login</app-button>
    </div>
</template>

<script setup lang="ts">
import AppButton from '@/components/AppButton.vue';
import AppInput from '@/components/AppInput.vue';
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
    router.push({path: '/transactions'});
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
    padding: 1rem;
    box-sizing: border-box;
    justify-content: center;
}
</style>
