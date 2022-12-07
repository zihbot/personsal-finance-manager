<template>
    <div
        v-if="category"
        class="category"
        :style="{ backgroundColor: category.color }"
    >
        <span class="fa-solid" :class="category.icon"></span>
    </div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useStore } from 'vuex';

const props = defineProps(['categoryId', 'categoryData']);
const store = useStore();

const category = computed(
    () =>
        props.categoryData ??
        store.state.categories.find((c) => c.id === props.categoryId) ?? {
            color: '#333333',
            icon: 'fa-question',
        }
);
</script>

<style scoped lang="scss">
.category {
    width: 2em;
    height: 2em;
    border-radius: 50%;

    .fa-solid {
        color: var(--app-light-text-color);
        translate: 0 50%;
    }
    &.selectable {
        border: 1px solid transparent;
    }
    &.selected {
        border: 1px solid var(--app-text-color);
    }
}
</style>
