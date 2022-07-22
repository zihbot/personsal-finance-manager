<template>
  <div class="layout-page-padding-around full-height">
    <h1>New transaction</h1>
    <number-keypad-input v-model="amount" />
    <button class="primary full-width" id="nextButton" @click="save">Save</button>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";

import NumberKeypadInput from '../components/number-input/NumberKeypadInput.vue';
import api from "@/services/api";
import dataService from "@/services/data";

@Options({
  components: {
    NumberKeypadInput,
  }
})
export default class NewTransaction extends Vue {
  amount = 0;
  saving = false;

  save() {
    api.saveTransaction({
      amount: this.amount * 100,
      targetId: 2,
      type: 'IN'
    }).subscribe(data => {
      console.log('Finished transaction', data);
      dataService.loadAccounts(false);
      this.$router.push('/home');
    }, error => {
      console.error('Cannot create transaction', error);
    });
  }
}
</script>
<style lang="scss">
  #nextButton {
    width: 100%;
    max-width: $bp-medium;
    margin-top: 1rem;
  }
</style>