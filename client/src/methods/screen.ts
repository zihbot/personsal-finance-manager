import { onMounted, onUnmounted, ref } from 'vue';

export type ScreenType = 'desktop' | 'tablet' | 'mobile';
export type ScreenOrientation = 'landscape' | 'portrait';

export function useScreen() {
    const type = ref('desktop' as ScreenType);
    const orientation = ref('landscape' as ScreenOrientation);

    function update() {
        const width = window.innerWidth ?? window.screen.width;
        const height = window.innerHeight ?? window.screen.height;

        if (width > 1200) {
            type.value = 'desktop';
        } else if (width > 800) {
            type.value = 'tablet';
        } else {
            type.value = 'mobile';
        }

        orientation.value = width < height ? 'portrait' : 'landscape';
    }

    onMounted(() => {
        window.addEventListener('resize', update);
        update();
    });
    onUnmounted(() => window.removeEventListener('resize', update));

    // expose managed state as return value
    return { type, orientation };
}
