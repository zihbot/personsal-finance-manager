<template>
  <div class="inputButton" @click="$emit('pushed', text)">
    {{text}}
  </div>
</template>
<script lang="ts">
export const KEY_DOT = '.';
export const KEY_DEL = 'DEL';

export default {
  name: 'InputButton',
  props: {
    text: String
  },
  emits: ['pushed'],
  methods: {
    keydown(e: KeyboardEvent): void {
      if (e.key === this.text
        || this.text === KEY_DEL && e.key === 'Backspace') {
        e.preventDefault();
        this.$emit('pushed', this.text);
      }
    }
  },
  mounted(): void {
    window.addEventListener('keydown', this.keydown);
  },
  unmounted(): void {
    window.removeEventListener('keydown', this.keydown);
  }
}
</script>
<style lang="scss">
.inputButton {
  border: 1px solid grey;
  //border-radius: 4px;
  padding: 1rem;
  text-align: center;
  user-select: none;

  &:hover {
    cursor: pointer;
    background-color: rgb(247, 242, 242);
  }
  &:active {
    background-color: lightgrey;
  }
}
</style>