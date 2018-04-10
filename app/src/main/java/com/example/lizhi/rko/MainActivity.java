package com.example.lizhi.rko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.PusherOptions;

public class MainActivity extends AppCompatActivity {

    private Button  btn_login,btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login  = (Button) findViewById(R.id.but_RL_r);
        btn_register = (Button) findViewById(R.id.but_RL_l);





//        PusherOptions options = new PusherOptions();
//        options.setCluster("ap1");
//        Pusher pusher = new Pusher("a10bbfa47d47c75025f8", options);
//
//        Channel channel = pusher.subscribe("my-channel");
//
//        channel.bind("my-event", new SubscriptionEventListener() {
//            @Override
//            public void onEvent(String channelName, String eventName, final String data) {
//                System.out.println(data);
//            }
//        });
//        pusher.connect();

    }
    public void login(View view){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void register(View view){
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
