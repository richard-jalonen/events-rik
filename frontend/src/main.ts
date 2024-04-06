import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import VueAxios from 'vue-axios'
import axios from 'axios'
import createBootstrap from 'bootstrap-vue-next'

import ee from '@/assets/messages.ee.json'
import '@/assets/app.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'
import { createI18n } from 'vue-i18n'

const app = createApp(App)
const i18n = createI18n({
  locale: 'ee',
  messages: { ee },
  legacy: false
})
app.use(createPinia())
app.use(router)
app.use(VueAxios, axios)
app.use(createBootstrap())
app.use(i18n)

app.mount('#app')
