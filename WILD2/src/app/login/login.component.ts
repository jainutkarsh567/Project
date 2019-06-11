import { Component, OnInit } from '@angular/core';
import {LOGIN1} from './log';
import { Animal1Service }  from '../animal1.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 login=LOGIN1;
user={
	username:'',
	password:''
}
message: any;
  constructor(private animalService: Animal1Service) { }

  ngOnInit() {
  }
onLogin()
{
	this.animalService.onCheck(this.user).subscribe(message => {
	this.message=message;
	});
}
}
