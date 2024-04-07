import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView
    },
    {
      path: '/event',
      component: () => import('../views/EventAddView.vue')
    },
    {
      path: '/event/:id',
      component: () => import('../views/EventEditView.vue')
    },
    {
      path: '/person/:id',
      component: () => import('../views/PersonEditView.vue')
    }
  ]
})

export default router
