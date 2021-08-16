package com.example.awsdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView taskTextView = findViewById(R.id.task);

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }

        Task item = Task.builder().title("Wash the Car").status("NOT WASHED").build();

        Amplify.DataStore.save(item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );

        Amplify.DataStore.query(Task.class,
                todos -> {
                    while (todos.hasNext()) {
                        Task todo = todos.next();

                        Log.i("Tutorial", "==== Task ====");
                        Log.i("Tutorial", "Name: " + todo.getTitle());
                        taskTextView.setText(todo.getTitle());
                    }
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );
    }
}