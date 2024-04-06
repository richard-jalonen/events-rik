import axios from 'axios'
import type { EventCreateRequest } from '@/models/Event'

const apiUrl = 'http://localhost:8080'

export default class ApiClient {
  public static getPaymentOptions() {
    return axios.get(`${apiUrl}/api/v1/payments`)
  }

  public static createEvent(request: EventCreateRequest) {
    return axios.post(`${apiUrl}/api/v1/events`, request)
  }

  public static getEvents() {
    return axios.get(`${apiUrl}/api/v1/events`)
  }
}
