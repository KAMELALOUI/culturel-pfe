import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/api/auth.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  form = new FormGroup({
    username: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  })
  get f() { return this.form.controls; }

  error:string='';
  success:string='';
  submitted = false;
  role: any;

  constructor(private api:AuthService, private router:Router) { }

  ngOnInit(): void {
  }
//   auth() {
//     console.log(this.form.value);
//     this.submitted = true;

//     // stop here if form is invalid
//     if (this.form.invalid) {
//         return;
//     }

//     // display form values on success
//     //alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.registerForm.value, null, 4));
//     let record = {};
//     let role =this.role;
//     let userrole= {};
//     if(role =="freelancer")
//     {
//       userrole['id']= 2;
//       userrole['name']= "ROLE_ADMIN";
//     }
//     if(role =="user")
//     {
//       userrole['id']= 1;
//       userrole['name']= "ROLE_USER";
//     }
//     console.log(userrole);
//     record = this.form.value;
//     record['role'] = userrole;
//     console.log(record);
//     this.api.signup(record)
//     .subscribe(
//       data => {
//         console.log(data);
//         this.router.navigate(['/login']);
//       },
//       error => console.log(error));
// }

// clickRole(role){
//   this.role = role.target.value;
 

// }

  auth(){
    this.error = '';
    this.api.signup(this.form.value).subscribe((res:any)=>{
      console.log(res);

      this.success=res.message;
      
      

    },(err)=>{
      this.error = err.message+'';
    })
  }
}