<template>
  <div id="loginContainer">
  <div>
    <h1>Login</h1>
    <TextInput label="Username" id="username" v-model="username"></TextInput>
    <TextInput label="Password" id="password" type="password" v-model="password"></TextInput>
    <span>{{ error }}</span>
    <button class="primary full-width" @click="login()">Login</button>
  </div>
  </div>
</template>

<script lang="ts">
import api from '@/services/api';
import auth from '@/services/auth';
import { Vue } from 'vue-class-component'

export default class Login extends Vue {
  username: string = '';
  password: string = '';

  error: string = '';

  login() {
    const payload = {username: this.username, password: this.password};
    api.login(payload).subscribe(
      data => {
        auth.setLogin({username: this.username, token: data, loggedin: true});
        this.$router.push('/home');
      }, error => {
        this.error = 'Authentication failed';
      }
    );
  }
}
</script>

<style lang="scss">
#loginContainer {
  margin: auto;
  border-radius: 1rem;
  background-color: $app-main-background;
  display: flex;
  align-items: center;
  justify-content: center;

  .tablet &, .desktop & {
    width: 100%;
    height: 80%;
  }
}

</style>