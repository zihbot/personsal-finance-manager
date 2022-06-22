import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import TextInput from "./components/input/TextInput.vue";
import filters from "./services/filters";

const app = createApp(App)
    .use(store)
    .use(router)
    .component('TextInput', TextInput);

app.config.globalProperties.$filter = filters;

app.mount("#app");
