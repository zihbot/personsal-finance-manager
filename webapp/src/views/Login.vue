<template>
  <div id="loginContainer" class="layout-page-middle-box">
  <div>
    <h1>Login</h1>
    <TextInput label="Username" id="username" v-model="username"></TextInput>
    <TextInput label="Password" id="password" type="password" v-model="password"></TextInput>
    <span>{{ error }}</span>
    <button class="primary full-width" :disabled="loading" @click="login()">Login</button>
  </div>
  </div>
</template>

<script lang="ts">
import api from '@/services/api';
import auth from '@/services/auth';
import data from '@/services/data';
import { Vue } from 'vue-class-component'

export default class Login extends Vue {
  username: string = '';
  password: string = '';

  error: string = '';
  loading = false;

  login() {
    this.loading = true;
    const payload = {username: this.username, password: this.password};
    api.login(payload).subscribe(
      token => {
        auth.setLogin({username: this.username, token: token, loggedin: true});
        api.getAllAccounts().subscribe(
          accounts => {
            this.$router.push('/home');
            data.setAccounts(accounts);
            this.loading = false;
          }, error => {
            this.error = 'Communication error';
            this.loading = false;
          }
        )
      }, error => {
        this.error = 'Authentication failed';
        this.loading = false;
      }
    );
  }
}
</script>

<style lang="scss">
#loginContainer {
  border-radius: 1rem;
  display: flex;
  align-items: center;
  justify-content: center;

  .tablet &, .desktop & {
    width: 100%;
    height: 80%;
  }
}

</style>