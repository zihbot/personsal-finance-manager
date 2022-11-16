<template>
    <div class="btn">
        <slot></slot>
    </div>
</template>

<script setup lang="ts">
const props = defineProps(['']);
const emit = defineEmits(['update:click']);
</script>

<style scoped lang="scss">
.btn {
    padding: 1rem;
    font-size: 1.2em;
    width: 100%;
    box-sizing: border-box;
    cursor: pointer;
    background-color: transparent;
    color: var(--app-primary-color);
    border: 1px solid var(--app-primary-color);
    border-radius: 2rem;
}
.primary {
    background-color: var(--app-primary-color);
    color: var(--app-text-inverse-color);
    font-weight: bolder;
}
</style>