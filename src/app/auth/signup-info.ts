export class SignUpInfo {
  firstName: string;
  lastName: string;
  phone: string;
  cin: string;
  email: string;
  role: string[];
  password: string;


  constructor(firstName: string, lastName: string, phone: string, cin: string, email: string, role: string[], password: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.cin = cin;
    this.email = email;
    this.role = role;
    this.password = password;
  }
}
