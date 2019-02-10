package com.example.home_pc.testpurchaselistapp.ui.addpurchaseactivity.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.home_pc.testpurchaselistapp.R;
import com.example.home_pc.testpurchaselistapp.ui.addpurchaseactivity.presenter.AddPurchasePresenter;
import com.example.home_pc.testpurchaselistapp.utils.ImageConverterHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class AddPurchaseActivity extends MvpAppCompatActivity implements AddPurchaseView, EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {

    private static final int UPLOAD_PHOTO_REQUEST_CODE = 100;
    private static final int TAKE_PHOTO_REQUEST_CODE = 300;
    private static final int MY_PERMISSIONS_REQUEST = 200;

    @BindView(R.id.ed_title) EditText edTitle;
    @BindView(R.id.ed_price)EditText edPrice;
    @BindView(R.id.img) ImageView imageView;
    @BindView(R.id.btn_make_photo) Button btnMakePhoto;
    @BindView(R.id.btn_upload_photo) Button btnUploadPhoto;
    @BindView(R.id.btn_submit) Button btnSubmit;

   private Bitmap imageBitmap;

   @Inject
   ImageConverterHelper imageConverterHelper;

    @Inject
    @InjectPresenter
    public AddPurchasePresenter presenter;

    @ProvidePresenter
    public AddPurchasePresenter providePresenter(){
        return new AddPurchasePresenter(getApplication());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        ButterKnife.bind(this);

        btnUploadPhoto.setOnClickListener(v -> {
            if(checkAllPermission()){
                uploadImageFromGallery();
            }
        });

        btnMakePhoto.setOnClickListener(v -> {
            if(checkAllPermission()){
                takePhotoAndUpload();
            }
        });

        btnSubmit.setOnClickListener(v -> {
            String title = edTitle.getText().toString();
            String price = edPrice.getText().toString();
            presenter.onSubmitBtnClick(title, price, imageBitmap);
        });
    }

    private void uploadImageFromGallery() {
        Intent imageIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        imageIntent.addCategory(Intent.CATEGORY_OPENABLE);
        imageIntent.setType("image/*");
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(imageIntent, UPLOAD_PHOTO_REQUEST_CODE);
    }

    private void takePhotoAndUpload(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PHOTO_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPLOAD_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                imageBitmap = imageConverterHelper.convertUriToBitmap(uri, this);
                imageView.setImageBitmap(imageBitmap);
            }
        }

        if (requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                imageBitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }

    @AfterPermissionGranted(MY_PERMISSIONS_REQUEST)
    private boolean checkAllPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
        if (EasyPermissions.hasPermissions(this, perms)) {
           return true;
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permissions_rationale),
                    MY_PERMISSIONS_REQUEST, perms);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(requestCode == MY_PERMISSIONS_REQUEST){
            Toast.makeText(AddPurchaseActivity.this, getString(R.string.your_permission_granted), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        checkAllPermission();
    }

    @Override
    public void onRationaleDenied(int requestCode) {
    }

    @Override
    public void showFieldsErrorMessage() {
        Toast.makeText(this, getString(R.string.input_fields_should_not_be_empty), Toast.LENGTH_LONG).show();
    }
}





