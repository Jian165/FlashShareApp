package com.markcode.flashshareapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateDeck extends AppCompatActivity {


    EditText DeckTitle, DeckCourseSubject, DeckTopic, DeckNote;
    Button CreateDeck;
    ImageView UploadDeckIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_deck);

        DeckTitle = findViewById(R.id.DeckTitle);



    }
}