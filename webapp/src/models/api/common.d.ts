import { CategoryDto } from './categories.d';
import { AccountDto } from './accounts.d';
export interface UserDataDto {
  accounts: AccountDto[];
  categories: CategoryDto[];
}