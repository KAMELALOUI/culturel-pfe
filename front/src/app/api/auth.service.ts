import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8088/api/auth';

  constructor(private http: HttpClient, private router: Router) { }

  login(credential: any) {
    return this.http.post<any>(`${this.apiUrl}/login`, credential);
  }

  signup(credential: any) {
    return this.http.post<any>(`${this.apiUrl}/signup`, credential);
  }

  logout1() {
    return this.http.post<any>(`${this.apiUrl}/logout`, {});
  }
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/auth']);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
}