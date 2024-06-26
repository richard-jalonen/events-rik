import ApiClient from '@/client/api.client'
import { defineStore } from 'pinia'

export const usePaymentStore = defineStore('payment', {
  state: () => ({
    paymentOptions: []
  }),
  getters: {
    getPaymentOptions(state) {
      return state.paymentOptions
    }
  },
  actions: {
    async initialize() {
      await this.fetchPaymentOptions()
    },
    async fetchPaymentOptions() {
      try {
        const data = await ApiClient.getPaymentOptions()
        this.paymentOptions = data.data
      } catch (error) {
        console.log(error)
      }
    }
  }
})
