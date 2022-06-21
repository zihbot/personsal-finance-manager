<template>
  <div class="home">
    <old-transaction v-for="tr in transactions" :key="tr?.id" :transaction="tr" />
  </div>
</template>

<script lang="ts">
import api from '@/services/api';
import { Options, Vue } from 'vue-class-component';
import OldTransaction from '../components/transaction/OldTransaction.vue';
import { TransactionDto } from '../models/api/transactions'

@Options({
  components: {
    OldTransaction,
  }
})
export default class Login extends Vue {
  transactions = [] as TransactionDto[];

  mounted(): void {
    api.getAllTransactions().subscribe((transactions) => {
      this.transactions = transactions;
    }, error => {
      console.error('Cannot create transaction', error);
    });
  }
}
</script>
