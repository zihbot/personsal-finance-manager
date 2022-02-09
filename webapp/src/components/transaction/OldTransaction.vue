<template >
  <div class="transaction">
    <span>{{ transaction.type }}</span>
    <span>{{ transaction.source }}
      <i class="fas fa-arrow-right"></i>
      {{ transaction.target }}</span>
    <span>{{ transaction.amount }}</span>
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
}
</style>