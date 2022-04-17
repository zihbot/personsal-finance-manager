<template>
  <div>
    <TextInput label="Username" id="username" v-model="username"></TextInput>
    <TextInput label="Password" id="password" type="password" v-model="password"></TextInput>
    {{username}}
    {{password}}
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component'

export default class Login extends Vue {
  username: string = '';
  password: string = '';
}
</script>

<style lang="scss">

</style>