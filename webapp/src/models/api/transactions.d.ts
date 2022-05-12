export interface TransactionDto {
  id: number;
  amount: number;
  source?: number;
  target?: number;
  type?: string;
  time?: number;
  labels?: string[];
  name?: string;
}
