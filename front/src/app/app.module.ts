import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './pages/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { HttpClientModule } from '@angular/common/http'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SigninComponent } from './pages/signin/signin.component';
import { CreateAccountComponent } from './pages/create-account/create-account.component';
import { CreateArticleComponent } from './pages/create-article/create-article.component';
import { CreateMediaComponent } from './pages/create-media/create-media.component';
import { CreateSitesComponent } from './pages/create-sites/create-sites.component';
import { CreateMappComponent } from './pages/create-mapp/create-mapp.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    SigninComponent,
    CreateAccountComponent,
    CreateArticleComponent,
    CreateMediaComponent,
    CreateSitesComponent,
    CreateMappComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
