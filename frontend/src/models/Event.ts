import type { Participant } from '@/models/Participant'

export interface Event {
  uuid: string
  name: string
  time: string
  location: string
  participants: Participant[]
}

export interface EventCreateRequest {
  name: string
  time: string
  location: string
  additionalInfo: string
}
