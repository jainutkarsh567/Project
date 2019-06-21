import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnimalsComponent} from './animals/animals.component';
import { GalleryComponent } from './gallery/gallery.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {AnimalDetailComponent} from './animal-detail/animal-detail.component';
import {SignupFormComponent} from './signup-form/signup-form.component'; 
import {ProfileComponent} from './Profile/Profile.component';
import {AddAnimalComponent} from './add-animal/add-animal.component';
import {ForgotPasswordComponent} from './forgot-password/forgot-password.component';
const routes: Routes = [
{path: '', redirectTo: '/home', pathMatch: 'full'}, 
{path : 'animals', component: AnimalsComponent},
{ path: 'home', component: HomeComponent},
{ path: 'gallery', component: GalleryComponent},
{ path: 'login', component: LoginComponent},
{ path: 'animal-detail/:id', component: AnimalDetailComponent},
{path: 'signup-form', component: SignupFormComponent},
{path: 'add-animal', component: AddAnimalComponent},
{path: 'Profile', component: ProfileComponent},
{path: 'forgot-password', component: ForgotPasswordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
