<template>
    <input :class="valid+'-input'" v-model="value" />
</template>

<script setup lang="ts">
import { computed } from "vue";

const props = defineProps(['modelValue', 'valid']);
const emit = defineEmits(['update:modelValue']);

const value = computed({
    get() {
        return props.modelValue;
    },
    set(val) {
        emit('update:modelValue', val);
    },
});
</script>

<style scoped lang="scss">
input {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border: 1px solid var(--item-color);
    border-radius: 5px;
    outline: none;

    padding: 1rem;
    font-size: 1.2em;
}
.number-input {
    text-align: right;
}
</style>
