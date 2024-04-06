export interface Event {
  uuid: string
  name: string
  time: string
  participantUuids: string[]
}

export interface EventCreateRequest {
  name: string
  time: string
  location: string
  additionalInfo: string
}
