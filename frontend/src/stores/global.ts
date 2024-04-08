import { defineStore } from 'pinia'

export const useGlobalStore = defineStore('global', {
  state: () => ({
    isLoading: false
  }),
  getters: {
    getLoading(state): boolean {
      return state.isLoading
    }
  },
  actions: {
    setLoading(loading: boolean) {
      this.isLoading = loading
    }
  }
})
