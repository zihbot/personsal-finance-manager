<template>
  <div>
    <TextInput label="Username" id="username" v-model="username"></TextInput>
    <TextInput label="Password" id="password" type="password" v-model="password"></TextInput>
    <button @click="login()">Login</button>
    <span>{{ error }}</span>
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

</style>