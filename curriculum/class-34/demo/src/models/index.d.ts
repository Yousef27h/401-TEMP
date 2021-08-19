import { ModelInit, MutableModel, PersistentModelConstructor } from "@aws-amplify/datastore";





type AccountMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

type ExpenseMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

export declare class Account {
  readonly id: string;
  readonly name: string;
  readonly description?: string;
  readonly balance?: number;
  readonly expences?: (Expense | null)[];
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<Account, AccountMetaData>);
  static copyOf(source: Account, mutator: (draft: MutableModel<Account, AccountMetaData>) => MutableModel<Account, AccountMetaData> | void): Account;
}

export declare class Expense {
  readonly id: string;
  readonly name: string;
  readonly description?: string;
  readonly cost?: number;
  readonly account: Account;
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<Expense, ExpenseMetaData>);
  static copyOf(source: Expense, mutator: (draft: MutableModel<Expense, ExpenseMetaData>) => MutableModel<Expense, ExpenseMetaData> | void): Expense;
}