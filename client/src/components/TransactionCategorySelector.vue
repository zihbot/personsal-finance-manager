<template>
    <div class="category-list single" v-if="modelValue">
        <transaction-category
            v-if="modelValue !== newCategoryData.id"
            class="action selected"
            :category-id="modelValue"
            @click="emit('update:modelValue', null)"
        ></transaction-category>
        <transaction-category
            v-if="modelValue === newCategoryData.id"
            class="action selected"
            :category-data="newCategoryData"
            @click="emit('update:modelValue', null)"
        ></transaction-category>
    </div>
    <div class="category-list" v-if="!modelValue">
        <transaction-category
            class="action selectable"
            v-for="category in categories"
            :category-id="category.id"
            :key="category.id"
            @click="emit('update:modelValue', category.id)"
        ></transaction-category>
        <transaction-category
            v-if="props.includeNew"
            :category-data="newCategoryData"
            @click="emit('update:modelValue', newCategoryData.id)"
        >
        </transaction-category>
    </div>
</template>

<script setup lang="ts">
import { computed } from '@vue/reactivity';
import { useStore } from 'vuex';
import TransactionCategory from './TransactionCategory.vue';

const newCategoryData = { color: '#D0D0D0', icon: 'fa-plus', id: 'new'};

const props = defineProps(['modelValue', 'valid', 'includeNew']);
const emit = defineEmits(['update:modelValue']);
const store = useStore();

const categories = computed(() => store.state.categories);
</script>

<style scoped lang="scss">
.category-list {
    display: flex;
    flex-wrap: wrap;
    gap: 2rem;
    grid-template-columns: 1fr 1fr 1fr;
    justify-items: center;
    font-size: 2em;
    .selectable {
        flex-shrink: 0;
    }
    &.single {
        grid-template-columns: 1fr;
    }
}
</style>
