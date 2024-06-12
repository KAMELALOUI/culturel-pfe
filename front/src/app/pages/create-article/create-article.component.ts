import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MainService } from 'src/app/api/main.service';

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.css']
})
export class CreateArticleComponent implements OnInit {

  form = new FormGroup({
    descreption: new FormControl('',Validators.required),
    title: new FormControl('',Validators.required),
    photo: new FormControl(null,Validators.required)
  })


  photo:any;

  error:string='';
  success:string='';


  constructor(private main:MainService) { }

  ngOnInit(): void {
  }

  getPhoto(event:any){
    const file = event.target.files[0];
    this.photo = file;

  }


  publish(){
    // const data:any = this.form.value;

    // let formData = new FormData();

 
    // formData.append('file',this.photo);
    // formData.append('descreption',data.descreption);
    // formData.append('title',data.title);

    // this.error='';
    // this.success='';

    // this.main.publish(formData).toPromise().then((res:any)=>{
    //   console.log(res);

    //   if( res.success == true ){
    //     this.success = res.message;
    //     this.form.reset();
    //   }

    // }).catch((err)=>{
    //   this.error=err.message;
    // })


    


  }

}
