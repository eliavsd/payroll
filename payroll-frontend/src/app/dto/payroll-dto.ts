import {TaxDTO} from './tax-dto';
import {SavingsDTO} from './savings-dto';
import {IncomeDTO} from './income-dto';

export class PayrollDTO {
  private incomeDTO : IncomeDTO;
  private taxes : TaxDTO[];
  private savings : SavingsDTO[];
}
