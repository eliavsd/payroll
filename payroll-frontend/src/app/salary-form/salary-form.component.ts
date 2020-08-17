import {Component, Input, OnInit} from '@angular/core';
import {PayrollService} from '../service/payroll.service';
import {ResultDTO} from '../dto/result-dto';
import {PayrollDTO} from '../dto/payroll-dto';

@Component({
  selector: 'app-salary-form',
  templateUrl: './salary-form.component.html',
  styleUrls: ['./salary-form.component.scss']
})
export class SalaryFormComponent implements OnInit {

  @Input()
  payrollDTO : PayrollDTO;

  constructor(private payrollService: PayrollService) {
    this.payrollDTO = new PayrollDTO();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.payrollService.countAllSuccess("").subscribe(data => {
        console.log(data);
        return data;
      }
    );
  }

}
