package com.example.customlist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();
    private ItemAdapter itemAdapter;
    private List<Drawable> images = new ArrayList<>();
    private ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.listViewMain);

        dropImages();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomItemData();
            }
        });

        itemAdapter = new ItemAdapter(this, null);
        listView.setAdapter(itemAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showInfo(position);
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void dropImages() {
        images.add(getDrawable(R.drawable.ic_favorite_border_black_24dp));
        images.add(getDrawable(R.drawable.ic_insert_emoticon_black_24dp));
        images.add(getDrawable(R.drawable.ic_star_border_black_24dp));
    }

    private void generateRandomItemData() {
        itemAdapter.addItem(new ItemData(
                images.get(random.nextInt(images.size())),
                "Hello" + itemAdapter.getCount(),
                "It\'s me"
                ));
    }

    private void showInfo(int position) {
        ItemData itemData = (ItemData) itemAdapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + itemData.getTitle() + "\n" +
                        "Subtitle: " + itemData.getSubtitle() ,
                Toast.LENGTH_SHORT).show();
    }

    public void delete(View v){
        int position = listView.getPositionForView(v);
        itemAdapter.removeItem(position);
    }
}
