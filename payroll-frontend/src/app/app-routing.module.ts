import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {IncomeFormComponent} from './salary-form/income-form/income-form.component';
import {TaxFormComponent} from './salary-form/tax-form/tax-form.component';
import {SavingsFormComponent} from './salary-form/savings-form/savings-form.component';
import {SalaryFormComponent} from './salary-form/salary-form.component';

const routes: Routes = [
  {
    path: '', pathMatch: 'full', redirectTo: 'salary/income'
  },
  {
    path: 'salary', component: SalaryFormComponent,
    children: [
      {path: 'income', component: IncomeFormComponent, data: { animation: 'isIncome' }},
      {path: 'taxes', component: TaxFormComponent, data: { animation: 'isTaxes' }},
      {path: 'savings', component: SavingsFormComponent, data: { animation: 'isSavings' }}
    ]
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
