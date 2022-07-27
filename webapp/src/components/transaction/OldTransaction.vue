<template >
  <div class="transaction">
    <CategoryIcon class="tr-category" />
    <span class="tr-name">
      {{ transaction.name }}
    </span>
    <span class="tr-amount">{{ transaction.amount / 100 }}</span>
  </div>
</template>
<script lang="ts">
import data from '@/services/data';
import { Options, Vue } from 'vue-class-component'
import { Prop } from 'vue-property-decorator';
import { TransactionDto } from '../../models/api/transactions'
import CategoryIcon from './CategoryIcon.vue';

@Options({
  components: {
    CategoryIcon,
  }
})
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
<style lang="scss" scoped>
.transaction {
  width: 100%;
  box-sizing: border-box;
  border-radius: 10px;
  background-color: whitesmoke;
  padding: 1rem;

  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: .5rem;

  font-size: 1.5rem;
  color: rgb(27, 27, 27);

  .tr-type, .tr-amount{
    width: 100px;
  }
  .tr-amount {
    text-align: right;
  }
}
</style>