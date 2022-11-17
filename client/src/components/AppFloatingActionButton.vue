<template>
    <div class="fab">
        <i v-if="props.icon" class="fa-solid" :class="props.icon"></i>
        <slot></slot>
    </div>
</template>

<script setup lang="ts">
const props = defineProps(["icon"]);
</script>

<style scoped lang="scss">
.fab {
    cursor: pointer;
    &-page {
        position: fixed;
        right: 4rem;
        bottom: 4rem;
        padding: 1rem;
        border-radius: 50%;
        background-color: var(--app-primary-color);
        color: var(--app-light-text-color);
        font-size: 1.5rem;
    }
}
</style>
