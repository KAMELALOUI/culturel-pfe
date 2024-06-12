import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/api/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  form = new FormGroup({
    username: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  })

  error:string='';
  constructor(private api:AuthService, private router:Router) { }

  ngOnInit(): void {
  }


  auth(){
    this.error = '';
    this.api.login(this.form.value).subscribe((res:any)=>{
      console.log(res);
      
      if(res.status == 401){
        this.error = "Wrong username or password";
      }else{
        // ok

        const tokenType = res.tokenType;
        const accessToken = res.accessToken; 
        const token = tokenType + ' '+accessToken; 
        localStorage.setItem('token',token); 
        this.router.navigateByUrl('/'); 

      }

    },(err)=>{
      this.error = "Wrong username or password";
    })
  }

}