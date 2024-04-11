import { describe, expect, it } from 'vitest'

import { mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import { beforeEach } from 'vitest'
import SubPageBanner from '../../../../src/components/common/SubPageBanner.vue'

describe('SubPageBanner', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })

  it('contains banner components', () => {
    const wrapper = mount(SubPageBanner, {
      props: {
        header: 'header'
      }
    })

    const bannerHeader = wrapper.find('#event-banner div h4').text()
    const bannerImage = wrapper.find('#event-banner img')

    expect(bannerHeader).toEqual(wrapper.props().header)
    expect(bannerImage).exist
  })
})
