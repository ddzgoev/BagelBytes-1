package com.bagelbytes.libertyhacksmerge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText uname,pswd;
    String username, password;
    CheckBox saveLoginCheckBox;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    boolean saveLogin;
    Button login;
    DBhandler db;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=(EditText)findViewById(R.id.uname);
        pswd=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if(saveLogin=true) {
            uname.setText(loginPreferences.getString("username", ""));
            pswd.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);

        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),RegisterActivity.class);
                startActivity(myIntent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),ListActivity.class);

                String name=uname.getText().toString();
                String password=pswd.getText().toString();
                int id= checkUser(new User(name,password));
                if(id==-1)
                {
                    Toast.makeText(LoginActivity.this,"Incorrect Username/Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Hello, " +name,Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(myIntent);
                }

            }
        });
        db=new DBhandler(LoginActivity.this);
//inserting users
        db.addUser(new User("jay", "liberty123"));
        db.addUser(new User("sa", ""));
        db.addUser(new User("david", "liberty123"));
        db.addUser(new User("sam", "liberty123"));
        db.addUser(new User("jeremy", "liberty123"));
    }

    public void onClickSaveLogin(View view) {
        if(view == login) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(uname.getWindowToken(),0);

            username = uname.getText().toString();
            password = pswd.getText().toString();

            if(saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            }else{
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
            saveLogin();
        }
    }
    public void saveLogin() {
        startActivity(new Intent(LoginActivity.this, ListActivity.class));
        LoginActivity.this.finish();
    }

    public int checkUser(User user)
    {
        return db.checkUser(user);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}