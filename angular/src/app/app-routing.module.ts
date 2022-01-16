import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfileComponent} from "./profile/profile.component";
import {LoginComponent} from "./login/login.component";
import {OrderComponent} from "./order/order.component";
import {ReportComponent} from "./report/report.component";
import {CommonComponent} from "./common/common.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'profile/:userId', component: ProfileComponent},
  {path: 'create/:userId', component: CommonComponent},
  {path: 'order/:userId', component: OrderComponent},
  {path: 'report/:userId', component: ReportComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
