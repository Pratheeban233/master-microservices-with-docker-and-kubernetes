import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'prathi'; 
  password = '';

  invalidLogin = false;
  invalid_credentials = 'Invalid Credentials'

  constructor() { }

  ngOnInit(): void {
  }

handleLogin() {
  console.log('username is = '+this.username)

  if(this.username === 'prathi' && this.password === 'password'){
    this.invalidLogin = false;
  }else{
    this.invalidLogin = true;
  }
}

}
