<template>
  <div class="home">
    <old-transaction v-for="tr in transactions" :key="tr.id" :transaction="tr" />
  </div>
</template>

<script lang="ts">
import api from '@/services/api';
import OldTransaction from '../components/transaction/OldTransaction.vue';
import { TransactionDto } from '../models/api/transactions'

export default {
  components: {
    OldTransaction
  },
  // eslint-disable-next-line
  data() {
    return {
      transactions: [] as TransactionDto[]
    }
  },
  mounted(): void {
    api.getAllTransactions().subscribe(data => {
      this.transactions = data
    }, error => {
      console.error('Cannot create transaction', error);
    });
  },
}
</script>
