import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import TextInput from "./components/input/TextInput.vue";

createApp(App)
    .use(store)
    .use(router)
    .component('TextInput', TextInput)
    .mount("#app");
