import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PayrollDTO} from '../dto/payroll-dto';

@Injectable({
  providedIn: 'root'
})
export class PayrollService {

  constructor(private http:HttpClient) { }

  public grossToNet(grossInfo: PayrollDTO): Observable<any> {
    return this.http.post<any>("http://localhost:8080/payroll/grossToNet/", grossInfo);
  }
}
