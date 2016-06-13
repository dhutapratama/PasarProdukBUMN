package com.pasarprodukbumn.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class KategoriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listView = (ListView) findViewById(R.id.listKategori);
        String[] values = new String[] {
                "Kesehatan",
                "Electronics",
                "Makanan & Minuman",
                "Token PLN",
                "Telekomunikasi",
                "Pariwisata",
                "Souvenir",
                "Pertanian",
                "Kebutuhan",
                "Fashion"
        };

        final String[] address = new String[] {
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=278",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=166",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=279",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=297",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=298",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=299",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=303",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=306",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=274",
                "http://pasarprodukbumn.com/index.php?dispatch=categories.view&category_id=277"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(KategoriActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("url", address[position]);
                startActivity(intent);
                finish();
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
