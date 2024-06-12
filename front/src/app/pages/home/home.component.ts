import { Component, OnInit } from '@angular/core';
import { MainService } from 'src/app/api/main.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit  {

  media: any[] = [];
  sites: any[] = [];
  mappings: any[] = [];

  mediaPage: number = 1;
  sitesPage: number = 1;
  mappingsPage: number = 1;

  constructor(private main: MainService) { }

  ngOnInit(): void {
    // Fetch media
    this.main.getMediaList().subscribe((res: any) => {
      this.media = res;
    });

    // Fetch heritage sites
    this.main.getSiteList().subscribe((res: any) => {
      this.sites = res;
    });

    // Fetch mappings
    this.main.getMappingList().subscribe((res: any) => {
      this.mappings = res;
    });
  }
}
