<template>
  <div class="inputButton" @click="$emit('pushed', text)">
    <span v-if="text == keys.KEY_DEL">
      <i class="fas fa-backspace"></i>
    </span>
    <span v-else>{{text}}</span>
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
  // eslint-disable-next-line
  data() {
    return {
      keys: {
        KEY_DOT,
        KEY_DEL
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