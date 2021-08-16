import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './service/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'eShopOnContainers';
  public isLogin: boolean | undefined;

  constructor(
    private router: Router,
    private storageService: StorageService
  ) { }
  
  ngOnInit(): void {
    this.isLogin = this.storageService.getToken(StorageService.TOKEN_KEY) ? true : false;
    console.log(this.isLogin);
    this.router.navigate([this.isLogin ? '/product' : '/login']);
  }
  
}
