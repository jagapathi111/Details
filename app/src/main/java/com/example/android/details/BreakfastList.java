package com.example.android.details;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.details.Breakfast.Breakfast;
import com.example.android.details.Breakfast.BreakfastActivity;
import com.example.android.details.Breakfast.BreakfastListAdapter;

import java.util.ArrayList;


public class BreakfastList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Breakfast> list;
    BreakfastListAdapter adapter = null;



    @TargetApi(Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_view);



        gridView = (GridView)findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new BreakfastListAdapter(this, R.layout.breakfast_items, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite

        Cursor cursor = BreakfastActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();


    while (cursor.moveToNext()) {
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        byte[] image = cursor.getBlob(2);

        list.add(new Breakfast(name, image, id));
    }


    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

/* int selectedIndex = adapter.foodsList.indexOf(position);
if(selectedIndex > -1) {

    adapter.foodsList.remove(selectedIndex);


} */

        }
    });


       // adapter.notifyDataSetChanged();

     /*   gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(BreakfastList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = BreakfastActivity.sqLiteHelper.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(BreakfastList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = BreakfastActivity.sqLiteHelper.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                           // showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });  */
    }



  /*  ImageView imageViewFood;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_breakfast_activity);
        dialog.setTitle("Update");

        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        BreakfastList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        }); */

     /*   btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BreakfastActivity.sqLiteHelper.updateData(
                            edtName.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            MainActivity.imageViewToByte(imageViewFood),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });  */
   // }

   /* private void showDialogDelete(final int idFood){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(BreakfastList.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    BreakfastActivity.sqLiteHelper.deleteData(idFood);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = BreakfastActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Breakfast(name, price, image, id));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewFood.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }  */
}