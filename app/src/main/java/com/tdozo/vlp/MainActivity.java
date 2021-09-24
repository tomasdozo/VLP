package com.tdozo.vlp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tdozo.vlp.database.CharacterViewModel;
import com.tdozo.vlp.entities.Character;

public class MainActivity extends AppCompatActivity {
    LinearLayout view_characters;
    CharacterViewModel viewModel;
    Button new_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_characters = findViewById(R.id.character_list);
        new_btn = findViewById(R.id.new_character);
        viewModel = new CharacterViewModel(this);

        viewModel.getCharacters().observe(this, characters -> {
            view_characters.removeAllViews();
            for (Character character : characters) {
                view_characters.addView(createCharacterView(character));
            }

        });

        new_btn.setOnClickListener(r -> startActivity(new Intent(this, CharacterActivity.class)));

    }

    private View createCharacterView(Character cha) {
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.character_row, null);
        TextView name_view = view.findViewById(R.id.name);
        TextView race_view = view.findViewById(R.id.race);
        View edit_btn = view.findViewById(R.id.edit);
        View delete_btn = view.findViewById(R.id.delete);

        name_view.setText(cha.getName());
        race_view.setText(getString(R.string.race_apitutde, getString(cha.getRace().getName()), getString(cha.getAptitude().getName())));

        //race_view.setText(cha.getRace().getName()+ " | " + cha.getAptitude().getName());

        view.setOnClickListener(r -> viewModel.loadCharacter(cha, this));
        //name_view.setOnClickListener(r -> viewModel.loadCharacter(cha, this));

        //race_view.setOnClickListener(r -> viewModel.loadCharacter(cha, this));

        edit_btn.setOnClickListener((r) -> {
            Intent intent = new Intent(this, CharacterActivity.class);
            intent.putExtra("Character", cha);
            startActivity(intent);
        });

        delete_btn.setOnClickListener(v -> new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.Remove))
                .setMessage("¿Esta seguro que desea eliminar su personaje?")
                .setNegativeButton(R.string.Remove, (dialog, which) ->
                        cha.delete(this)).show());
        return view;
    }

}
