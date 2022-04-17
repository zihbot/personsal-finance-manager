<template>
  <div class="input">
    <label :for="id">
      {{label ?? id}}
    </label>
    <input
      v-model="value"
      :type="type"
      :id="id"
      :name="id"
    >
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component'
import { Model, Prop } from 'vue-property-decorator'

export default class TextInput extends Vue {
  @Prop() label!: string;
  @Prop() type: string = 'text';
  @Prop() id: string = 'input';
  @Model('modelValue') value!: string;
}
</script>

<style lang="scss">

</style>