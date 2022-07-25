<template>
  <div class="layout-page-padding-around">
    <div v-for="account in accounts" :key="account.id" class="account-container">
      <div class="name tg-h1">
        {{account.name}}
      </div>
      <div class="balance">
        {{ $filter.money(account.balance) }}
      </div>
    </div>
    <button @click="showNewAccountInput = true" v-if="!showNewAccountInput">
        <i class="fas fa-plus"></i>
        Add account
    </button>
    <div v-if="showNewAccountInput" class="new-account-container">
      <TextInput label="Account name" id="newAccountName" v-model="newAccountName"></TextInput>
      <button @click="addAccount()"><i class="fas fa-plus"></i></button>
    </div>
  </div>
</template>

<script lang="ts">
import { AccountDto } from '../models/api/accounts';
import { Options, Vue } from 'vue-class-component';
import data from '@/services/data';
import api from '@/services/api';

@Options({
  components: {
  }
})
export default class Account extends Vue {
  accounts = [] as AccountDto[];
  showNewAccountInput = false;
  newAccountName = '';

  mounted(): void {
    this.accounts = data.accounts ?? [];
  }

  addAccount(): void {
    api.addAccount({name: this.newAccountName}).subscribe(res => {
      data.loadAccounts(false).subscribe(res => {
        this.accounts = data.accounts ?? [];
      });
      this.showNewAccountInput = false;
      this.newAccountName = '';
    }, error => {});
  }
}
</script>

<style lang="scss">
.account-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: baseline;
}

.balance {
  font-weight: bold;
  font-size: larger;
}
.new-account-row {
  font-size: 1.5rem;
  display: flex;
  align-content: space-between;
}
.new-account-container {
  display: flex;
}
</style>