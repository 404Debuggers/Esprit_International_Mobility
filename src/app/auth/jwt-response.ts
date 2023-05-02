export class JwtResponse {
  Token: string | undefined;
  type: string | undefined;
  email: string | undefined;
  authorities: string[] | undefined;
}
