import { fileURLToPath } from 'node:url'
import { configDefaults, defineConfig, mergeConfig } from 'vitest/config'
import viteConfig from './vite.config'

const rootPath = fileURLToPath(new URL('./', import.meta.url))

export default mergeConfig(
  viteConfig,
  defineConfig({
    test: {
      setupFiles: ['./tests/unit/unit-tests-setup.ts'],
      environment: 'jsdom',
      exclude: [...configDefaults.exclude, 'e2e/*'],
      root: rootPath,
      coverage: {
        provider: 'istanbul'
      }
    }
  })
)
