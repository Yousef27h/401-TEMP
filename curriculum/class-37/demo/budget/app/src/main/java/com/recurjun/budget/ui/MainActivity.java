package com.recurjun.budget.ui;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Account;
import com.amplifyframework.datastore.generated.model.Expense;
import com.recurjun.budget.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_FOR_FILE = 999;

    private SharedPreferences sharedPreferences;
    private Account account;
    private TextView accountPreference;
    private EditText expenseEditText;
    private EditText descriptionEditText;
    private EditText costEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCurrentUser();

        // get account from shared preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Handler handler = new Handler(Looper.getMainLooper(), // get main ui thread
                message -> {
                    Toast.makeText(
                            MainActivity.this,
                            "Account has been set",
                            Toast.LENGTH_SHORT).show();
                    accountPreference.setText(account.getName());
                    return false;
                });

        String accountId = sharedPreferences.getString("accountId", "");
        Amplify.API.query(ModelQuery.get(Account.class, accountId),
                success -> {
                    Log.i(TAG, "the account is: " + success.getData().getName());
                    account = success.getData();
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e(TAG, "an error occurred: " + error.toString())
        );

        expenseEditText = findViewById(R.id.account);
        descriptionEditText = findViewById(R.id.description);
        costEditText = findViewById(R.id.cost);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            expenseEditText = findViewById(R.id.account);
            descriptionEditText = findViewById(R.id.description);
            costEditText = findViewById(R.id.cost);

            String expenseName = expenseEditText.getText().toString();
            String expenseDescription = descriptionEditText.getText().toString();
            String expenseCost = costEditText.getText().toString();

            if (expenseName.isEmpty() || expenseCost.isEmpty()) {
                Toast.makeText(
                        MainActivity.this,
                        "Please fill out the form",
                        Toast.LENGTH_SHORT).show();
            } else {
                // create a new expense
                Expense expenseItem = Expense.builder()
                        .name(expenseName)
                        .account(account)
                        .description(expenseDescription)
                        .cost(Double.parseDouble(expenseCost))
                        .build();

                if (isNetworkAvailable(getApplicationContext())) {

                    Log.i(TAG, "onClick: the network is available");
                    Amplify.API.mutate(ModelMutation.create(Account.builder().name("Savings").description("Savings Account").build()),
                            success -> Log.i(TAG, "Saved item: " + success.getData()),
                            error -> Log.e(TAG, "Could not save item to API/dynamodb", error));

                    saveExpenseToAPI(expenseItem);

                    Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i(TAG, "onClick: net down");
                    Amplify.DataStore.save(expenseItem,
                            success -> Log.i("Tutorial", "Saved item: " + success.item().getName()),
                            error -> Log.e("Tutorial", "Could not save item to DataStore", error)
                    );
                }
            }
        });

        Button clearForm = findViewById(R.id.clear_form);
        clearForm.setOnClickListener(view -> {
            expenseEditText.setText("");
            expenseEditText.requestFocus();
            descriptionEditText.setText("");
            costEditText.setText("");
        });

        ImageView settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(view -> {
            Intent goToSettings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(goToSettings);
        });

        Button limitButton = findViewById(R.id.budget_limit);
        limitButton.setOnClickListener(view -> {

        });

        Button upload = findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFileFromDevice();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_FOR_FILE && resultCode == RESULT_OK) {
            Log.i(TAG, "onActivityResult: returned from file explorer");
            Log.i(TAG, "onActivityResult: => " + data.getData());

            File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFile");

            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                FileUtils.copy(inputStream, new FileOutputStream(uploadFile));
            } catch (Exception exception) {
                Log.e(TAG, "onActivityResult: file upload failed" + exception.toString());
            }

            Amplify.Storage.uploadFile(
                    new Date().toString() + ".png",
                    uploadFile,
                    success -> {
                        Log.i(TAG, "uploadFileToS3: succeeded " + success.getKey());
                    },
                    error -> {
                        Log.e(TAG, "uploadFileToS3: failed " + error.toString());
                    }
            );
        }
    }

    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
//        chooseFile.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{".jpg", ".png"});
        chooseFile = Intent.createChooser(chooseFile, "Choose a File");
        startActivityForResult(chooseFile, REQUEST_FOR_FILE); // deprecated
    }

//    private void getFileFromS3Storage() {
//        Amplify.Storage.downloadFile(
//                "",
//                new File(),
//                success -> {
//                    // displaying in an image view
//                    success.getFile();
//                },
//                error -> {}
//        );
//    }

    private void uploadFileToS3() {
        File testFile = new File(getApplicationContext().getFilesDir(), "test");

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testFile));
            bufferedWriter.append("This is a test file to demonstrate S3 functionality");
            bufferedWriter.close();
        } catch (Exception exception) {
            Log.e(TAG, "uploadFileToS3: failed" + exception.toString());
        }

        Amplify.Storage.uploadFile(
                "test",
                testFile,
                success -> {
                    Log.i(TAG, "uploadFileToS3: succeeded " + success.getKey());
                },
                error -> {
                    Log.e(TAG, "uploadFileToS3: failed " + error.toString());
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        String accountName = sharedPreferences.getString("accountName", "Set account in settings");
        accountPreference = findViewById(R.id.preferencesAccount);
        accountPreference.setText(accountName);
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

    private void saveExpenseToAPI(Expense expenseItem) {
        Amplify.API.mutate(ModelMutation.create(expenseItem),
                success -> Log.i(TAG, "Saved item: " + success.getData()),
                error -> Log.e(TAG, "Could not save item to API/dynamodb", error));

    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnected();
    }

    void getCurrentUser() {
        AuthUser authUser = Amplify.Auth.getCurrentUser();
        Log.i(TAG, "getCurrentUser: " + authUser.toString());
        Log.i(TAG, "getCurrentUser: username" + authUser.getUsername());
        Log.i(TAG, "getCurrentUser: userId" + authUser.getUserId());

        Toast.makeText(this, "The user is => " + authUser.getUsername(), Toast.LENGTH_SHORT).show();
    }


}