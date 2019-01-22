package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TelaAddLoteListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Conferente;
import model.Lote;
import request.Connection;
import request.Solicita;

public class FragmentAddLote extends Fragment {

    private TelaAddLoteListener listener;
    private ImageView imageAltura,imageLastro, imageView;
    private Button btnAdd;
    private Conferente conferente;
    private String pathImagerAltura, pathImagerLastro, mCurrentPhotoPath;
    private boolean altura;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_lote,container,false);

        conferente = (Conferente) getArguments().getSerializable("funcionario");

        ((TextView) view.findViewById(R.id.quantidade)).setText(""+conferente.getCarga().getLotes().size());

        btnAdd = view.findViewById(R.id.adicionar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.addLote(newLote(view));
            }
        });

        imageAltura = view.findViewById(R.id.imageAlturaUpload);
        selectedImageListener(imageAltura,true);

        imageLastro = view.findViewById(R.id.imageLastroUpload);
        selectedImageListener(imageLastro,false);


        return view;
    }

    public Lote newLote(View view){

        String produto  = ((TextInputEditText) view.findViewById(R.id.produto)).getText().toString();
        int altura      = Integer.parseInt(((TextInputEditText) view.findViewById(R.id.altura)).getText().toString());
        int lastro      = Integer.parseInt(((TextInputEditText)view.findViewById(R.id.lastro)).getText().toString());

        return new Lote(altura,lastro, produto, "", pathImagerAltura, pathImagerLastro);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TelaAddLoteListener) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        galleryAddPic();
        setPic(imageView,altura);
    }

    private void selectedImageListener(final ImageView image, final boolean altura){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView = image;
                dispatchTakePictureIntent(altura);
            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_3MCONF";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent(boolean altura) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
            this.altura = altura;
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getContext().sendBroadcast(mediaScanIntent);
    }

    private void setPic(ImageView imageView,boolean altura) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        if (altura){
            pathImagerAltura = Base64.encodeToString(b, Base64.DEFAULT);
        }else {
            pathImagerLastro = Base64.encodeToString(b, Base64.DEFAULT);
        }
        imageView.setImageBitmap(bitmap);


    }

}
