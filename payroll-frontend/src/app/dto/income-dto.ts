import {PayBasis} from '../enum/pay-basis.enum';

export class IncomeDTO {

  private baseSalary : number;
  private globalOvertime : number;
  private payBasis : PayBasis;
  private travelCopay : number;
  private otherCopay : number;
}
