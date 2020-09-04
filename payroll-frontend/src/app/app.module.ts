import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SalaryFormComponent } from './salary-form/salary-form.component';
import {
  ButtonModule,
  CardModule,
  CheckboxModule,
  DropdownModule,
  InputTextModule,
  RadioButtonModule,
  StepsModule
} from 'primeng';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { TaxFormComponent } from './salary-form/tax-form/tax-form.component';
import { SavingsFormComponent } from './salary-form/savings-form/savings-form.component';
import { AppRoutingModule } from './app-routing.module';
import {IncomeFormComponent} from './salary-form/income-form/income-form.component';

@NgModule({
  declarations: [
    AppComponent,
    SalaryFormComponent,
    IncomeFormComponent,
    TaxFormComponent,
    SavingsFormComponent,
  ],
  imports: [
    BrowserModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CheckboxModule,
    RadioButtonModule,
    CardModule,
    DropdownModule,
    StepsModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
