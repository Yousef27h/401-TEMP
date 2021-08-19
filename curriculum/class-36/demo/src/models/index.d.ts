import { ModelInit, MutableModel, PersistentModelConstructor } from "@aws-amplify/datastore";





type TaskMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

type NoteMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

type AccountMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

type ExpenseMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

export declare class Task {
  readonly id: string;
  readonly title: string;
  readonly description?: string;
  readonly status?: string;
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<Task, TaskMetaData>);
  static copyOf(source: Task, mutator: (draft: MutableModel<Task, TaskMetaData>) => MutableModel<Task, TaskMetaData> | void): Task;
}

export declare class Note {
  readonly id: string;
  readonly content: string;
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<Note, NoteMetaData>);
  static copyOf(source: Note, mutator: (draft: MutableModel<Note, NoteMetaData>) => MutableModel<Note, NoteMetaData> | void): Note;
}

export declare class Account {
  readonly id: string;
  readonly name: string;
  readonly description?: string;
  readonly balance?: number;
  readonly expenses?: (Expense | null)[];
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