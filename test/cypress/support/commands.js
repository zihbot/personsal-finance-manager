const { TESTUSER, TESTPASS, BASEURL } = require("../../vars")

Cypress.Commands.add("createUser", (username=TESTUSER, password=TESTPASS, ...args) => {
    cy.request('POST', `${BASEURL}/users/login`, {
        username: 'root',
        password: 'root'
    }).then(res => {
        return res.body;
    }).then(rootToken => {
    cy.request({
        method: 'DELETE',
        url: `${BASEURL}/users/${username}`,
        headers: {
            'Authorization': `Bearer ${rootToken}`
        },
        failOnStatusCode: false
    }).then(res => {
        return rootToken;
    }).then(rootToken => {
    cy.request({
        method: 'POST',
        url: `${BASEURL}/users`,
        headers: {
            'Authorization': `Bearer ${rootToken}`
        },
        body: {
            username,
            password
        },
    }).then(res => {
        rootToken = res.body;
    })})})
})