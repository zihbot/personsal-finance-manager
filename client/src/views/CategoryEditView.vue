<template>
    <div class="color-select">
        <div
            class="color-item"
            v-for="(color, i) in colors"
            :key="i"
            :style="{ backgroundColor: color }"
        ></div>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const colors = ref(["#"]);
</script>

<style scoped lang="scss">
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
