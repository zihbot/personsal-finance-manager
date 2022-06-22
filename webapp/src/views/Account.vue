<template>
  <div class="home">
    <div v-for="account in accounts" :key="account.id" class="account-container">
      <div class="name tg-h1">
        {{account.name}}
      </div>
      <div class="balance tg-h1">
        {{ $filter.money(account.balance) }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { AccountDto } from '../models/api/accounts';
import { Options, Vue } from 'vue-class-component';
import data from '@/services/data';

@Options({
  components: {
  }
})
export default class Account extends Vue {
  accounts = [] as AccountDto[];

  mounted(): void {
    this.accounts = data.accounts ?? [];
  }
}
</script>

<style lang="scss">
.account-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>