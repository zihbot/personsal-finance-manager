import { ajax, AjaxResponse } from 'rxjs/ajax'
import { Observable } from 'rxjs'
import { API_URL } from '../config'

class API {
  apiUrl = API_URL;

  saveTransaction(payload: {amount: number, targetId: number, type: string}): Observable<AjaxResponse> {
    return this.post(this.apiUrl + '/transactions', payload);
  }

  getAllTransactions(): Observable<AjaxResponse> {
    return this.get(this.apiUrl + '/transactions');
  }

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