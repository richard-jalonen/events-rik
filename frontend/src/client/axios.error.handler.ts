import { useToast } from 'vue-toast-notification'
import axios from 'axios'

const toast = useToast()

export function useAxiosErrorHandler() {
  axios.interceptors.response.use(
    (response) => response,
    (error) => {
      if (!error.response) {
        toast.error('Network Error')
        return Promise.reject(error)
      }

      const { status, data } = error.response

      switch (status) {
        case 400:
          toast.error(data.message || 'Bad Request')
          break
        case 404:
          toast.error(data.message || 'Not found')
          break
        case 409:
          toast.error(data.message || 'Conflict')
          break
        case 500:
          toast.error(data.message || 'Internal server error')
          break
        default:
          toast.error(data.message || 'Unknown error')
          break
      }

      return Promise.reject(error)
    }
  )
}
