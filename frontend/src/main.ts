import { createPinia } from 'pinia'
import { createApp } from 'vue'

import createBootstrap from 'bootstrap-vue-next'
import VueAxios from 'vue-axios'
import router from './router'
import App from './App.vue'
import axios from 'axios'

import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'
import ee from '@/assets/messages.ee.json'
import 'bootstrap/dist/css/bootstrap.css'
import { createI18n } from 'vue-i18n'
import '@/assets/app.css'

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
