export interface User {
  id?: number;
  firstName?: string,
  lastName?: string,
  birthdate?: Date,
  email?: string,
  password?: string,
  roles?:string[]
}
