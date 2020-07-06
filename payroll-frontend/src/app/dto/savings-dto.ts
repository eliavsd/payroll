import {SavingsType} from '../enum/savings-type.enum';

export class SavingsDTO {
  private savingsName : string;
  private savingsType : SavingsType = SavingsType.PERCENTAGE;
  private savingsFactor : number;
}
