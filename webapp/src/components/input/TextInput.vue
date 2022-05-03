<template>
  <div class="input" :class="{focus: focused}" @click.prevent="$refs.inputRef.focus()">
    <label :for="id">
      {{label ?? id}}
    </label>
    <input
      v-model="value"
      ref="inputRef"
      :type="type"
      :id="id"
      :name="id"
      @focus="focused = true"
      @blur="focused = false"
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

  private focused = false;
}
</script>

<style lang="scss">
.input {
  border: 2px $app-black solid;
  border-radius: .7rem;
  display: flex;
  flex-direction: column;
  padding: 10px;
  width: 400px;
  margin: 0.5rem 0 1rem;
  background-color: $app-body-background;
  label {
    font-size: 0.8em;
    color: $app-disabled;
  }
  input {
    font-size: 1.3em;
    &, &:active, &:focus {
      appearance: none;
      border: 0;
      background: none;
      outline: 0;
    }
  }
  &.focus {
    border-color: $app-primary;
    label {
      color: $app-black;
    }
    color: $app-black;
  }
}
</style>