import { Component, OnInit } from '@angular/core';
import{Animal1Service} from '../animal1.service';
import { Routes, RouterModule } from '@angular/router';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
user= {
username: '',
token:1,
}
message:any;
	constructor(private animalService: Animal1Service,
  private route:ActivatedRoute,private router:Router) { }

  ngOnInit() { }
  onForgot(){
	
	this.animalService.forgotPassword(this.user).subscribe(message => {
	this.message=message;
	if(this.message="Successful")
	{
	this.router.navigate(['/login']);
	}

	else{
	alert("Wrong Credentials");
	}
	});
	

}
}
