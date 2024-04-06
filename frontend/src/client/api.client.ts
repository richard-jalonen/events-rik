import axios from 'axios'

const apiUrl = 'http://localhost:8080'

export default class ApiClient {
  public static getPaymentOptions() {
    return axios.get(`${apiUrl}/api/v1/payments`)
  }
}