import axios from 'axios'
import type { EventCreateRequest } from '@/models/Event'
import type { PersonCreateRequest, PersonUpdateRequest } from '@/models/Person'

const apiUrl = import.meta.env.VITE_API_URL

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

  public static getPerson(uuid: string) {
    return axios.get(`${apiUrl}/api/v1/participants/${uuid}`)
  }

  public static updatePerson(request: PersonUpdateRequest) {
    return axios.put(`${apiUrl}/api/v1/participants`, request)
  }

  public static deleteEvent(uuid: string) {
    return axios.delete(`${apiUrl}/api/v1/events/${uuid}`)
  }

  public static deleteParticipant(uuid: string) {
    return axios.delete(`${apiUrl}/api/v1/participants/${uuid}`)
  }
}
