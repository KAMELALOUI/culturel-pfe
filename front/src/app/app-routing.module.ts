import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { SigninComponent } from './pages/signin/signin.component';
import { CreateAccountComponent } from './pages/create-account/create-account.component';
import { AuthGuard } from './guards/auth.guard';
import { CreateArticleComponent } from './pages/create-article/create-article.component';
import { CreateSitesComponent } from './pages/create-sites/create-sites.component';
import { CreateMediaComponent } from './pages/create-media/create-media.component';
import { CreateMappComponent } from './pages/create-mapp/create-mapp.component';

const routes: Routes = [
  { path:'', component:HomeComponent  },
  { path:'auth', component:SigninComponent },
  { path:'create-account', component:CreateAccountComponent },
  { path:'app/article/add', component:CreateArticleComponent, canActivate:[AuthGuard]  },
  { path: 'app/site/add', component: CreateSitesComponent , canActivate:[AuthGuard] },
  { path: 'app/media/add', component: CreateMediaComponent, canActivate:[AuthGuard]  },
  { path: 'app/mapping/add', component: CreateMappComponent , canActivate:[AuthGuard]},

  
  
];
// , canActivate: [AuthGuard] 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
