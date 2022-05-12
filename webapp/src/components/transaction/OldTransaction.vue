<template >
  <div class="transaction">
    <span class="tr-type">{{ transaction.type }}</span>
    <span class="tr-accounts">
      {{ sourceName }}
      <i class="fas fa-arrow-right"></i>
      {{ targetName }}
    </span>
    <span class="tr-amount">{{ transaction.amount / 100 }}</span>
  </div>
</template>
<script lang="ts">
import data from '@/services/data';
import { Vue } from 'vue-class-component'
import { Prop } from 'vue-property-decorator';
import { TransactionDto } from '../../models/api/transactions'

export default class OldTransaction extends Vue {
  @Prop()
  transaction!: TransactionDto;

  get targetName(): string {
    return data.getAccount(this.transaction.target)?.name ?? '';
  }
  get sourceName(): string {
    return data.getAccount(this.transaction.source)?.name ?? '';
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