import { ajax, AjaxResponse } from 'rxjs/ajax'
import { Observable } from 'rxjs'
import { API_URL } from '../config'

class API {
  apiUrl = API_URL;

  saveTransaction(payload: {amount: number, targetId: number, type: string}): Observable<AjaxResponse> {
    return ajax.post(this.apiUrl + '/transactions', payload);
  }
}

export default new API();