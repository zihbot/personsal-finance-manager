<template>
    <div class="edit-container">
        <transaction-category-selector
            v-if="!categoryId"
            v-model="categoryId"
            :include-new="true"
        >
        </transaction-category-selector>
        <div class="edited" v-if="categoryId">
            <transaction-category
                @click="categoryId = null"
                :category-data="category"
            ></transaction-category>
        </div>
        <div v-if="categoryId">
            Color
            <div class="color-select">
                <div
                    class="color-item"
                    v-for="(color, i) in colors"
                    :key="i"
                    :style="{ backgroundColor: color }"
                ></div>
            </div>
            Icon
            <div class="color-select">
                <div
                    class="color-item"
                    v-for="(icon, i) in icons"
                    :key="i"
                ><i class="fa" :class="icon"></i></div>
            </div>
        </div>
        <app-button
            v-if="categoryId"
            class="primary"
            :disabled="!icon"
            @click="save()"
            >Save</app-button
        >
    </div>
</template>

<script setup lang="ts">
import AppButton from '@/components/AppButton.vue';
import TransactionCategory from '@/components/TransactionCategory.vue';
import TransactionCategorySelector from '@/components/TransactionCategorySelector.vue';
import { computed, ref } from 'vue';
import { useStore } from 'vuex';

const store = useStore();

const categoryId = ref(null);
const category = computed(
    () =>
        store.state.categories.find((c) => c.id === categoryId.value) ?? {
            color: '#EEEEEE',
            icon: '',
        }
);
const color = computed(() => category.value.color);
const icon = computed(() => category.value.icon);

const colors = ref(['#54426B', '#89B0EF', '#B0E298', '#3D3B8E']);
const icons = ref(['fa-lightbulb', 'fa-train-subway', 'fa-utensils', 'fa-cart-shopping']);
if (!colors.value.includes('')) colors.value.push('');

function save() {
    // intentional
}
</script>

<style scoped lang="scss">
.edit-container {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}
.edited {
    font-size: 2em;
    margin: auto;
}
.color {
    &-select {
        display: flex;
        flex-wrap: wrap;
        gap: 1rem;
    }
    &-item {
        width: 2em;
        height: 2em;
    }
}
</style>
