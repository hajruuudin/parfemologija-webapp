import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms'
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserCreate, UserModel } from '../../../model/user-model';
import { AuthService } from '../../../controller/auth.service';
import { NgxSpinnerComponent, NgxSpinnerService } from 'ngx-spinner';
import { response } from 'express';
import { error } from 'console';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, RouterLink, NgxSpinnerComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  host: {
    class: 'w-full md:w-3/4 lg:w-2/3 h-full md:h-2/3 bg-(--background-200) rounded-2xl outline-2 outline-(--background-400) transition-all transition-200'
  }
})
export class RegisterComponent implements OnInit{
  registerForm! : FormGroup;

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private authService: AuthService,
    private router: Router,
    private spinner: NgxSpinnerService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required]], 
      surname: ['', [Validators.required]],
      username: ['', [Validators.required]],
      email: ['', [Validators.required]],
      phone: ['', [Validators.required]],
      location: ['', [Validators.required]],
      password: ['', [Validators.required]],
      repeatPassword: ['', [Validators.required]]
    });
  }

  onRegisterSubmit(){
    if (this.registerForm.valid) {
      const password = this.registerForm.get('password')?.value;
      const repeatPassword = this.registerForm.get('repeatPassword')?.value;

      if (password.length < 6) {
        this.toastr.error('Password must be longer than 6 characters.', 'Validation Error');
        return;
      }

      if (!/[A-Z]/.test(password)) {
        this.toastr.error('Password must contain at least one uppercase letter.', 'Validation Error');
        return;
      }

      if (password !== repeatPassword) {
        this.toastr.error('Passwords do not match.', 'Validation Error');
        return;
      }

      this.spinner.show()
      this.authService.checkUsername(this.registerForm.get('username')?.value).subscribe({
        next: (response : any) => {
          if(!response['status']){
            let registeredUser : UserCreate = new UserCreate(
              this.registerForm.get('username')?.value,
              this.registerForm.get('name')?.value,
              this.registerForm.get('surname')?.value,
              this.registerForm.get('email')?.value,
              this.registerForm.get('password')?.value,
              this.registerForm.get('location')?.value,
              this.registerForm.get('phone')?.value
            )
            console.log(registeredUser)
      
            this.authService.register(registeredUser).subscribe({
              next: (request : any) => {
                this.spinner.hide()
                this.router.navigate(['/auth/login'])
              },
              error: (error : Error) => {
                this.spinner.hide()
                this.toastr.error('There was an error while registering. Please try again later!');
                console.error(error)
              }
            })
          } else {
            this.spinner.hide()
            this.toastr.error('Username already taken!')
          }
        },
        error: (error: Error) => {
          this.spinner.hide()
          this.toastr.show("Internal server error")
          console.error(error);
        }
      })
    } else {
      this.toastr.error('Please fill in the form');
    }
  }
}
