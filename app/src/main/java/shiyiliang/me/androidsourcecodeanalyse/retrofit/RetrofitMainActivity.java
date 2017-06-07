package shiyiliang.me.androidsourcecodeanalyse.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import shiyiliang.me.androidsourcecodeanalyse.R;

public class RetrofitMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main);
    }

    public void basicUse(View v) {
        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIStore apiStore = retrofit.create(APIStore.class);
        Observable<String> string = apiStore.getString();
    }

    public interface APIStore{
        @GET
        Observable<String> getString();
    }
}
