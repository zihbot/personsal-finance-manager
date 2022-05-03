class Auth {
  private loggedIn = false;
  private username?: string;
  private jwtToken?: string;

  setLogin(params: {
    loggedin: boolean,
    username: string,
    token: string
  }) {
    this.loggedIn = params.loggedin;
    if (!params.loggedin) {
      this.jwtToken = undefined;
      this.username = undefined;
      return;
    }
    this.username = params.username;
    this.jwtToken = params.token;
  }

  getUsername(): string | null {
    return this.username ?? null;
  }

  getAuthHeader(): any {
    if (!this.jwtToken) return {};
    return {
      Authorization: `Bearer ${this.jwtToken}`
    }
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }
}

export default new Auth();