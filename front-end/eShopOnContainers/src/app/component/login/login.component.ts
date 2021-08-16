import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/service/storage.service';
import { ValidatorService } from 'src/app/service/validator.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public errorMessage: any;
  public form!: FormGroup;

  constructor(
    private storageService: StorageService,
    private router: Router,
    private validator: ValidatorService
  ) { }

  ngOnInit(): void {
    // navigate to product page if token_key is valid
    if (this.storageService.getToken(StorageService.TOKEN_KEY)) {
      this.router.navigate(['/product']);
    }

    this.form = new FormGroup({
      username: new FormControl(null, this.validator.textRequired()),
      password: new FormControl(null, this.validator.textRequired())
    }, { updateOn: 'change' });
  }

  onSubmit() {
    this.storageService.setToken(StorageService.USER_KEY, "admin");
    this.storageService.setToken(StorageService.TOKEN_KEY, "token");
    location.reload();
  }
}
