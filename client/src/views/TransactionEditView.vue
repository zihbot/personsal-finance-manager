<template>
    <div class="form-container">
        <div><app-input valid="number" v-model="value"></app-input> Ft</div>
        <transaction-category-selector v-model="categoryId"></transaction-category-selector>
        <app-button class="primary" @click="save()">Save</app-button>
    </div>
</template>

<script setup lang="ts">
import AppButton from '@/components/AppButton.vue';
import AppInput from '@/components/AppInput.vue';
import TransactionCategorySelector from '@/components/TransactionCategorySelector.vue';
import router from '@/router';
import { ACT_TRANSACTIONS_CREATE } from '@/store/actions'
import { ref } from 'vue';
import { useStore } from 'vuex';

const store = useStore();

const categoryId = ref();
const value = ref('');

function save() {
    store.dispatch(ACT_TRANSACTIONS_CREATE, {
        category: categoryId.value,
        value: Number(value.value)
    }).then(() => router.push('/transactions'));
}
</script>

<style scoped lang="scss">
.form-container {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: max-content;
    margin: 0 auto;
}
</style>