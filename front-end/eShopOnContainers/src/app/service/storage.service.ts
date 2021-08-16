import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  static readonly TOKEN_KEY = 'auth-token';
  static readonly USER_KEY = 'auth-user';

  constructor() { }

  public clearToken() {
    window.localStorage.clear();
  }

  public setToken(tokenName: string, value: any) {
    window.localStorage.removeItem(tokenName);
    window.localStorage.setItem(tokenName, value);
  }

  public getToken(tokenName: string) {
    return window.localStorage.getItem(tokenName);
  }
}
