package com.recurjun.amplifydemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Expense;
import com.recurjun.amplifydemo.R;
import com.recurjun.amplifydemo.data.ExpenseDataManager;

import java.net.InetAddress;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText expenseEditText = findViewById(R.id.expense);
            EditText descriptionEditText = findViewById(R.id.description);
            EditText costEditText = findViewById(R.id.cost);

            String expenseName = expenseEditText.getText().toString();
            String expenseDescription = descriptionEditText.getText().toString();
            String expenseCost = costEditText.getText().toString();

            // create expense item
            Expense expenseItem = Expense.builder()
                    .name(expenseName)
                    .description(expenseDescription)
                    .cost(Double.parseDouble(expenseCost))
                    .build();

            if (isNetworkAvailable(getApplicationContext())) {
                Log.i(TAG, "onClick: the network is available");
            } else {
                Log.i(TAG, "onClick: net down");
            }

            saveExpenseToAPI(expenseItem);
            ExpenseDataManager.getInstance().getData().add(expenseItem);

            // TODO: 8/18/21 save locally to Room

            Toast.makeText(MainActivity.this, "Expense saved", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAmplify();

        Button addExpense = findViewById(R.id.addButton);
        addExpense.setOnClickListener(listener);

        // uses local data store
//        Amplify.DataStore.save(expenseItem,
//                success -> Log.i(TAG, "Saved item: " + success.item().getName()),
//                error -> Log.e(TAG, "Could not save item to DataStore", error)
//        );
//
//        Amplify.DataStore.query(Expense.class,
//                expenseIterator -> {
//                    while (expenseIterator.hasNext()) {
//                        Expense expense = expenseIterator.next();
//
//                        Log.i(TAG, "==== Expense ====");
//                        Log.i(TAG, "Name: " + expense.getName());
//                    }
//                },
//                failure -> Log.e(TAG, "Could not query DataStore", failure)
//        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_expenses) {
            Intent secondListIntent = new Intent(this, ExpensesActivity.class);
            startActivity(secondListIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configureAmplify() {
        // configure Amplify plugins
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i(TAG, "onCreate: Successfully initialized Amplify plugins");
        } catch (AmplifyException exception) {
            Log.e(TAG, "onCreate: Failed to initialize Amplify plugins => " + exception.toString());
        }
    }

    private void saveExpenseToAPI(Expense expenseItem) {
        Amplify.API.mutate(ModelMutation.create(expenseItem),
                success -> Log.i(TAG, "Saved item: " + success.getData().getName()),
                error -> Log.e(TAG, "Could not save item to API/dynamodb", error));

    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnected();
    }
}