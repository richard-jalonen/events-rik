export interface Person {
  uuid: string
  paymentType: string
  firstName: string
  lastName: string
  personCode: string
  additionalInfo: string
}

export interface PersonCreateRequest {
  type: string
  firstName: string
  lastName?: string
  personCode: string
  paymentType: string
  additionalInfo: string
}
