import { mergeConfig, defineConfig, configDefaults } from 'vitest/config'
import { fileURLToPath } from 'node:url'
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
