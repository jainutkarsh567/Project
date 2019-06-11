import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnimalsComponent } from './animals/animals.component';
import { GalleryComponent } from './gallery/gallery.component';
import { AnimalDetailComponent } from './animal-detail/animal-detail.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupFormComponent } from './signup-form/signup-form.component';
import {HttpClientModule} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { AddAnimalComponent } from './add-animal/add-animal.component';
import {InterceptorModule} from './interceptor.module';


@NgModule({
  declarations: [
    AppComponent,
    AnimalsComponent,
    GalleryComponent,
    AnimalDetailComponent,
    HomeComponent,
    LoginComponent,
    SignupFormComponent,
    AddAnimalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    InterceptorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
