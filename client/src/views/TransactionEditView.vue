<template>
    <div class="form-container">
        <div class="input-postfix">
            <app-input
                class="max-width"
                valid="number"
                v-model="value"
            ></app-input>
            Ft
        </div>
        <transaction-category-selector
            v-model="categoryId"
        ></transaction-category-selector>
        <app-button
            class="primary"
            v-bind:disabled="!value || !categoryId"
            @click="save()"
            >Save</app-button
        >
    </div>
</template>

<script setup lang="ts">
import AppButton from '@/components/AppButton.vue';
import AppInput from '@/components/AppInput.vue';
import TransactionCategorySelector from '@/components/TransactionCategorySelector.vue';
import router from '@/router';
import { ACT_TRANSACTIONS_CREATE } from '@/store/actions';
import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

const store = useStore();
const route = useRoute();

const transaction = computed(() =>
    store.state.transactions.find((t) => t.id === route.query.id)
).value;
const categoryId = ref(transaction ? transaction.category : null);
const value = ref(transaction ? transaction.value : null);

function save() {
    store
        .dispatch(ACT_TRANSACTIONS_CREATE, {
            category: categoryId.value,
            value: Number(value.value),
        })
        .then(() => router.push('/transactions'));
}
</script>

<style scoped lang="scss">
.input-postfix {
    display: flex;
    align-items: center;
    gap: 0.5em;
    width: 100%;
}
.form-container {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: 100%;
}
</style>
