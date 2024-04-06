export interface Event {
  todo: string
}

export interface EventCreateRequest {
  name: string
  time: string
  location: string
  additionalInfo: string
}
