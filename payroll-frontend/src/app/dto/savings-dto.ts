import {SavingsType} from '../enum/savings-type.enum';

export class SavingsDTO {
  savingsName : string;
  savingsType : SavingsType = SavingsType.PERCENTAGE;
  savingsFactor : number;
}
