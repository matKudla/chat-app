import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient) {
    this.getData();
  }


  getData(): Observable<string> {
    return this.http.get(environment.apiUrl + '/api/test', {responseType: 'text'});
  }
}
