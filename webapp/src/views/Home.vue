<template>
  <div class="home">
    <old-transaction v-for="tr in transactions" :key="tr.id" :transaction="tr" />
  </div>
</template>

<script lang="ts">
import api from '@/services/api';
import data from '@/services/data';
import { combineLatest, merge } from 'rxjs';
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
    combineLatest(
      api.getAllTransactions(), 
      data.loadAccounts()
    ).subscribe(([transactions]) => {
      this.transactions = transactions;
    }, error => {
      console.error('Cannot create transaction', error);
    });
  },
}
</script>
