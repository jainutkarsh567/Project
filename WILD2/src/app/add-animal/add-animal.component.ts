import { Component, OnInit } from '@angular/core';
import{Animal1Service} from '../animal1.service';
import {ANIMAL} from '../animals/animal';


@Component({
  selector: 'app-add-animal',
  templateUrl: './add-animal.component.html',
  styleUrls: ['./add-animal.component.css']
})
export class AddAnimalComponent implements OnInit {
users: ANIMAL[];
user= {
name: '',
id:''
}
message: any
	constructor(private animalService: Animal1Service) { }

  ngOnInit() {

  }
onAdd(){
	
	this.animalService.addUser(this.user).subscribe(message => {
	this.message=message;
	});
	console.log(this.message);
}
}
