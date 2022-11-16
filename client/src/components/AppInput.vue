<template>
    <input v-model="value"/>
</template>

<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps(['modelValue']);
const emit = defineEmits(['update:modelValue']);

const value = computed({
    get() {
        return props.modelValue;
    },
    set(value) {
        emit('update:modelValue', value);
    }
})
</script>

<style scoped lang="scss">
input {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border: 1px solid var(--app-primary-color);
    border-radius: 5px;
    outline: none;

    padding: 1rem;
    font-size: 1.2em;
}
</style>