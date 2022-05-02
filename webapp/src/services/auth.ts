class Auth {
  private username?: string;
  private jwtToken?: string;

  setLogin(params: {
    loggedin: boolean,
    username: string,
    token: string
  }) {
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
}

export default new Auth();