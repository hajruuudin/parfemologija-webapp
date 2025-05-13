import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms'
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../../controller/auth.service';
import { UserLogin } from '../../../model/user-model';
import { NgxSpinner, NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink, NgxSpinnerComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  host: {
    class: 'w-full md:w-3/4 lg:w-2/3 h-full md:h-2/3 bg-(--background-200) rounded-2xl outline-2 outline-(--background-400) transition-all transition-200'
  }
})
export class LoginComponent implements OnInit{
  loginForm! : FormGroup;

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private authService: AuthService,
    private router: Router,
    private spinner: NgxSpinnerService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onLoginSubmit(){
    if (this.loginForm.valid) {
      this.spinner.show()
      let userLogin : UserLogin = new UserLogin(
        this.loginForm.get('username')?.value,
        this.loginForm.get('password')?.value
      )

      this.authService.login(userLogin).subscribe({
        next: (response: any) => {
          this.spinner.hide()
          setTimeout(() => {
            this.router.navigate(['/']);
          }, 100); 

        },
        error: (error: Error) => {
          this.spinner.hide()
          this.toastr.error('Login failed!')
        }
      })
    } else {
      this.toastr.error("Please fill in the form!")
    }
  }


}
