<template>
    <app-button class="icon icon-button">
        <span class="fa-solid" :class="props.icon"></span>
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
</style>