import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/service/storage.service';
import { UserService } from 'src/app/service/user.service';
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
    private validator: ValidatorService,
    private userService: UserService
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
    this.form.disable();
    this.errorMessage = '';
    this.userService.authenticate(this.form.value).subscribe(response => {
      this.storageService.setToken(StorageService.TOKEN_KEY, response.token)
      location.reload();
    }, error => {
      this.errorMessage = 'Invalid username or password!';
    }).add(() => {
      this.form.enable();
    });

  }
}
