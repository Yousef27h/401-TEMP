// @ts-check
import { initSchema } from '@aws-amplify/datastore';
import { schema } from './schema';



const { Account, Expense } = initSchema(schema);

export {
  Account,
  Expense
};