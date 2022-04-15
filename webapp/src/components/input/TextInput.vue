<template>
  <div>
    <input v-model="value">
    {{label}}
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component'
import { Prop } from 'vue-property-decorator'

export default class TextInput extends Vue {
  @Prop() label: string = '';
  value: string = '';

}
</script>

<style lang="scss">

</style>