// @ts-check
import { initSchema } from '@aws-amplify/datastore';
import { schema } from './schema';



const { Task, Note, Account, Expense } = initSchema(schema);

export {
  Task,
  Note,
  Account,
  Expense
};