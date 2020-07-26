package com.connectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class datainsert extends AppCompatActivity {

    EditText txtname, txtcedula, txtencuestauno, txtencuestados;
    Button btnsave;
    DatabaseReference reff;
    Member member;
    long maxid=0;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(datainsert.this, "Conectado a Firebase", Toast.LENGTH_LONG).show();
        txtname=(EditText)findViewById(R.id.txtname);
        txtcedula=(EditText)findViewById(R.id.txtcedula);
        txtencuestauno=(EditText)findViewById(R.id.txtencuentauno);
        txtencuestados=(EditText)findViewById(R.id.txtencuentados);
        btnsave=(Button) findViewById(R.id.btnsave);
        inicializarFirebase();
        member= new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula= txtcedula.getText().toString().trim();
                String name =txtname.getText().toString().trim();
                String encuestaUno = txtencuestauno.getText().toString().trim();
                String encuestaDos = txtencuestados.getText().toString().trim();
                member.setName(name);
                member.setCedula(cedula);
                member.setEncuentaUno(encuestaUno);
                member.setEncuentaDos(encuestaDos);
                //reff.child(new Date().getTime() + "").setValue(member);
                reff.child(String.valueOf(maxid+1)).setValue(member);

                Toast.makeText(datainsert.this,"Dato insertado con éxito", Toast.LENGTH_LONG).show();
                limpiar();
            }
        });
    }


    /**private String generateID() {
        String deviceId = android.provider.Settings.Secure.getString(this.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        if ("9774d56d682e549c".equals(deviceId) || deviceId == null ){
            deviceId = ((TelephonyManager) this
                    .getSystemService(Context.TELEPHONY_SERVICE))
                    .getDeviceId();
            if (deviceId == null){
                Random tmpRand = new Random();
                deviceId = String.valueOf(tmpRand.nextLong());
            }
        }
        return getHash (deviceId);
    }

    public String getHash (String stringToHash){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
         byte [] result = null;
        try {
            result = digest.digest(stringToHash.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (byte b : result){
            sb.append(String.format("%02x", b));
        }

        String messageDigest = sb.toString();
        return messageDigest;

    }**/

    private void limpiar() {
        txtname.setText("");
        txtcedula.setText("");
        txtencuestauno.setText("");
        txtencuestados.setText("");
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

}
