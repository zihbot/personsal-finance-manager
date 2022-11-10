const { defineConfig } = require('cypress')
const { BASEURL } = require('./vars')

module.exports = defineConfig({
  e2e: {
    baseUrl: BASEURL,
    specPattern: './cypress/spec/**/*.cy.js',
    video: false,
  }
})