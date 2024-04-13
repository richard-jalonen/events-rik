import { mount } from '@vue/test-utils'
import { describe, expect, it } from 'vitest'
import WelcomeBanner from '../../../../src/components/home/WelcomeBanner.vue'

describe('WelcomeBanner', () => {
  it('displays event list', async () => {
    const wrapper = mount(WelcomeBanner)

    const bannerText = wrapper.find('.flex-fill').text()
    const banner = wrapper.find('img')

    expect(bannerText).toEqual('home.bannerText')
    expect(banner.exists()).toBeTruthy
  })
})
