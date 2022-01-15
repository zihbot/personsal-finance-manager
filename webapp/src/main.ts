import { createApp } from "vue";
import BalmUI from "balm-ui"; // Official Google Material Components
import BalmUIPlus from "balm-ui/dist/balm-ui-plus"; // BalmJS Team Material Components
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "balm-ui/dist/balm-ui.css";

createApp(App).use(store).use(router).use(BalmUI).use(BalmUIPlus).mount("#app");
