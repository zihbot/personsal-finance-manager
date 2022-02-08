<template>
  <h1 class="mdc-typography--headline3">New transaction</h1>
  <number-keypad-input v-model="amount" />
  <button class="mdc-button mdc-button--raised" id="nextButton" @click="save">
    <div class="mdc-button__ripple"></div>
    <span class="mdc-button__label">Save</span>
  </button>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";

import NumberKeypadInput from '../components/number-input/NumberKeypadInput.vue';
import api from "../services/api";

@Options({
  components: {
    NumberKeypadInput,
  },
  // eslint-disable-next-line
  data() {
    return {
      amount: 0
    }
  },
  methods: {
    save() {
      api.saveTransaction({
        amount: this.amount * 100,
        targetId: 0,
        type: 'IN'
      }).subscribe(data => {
        console.log('DATA', data);
      }, error => {
        console.error('Cannot create transaction', error);
      });
    }
  }
})
export default class NewTransaction extends Vue {}
</script>
<style lang="scss">
  #nextButton {
    width: 100%;
    max-width: $bp-medium;
    margin-top: 1rem;
  }
</style>