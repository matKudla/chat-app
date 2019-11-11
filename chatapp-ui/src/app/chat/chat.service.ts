import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = 'http://localhost:8080/api/test';

  constructor(private http: HttpClient) {
    this.getData();
  }


  getData(): Observable<string>{
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}
