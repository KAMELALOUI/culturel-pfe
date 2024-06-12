import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service'; // Import AuthService

@Injectable({
  providedIn: 'root'
})
export class MainService {
  private servicesUrl1 = 'http://localhost:8086/api/media';
  private servicesUrl2 = 'http://localhost:8085/api/sites';
  private servicesUrl3 = 'http://localhost:8087/api/heritage';

  constructor(private http: HttpClient) { } // Inject AuthService


  
/// media services 
  publishM(mediaFormData:FormData): Observable<any> {
    const headers = new HttpHeaders({ 
      'Authorization': ''+localStorage.getItem('token')
    });

    return this.http.post(`${this.servicesUrl1}/add`, mediaFormData, { headers });
  }
  getMediaList() : Observable<any> {const headers = new HttpHeaders({ 
    'Authorization': ''+localStorage.getItem('token')
  });
    return this.http.get(`${this.servicesUrl1}/list`, { headers});
  }
      
  ///// site services 
  addSite(formData: FormData): Observable<any>  {
    const headers = new HttpHeaders({ 
      'Authorization': ''+localStorage.getItem('token')
    });

    return this.http.post(`${this.servicesUrl2}/add`, formData, { headers })
     
  }

  getSiteList() : Observable<any> { const headers = new HttpHeaders({ 
    'Authorization': ''+localStorage.getItem('token')
  });

    return this.http.get(`${this.servicesUrl2}/list`, { headers })
      
  }

  siteSearch() { 
    const headers = new HttpHeaders({ 
    'Authorization': ''+localStorage.getItem('token')
  });

    return this.http.get(`${this.servicesUrl2}/search`, { headers })
      
  }
// mapping services
  publishMapping(mappFormData: FormData): Observable<any> {
    const headers = new HttpHeaders({ 
      'Authorization': ''+localStorage.getItem('token')
    });

    return this.http.post(`${this.servicesUrl3}/add`, mappFormData, { headers })
      
  }

  getMappingList(): Observable<any> {
    const headers = new HttpHeaders({ 
      'Authorization': ''+localStorage.getItem('token')
    });

    return this.http.get(`${this.servicesUrl3}/list`, { headers})
     
  }

  searchMapping(name: string, location: string, keyword: string): Observable<any> {
    const headers = new HttpHeaders({ 
      'Authorization': ''+localStorage.getItem('token')
    });

    let params = new HttpParams();
    if (name) params = params.set('name', name);
    if (location) params = params.set('location', location);
    if (keyword) params = params.set('keyword', keyword);

    return this.http.get(`${this.servicesUrl3}/search`, { headers})
      
  }


}