package shiyiliang.me.androidsourcecodeanalyse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import shiyiliang.me.androidsourcecodeanalyse.retrofit.RetrofitMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, shiyiliang.me.androidsourcecodeanalyse.easypickview.MainActivity.class));
            }
        });

    }
    public void viewResource(View v){
        startActivity(new Intent(this,ViewResourceActivity.class));
    }
//    public void picView(View v){
//        startActivity(new Intent(this, shiyiliang.me.androidsourcecodeanalyse.easypickview.MainActivity.class));
//    }

    public void retrofitLearn(View v){
        startActivity(new Intent(this, RetrofitMainActivity.class));
    }
}
