import { Component, OnInit } from '@angular/core';
import {LOGIN1} from './log';
import { Animal1Service }  from '../animal1.service';
import { Routes, RouterModule } from '@angular/router';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 login=LOGIN1;
 condition: boolean=true;
user={
	username:'',
	password:''
}
users={
  username:'',
  uname:'',
  age: '',
  gender:'',
}
message: any;
  constructor(private animalService: Animal1Service,
  private route:ActivatedRoute,private router:Router) { }

  ngOnInit() { 
  
  }
onLogin()
{
	this.animalService.onCheck(this.user).subscribe(users => {
	this.users=users; 
  console.log("yhi h " + users);
  if(this.users!=null)
  {
     sessionStorage.setItem('username',this.users.username);
    
     this.condition=true;
     console.log( "localStorage.getItem('username')::::" +localStorage.getItem('username'));
     this.router.navigate(['/Profile']);
     this.animalService.dataFromService=this.condition;
    
     console.log("login "+ this.animalService.dataFromService);

  }
  else{
  alert("Wrong Credentials");
  sessionStorage.clear();
  }

	});
 }
}
