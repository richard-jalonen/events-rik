import '@/assets/app.css'
import ee from '@/assets/messages.ee.json'
import { useAxiosInterceptor } from '@/client/axios.interceptor'
import axios from 'axios'
import createBootstrap from 'bootstrap-vue-next'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'
import 'bootstrap/dist/css/bootstrap.css'
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import VueAxios from 'vue-axios'
import { createI18n } from 'vue-i18n'
import ToastPlugin from 'vue-toast-notification'
import 'vue-toast-notification/dist/theme-bootstrap.css'
import App from './App.vue'
import router from './router'

const app = createApp(App)
const i18n = createI18n({
  locale: 'ee',
  messages: { ee },
  legacy: false
})

useAxiosInterceptor()
app.use(ToastPlugin)
app.use(createPinia())
app.use(router)
app.use(VueAxios, axios)
app.use(createBootstrap())
app.use(i18n)

app.mount('#app')
