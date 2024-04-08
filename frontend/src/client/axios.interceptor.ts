import { useToast } from 'vue-toast-notification'
import { useGlobalStore } from '@/stores/global'
import axios from 'axios'

const toast = useToast()

export function useAxiosInterceptor() {
  axios.interceptors.request.use(
    (config) => {
      useGlobalStore().setLoading(true)
      return config
    },
    (error) => {
      return Promise.reject(error)
    }
  )

  axios.interceptors.response.use(
    (response) => {
      useGlobalStore().setLoading(false)
      return response
    },
    (error) => {
      useGlobalStore().setLoading(false)
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
