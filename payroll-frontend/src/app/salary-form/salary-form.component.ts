import {Component, OnInit} from '@angular/core';
import {PayrollService} from '../service/payroll.service';

@Component({
  selector: 'app-salary-form',
  templateUrl: './salary-form.component.html',
  styleUrls: ['./salary-form.component.scss']
})
export class SalaryFormComponent implements OnInit {

  Re

  constructor(private payrollService: PayrollService) {
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
