package com.example.sagar.lab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText uname,pass;
DBhandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname=findViewById(R.id.uname);
        pass=findViewById(R.id.pass);
        db=new DBhandler(this,"login.db",null,1);
    }

    public void login(View view) {
        Boolean b=db.compare(uname.getText().toString(),pass.getText().toString());
        if(b){
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Main2Activity.class));
        }
        else{
            Toast.makeText(this,"Invalid username or pass",Toast.LENGTH_SHORT).show();
        }
    }
    public void register(View view) {
        try{
            db.insertData(uname.getText().toString(),pass.getText().toString());
            Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show();
        }catch (Exception e){ Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();}
    }
}
