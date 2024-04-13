import { describe, expect, it } from 'vitest'

import { shallowMount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import { beforeEach } from 'vitest'
import SubPage from '../../../../src/components/common/SubPage.vue'

describe('SubPage', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })

  it('contains required components', () => {
    const wrapper = shallowMount(SubPage, {
      props: {
        bannerHeader: 'header'
      }
    })

    const banner = wrapper.find('sub-page-banner-stub')
    const loadingOverlay = wrapper.find('b-overlay-stub')

    expect(banner).exist
    expect(loadingOverlay).exist
  })
})
