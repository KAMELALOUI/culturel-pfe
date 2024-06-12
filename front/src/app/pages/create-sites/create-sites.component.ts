import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MainService } from 'src/app/api/main.service';

@Component({
  selector: 'app-create-sites',
  templateUrl: './create-sites.component.html',
  styleUrls: ['./create-sites.component.css']
})
export class CreateSitesComponent implements OnInit  {
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
    let formData = new FormData();
    formData.append('nom', data.nom);
    formData.append('localisation', data.localisation); // Correct field name
    formData.append('descriptionHistorique', data.descriptionHistorique);
    formData.append('file', this.photo); // Ensure 'file' matches backend field name

    this.error = '';
    this.success = '';

    this.main.addSite(formData).toPromise().then((res: any) => {
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