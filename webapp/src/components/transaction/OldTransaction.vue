<template >
  <div class="transaction">
    <span class="tr-type">{{ transaction.type }}</span>
    <span class="tr-accounts">{{ transaction.source }}
      <i class="fas fa-arrow-right"></i>
      {{ transaction.target }}</span>
    <span class="tr-amount">{{ transaction.amount / 100 }}</span>
  </div>
</template>
<script lang="ts">
import { TransactionDto } from '../../models/api/transactions'

export default {
  name: 'OldTransaction',
  props: {
    transaction: Object as () => TransactionDto
  }
}
</script>
<style lang="scss">
.transaction {
  width: 100%;
  border: 1px solid black;
  border-radius: 10px;
  background-color: whitesmoke;
  margin: 0.25rem;
  padding: 1rem;

  display: flex;
  justify-content: space-between;

  .tr-type, .tr-amount{
    width: 100px;
  }
  .tr-amount {
    text-align: right;
  }
}
</style>