export interface Participant {
  uuid: string
  type: string
  paymentType: string
  firstName: string
  lastName: string
  personCode: string
  participantCount: number
  additionalInfo: string
}

export interface ParticipantCreateRequest {
  type: string
  firstName: string
  lastName?: string
  participantCount?: number
  personCode: string
  paymentType: string
  additionalInfo: string
}

export interface ParticipantUpdateRequest extends ParticipantCreateRequest {
  uuid: string
}
