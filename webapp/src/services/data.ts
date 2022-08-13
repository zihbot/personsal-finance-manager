import { CategoryDto } from './../models/api/categories.d';
import { AccountDto } from "@/models/api/accounts";
import { Observable, of, from, BehaviorSubject } from 'rxjs'

class DataSubject<T> extends BehaviorSubject<{loading: boolean, error?: string, data: T}>{
  nullvalue: T;

  constructor(data: T, nullvalue?: T) {
    super({loading: true, data: data});    
    this.nullvalue = nullvalue ?? data;
  }

  set(data: T) {
    this.next({
      loading: false,
      error: undefined,
      data: data
    })
  }

  error(error: string) {
    this.next({
      loading: false,
      error: error,
      data: this.nullvalue
    })
  }

  loading(clean = false) {
    const newdata = clean ? this.nullvalue : this.getValue().data;
    this.next({
      loading: true,
      error: undefined,
      data: newdata
    })
  }
}

class Data {
  accounts = new DataSubject<AccountDto[]>([]);
  categories = new DataSubject<CategoryDto[]>([]);

  getAccount(id?: number): AccountDto | undefined {
    return this.accounts.getValue().data.find(acc => acc.id === id);
  }
}

export default new Data();