import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PayrollDTO} from '../dto/payroll-dto';

@Injectable({
  providedIn: 'root'
})
export class PayrollService {

  payrollInfo: PayrollDTO;

  constructor(private http:HttpClient) {
    this.payrollInfo = new PayrollDTO();
  }

  public grossToNet(): Observable<any> {
    return this.http.post<any>("http://localhost:8080/payroll/grossToNet/", this.payrollInfo);
  }
}
