const { TESTUSER, TESTPASS, BASEURL } = require("../../vars")

Cypress.Commands.add("createUser", (username=TESTUSER, password=TESTPASS, ...args) => {
    cy.request('POST', `${BASEURL}/users/login`, {
        username: 'root',
        password: 'root'
    }).then(res => {
        return res.body;
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
        //failOnStatusCode: false
    }).then(res => {
        rootToken = res.body;
    })})
})