import { shallowMount } from '@vue/test-utils';
import { expect } from 'vitest';
import AppActionMenu from '../AppActionMenu.vue';

test('AppActionMenu slot', async () => {
    const wrapper = shallowMount(AppActionMenu, {
        slots: { default: '<div id="test-menu-item">Test menu item</div>' },
    });
    expect(wrapper.find('#test-menu-item').text()).toContain('Test menu item');
});

test('AppActionMenu back emit', async () => {
    const wrapper = shallowMount(AppActionMenu);
    wrapper.find('#menu-back').trigger('click');
    expect(wrapper.emitted()).toHaveProperty('back');
});

test('AppButton hasBack false', async () => {
    const wrapper = shallowMount(AppActionMenu, { props: { hasBack: false } });
    expect(wrapper.find('#menu-back').exists()).toBe(false);
});
