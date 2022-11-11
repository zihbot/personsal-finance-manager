const { BASEURL, TESTUSER, TESTPASS } = require("../../../vars")

describe('01 Account Management', () => {
    before(() => {
        cy.createUser();
        cy.loginRestUser();
    });

    it('01 Empty userdata', () => {
        cy.request({method: 'GET', url: `/configs/userdata`}).then(res => {
            expect(res.body.accounts).to.have.length(0);
            expect(res.body.categories).to.have.length(0);
        });
    });

    it('02 Add account', () => {
        cy.request({
            method: 'POST',
            url: `/accounts`,
            body: {
                name: 'Debit card'
            }
        });
        cy.request({ method: 'GET', url: `/accounts` }).then(({body}) => {
            expect(body).to.have.length(1);
        });
    });
});
