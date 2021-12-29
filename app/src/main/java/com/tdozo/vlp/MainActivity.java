package com.tdozo.vlp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tdozo.vlp.database.CharacterViewModel;
import com.tdozo.vlp.database.DatabaseVLP;
import com.tdozo.vlp.entities.Character;
import com.tdozo.vlp.utils.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static final int CREATE_FILE = 1;
    private static final int OPEN_FILE = 2;
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

        ImageView backup_btn = findViewById(R.id.backup);
        ImageView restore_btn = findViewById(R.id.restore);

        backup_btn.setOnClickListener(r -> backup());
        restore_btn.setOnClickListener(r -> restore());

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

    public void backup() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/db");
        intent.putExtra(Intent.EXTRA_TITLE, "backup_vlp.db");

        startActivityForResult(intent, CREATE_FILE);
    }

    public void restore() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/*");

        startActivityForResult(intent, OPEN_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_FILE && resultCode == Activity.RESULT_OK) {
            DatabaseVLP.databaseWriteExecutor.execute(() -> {
                DatabaseVLP.getDatabase(this).characterDao().checkpoint(new SimpleSQLiteQuery("pragma wal_checkpoint(full)"));
                if (data != null) {
                    Uri uri = data.getData();
                    // TODO HACER MIS COSAS
                    try {
                        OutputStream out = getContentResolver().openOutputStream(uri);
                        FileInputStream in = new FileInputStream(getDatabasePath("vlp_database"));
                        IOUtils.copy(in, out);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        }
        if (requestCode == OPEN_FILE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    InputStream in = getContentResolver().openInputStream(uri);
                    FileOutputStream out = new FileOutputStream(getDatabasePath("vlp_database"));
                    IOUtils.copy(in, out);
                    finish();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
