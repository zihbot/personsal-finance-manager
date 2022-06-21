import { AccountDto } from "@/models/api/accounts";
import { Observable, of, from } from 'rxjs'
import api from "./api";

class Data {
  accounts?: AccountDto[];

  setAccounts(accounts: AccountDto[]) {
    this.accounts = accounts;
  }

  loadAccounts(cache = true): Observable<void> {
    if (cache && this.accounts) {
      return of();
    }
    return from(new Promise<void>((resolve, reject) => {
      api.getAllAccounts().subscribe(data => {
        this.accounts = data;
        resolve();
      }, error => reject());
    }));
  }

  getAccount(id?: number): AccountDto | undefined {
    return this.accounts?.find(acc => acc.id === id);
  }
}

export default new Data();