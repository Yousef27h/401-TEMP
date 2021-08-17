package com.recurjun.budget.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.recurjun.budget.R;
import com.recurjun.budget.data.Expense;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }

        Task item = Task.builder().title("Wash Dishes").description("It is very dirty").build();
//
//        Amplify.DataStore.save(item,
//                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
//                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
//        );

        Amplify.API.mutate(ModelMutation.create(item),
                success -> Log.i("Tutorial", "Saved item: " + item.getTitle()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error));

//        Amplify.DataStore.query(Task.class,
//                tasks -> {
//                    while (tasks.hasNext()) {
//                        Task todo = tasks.next();
//
//                        Log.i("Tutorial", "==== Task ====");
//                        Log.i("Tutorial", "Name: " + todo.getTitle());
//                        Log.i("Tutorial", "Name: " + todo.getDescription());
//                    }
//                },
//                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
//        );
    }
}