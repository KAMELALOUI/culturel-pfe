import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MainService } from 'src/app/api/main.service';

@Component({
  selector: 'app-create-mapp',
  templateUrl: './create-mapp.component.html',
  styleUrls: ['./create-mapp.component.css']
})
export class CreateMappComponent implements OnInit {

  form = new FormGroup({
    nom: new FormControl('', Validators.required),
    localisation: new FormControl('', Validators.required),
    descriptionHistorique: new FormControl('', Validators.required),
    photo: new FormControl(null, Validators.required)
  });

  photo: any;
  error: string = '';
  success: string = '';

  constructor(private main: MainService) { }

  ngOnInit(): void { }

  getPhoto(event: any) {
    const file = event.target.files[0];
    this.photo = file;
  }

  publish() {
    const data: any = this.form.value;
    let mappFormData = new FormData();
    mappFormData.append('name', data.nom);  // Ensure these match the backend field names
    mappFormData.append('location', data.localisation); // Ensure these match the backend field names
    mappFormData.append('description', data.descriptionHistorique); // Ensure these match the backend field names
    mappFormData.append('file', this.photo); // Ensure these match the backend field names

    this.error = '';
    this.success = '';

    this.main.publishMapping(mappFormData).toPromise().then((res: any) => {
      console.log(res);
      if (res.success === true) {
        this.success = res.message;
        this.form.reset();
      }
    }).catch((err) => {
      this.error = err.message;
    });
  }
}
