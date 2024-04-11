import { describe, expect, it } from 'vitest'

import { mount } from '@vue/test-utils'
import ContactInfo from '../../../../../src/components/common/footer/ContactInfo.vue'

describe('ContactInfo', () => {
  it('contains required components', () => {
    const wrapper = mount(ContactInfo)

    const header = wrapper.find('h3')
    const columnValues = wrapper.findAll('.row .col-md-auto div').map((el) => el.text())

    expect(header).exist
    expect(columnValues).toEqual([
      'footer.contactInfo.mainOffice footer.contactInfo.firstOfficeExample.city',
      'footer.contactInfo.firstOfficeExample.address',
      'footer.contactInfo.phone footer.contactInfo.firstOfficeExample.phone',
      'footer.contactInfo.fax footer.contactInfo.firstOfficeExample.fax',
      'footer.contactInfo.secondaryOffice footer.contactInfo.secondOfficeExample.city',
      'footer.contactInfo.secondOfficeExample.address',
      'footer.contactInfo.phone footer.contactInfo.secondOfficeExample.phone',
      'footer.contactInfo.fax footer.contactInfo.secondOfficeExample.fax'
    ])
  })
})
