export class UserModel {
    constructor(
        public id: number,
        public username: string,
        public name: string,
        public surname: string,
        public email: string,
        public password: string,
        public phoneNumber: String,
        public location: string,
        public isAdmin: boolean,
        public joinedAt: Date
    ) {}
}

export class UserCreate {
    constructor(
        public username: string,
        public name: string,
        public surname: string,
        public email: string,
        public password: string,
        public location: string,
        public phoneNumber: String
    ) {}
}

export class UserLogin{
    constructor(
        public username: String,
        public password: String
    ) {}
}

export interface LoggedUserProfile{
    id: number,
    username: string;
    name: string;
    surname: string;
    email: string;
    phoneNumber: number;
    location: string;
    isAdmin: boolean;
    joinedAt: string;
}