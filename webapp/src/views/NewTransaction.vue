<template>
  <div class="layout-page-padding-around full-height">
    <h1>New transaction</h1>
    <template v-if="page === 'amount'">
      <number-keypad-input v-model="amount" />
      <button class="primary full-width" id="nextButton" @click="next('category')">OK</button>
    </template>
    <div class="full-width">
      <category-icon v-for="category of categories" class="tr-category" />
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

type Pages = 'amount' | 'category';

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
  
  mounted(): void {
    data.categories.subscribe(sub => {
      this.categories = sub.data;
    })
  }

  next(page: Pages) {
    this.page = page;
  }

  save() {
    api.saveTransaction({
      amount: this.amount * 100,
      targetId: 2,
      type: 'IN'
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
</style>