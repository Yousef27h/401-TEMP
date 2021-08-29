package com.recurjun.budgeto.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.recurjun.budgeto.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Log.i(TAG, "onActivityResult: Merry Christmas");
                        }
                    }
                });

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        TextView textView = findViewById(R.id.button_accounts);
        textView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
        });

        TextView department = findViewById(R.id.textView_department);
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "It worked", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onClick: It worked");

//                try {
//                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//                    intent.addCategory(Intent.CATEGORY_OPENABLE);
//                    intent.setType("application/pdf");
//
//                    // Optionally, specify a URI for the file that should appear in the
//                    // system file picker when it loads.
//                    intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.decode(""));
//
//                    startActivityForResult(intent, 999);
//                } catch (ActivityNotFoundException exception) {
//                    Toast.makeText(getApplicationContext(), "No App Found" + exception.toString(), Toast.LENGTH_SHORT).show();
//                }

//                Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("application/pdf");
//                intent.putExtra(Intent.EXTRA_TITLE, "invoice.pdf");
//
//                // Optionally, specify a URI for the directory that should be opened in
//                // the system file picker when your app creates the document.
//                intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.decode(""));
//
//                startActivityForResult(intent, 999);

//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                intent.addCategory(Intnew Intent(Intent.ACTION_GET_CONTENT);ent.CATEGORY_APP_BROWSER);
//                Intent sharedintent = Intent.createChooser(intent, "Pick Something");
//                startActivityForResult(sharedintent, 999);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                String[] mimeTypes =
                        {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                                "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                                "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                                "text/plain",
                                "application/pdf",
                                "application/zip", "application/vnd.android.package-archive"};

                intent = new Intent(Intent.ACTION_GET_CONTENT); // or ACTION_OPEN_DOCUMENT
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                Intent chooser = Intent.createChooser(intent, "Choose Something");
                someActivityResultLauncher.launch(chooser);

//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//                sendIntent.setType("image/*");
//
//                Intent shareIntent = Intent.createChooser(sendIntent, null);
//                startActivity(shareIntent);


            }
        });
    }
}