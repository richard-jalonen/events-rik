import { defineStore } from 'pinia'
import axios from 'axios'

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
        const data = await axios.get('http://localhost:8080/api/v1/payments')
        this.paymentOptions = data.data
      } catch (error) {
        console.log(error)
      }
    }
  }
})
