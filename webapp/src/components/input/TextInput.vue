<template>
  <div class="input" :class="{focus: focused}" @click.prevent="focusref()">
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
  @Prop() label: string = '';
  @Prop() type: string = 'text';
  @Prop() id: string = 'input';
  @Model('modelValue') value: string = '';

  public focused = false;

  focusref() {
    (this.$refs.inputRef as HTMLInputElement).focus();
  }
}
</script>

<style lang="scss">
.input {
  border-bottom: 2px $app-black solid;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  display: flex;
  flex-direction: column;
  padding: 10px;
  width: 100%;
  box-sizing: border-box;
  min-width: 100px;
  margin: 0.5rem 0 1rem;
  background-color: $app-colored-background;
  label {
    font-size: 0.8em;
    color: $app-disabled;
  }
  input {
    appearance: none;
    font-size: 1.5em;
    border: 0;
    background: none;
    outline: 0;
  }
  &.focus {
    border-color: $app-primary;
    background-color: darken($color: $app-colored-background-darker, $amount: 3);
    label {
      color: $app-primary;
    }
    color: $app-black;
  }
  &:hover:not(.focus) {
    background-color: $app-colored-background-darker;
  }
}
</style>