import axios from 'axios'
import type { EventCreateRequest } from '@/models/Event'
import type { PersonCreateRequest } from '@/models/Person'

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

  public static getEvent(uuid: string) {
    return axios.get(`${apiUrl}/api/v1/events/${uuid}`)
  }

  public static createPerson(eventUuid: string, request: PersonCreateRequest) {
    return axios.post(`${apiUrl}/api/v1/events/${eventUuid}/participants`, request)
  }
}
