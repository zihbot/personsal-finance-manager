<template>
    <div class="menu-row">
        <slot></slot>
    </div>
</template>

<script setup lang="ts">
</script>

<style scoped lang="scss">
.menu-row {
    display: flex;
    margin-bottom: 1rem;
    background-color: var(--app-lighter-background);
    padding: .5rem;
    border-radius: 2rem;
}
</style>