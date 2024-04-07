import ApiClient from '@/client/api.client'
import type { Event } from '@/models/Event'
import { defineStore } from 'pinia'

export const useEventStore = defineStore('event', {
  state: () => ({
    events: [] as Event[]
  }),
  getters: {
    getEvents(state) {
      return state.events
    },
    getPastEvents(state) {
      return state.events.filter((event) => {
        const eventDateTime = new Date(event.time)
        return eventDateTime < new Date()
      })
    },
    getFutureEvents(state) {
      return state.events.filter((event) => {
        const eventDateTime = new Date(event.time)
        return eventDateTime > new Date()
      })
    }
  },
  actions: {
    async initialize() {
      await this.fetchEvents()
    },
    async fetchEvents() {
      try {
        const data = await ApiClient.getEvents()
        this.events = data.data || []
      } catch (error) {
        console.log(error)
      }
    }
  }
})
