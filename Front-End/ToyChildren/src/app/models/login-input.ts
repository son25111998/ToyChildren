export class LoginInput {
    userName: string;
    password: string;

    public constructor(userName: string, password: string) {
        this.userName = userName;
        this.password = password;
    }
}
