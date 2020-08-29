import {TaxDTO} from './tax-dto';
import {SavingsDTO} from './savings-dto';
import {IncomeDTO} from './income-dto';

export class PayrollDTO {
  incomeDTO : IncomeDTO;
  taxes : TaxDTO[];
  savings : SavingsDTO[];

  constructor() {
    this.incomeDTO = new IncomeDTO();
    this.taxes = [];
    this.savings = [];
  }
}
