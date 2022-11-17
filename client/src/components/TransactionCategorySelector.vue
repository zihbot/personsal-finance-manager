<template>
    <div class="category-list" v-if="modelValue">
        <transaction-category
            class="action"
            :category-id="modelValue"
            @click="emit('update:modelValue', null)"
        ></transaction-category>
    </div>
    <div class="category-list" v-if="!modelValue">
        <transaction-category
            class="action"
            v-for="category in categories"
            :category-id="category.id"
            :key="category.id"
            @click="emit('update:modelValue', category.id)"
        ></transaction-category>
    </div>
</template>

<script setup lang="ts">
import { computed } from "@vue/reactivity";
import { useStore } from "vuex";
import TransactionCategory from "./TransactionCategory.vue";

const props = defineProps(['modelValue', 'valid']);
const emit = defineEmits(['update:modelValue']);
const store = useStore();

const categories = computed(() => store.state.categories);
</script>

<style scoped lang="scss">
.category-list {
    display: grid;
    gap: 1rem;
    grid-template-columns: 1fr 1fr 1fr;
    justify-items: center;
    font-size: 2em;
}
</style>
