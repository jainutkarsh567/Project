import { Component } from '@angular/core';
import {Animal1Service} from './animal1.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'VISITgo';
  user:any;
  constructor(private animalService: Animal1Service) { this.user=this.animalService.dataFromService;
  console.log("app "+ this.user);}
  
  }
