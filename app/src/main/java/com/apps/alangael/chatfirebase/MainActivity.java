package com.apps.alangael.chatfirebase;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;

    private CircleImageView fotoperfil;
    private RecyclerView rvmensajes;
    private TextView nombre;
    private EditText txtMensajes;
    private Button btnSend;
    private ImageButton btnEnviarFoto;

    private AdapterMensajes adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (TextView) findViewById(R.id.txtNombre);
        fotoperfil = (CircleImageView) findViewById(R.id.fotop);
        rvmensajes = (RecyclerView) findViewById(R.id.rvMens);
        txtMensajes = (EditText) findViewById(R.id.txtMensaje);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnEnviarFoto = (ImageButton) findViewById(R.id.imgBtn);
        fotoPerfilCadena="";

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");//sala de chat(nombre)

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        final AdapterMensajes adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvmensajes.setLayoutManager(l);
        rvmensajes.setAdapter(adapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    databaseReference.push().setValue(new MensajeEnviar(txtMensajes.getText().toString(), nombre.getText().toString(), fotoPerfilCadena, "1", ServerValue.TIMESTAMP));
                    txtMensajes.setText("");
                //adapter.addmensaje(new Mensaje(txtMensajes.getText().toString(),nombre.getText().toString(), "", "1", "00:00" ));
            }
        });
        btnEnviarFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una foto"), PHOTO_SEND);
            }
        });
        /*adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrolbar();
            }
        });*/
        fotoperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una foto"), PHOTO_PERFIL);
            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                adapter.addmensaje(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    /*public void setScrolbar(){
        rvmensajes.scrollToPosition(adapter.getItemCount()-1);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imageneschat");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    MensajeEnviar m = new MensajeEnviar("Alan te ha enviado una foto",u.toString(), nombre.getText().toString(), fotoPerfilCadena, "2", ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        }else if (requestCode == PHOTO_PERFIL && resultCode == RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imagenesperfiles");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    fotoPerfilCadena = u.toString();
                    MensajeEnviar m = new MensajeEnviar("Alan ha actualizadu su foto",u.toString(), nombre.getText().toString(), fotoPerfilCadena, "2", ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                    Glide.with(getApplicationContext()).load(u.toString()).into(fotoperfil);
                }
            });
        }
    }
}