import { Component, OnInit } from '@angular/core';
import {User} from '../User';
import { Animal1Service }  from '../animal1.service';
import { Routes, RouterModule } from '@angular/router';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {
gender= ['MALE','FEMALE','OTHERS'];
user1 = new User(72, 'Utkarsh Jain', this.gender[0], 22,'jainutkarsh567', '********','********');
user={
  name:'',
  gender:'',
  age:'',
  username:'',
  password:''
};
submitted=false;
message:any;
onSubmit(){
	this.submitted=true;
}
  constructor(private animalService: Animal1Service,
  private route:ActivatedRoute,private router:Router) { }

  ngOnInit() {
  }
  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.user1);

}

onSignUp()
{
  this.animalService.onSign_Up(this.user).subscribe(message => {
  this.message=message;
  });
  if(this.message="Successful")
   {this.router.navigate(['/home']);}

}
}