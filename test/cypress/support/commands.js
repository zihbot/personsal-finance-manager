const { TESTUSER, TESTPASS, BASEURL } = require("../../vars")

const ENV_TOKEN = 'token';

Cypress.Commands.overwrite('request', (originalFn, ...options) => {
    const optionsObject = options[0];
    const token = Cypress.env(ENV_TOKEN);

    if (token && typeof optionsObject === 'object') {
      optionsObject.headers = {
        'Authorization': 'Bearer ' + token,
        ...optionsObject.headers,
      };

      return originalFn(optionsObject);
    }

    return originalFn(...options);
});

Cypress.Commands.add("createUser", (username=TESTUSER, password=TESTPASS, ...args) => {
    cy.request({
        method: 'POST',
        url: `/users/login`,
        body: {
            username: 'root',
            password: 'root'
        }
    }).then(res => {
        Cypress.env(ENV_TOKEN, res.body);
    })
    cy.request({
        method: 'DELETE',
        url: `/users/${username}`,
        failOnStatusCode: false
    })
    cy.request({
        method: 'POST',
        url: `/users`,
        body: {
            username,
            password
        },
    })
})
Cypress.Commands.add("loginRestUser", (username=TESTUSER, password=TESTPASS, ...args) => {
    cy.request({
        method: 'POST',
        url: `/users/login`,
        body: {
            username,
            password
        }
    }).then(res => {
        Cypress.env(ENV_TOKEN, res.body);
    })
})