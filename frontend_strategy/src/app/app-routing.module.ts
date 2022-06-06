import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'inicial', pathMatch: 'full' }
	// { path: '', redirectTo: 'inicial', pathMatch: 'full' },
	// { path: 'inicial', loadChildren: () => import('./modules/initial/initial.module').then(m => m.InitialModule) },
	// { path: 'expedicao', loadChildren: () => import('./modules/expedition/expedition.module').then(m => m.ExpeditionModule) },
	// { path: 'operacao', loadChildren: () => import('./modules/operation/operation.module').then(m => m.OperationModule) },
	// { path: 'operacao-bi', loadChildren: () => import('./modules/operation/operation.module').then(m => m.OperationModule) },
	// { path: 'administracao', loadChildren: () => import('./modules/administrator/administrator.module').then(m => m.AdministratorModule) },
	// { path: 'configuracao', loadChildren: () => import('./modules/configuration-system/configuration-system.module').then(m => m.ConfigurationSystemModule) },
	// { path: 'service-center', loadChildren: () => import('./modules/service-center/service-center.module').then(m => m.ServiceCenterModule) },
	// { path: 'exportacao', loadChildren: () => import('./modules/exportation/exportation.module').then(m => m.ExportationModule) },
	// { path: 'importacao', loadChildren: () => import('./modules/importation/importation.module').then(m => m.ImportationModule) },
	// { path: 'minha-conta', loadChildren: () => import('./modules/my-account/my-account.module').then(m => m.MyAccountModule) },
	// { path: 'customers', loadChildren: () => import('./modules/customers/customers.module').then(m => m.CustomersModule) },
];

@NgModule({
	imports: [RouterModule.forRoot(routes, { useHash: true })],
	exports: [RouterModule]
})
export class AppRoutingModule { }