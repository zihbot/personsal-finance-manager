<template>
    <datepicker v-model="value" :auto-apply="true"></datepicker>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

const props = defineProps(['modelValue', 'valid']);
const emit = defineEmits(['update:modelValue']);

const value = computed({
    get() {
        return props.modelValue;
    },
    set(value) {
        emit('update:modelValue', value);
    },
});
</script>

<style scoped lang="scss"></style>
