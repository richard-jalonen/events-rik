import { describe, expect, it } from 'vitest'

import { mount } from '@vue/test-utils'
import GeneralInfo from '../../../../../src/components/common/footer/GeneralInfo.vue'

describe('GeneralInfo', () => {
  it('contains required components', () => {
    const wrapper = mount(GeneralInfo, {
      props: {
        translations: {
          header: 'header',
          firstText: 'text1',
          secondText: 'text2',
          thirdText: 'text3',
          fourthText: 'text4'
        }
      }
    })

    const header = wrapper.find('h3')
    const columnValues = wrapper.findAll('.grid .col div').map((el) => el.text())

    expect(header.text()).toEqual('header')
    expect(columnValues).toEqual(['text1', 'text2', 'text3', 'text4'])
  })
})
