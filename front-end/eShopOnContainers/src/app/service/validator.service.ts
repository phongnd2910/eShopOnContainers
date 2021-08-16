import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {

  // Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
  // Username allowed of the dot (.), underscore (_), and hyphen (-).
  // The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
  // The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex
  // The number of characters must be between 5 to 20.
  readonly USERNAME_PATTERN = /^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$/;

  // Username should contain one special character
  // Username should contain one character
  // Username should contain one number
  // The number of characters must be between 8 to 40.
  readonly PASSWORD_PATTERN = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,40}$/

  // tslint:disable-next-line:max-line-length
  readonly EMAIL_PATTERN = /^((([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,})))?$/;

  constructor() { }

  public required(message?: string): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any | null } => {
      return (!control.value) ? { message: message ? message : 'Field required!' } : null as any;
    };
  }

  public textRequired(message?: string): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any | null } => {
      return (!control.value || control.value.trim() === '') ?
        { message: message ? message : 'Field required!' } : null as any;
    };
  }

  public maxLength(max: number, message?: string): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any | null } => {
      return (control.value && control.value.length > max) ?
        { message: message ? message : `Input length required less than ${max}!` } : null as any;
    };
  }

  public minLength(min: number, message?: string): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any | null } => {
      return (control.value && control.value.length < min) ?
        { message: message ? message : `Input length required greater than ${min}` } : null as any;
    };
  }

  public username(message?: string): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any | null } => {
      return (!this.USERNAME_PATTERN.test(control.value)) ?
        { message: message ? message : 'Invalid username format!' } : null as any;
    };
  }

  public password(message?: string): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any | null } => {
      return (!this.PASSWORD_PATTERN.test(control.value)) ?
        { message: message ? message : 'Invalid password format!' } : null as any;
    };
  }
}
