import { Routes } from '@angular/router';
import { AuthContainerComponent } from './views/auth/auth-container/auth-container.component';
import { LoginComponent } from './views/auth/login/login.component';
import { RegisterComponent } from './views/auth/register/register.component';
import { MainContainerComponent } from './views/main/main-container/main-container.component';

export const routes: Routes = [
    {
        path: 'auth',
        component: AuthContainerComponent,
        children: [
            {
                path: '', redirectTo: 'login', pathMatch: 'full'
            },
            {
                path: 'login',
                component: LoginComponent,
                data: { animation: 'loginPage' }
            },
            {
                path: 'register',
                component: RegisterComponent,
                data: { animation: 'registerPage' }
            }
        ]
    },
    {
        path: '',
        component: MainContainerComponent,
        children: []

    }
];
