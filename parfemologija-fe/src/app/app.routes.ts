import { Routes } from '@angular/router';
import { AuthContainerComponent } from './views/auth/auth-container/auth-container.component';
import { LoginComponent } from './views/auth/login/login.component';
import { RegisterComponent } from './views/auth/register/register.component';
import { MainContainerComponent } from './views/main/main-container/main-container.component';
import { HomepageComponent } from './views/main/homepage/homepage.component';
import { BrowseArticlesComponent } from './views/main/browse-articles/browse-articles.component';
import { BrowseFragrancesComponent } from './views/main/browse-fragrances/browse-fragrances.component';
import { ProfileComponent } from './views/main/profile/profile.component';

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
        children: [
            {
                path: '', redirectTo: 'homepage', pathMatch: 'full'
            },
            {
                path: 'homepage',
                component: HomepageComponent
            },
            {
                path: 'articles',
                component: BrowseArticlesComponent
            },
            {
                path: 'fragrances',
                component: BrowseFragrancesComponent
            },
            {
                path: 'profile',
                component: ProfileComponent
            }
        ]

    }
];
