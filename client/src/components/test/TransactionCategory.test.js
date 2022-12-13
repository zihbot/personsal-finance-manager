import { shallowMount } from '@vue/test-utils';
import { expect } from 'vitest';
import TransactionCategory from '../TransactionCategory.vue';

test('TransactionCategory data', async () => {
    const wrapper = shallowMount(TransactionCategory, {
        props: {
            categoryData: {
                icon: 'fa-eye',
                color: '#ABCDEF',
            },
        },
    });
    expect(wrapper.find('.category').attributes('style')).toContain('background-color: #ABCDEF');
});
