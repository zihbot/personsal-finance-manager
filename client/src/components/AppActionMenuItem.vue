<template>
    <app-button class="icon icon-button">
        <i class="fa-solid" :class="props.icon"></i>
    </app-button>
</template>

<script setup lang="ts">
import AppButton from './AppButton.vue';

const props = defineProps(["icon"]);
</script>

<style scoped lang="scss">
.icon-button {
    width: 2.5em;
    height: 2.5em;
}
.danger {
    --app-primary-color: var(--app-danger-color);
}
</style>