<template>
    <div class="form-container">
        <div><app-input valid="number"></app-input> Ft</div>
        <app-button class="primary">Save</app-button>
    </div>
</template>

<script setup lang="ts">
import AppButton from '@/components/AppButton.vue';
import AppInput from '@/components/AppInput.vue';
</script>

<style scoped lang="scss">
.form-container {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    max-width: 300px;
    margin: 0 auto;
}
</style>