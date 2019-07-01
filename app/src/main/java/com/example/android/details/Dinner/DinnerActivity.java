package com.example.android.details.Dinner;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.android.details.Dinner.Dinner;
import com.example.android.details.Dinner.DinnerListAdapter;
import com.example.android.details.Dinner.DinnerActivity;
import com.example.android.details.R;
import com.example.android.details.SQLiteHelper;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class DinnerActivity extends AppCompatActivity  {

    EditText edtName,edtdate;
    Button btnChoose, btnAdd, btnSave, btnView, btnList, btnClick;
    ImageView imageView;
    //FloatingActionButton fab;
    Dialog d;
    DatePickerDialog picker;
    public String selecteditem;

    GridView gridView;  //
    ArrayList<Dinner> list;  //
    DinnerListAdapter adapter = null;  //

    final int REQUEST_CODE_GALLERY = 999;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMMISSION_CODE = 100;

    public static SQLiteHelper sqLiteHelper;


    @Override

    protected void onCreate(@Nullable Bundle savedInstancestate) {

        super.onCreate(savedInstancestate);
        setContentView(R.layout.breakfast_view);
        edtdate =  findViewById(R.id.edit_date);
        btnSave =  findViewById(R.id.btnSave);
        btnView =  findViewById(R.id.btnView);
        // btnClick = findViewById(R.id.btnClick);
        edtName =  findViewById(R.id.edtName);
        edtdate = findViewById(R.id.edit_date);
        btnChoose =  findViewById(R.id.btnChoose);
        btnAdd =  findViewById(R.id.btnAdd);
        //btnList =   findViewById(R.id.btnList);
        imageView =  findViewById(R.id.imageView);

        // For viewing menu of Items
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list();

            }
        });

        // To select date
        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date();
            }
        });

        // To save what we ate

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtdate.getText().toString().isEmpty())
                {

                    Toast.makeText(getApplicationContext(),"Date cannot be Empty!", Toast.LENGTH_SHORT).show();

                }


                else {

                    sqLiteHelper.insertDinner(selecteditem, edtdate.getText().toString());

                    Toast.makeText(com.example.android.details.Dinner.DinnerActivity.this, "Saved successfully!", Toast.LENGTH_SHORT).show();

                    edtdate.setText("");

                }

            }
        });




        sqLiteHelper = new SQLiteHelper(this,"Fooddata.sqlite", null, 2);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS FOOD2(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, image shortBLOB)");

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS DINNER(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, date VARCHAR)");


        final   FloatingActionMenu fam;
        final   FloatingActionButton fabView, fabAdd;

        fabAdd =  findViewById(R.id.fabadd);
        fabView =  findViewById(R.id.fabview);
        fam =  findViewById(R.id.fab_menu);


        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {



                } else {



                }
            }
        });

     /*   fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened())
                {
                    fam.close(true);
                }
            }
        }); */

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayInputdialog(-1);
                fam.close(true);


            }
        });

        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fam.close(true);

                Cursor b = sqLiteHelper.getDinner();

                if (b.getCount() == 0) {
                    // show message
                    showMessage("Empty", "No data found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (b.moveToNext()) {
                    // buffer.append("Id:" + b.getString(0) + "\n");
                    buffer.append("Item :" + b.getString(1) + "\n");
                    buffer.append("Date :" + b.getString(2) + "\n\n");
                }

                showMessage("dinner", buffer.toString());

            }
        });


    }






//        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayInputdialog(-1);


            }
        });

    } */

    private void displayInputdialog ( final int pos) {
        d = new Dialog(this);
        d.setContentView(R.layout.breakfast_input_dialog);

        edtName = (EditText) d.findViewById(R.id.edtName);
        btnAdd = (Button) d.findViewById(R.id.btnAdd);
        btnChoose = (Button) d.findViewById(R.id.btnChoose);
        // btnClick = d.findViewById(R.id.btnClick);
        imageView = (ImageView) d.findViewById(R.id.imageView);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence[] items = {"Choose from Gallery", "click photo"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(com.example.android.details.Dinner.DinnerActivity.this);

                //dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {

                            ActivityCompat.requestPermissions
                                    (com.example.android.details.Dinner.DinnerActivity.this,
                                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                            REQUEST_CODE_GALLERY
                                    );

                        } else {

                            ActivityCompat.requestPermissions(com.example.android.details.Dinner.DinnerActivity.this,
                                    new String[]  {Manifest.permission.CAMERA},
                                    MY_CAMERA_PERMMISSION_CODE);



                        }
                    }
                });

                dialog.show();

            }
        });




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sedtname;

                sedtname = edtName.getText().toString().trim();

                if (edtName.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Item name cannot be empty!", Toast.LENGTH_SHORT).show();

                } else {

                    try {

                        sqLiteHelper.insertData2(
                                sedtname,
                                imageViewToByte(imageView));


                        d.dismiss();

                        Toast.makeText(getApplicationContext(), "Item added to Menu!" , Toast.LENGTH_SHORT).show();



                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Intent intent = new Intent(BreakfastActivity.this, BreakfastList.class);
                    //startActivity(intent);

                    list();

                }

            }

        });

        d.show();
    }

    public void list() {


        gridView = findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new DinnerListAdapter(this, R.layout.dinner_items,list);
        gridView.setAdapter(adapter);

        // get all data from sqlite

        final Cursor cursor = com.example.android.details.Dinner.DinnerActivity.sqLiteHelper.getData("SELECT * FROM FOOD2");
        list.clear();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new Dinner(name, image, id));


        }

        // Intent intent = new Intent(BreakfastActivity.this, BreakfastList.class);
        // startActivity(intent);

        adapter.notifyDataSetChanged();

        // On grid Item click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                Toast.makeText(getApplicationContext(), "Item selected now enter the date and save", Toast.LENGTH_LONG).show();

                view.setBackgroundColor(Color.parseColor("#EEEEEE"));

                final Cursor cursor1= com.example.android.details.Dinner.DinnerActivity.sqLiteHelper.getData("SELECT * FROM FOOD2");

                while (cursor1.moveToPosition(position)) {

                    // int ids = cursor1.getInt(0);
                    selecteditem = cursor1.getString(1);
                    //byte[] image = cursor1.getBlob(2);

                    System.out.println(selecteditem);
                    break;

                }
            }

        });
    }

    public void date() {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edtdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult  (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //for selecting photo from gallery

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);


            }
            else {
                Toast.makeText(this, "You don't have permission to access gallery", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        // for clicking photo

        else if (requestCode == MY_CAMERA_PERMMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();



            try {
                InputStream inputStream =  this.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

  /*  @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
                return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
            {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        } */






}