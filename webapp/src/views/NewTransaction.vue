<template>
  <h1 :class="$tt('page-name')">New transaction</h1>
  <number-keypad-input />
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import NumberKeypadInput from '../components/NumberInput.vue';

@Options({
  components: {
    NumberKeypadInput,
  },

})
export default class NewTransaction extends Vue {}
</script>
