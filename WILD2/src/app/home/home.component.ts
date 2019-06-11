import { Component, OnInit } from '@angular/core';
import {ANIMAL} from '../animals/animal';
import {Animal1Service} from '../animal1.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	anima: ANIMAL[]=[];
  constructor(private animalService: Animal1Service) {}

  ngOnInit() {
  this.getAnimals();
  }
  getAnimals(): void{
  this.animalService.getAnimals().subscribe(anima => this.anima = anima.slice(1,5));
  }

}
