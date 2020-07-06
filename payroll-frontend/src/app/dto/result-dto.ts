export class ResultDTO {
  private totalSavingsEligibleIncome : number;
  private totalGrossIncome : number;
  private totalTaxableIncome : number;
  private taxResults : Map<string, number>;
  private savingsResults : Map<string, number>;
}
