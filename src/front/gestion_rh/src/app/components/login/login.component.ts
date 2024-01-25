import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {User} from "../../shared/models/user";
import {environment} from "../../../environments/environment.development";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  errorLogin: boolean = false;
  loggedUser: User = {};
  unLoggedUser: User = {};
  email: string | undefined;
  password: string | undefined;
  userId: number | undefined;
  loggedBtn: boolean = false;

  check(){
    environment.check=!environment.check;
  }

  login() {

  }
}
