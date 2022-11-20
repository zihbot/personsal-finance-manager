<template>
    <div>
        <div
            class="btn"
            :class="{
                disabled: disabled,
                danger: variant === 'danger',
                primary: variant === 'primary',
            }"
            @click="handleClick"
        >
            <slot></slot>
        </div>
    </div>
</template>

<script setup lang="ts">
const props = defineProps({ disabled: Boolean, variant: String });
const emit = defineEmits<{
    (e: 'click', event: Event): void;
}>();

function handleClick(event: Event) {
    if (!props.disabled) {
        emit('click', event);
    }
}
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
.primary .btn,
.btn.primary {
    background-color: var(--app-primary-color);
    color: var(--app-text-inverse-color);
    font-weight: bolder;
}
.disabled {
    cursor: not-allowed;
    background-color: transparent;
    border: 1px solid var(--app-disabled-color);
    .primary & {
        background-color: var(--app-disabled-color);
    }
}
.icon .btn {
    font-size: 1em;
    border: 1px solid var(--app-primary-color);
    background-color: var(--app-primary-color);
    padding: 0;
    color: var(--app-text-inverse-color);
    font-size: 1.2em;
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    &.danger {
        border: 1px solid var(--app-danger-color);
        background-color: var(--app-danger-color);
    }
}
</style>
