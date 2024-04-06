export interface Person {
  uuid: string
  type: string
  paymentType: string
  firstName: string
  lastName: string
  personCode: string
  participantCount: number
  additionalInfo: string
}

export interface PersonCreateRequest {
  type: string
  firstName: string
  lastName?: string
  participantCount?: number
  personCode: string
  paymentType: string
  additionalInfo: string
}

export interface PersonUpdateRequest extends PersonCreateRequest {
  uuid: string
}
