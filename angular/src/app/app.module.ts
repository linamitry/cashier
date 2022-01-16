import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ProfileComponent} from './profile/profile.component';
import {NavbarComponent} from './navbar/navbar.component';
import {LoginComponent} from './login/login.component';
import {UserService} from "./service/user.service";
import {CookieService} from 'ngx-cookie-service';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {CdkAccordionModule} from '@angular/cdk/accordion';
import {MatFormFieldModule} from "@angular/material/form-field";
import { OrderComponent } from './order/order.component';
import { ReportComponent } from './report/report.component';
import {MatListModule} from "@angular/material/list";
import { CommonComponent } from './common/common.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { ProductListComponent } from './product-list/product-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProfileComponent,
    LoginComponent,
    OrderComponent,
    ReportComponent,
    CommonComponent,
    CreateOrderComponent,
    ProductListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NoopAnimationsModule,
    MatAutocompleteModule,
    CdkAccordionModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatListModule,
  ],
  // providers: [
  //   UserService,
  //   CookieService,
  //   {
  //     provide:HTTP_INTERCEPTORS,
  //     multi:true,
  //     useClass:AuthInterceptorService
  //   }
  // ],
  bootstrap: [AppComponent],
})
export class AppModule { }
