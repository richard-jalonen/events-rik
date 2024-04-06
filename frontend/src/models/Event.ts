import type { Person } from '@/models/Person'

export interface Event {
  uuid: string
  name: string
  time: string
  location: string
  participants: Person[]
}

export interface EventCreateRequest {
  name: string
  time: string
  location: string
  additionalInfo: string
}
