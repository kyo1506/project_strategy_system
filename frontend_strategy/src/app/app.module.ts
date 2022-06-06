import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { SharedModule } from 'src/app/shared/shared.module';
import { AutenticationService } from './services/autentication.service';
import { TagInputModule } from 'ngx-chips';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { GeneralInformationsService } from './services/general-informations.service';
import { AuthInterceptor } from './auth.interceptor';
import { NgxMaskModule } from 'ngx-mask';
import { CustomPaginator } from './custom-paginator';
import { SafeUrlPipe } from './shared/pipes/safe-url.pipe';
import { FileSaverModule } from 'ngx-filesaver';
import { DialogDataSalvarComponent } from './shared/components/dialog-data-salvar/dialog-data-salvar.component';
import { EditarResultadoOcrAlvoComponent } from './modules/initial/editar-resultado-ocr-alvo/editar-resultado-ocr-alvo.component';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { OverlayModule } from '@angular/cdk/overlay';

import { MatDividerModule } from '@angular/material/divider';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MAT_DATE_LOCALE } from '@angular/material/core/datetime';
import { MatPaginatorIntl } from '@angular/material/paginator';

import localePt from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { DialogDataExcluirComponent } from './shared/components/dialog-data-excluir/dialog-data-excluir.component';

registerLocaleData(localePt, 'pt-BR');

@NgModule({
	declarations: [
		AppComponent,
		SafeUrlPipe,
		DialogDataSalvarComponent,
		EditarResultadoOcrAlvoComponent,
		DialogDataExcluirComponent 
	],
	imports: [
		BrowserAnimationsModule,
		BrowserModule,
		SharedModule,
		HttpClientModule,
		AppRoutingModule,
		NgxMaskModule.forRoot(),
		MatSidenavModule,
		MatToolbarModule,
		MatMenuModule,
		MatIconModule,
		MatTableModule,
		MatSortModule,
		FileSaverModule,
		ReactiveFormsModule,
		FormsModule,
		MatInputModule,
		ScrollingModule,
		MatDialogModule,
		MatFormFieldModule,
		MatDatepickerModule,
		MatSelectModule,
		MatCardModule,
		MatDividerModule,
		MatExpansionModule,
		MatButtonModule,
		OverlayModule,
		MatChipsModule,
		TagInputModule
	],
	exports: [
		SafeUrlPipe
	],
	providers: [
		{ provide: MAT_DATE_LOCALE, useValue: 'pt-BR' },
		{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
		AutenticationService,
		GeneralInformationsService,
		{ provide: MatPaginatorIntl, useClass: CustomPaginator }
	],
	bootstrap: [AppComponent],
	entryComponents: [
		DialogDataSalvarComponent, 
		EditarResultadoOcrAlvoComponent,
		DialogDataExcluirComponent
	]
})
export class AppModule { }