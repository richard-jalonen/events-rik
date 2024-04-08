import { useAxiosInterceptor } from '@/client/axios.interceptor'
import 'vue-toast-notification/dist/theme-bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'
import ToastPlugin from 'vue-toast-notification'
import createBootstrap from 'bootstrap-vue-next'
import ee from '@/assets/messages.ee.json'
import 'bootstrap/dist/css/bootstrap.css'
import { createI18n } from 'vue-i18n'
import { createPinia } from 'pinia'
import VueAxios from 'vue-axios'
import { createApp } from 'vue'
import router from './router'
import App from './App.vue'
import '@/assets/app.css'
import axios from 'axios'

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
