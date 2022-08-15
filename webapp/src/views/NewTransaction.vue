<template>
  <div class="layout-page-padding-around full-height">
    <h1>New transaction</h1>
    <template v-if="page === 'amount'">
      <number-keypad-input v-model="amount" />
      <button class="primary full-width" id="nextButton" @click="next('category')">OK</button>
    </template>
    <div v-if="page === 'category'" class="full-height">
      <category-icon v-for="category of categories"
          class="tr-category"
          :class="{selected: selected.category === category.id}"
          @click="selected.category = category.id "
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
  selected: {
    category?: number
  } = {};
  
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
      sourceId: 0,
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
</style>