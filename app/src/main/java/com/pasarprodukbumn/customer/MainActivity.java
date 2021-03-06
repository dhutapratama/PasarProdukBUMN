package com.pasarprodukbumn.customer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    RelativeLayout menuLogin, menuHome, menuKategori, menuPesawat, menuHubungiKami, menuTentang;
    WebView webView;
    String mobile, desktop;
    Dialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobile = "Mozilla/5.0 (Linux; U; Android 4.4.4; en-us; 2014817 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/42.0.0.0 Mobile Safari/537.36";
        desktop = "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0";
        mobile = desktop;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loading = Dialogs.loading(this);

        // MySelf
        webView =(WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://www.pasarprodukbumn.com/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loading.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.dismiss();
            }

        });

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url != null) {
            webView.loadUrl(url);
        }

        // Button / Menu
        LinearLayout menuButton = (LinearLayout)findViewById(R.id.layout_click_sidebar);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

        View menuHeader = navigationView.inflateHeaderView(R.layout.nav_header_main);

        menuLogin = (RelativeLayout)menuHeader.findViewById(R.id.menuLogin);
        menuHome = (RelativeLayout)menuHeader.findViewById(R.id.menuHome);
        menuKategori = (RelativeLayout)menuHeader.findViewById(R.id.menuKategori);
        menuPesawat = (RelativeLayout)menuHeader.findViewById(R.id.menuPesawat);
        menuHubungiKami = (RelativeLayout)menuHeader.findViewById(R.id.menuHubungiKami);
        menuTentang = (RelativeLayout)menuHeader.findViewById(R.id.menuTentang);

        menuLogin.setOnClickListener(this);
        menuHome.setOnClickListener(this);
        menuKategori.setOnClickListener(this);
        menuPesawat.setOnClickListener(this);
        menuHubungiKami.setOnClickListener(this);
        menuTentang.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if(v.getId() == R.id.menuLogin) {
            webView.getSettings().setUserAgentString(mobile);
            webView.loadUrl("https://pasarprodukbumn.com/index.php?dispatch=auth.login_form");
        } else if(v.getId() == R.id.menuHome) {
            webView.getSettings().setUserAgentString(mobile);
            webView.loadUrl("https://www.pasarprodukbumn.com/");
        } else if(v.getId() == R.id.menuKategori) {
            //webView.getSettings().setUserAgentString(mobile);
            //webView.loadUrl("http://pasarprodukbumn.com/#/category/0");
            Intent intent = new Intent(MainActivity.this, KategoriActivity.class);
            startActivity(intent);
        } else if(v.getId() == R.id.menuPesawat) {
            webView.getSettings().setUserAgentString(desktop);
            webView.loadUrl("https://pasarprodukbumn.com/index.php?dispatch=tiket.garuda");
        } else if(v.getId() == R.id.menuHubungiKami) {
            Intent intent = new Intent(this, ContactActivity.class);
            startActivity(intent);
        } else if(v.getId() == R.id.menuTentang) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
