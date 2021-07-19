package com.example.cybercardv02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import it.auron.library.mecard.MeCard;
import net.glxn.qrgen.android.QRCode;

/**
* <h1>CyberCard</h1>
* Convenient way to give out personal
* contact information using a QR Code.
* <p>
*
* @author  Blane Staskiewicz
* @version 2.0
* @since   2021-05-04
*/
public class MainActivity extends AppCompatActivity implements SaveDialog.ButtonClickListener {
    String first_name;
    String last_name;
    String company;
    String p_number;
    String email;
    int or = 0;   // orientation
    
    /**
    * This method is used to add two integers. This is
    * a the simplest form of a class method, just to
    * show the usage of various javadoc Tags.
    *
    * @param savedInstanceState Used to set up onCreate
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sets tab selected listener
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager vp = findViewById(R.id.viewpager);
        fragmentAdapter fa = new fragmentAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        vp.setAdapter(fa);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
                // Home Tab position
                if(tab.getPosition() == 0){or = 0;}
                // Contact Tab position
                else if(tab.getPosition() == 1){
                    or = 0;
                    if (savedInstanceState != null){
                        AsyncTask.execute(() -> {
                            first_name = savedInstanceState.getString("fName");
                            last_name = savedInstanceState.getString("lName");
                            company = savedInstanceState.getString("comp");
                            p_number = savedInstanceState.getString("pNum");
                            email = savedInstanceState.getString("eMail");
                        });
                    }
                }
                // QR Code Tab position
                else if(tab.getPosition() == 2){
                    // makes QR code and sets listener to SAVE button
                    or = 2;
                    initQRCode();
                    Button b = findViewById(R.id.button);
                    AsyncTask.execute(() -> b.setOnClickListener(view -> {
                        SaveDialog new_dialog = new SaveDialog();
                        new_dialog.setTitle("Allow CyberCard to access photos?");
                        new_dialog.show(getSupportFragmentManager(), "tag");
                    }));
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    /**
     * initializes QR Code - uses ZXING/QRGen/QrCardParsing libraries
     */
    private void initQRCode() {
        EditText et1 = findViewById(R.id.editTextTextPersonName3);
        EditText et2 = findViewById(R.id.editTextTextPersonName4);
        EditText et3 = findViewById(R.id.editTextTextPersonName5);
        EditText et4 = findViewById(R.id.editTextTextPersonName6);
        EditText et5 = findViewById(R.id.editTextTextPersonName7);

        first_name = et1.getText().toString();
        last_name = et2.getText().toString();
        company = et3.getText().toString();
        p_number = et4.getText().toString();
        email = et5.getText().toString();

        ImageView v = findViewById(R.id.idIVQrcode);

        MeCard meCard = new MeCard();
        meCard.setName(first_name);
        meCard.setSurname(last_name);
        meCard.setEmail(email);
        meCard.addTelephone(p_number);
        meCard.setOrg(company);

        // ios scan can pick up a url (homepage)
        //meCard.setUrl(url);

        String meCardContent = meCard.buildString();
        v.setImageBitmap(QRCode.from(meCardContent).bitmap());
        v.setVisibility(View.VISIBLE);
    }

    /**
     * Saves QR Code, shows snackbars as indicators
     * QR Code tab: on click SAVE button, SaveDialog callback
     *
     * @param val value of callback from SaveDialog
     *            true: save to photos, display saved snackbar
     *            false: cancel, display not saved snackbar
     */
    public void onButtonClick(boolean val) {
        View view = findViewById(R.id.viewpager);
        if (val) {
            // save to photos
            ImageView v = findViewById(R.id.idIVQrcode);
            v.buildDrawingCache();
            AsyncTask.execute(() -> {
                Bitmap bmp = v.getDrawingCache();
                MediaStore.Images.Media.insertImage(getContentResolver(), bmp, "CyberCard",
                        "Contact Information");
            });
            Snackbar snackbar = Snackbar.make(view, "QR Code Saved to Photos",
                    Snackbar.LENGTH_LONG);
            View mView = snackbar.getView();
            TextView textView = mView.findViewById(R.id.snackbar_text);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        }
        else{
            // cancel
            Snackbar snackbar = Snackbar.make(view, "QR Code Was Not Saved",
                    Snackbar.LENGTH_LONG);
            View mView = snackbar.getView();
            TextView textView = mView.findViewById(R.id.snackbar_text);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
        }
    }

    /**
     * persists input data, when oriented
     *
     * @param outState bundle of states to save
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("fName", first_name);
        outState.putString("lName", last_name);
        outState.putString("comp", company);
        outState.putString("pNum", p_number);
        outState.putString("eMail", email);
    }

    /**
     * Reinitializes QR Code when oriented
     *
     * @param newConfig The new view configuration
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(or == 2) {
            initQRCode();
        }
    }
}
