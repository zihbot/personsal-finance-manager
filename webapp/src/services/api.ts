import { ajax, AjaxResponse } from 'rxjs/ajax'
import { Observable, OperatorFunction } from 'rxjs'
import { map, tap } from 'rxjs/operators'
import { API_URL } from '../config'
import auth from './auth'
import { TransactionDto } from '@/models/api/transactions';
import { AccountDto } from '@/models/api/accounts'
import data from './data'

class API {
  apiUrl = API_URL;

  login(payload: {username: string, password: string}): Observable<any> {
    return this.post('/users/login', payload, {responseType: 'text'});
  }

  saveTransaction(payload: {amount: number, targetId: number, type: string}): Observable<any> {
    return this.post('/transactions', payload);
  }

  getAllTransactions(): Observable<TransactionDto[]> {
    return this.get('/transactions');
  }

  getAllAccounts(): Observable<AccountDto[]> {
    return this.get('/accounts').pipe(tap(d => data.accounts.set(d)));
  }

  addAccount(payload: {name: string}) {
    return this.post('/accounts', payload);
  }

  responseOperator = map((res: AjaxResponse<any>) => res.response);

  private post(url: string, body?: any, params?: {headers?: any, responseType?: XMLHttpRequestResponseType}): Observable<any> {
    return this.request({
      method: 'POST',
      url,
      body,
      headers: params?.headers,
      responseType: params?.responseType
    });
  }

  private get(url: string): Observable<any> {
    return this.request({
      method: 'GET',
      url
    });
  }

  private request(params: {
    [key: string]: any;
    url: string;
    method: 'GET' | 'POST' | 'PUT' | 'DELETE';
    body?: any;
    headers?: { [key: string]: string };
    responseType?: XMLHttpRequestResponseType;
  }, mapper?: OperatorFunction<AjaxResponse<any>, any>): Observable<any> {
    const $ajax = ajax({
      url: this.apiUrl + params.url,
      method: params.method,
      headers: {
        'Content-Type': 'application/json',
        ...auth.getAuthHeader(),
        ...params.headers
      },
      body: params.body,
      responseType: params.responseType ?? 'json'
    });
    return $ajax
      .pipe(tap(val => console.log('Response', val.status, val.response)))
      .pipe(mapper ?? this.responseOperator);
  }
}

export default new API();