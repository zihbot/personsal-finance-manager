<template>
  <div class="home">
    <old-transaction />
  </div>
</template>

<script lang="ts">
import api from '@/services/api';
import OldTransaction from '../components/transaction/OldTransaction.vue';

export default {
  components: {
    OldTransaction
  },
  // eslint-disable-next-line
  data() {
    return {
      transactions: []
    }
  },
  mounted(): void {
    api.getAllTransactions().subscribe(data => {
      console.log('TRANSACTIONS', data);
      this.transactions = data
    }, error => {
      console.error('Cannot create transaction', error);
    });
  },
}
</script>
