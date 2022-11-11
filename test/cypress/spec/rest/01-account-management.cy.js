const { BASEURL, TESTUSER, TESTPASS } = require("../../../vars")

let accountId;
let categoryId;
describe('01 Account Management', () => {
    before(() => {
        cy.createUser();
        cy.loginRestUser();
    });

    it('010 Empty userdata', () => {
        cy.request({method: 'GET', url: `/configs/userdata`}).then(res => {
            expect(res.body.accounts).to.have.length(0);
            expect(res.body.categories).to.have.length(0);
        });
    });

    it('020 Add account', () => {
        cy.request({
            method: 'POST',
            url: `/accounts`,
            body: {
                name: 'Debit card'
            }
        });
        cy.request({ method: 'GET', url: `/accounts` }).then(({body}) => {
            expect(body).to.have.length(1);
            expect(body[0].name).to.equal('Debit card');
            accountId = body[0].id;
        });
    });

    it('030 Add category', () => {
        cy.request({
            method: 'POST',
            url: `/categories`,
            body: {
                name: 'Groceries',
                icon: 'shopping-cart',
                color: '#3b5df5'
            }
        });
        cy.request({ method: 'GET', url: `/categories` }).then(({body}) => {
            expect(body).to.have.length(1);
            expect(body[0].name).to.equal('Groceries');
            categoryId = body[0].id;
        });
    });

    it('031 Filled userdata', () => {
        cy.request({method: 'GET', url: `/configs/userdata`}).then(res => {
            expect(res.body.accounts).to.have.length(1);
            expect(res.body.categories).to.have.length(1);
        });
    });

    it('030 Add transaction', () => {
        cy.request({
            method: 'POST',
            url: `/transactions`,
            body: {
                amount: 1000,
                target: accountId,
                category: categoryId
            }
        }).then(({body}) => {
            expect(body.amount).to.equal(1000);
        });
    });
});
