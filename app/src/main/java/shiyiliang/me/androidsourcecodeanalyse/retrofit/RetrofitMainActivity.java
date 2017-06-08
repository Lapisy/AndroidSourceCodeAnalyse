package shiyiliang.me.androidsourcecodeanalyse.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import shiyiliang.me.androidsourcecodeanalyse.R;

public class RetrofitMainActivity extends AppCompatActivity {

    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main);
        this.baseUrl = "http://wthrcdn.etouch.cn/";
    }

    public void basicUse(View v) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIStore apiStore = retrofit.create(APIStore.class);
        final Observable<ResponseBody> string = apiStore.getString("北京");
        string.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("完成了");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody s) {
                        try {
                            System.out.println(s.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public interface APIStore {
        @GET("weather_mini")
        Observable<ResponseBody> getString(@Query("city") String city);
    }
}
