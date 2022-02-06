<template>
  <span class="amountInput">{{ amount }}</span>
  <div class="inputGrid">
    <input-button v-for="key in keys" :key="key"
      :text="key"
      @pushed="pushed" />
  </div>
</template>
<script lang="ts">
import InputButton, { KEY_DEL, KEY_DOT } from './InputButton.vue'

export default {
  name: 'NumberKeypadInput',
  components: {
    InputButton,
  },
  // eslint-disable-next-line
  data() {
    return {
      amount: '0',
      keys: [
        '1', '2', '3', '4', '5', '6', '7', '8', '9', KEY_DOT, '0', KEY_DEL
      ]
    };
  },
  methods: {
    pushed(value: string): void {
      switch (value) {
        case KEY_DEL:
          this.amount = this.amount.substring(0, this.amount.length - 1);
          break;
        case KEY_DOT:
          if (!this.amount.includes(KEY_DOT)) {
            this.amount += value;
          }
          break;
        default:
          if (this.amount.includes(KEY_DOT) || this.amount.length < 9) {
            this.amount += value;
          }
          break;
      }
      const parts = this.amount.split(KEY_DOT);
      if (parts[0].length > 1 && this.amount.startsWith('0')) {
        this.amount = this.amount.substring(1);
      }
      if (parts.length > 1 && parts[1].length > 2) {
        this.amount = parts[0] + KEY_DOT + parts[1].substring(0, 2);
      }
      if (!this.amount) {
        this.amount = '0';
      }
    }
  }
}
</script>
<style lang="scss">
.inputGrid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  max-width: 300px;
  & :first-child {
    border-radius: 4px 0 0 0;
  }
  & :nth-child(3) {
    border-radius: 0 4px 0 0;
  }
  & :nth-last-child(3) {
    border-radius: 0 0 0 4px;
  }
  & :last-child {
    border-radius: 0 0 4px 0;
  }
}

.amountInput {
  width: 100%;
  max-width: 300px;
  font-size: xx-large;
  text-align: right;
  display: block;
}
</style>