<template>
  <h1 class="mdc-typography--headline3">New transaction</h1>
  <number-keypad-input />
  <button class="mdc-button mdc-button--raised" id="nextButton">
    <div class="mdc-button__ripple"></div>
    <span class="mdc-button__label">Next</span>
  </button>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import NumberKeypadInput from '../components/number-input/NumberInput.vue';

@Options({
  components: {
    NumberKeypadInput,
  },

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