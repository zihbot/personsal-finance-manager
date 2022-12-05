import { shallowMount } from '@vue/test-utils';
import { expect } from 'vitest';
import AppButton from '../AppButton.vue';

test('AppButton slot', async () => {
    const wrapper = shallowMount(AppButton, { slots: { default: 'Example' } });
    expect(wrapper.text()).toBe('Example');
});

test('AppButton click', async () => {
    const wrapper = shallowMount(AppButton, { slots: { default: 'Example' } });
    wrapper.find('.btn').trigger('click');
    expect(wrapper.emitted()).toHaveProperty('click');
});
