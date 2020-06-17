package com.example.lab8sensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab8sensores.data.Contacto;
import com.example.lab8sensores.data.Contenedor;

public class ContactActivity extends AppCompatActivity {

    ImageView photo;
    ImageButton callBtn, smsBtn, volverBtn,sendBtn;
    EditText phone, name,message;
    private static final int Image_Capture_Code = 1;
    private boolean editable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        photo = (ImageView) findViewById(R.id.photo);

        callBtn = (ImageButton) findViewById(R.id.callBtn);
        smsBtn = (ImageButton) findViewById(R.id.smsBtn);
        volverBtn = (ImageButton) findViewById(R.id.volverBtn);
        sendBtn = (ImageButton) findViewById(R.id.sendBtn);

        phone = (EditText) findViewById(R.id.phoneNumber);
        name = (EditText) findViewById(R.id.nameText);
        message = (EditText) findViewById(R.id.messageContent);


        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(message.getVisibility()==View.INVISIBLE && sendBtn.getVisibility()==View.INVISIBLE){
                    message.setVisibility(View.VISIBLE);
                    sendBtn.setVisibility(View.VISIBLE);
                }
            }
        });


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(phone.getText().toString(),null,message.getText().toString(),null,null);
                    Toast.makeText(ContactActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                    message.setVisibility(View.INVISIBLE);
                    sendBtn.setVisibility(View.INVISIBLE);
                    message.setText("");
                }
                catch (Exception e){
                    Toast.makeText(ContactActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            editable = extras.getBoolean("editable");
            if (editable){
                Contacto aux = (Contacto) getIntent().getSerializableExtra("contacto");
                phone.setText(aux.getNumber());
                name.setText(aux.getNombre());
                volverBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editContact();
                    }
                });
            }else{
                volverBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addContact();
                    }
                });
            }

        }

    }

    public void addContact() {
        Contacto aux= new Contacto(name.getText().toString(),phone.getText().toString());
        //Agregar la vara de meterlo en la lista para que salga en Lista de contactos
        Contenedor.getInstance().lista.add(aux);
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
            //sending carrera data
            intent.putExtra("addContact", aux);
            startActivity(intent);
            finish(); //prevent go back
    }

    public void editContact(){
        Contacto aux= new Contacto(name.getText().toString(),phone.getText().toString());
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("editContacto", aux);
        startActivity(intent);
        finish(); //prevent go back
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                photo.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            }
        }
    }

    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContactActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone.getText().toString()));
                startActivity(callIntent);

            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone.getText().toString()));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}