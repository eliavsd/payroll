import {PayBasis} from '../enum/pay-basis.enum';

export class IncomeDTO {

  baseSalary : number;
  globalOvertime : number;
  payBasis : PayBasis;
  travelCopay : number;
  otherCopay : number;
}
