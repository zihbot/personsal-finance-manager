<template>
    <app-action-menu @back="router.push({name: 'transactions'})">
        <app-action-menu-item
            class="danger"
            :icon="'fa-trash'"
            @click="remove()"
        ></app-action-menu-item>
    </app-action-menu>
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
        <app-date-picker v-model="time"></app-date-picker>
        <app-button
            class="primary"
            v-bind:disabled="!value || !categoryId"
            @click="save()"
            >Save</app-button
        >
    </div>
</template>

<script setup lang="ts">
import AppActionMenu from '@/components/AppActionMenu.vue';
import AppActionMenuItem from '@/components/AppActionMenuItem.vue';
import AppButton from '@/components/AppButton.vue';
import AppDatePicker from '@/components/AppDatePicker.vue';
import AppInput from '@/components/AppInput.vue';
import TransactionCategorySelector from '@/components/TransactionCategorySelector.vue';
import router from '@/router';
import {
    ACT_TRANSACTIONS_CREATE,
    ACT_TRANSACTIONS_DELETE,
    ACT_TRANSACTIONS_EDIT,
} from '@/store/actions';
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
const time = ref(transaction ? transaction.time : new Date().toISOString());

function save() {
    store
        .dispatch(
            transaction ? ACT_TRANSACTIONS_EDIT : ACT_TRANSACTIONS_CREATE,
            {
                id: transaction?.id ?? null,
                category: categoryId.value,
                value: Number(value.value),
                currency: 'HUF',
                time: time.value,
            }
        )
        .then(() => router.push('/transactions'));
}
function remove() {
    if (!transaction) {
        router.push('/transactions');
        return;
    }
    store
        .dispatch(ACT_TRANSACTIONS_DELETE, {
            id: transaction?.id,
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
