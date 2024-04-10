import { config } from '@vue/test-utils'
import { MockInstance, beforeEach, vi } from 'vitest'
import {
  RouterMockOptions,
  VueRouterMock,
  createRouterMock as _createRouterMock,
  injectRouterMock
} from 'vue-router-mock'

export function createRouterMock(options?: RouterMockOptions) {
  return _createRouterMock({
    ...options,
    spy: {
      create: (fn) => vi.fn(fn),
      reset: (spy: MockInstance) => spy.mockClear(),
      ...options?.spy
    }
  })
}

export function setupRouterMock() {
  if (typeof global.document === 'undefined') {
    return
  }

  const router = createRouterMock({
    useRealNavigation: true
  })

  beforeEach(() => {
    router.reset()
    injectRouterMock(router)
  })

  config.plugins.VueWrapper.install(VueRouterMock)
}

export function setupI18() {
  config.global.mocks = {
    $t: (tKey: any) => tKey
  }
}

setupRouterMock()
setupI18()
