import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms'
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  host: {
    class: 'w-full md:w-3/4 lg:w-2/3 h-full md:h-2/3 bg-(--background-200) rounded-2xl outline-2 outline-(--background-400) transition-all transition-200'
  }
})
export class RegisterComponent implements OnInit{
  registerForm! : FormGroup;

  constructor(
    private fb: FormBuilder
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
}
