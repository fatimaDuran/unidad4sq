package mx.com.dloza.crudcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.com.dloza.crudcar.model.Cop;
import mx.com.dloza.crudcar.utils.CopDBHelper;

public class AddRecordActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mMarkEditText;
    private EditText mModelEditText;
    private EditText mProcesadorEditText;
    private EditText mMemoriaEditText;
    private EditText mYearEditText;
    private EditText mImageEditText;
    private Button mAddBtn;

    private CopDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        //init
        mNameEditText = (EditText)findViewById(R.id.car_name);
        mMarkEditText = (EditText)findViewById(R.id.car_mark);
        mModelEditText = (EditText)findViewById(R.id.car_model);
        mProcesadorEditText = (EditText)findViewById(R.id.car_transmission);
        mMemoriaEditText = (EditText)findViewById(R.id.car_combustible);
        mYearEditText = (EditText)findViewById(R.id.car_year);
        mImageEditText = (EditText)findViewById(R.id.car_image_link);
        mAddBtn = (Button)findViewById(R.id.add_new_car_button);

        //listen to add button click
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save car method
                saveCop();
            }
        });

    }

    private void saveCop(){
        String name = mNameEditText.getText().toString().trim();
        String mark = mMarkEditText.getText().toString().trim();
        String model = mModelEditText.getText().toString().trim();
        String procesador = mProcesadorEditText.getText().toString().trim();
        String memoria = mMemoriaEditText.getText().toString().trim();
        String year = mYearEditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();
        dbHelper = new CopDBHelper(this);

        if(name.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(mark.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an mark", Toast.LENGTH_SHORT).show();
        }

        if(model.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an model", Toast.LENGTH_SHORT).show();
        }

        if(procesador.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an transmission", Toast.LENGTH_SHORT).show();
        }

        if(memoria.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an combustible", Toast.LENGTH_SHORT).show();
        }

        if(year.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an year", Toast.LENGTH_SHORT).show();
        }

        if(image.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an image link", Toast.LENGTH_SHORT).show();
        }

        //create new car
        Cop car = new Cop(name, mark, model, procesador, memoria,year, image);
        dbHelper.saveNewCar(car);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(AddRecordActivity.this, MainActivity.class));
    }
}
