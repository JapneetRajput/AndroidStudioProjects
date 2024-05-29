package com.example.videostreamfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_VIDEO=1;
    VideoView videoView;
    Button button;
    private Uri videoUri;
    TextInputEditText editText;
    TextInputLayout inputLayout;
    MediaController mediaController;
    ProgressBar progressBar;
    StorageReference storageReference;
    Member member;
    DatabaseReference databaseReference;
    UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        member = new Member();
        storageReference = FirebaseStorage.getInstance().getReference("Video");
        databaseReference = FirebaseDatabase.getInstance().getReference("video");

        videoView=findViewById(R.id.video1);
        button=findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        editText=findViewById(R.id.input1);
        mediaController= new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadVideo();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==PICK_VIDEO || resultCode==RESULT_OK || data!=null || data.getData()!=null){
            videoUri=data.getData();
            videoView.setVideoURI(videoUri);
        }
    }

    public void ChooseVideo(View view) {

        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_VIDEO);
    }

    private String getExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void ShowVideo(View view) {
    }

    private void UploadVideo(){
        String videoName = editText.getText().toString();
        String search = editText.getText().toString().toLowerCase();
        if(videoUri != null || !TextUtils.isEmpty(videoName)){
            progressBar.setVisibility(View.VISIBLE);
            final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." +getExt(videoUri));
            uploadTask = reference.putFile(videoUri);

            Task<Uri> urltask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.isSuccessful()){
                                Uri downloadUrl = task.getResult();
                                progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(MainActivity.this, "Uploaded Successfully.", Toast.LENGTH_SHORT).show();
                                member.setName(videoName);
                                member.setVideoUrl(downloadUrl.toString());
                                member.setSearch(search);
                                String i = databaseReference.push().getKey();
                                databaseReference.child(i).setValue(member);
                            } else{
                                Toast.makeText(MainActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else{
            Toast.makeText(MainActivity.this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
        }
    }
}