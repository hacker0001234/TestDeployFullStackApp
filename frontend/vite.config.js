import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

export default defineConfig({
  plugins: [react()],
  base: '/',
  build: {
    outDir: path.resolve(__dirname, 'TestDeploy/src/main/resources/static'), // або відповідно до твоєї структури
    emptyOutDir: true
  }
})
