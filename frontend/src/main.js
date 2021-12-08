import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faPhone } from '@fortawesome/free-solid-svg-icons'
import { faEnvelope } from "@fortawesome/free-solid-svg-icons";
import { faArrowUp } from "@fortawesome/free-solid-svg-icons";
import { faArrowDown } from "@fortawesome/free-solid-svg-icons/faArrowDown";

import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

library.add(faPhone,faEnvelope,faArrowDown,faArrowUp);

createApp(App).use(store).use(router).component("font-awesome-icon", FontAwesomeIcon).mount('#app')
