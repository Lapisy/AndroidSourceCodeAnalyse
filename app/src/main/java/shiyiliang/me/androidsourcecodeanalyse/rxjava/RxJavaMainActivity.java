package shiyiliang.me.androidsourcecodeanalyse.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import shiyiliang.me.androidsourcecodeanalyse.R;

public class RxJavaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.one)
    public void testDo() {
        final String[] str = new String[]{"one", "two", "three", "four", "five"};
        final Subscription subscribe = Observable.from(str)
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println(Thread.currentThread().getName() + "-->doOnCompleted");
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(Thread.currentThread().getName() + "-->doOnNext-->" + s);
                    }
                })
                .doOnEach(new Action1<Notification<? super String>>() {
                    @Override
                    public void call(Notification<? super String> notification) {
                        System.out.println(Thread.currentThread().getName() + "-->doOnEach");
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println(Thread.currentThread().getName() + "-->onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(Thread.currentThread().getName() + "-->onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName() + "-->" + s);
                    }
                });

    }

    @OnClick(R.id.two)
    public void testSubscription(){
        Subscription subscribe = Observable.from(new String[]{"one", "tow"})
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("结束了");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("出错了");
                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(s);
                    }
                });
        System.out.println(subscribe.isUnsubscribed());
        subscribe.unsubscribe();
        System.out.println(subscribe.isUnsubscribed());
    }
}
