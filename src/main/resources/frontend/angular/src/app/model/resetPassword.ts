export class ResetPasswordRequest {
  resetPasswordToken: string
  newPassword: string;

  constructor(resetPasswordToken: string, newPassword: string) {
    this.resetPasswordToken = resetPasswordToken;
    this.newPassword = newPassword;
  }
}
