<template>
  <h1>New transaction</h1>
  <number-keypad-input />
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
