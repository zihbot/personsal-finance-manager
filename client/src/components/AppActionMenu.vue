<template>
    <div class="menu-row">
        <app-action-menu-item :icon="'fa-trash'"></app-action-menu-item>
    </div>
</template>

<script setup lang="ts">
import AppActionMenuItem from './AppActionMenuItem.vue';
</script>

<style scoped lang="scss">
.menu-row {
    display: flex;
    margin-bottom: 1rem;
}
</style>