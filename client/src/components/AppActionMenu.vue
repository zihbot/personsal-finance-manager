<template>
    <div class="menu-row">
        <app-action-menu-item
            :icon="'fa-arrow-left'"
            v-if="props.hasBack"
            @click="emit('back')"
        ></app-action-menu-item>
        <span class="separator"></span>
        <slot></slot>
    </div>
</template>

<script setup lang="ts">
import AppActionMenuItem from './AppActionMenuItem.vue';

const props = defineProps({
    hasBack: { type: Boolean, default: true },
});
const emit = defineEmits(['back']);
</script>

<style scoped lang="scss">
.menu-row {
    display: flex;
    margin-bottom: 1rem;
    background-color: var(--app-lighter-background);
    padding: 0.5rem;
    border-radius: 2rem;
}
.separator {
    margin-left: auto;
}
</style>
