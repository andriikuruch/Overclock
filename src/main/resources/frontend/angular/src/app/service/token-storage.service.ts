import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    const storedToken = sessionStorage.getItem(TOKEN_KEY);
    if (typeof storedToken === 'string') {
      return storedToken;
    } else return null;
  }

  public saveUser(user : any) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser() : any | null{
    const storedUser = sessionStorage.getItem(USER_KEY);
    if (typeof storedUser === 'string') {
      return JSON.parse(storedUser);
    } else return null;
  }
}
