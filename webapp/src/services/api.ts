import { ajax, AjaxResponse } from 'rxjs/ajax'
import { Observable } from 'rxjs'
import { map } from 'rxjs/operators'
import { API_URL } from '../config'
import { TransactionDto } from '@/models/api/transactions';

class API {
  apiUrl = API_URL;

  saveTransaction(payload: {amount: number, targetId: number, type: string}): Observable<any> {
    return this.post(this.apiUrl + '/transactions', payload)
    .pipe(this.responseOperator);
  }

  getAllTransactions(): Observable<TransactionDto[]> {
    return this.get(this.apiUrl + '/transactions')
    .pipe(this.responseOperator);
  }

  responseOperator = map((res: AjaxResponse) => res.response);

  private post(url: string, body?: any, headers?: any): Observable<AjaxResponse> {
    return ajax.post(url, body, {
      'Content-Type': 'application/json',
      ...(headers ?? {})
    })
  }

  private get(url: string, headers?: any): Observable<AjaxResponse> {
    return ajax.get(url, {
      ...(headers ?? {})
    })
  }
}

export default new API();