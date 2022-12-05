<template>
    <div class="edit-container">
        <transaction-category-selector
            v-if="!categoryId"
            v-model="categoryId"
            :include-new="true"
        >
        </transaction-category-selector>
        <div v-if="categoryId">
            <app-action-menu @back="categoryId = null">
                <app-action-menu-item
                    class="danger"
                    :icon="'fa-trash'"
                    @click="remove()"
                ></app-action-menu-item>
            </app-action-menu>
            <div class="edited">
                <transaction-category
                    class="selectable"
                    @click="categoryId = null"
                    :category-data="category"
                ></transaction-category>
            </div>
            <h2>Name</h2>
            <app-input
                id="categoryName"
                v-model="name"
                autocomplete="off"
            ></app-input>
            <h2>Color</h2>
            <div class="list-select">
                <div
                    class="list-item"
                    v-for="(colorItem, i) in colors"
                    :key="i"
                    :style="{ backgroundColor: colorItem }"
                    @click="color = colorItem"
                ></div>
            </div>
            <h2>Icon</h2>
            <div class="list-select">
                <div
                    class="list-item"
                    v-for="(iconItem, i) in icons"
                    :key="i"
                    @click="icon = iconItem"
                >
                    <i class="fa" :class="iconItem"></i>
                </div>
            </div>
        </div>
        <app-button
            v-if="categoryId"
            class="primary"
            :disabled="!icon || !color || color === DEFAULT_COLOR || !name"
            @click="save()"
            >Save</app-button
        >
    </div>
</template>

<script setup lang="ts">
import AppActionMenu from '@/components/AppActionMenu.vue';
import AppActionMenuItem from '@/components/AppActionMenuItem.vue';
import AppButton from '@/components/AppButton.vue';
import AppInput from '@/components/AppInput.vue';
import TransactionCategory from '@/components/TransactionCategory.vue';
import TransactionCategorySelector from '@/components/TransactionCategorySelector.vue';
import {
    ACT_CATEGORIES_CREATE,
    ACT_CATEGORIES_DELETE,
    ACT_CATEGORIES_EDIT,
} from '@/store/actions';
import { computed, Ref, ref, watch } from 'vue';
import { useStore } from 'vuex';

const store = useStore();

const DEFAULT_COLOR = '#EEEEEE';

const categoryId = ref(null) as Ref<null | string>;
const name = ref('');
const color = ref(DEFAULT_COLOR);
const icon = ref('');
const category = computed(() => ({
    id: null,
    color: color.value,
    icon: icon.value,
}));

const colors = ref([
    '#54426B',
    '#89B0EF',
    '#B0E298',
    '#3D3B8E',
    '#EDEBA0',
    '#C3C48D',
    '#928C6F',
    '#883677',
    '#441151',
    '#8F7E4F',
    '#524632',
    '#7F95D1',
    '#1F7A8C',
    '#022B3A',
    '#395E66',
    '#387D7A',
    '#BC2C1A',
    '#7D1538',
]);
const icons = ref([
    'fa-lightbulb',
    'fa-train-subway',
    'fa-utensils',
    'fa-cart-shopping',
    'fa-music',
    'fa-phone',
    'fa-envelope',
    'fa-gift',
    'fa-book',
    'fa-plane',
    'fa-camera',
    'fa-house',
    'fa-tree',
    'fa-shirt',
    'fa-tv',
    'fa-medal',
    'fa-volleyball',
    'fa-bicycle',
]);

watch([categoryId], (catId) => {
    const category = store.state.categories.find((c) => c.id === catId[0]);
    color.value = category?.color ?? DEFAULT_COLOR;
    icon.value = category?.icon ?? '';
    name.value = category?.name ?? '';
});

function save() {
    store
        .dispatch(
            categoryId.value !== 'new'
                ? ACT_CATEGORIES_EDIT
                : ACT_CATEGORIES_CREATE,
            {
                id: categoryId.value !== 'new' ? categoryId.value : null,
                color: color.value,
                icon: icon.value,
                name: name.value,
            }
        )
        .then(() => (categoryId.value = null));
}

function remove() {
    if (categoryId.value === 'new') categoryId.value = null;
    store
        .dispatch(ACT_CATEGORIES_DELETE, {
            id: categoryId.value,
        })
        .then(() => (categoryId.value = null));
}
</script>

<style scoped lang="scss">
.edit-container {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}
.edited * {
    font-size: 2em;
    margin: auto;
}
.list {
    &-select {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 1rem;
    }
    &-item {
        width: 2em;
        height: 2em;
        cursor: pointer;
    }
}
#categoryName {
    width: 100%;
    box-sizing: border-box;
}
</style>
