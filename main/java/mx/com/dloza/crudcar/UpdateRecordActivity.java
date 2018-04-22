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

public class UpdateRecordActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mMarkEditText;
    private EditText mModelEditText;
    private EditText mProcesadorEditText;
    private EditText mMemoriaEditText;
    private EditText mYearEditText;
    private EditText mImageEditText;
    private Button mUpdateBtn;

    private CopDBHelper dbHelper;
    private long receivedCopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);

        //init
        mNameEditText = (EditText)findViewById(R.id.cop_name_update);
        mMarkEditText = (EditText)findViewById(R.id.cop_mark_update);
        mModelEditText = (EditText)findViewById(R.id.cop_model_update);
        mProcesadorEditText = (EditText)findViewById(R.id.cop_transmission_update);
        mMemoriaEditText = (EditText)findViewById(R.id.cop_combustible_update);
        mYearEditText = (EditText)findViewById(R.id.cop_year_update);
        mImageEditText = (EditText)findViewById(R.id.cop_image_link_update);
        mUpdateBtn = (Button)findViewById(R.id.update_cop_button);

        dbHelper = new CopDBHelper(this);

        try {
            //get intent to get car id
            receivedCopId = getIntent().getLongExtra("COP_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate car data before update***/
        Cop queriedCop = dbHelper.getCop(receivedCopId);
        //set field to this user data
        mNameEditText.setText(queriedCop.getName());
        mMarkEditText.setText(queriedCop.getMark());
        mModelEditText.setText(queriedCop.getModel());
        mProcesadorEditText.setText(queriedCop.getProcesador());
        mMemoriaEditText.setText(queriedCop.getMemoria());
        mYearEditText.setText(queriedCop.getYear());
        mImageEditText.setText(queriedCop.getImage());

        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updateCop();
            }
        });
    }

    private void updateCop(){
        String name = mNameEditText.getText().toString().trim();
        String mark = mMarkEditText.getText().toString().trim();
        String model = mModelEditText.getText().toString().trim();
        String procesador = mProcesadorEditText.getText().toString().trim();
        String memoria = mMemoriaEditText.getText().toString().trim();
        String year = mYearEditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();

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

        //create updated cop
        Cop updatedCop = new Cop(name, mark, model, procesador, memoria,year, image);

        //call dbhelper update
        dbHelper.updateCopRecord(receivedCopId, this, updatedCop);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
