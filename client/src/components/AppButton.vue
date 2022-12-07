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
    color: var(--item-color);
    border: 1px solid var(--item-color);
    border-radius: 2rem;
}
.primary .btn,
.btn.primary {
    background-color: var(--item-color);
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
    padding: 0;
    border: 1px solid var(--app-lighter-background);
    background-color: var(--app-lighter-background);
    color: var(--item-color);
    font-size: 1.2em;
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    .primary & {
        border: 1px solid var(--item-color);
        background-color: var(--item-color);
        color: var(--app-text-inverse-color);
    }
    &:hover {
        border: 1px solid var(--app-light-background);
        background-color: var(--app-light-background);
    }
}
</style>
