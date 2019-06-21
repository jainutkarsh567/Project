import { Component, OnInit } from '@angular/core';
import {ANIMAL} from '../animals/animal';
import {Animal1Service} from '../animal1.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
user:any
	//anima: ANIMAL[]=[];
  show: boolean;
  constructor(private animalService: Animal1Service) {
   this.user=this.animalService.userFromService.uname;
   if(sessionStorage.getItem('username'))
   {
   this.show=true;
   }
   else
   {
   this.show=false;
   }
  }


  ngOnInit() {
  //this.getAnimals();
   console.log("home" + localStorage.getItem('username'));
  }
  //getAnimals(): void
//  {
  //this.animalService.getAnimals().subscribe(anima => this.anima = anima.slice(1,5));
  //}



}
