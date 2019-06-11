import { Component, OnInit,Input } from '@angular/core';
import { ANIMAL } from '../animals/animal';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Animal1Service }  from '../animal1.service';

@Component({
  selector: 'app-animal-detail',
  templateUrl: './animal-detail.component.html',
  styleUrls: ['./animal-detail.component.css']
})
export class AnimalDetailComponent implements OnInit {
 i: ANIMAL;
  constructor(
  private route: ActivatedRoute,
  private animalService: Animal1Service,
  private location: Location
) {  }

  ngOnInit():void  {
  this.getAnimal();
  }
  getAnimal(): void{
  const id= +this.route.snapshot.paramMap.get('id');
  this.animalService.getAnimal(id).subscribe(i => this.i = i);
  }
goBack(): void {
  this.location.back();
}
}
