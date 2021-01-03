package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Priority;
import com.amplifyframework.datastore.generated.model.Todo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
        Amplify.DataStore.query(
                Todo.class,
                todos -> {
                    while (todos.hasNext()) {
                        Todo todo = todos.next();

                        Log.i("Tutorial", "==== Todo ====");
                        Log.i("Tutorial", "Name: " + todo.getName());

                        if (todo.getPriority() != null) {
                            Log.i("Tutorial", "Priority: " + todo.getPriority().toString());
                        }

                        if (todo.getDescription() != null) {
                            Log.i("Tutorial", "Description: " + todo.getDescription());
                        }
                    }
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}