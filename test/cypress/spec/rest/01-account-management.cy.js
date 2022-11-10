const { BASEURL, TESTUSER, TESTPASS } = require("../../../vars")

describe('01 Account Management', () => {
    before(() => {
        cy.createUser();
    });

    it('01 Login', () => {
        cy.request('POST', `${BASEURL}/users/login`, {
            username: TESTUSER,
            password: TESTPASS
        }).then(res => {
            cy.log(res);
        });
    });
});
