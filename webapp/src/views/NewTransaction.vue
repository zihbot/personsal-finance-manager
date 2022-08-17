<template>
  <div class="layout-page-padding-around full-height">
    <h1>New transaction</h1>
    <div id="summary" v-if="page !== 'amount'">
      <div id="amountSummary"><p>{{$filter.money(amount*100)}}</p></div>
    </div>
    <template v-if="page === 'amount'">
      <number-keypad-input v-model="amount" />
      <button class="primary full-width" id="nextButton" @click="next('account')">OK</button>
    </template>
    <div v-if="page === 'account'" class="full-height flex-column">
      <div id="directionSelect">
        <div><p>{{}}</p></div>
      </div>
    </div>
    <div v-if="page === 'category'" class="full-height flex-column">
      <category-icon v-for="category of categories"
          class="tr-category"
          :class="{selected: selected.category === category.id}"
          @click="selected.category = category.id"
      />
      <button class="primary full-width" id="saveButton" @click="save()">Save</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";

import NumberKeypadInput from '../components/number-input/NumberKeypadInput.vue';
import CategoryIcon from '../components/transaction/CategoryIcon.vue';
import api from "@/services/api";
import { CategoryDto } from "@/models/api/categories";
import data from "@/services/data";
import { AccountDto } from "@/models/api/accounts";

type Pages = 'amount' | 'account' | 'category';

@Options({
  components: {
    NumberKeypadInput,
    CategoryIcon,
  }
})
export default class NewTransaction extends Vue {
  amount = 0;
  saving = false;
  page: Pages = 'amount';
  categories = [] as CategoryDto[];
  accounts = [] as AccountDto[];
  selected: {
    direction?: 'in' | 'out'
    category?: number
  } = {};
  
  mounted(): void {
    data.categories.subscribe(sub => this.categories = sub.data);
    data.accounts.subscribe(sub => this.accounts = sub.data);
  }

  next(page: Pages) {
    this.page = page;
  }

  save() {
    api.saveTransaction({
      amount: this.amount * 100,
      sourceId: this.accounts[0].id,
      category: this.selected.category,
      time: new Date().valueOf(),
    }).subscribe(data => {
      console.log('Finished transaction', data);
      api.getAllAccounts().subscribe();
      this.$router.push('/home');
    }, error => {
      console.error('Cannot create transaction', error);
    });
  }
}
</script>
<style lang="scss">
  #nextButton {
    width: 100%;
    max-width: $bp-medium;
    margin-top: 1rem;
  }
  #saveButton {
    margin-top: auto;
  }
  .selected {
    border: 2px solid darkgrey;
    box-sizing: border-box;
  }
  #summary {
    #amountSummary {
      height: 1.5rem;
      font-size: 1.2rem;
      display: flex;
      p {
        margin: auto;
      }
    }
  }
</style>