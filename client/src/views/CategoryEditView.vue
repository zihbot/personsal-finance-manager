<template>
    <div class="edit-container">
        <transaction-category-selector v-model="categoryId" :include-new="true">
        </transaction-category-selector>
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
        </div>
    </div>
</template>

<script setup lang="ts">
import TransactionCategorySelector from '@/components/TransactionCategorySelector.vue';
import { ref } from 'vue';

const categoryId = ref(null);

const colors = ref(['#AAAAAA']);
</script>

<style scoped lang="scss">
.edit-container {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}
.color {
    &-select {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
    }
    &-item {
        width: 2em;
        height: 2em;
    }
}
</style>
