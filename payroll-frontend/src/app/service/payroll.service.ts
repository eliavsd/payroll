import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PayrollService {

  constructor(private http:HttpClient) { }

  public countAllSuccess(timestamp: string): Observable<number> {
    return this.http.get<number>("localhost:8080/countByStatus/success/" + timestamp);
  }
}
