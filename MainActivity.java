package com.example.autotradesignal;

import android.app.*; import android.os.*; import android.graphics.Color; import android.view.*; import android.widget.*; import java.util.*;

public class MainActivity extends Activity {
 TextView price, signal, details; String asset="BTC/USDT"; Random r=new Random();
 @Override public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  price=findViewById(R.id.price); signal=findViewById(R.id.signal); details=findViewById(R.id.details);
  findViewById(R.id.btcBtn).setOnClickListener(v->{asset="BTC/USDT"; update();});
  findViewById(R.id.goldBtn).setOnClickListener(v->{asset="XAUUSD"; update();});
  findViewById(R.id.stopBtn).setOnClickListener(v->{signal.setText("SIGNAL: STOPPED"); signal.setTextColor(Color.rgb(239,68,68)); Toast.makeText(this,"All demo trading stopped",Toast.LENGTH_SHORT).show();});
  ((Switch)findViewById(R.id.autoTrade)).setOnCheckedChangeListener((v,c)->{if(c) Toast.makeText(this,"Demo auto-trade enabled",Toast.LENGTH_SHORT).show();}); update();
 }
 void update(){double p=asset.equals("BTC/USDT")?79000+r.nextInt(1800):4400+r.nextInt(80); boolean buy=r.nextBoolean();
  price.setText(String.format(Locale.US,"%s  %.2f",asset,p)); signal.setText("SIGNAL: "+(buy?"BUY":"SELL")); signal.setTextColor(buy?Color.rgb(34,197,94):Color.rgb(239,68,68));
  double sl=asset.equals("BTC/USDT")?p*(buy?.992:1.008):p*(buy?.997:1.003); double tp1=asset.equals("BTC/USDT")?p*(buy?1.012:.988):p*(buy?1.006:.994); double tp2=asset.equals("BTC/USDT")?p*(buy?1.022:.978):p*(buy?1.012:.988);
  details.setText(String.format(Locale.US,"Entry: %.2f\nStop Loss: %.2f\nTP1: %.2f\nTP2: %.2f\nRisk: 1%% per trade\n\nDemo signal only — not financial advice.",p,sl,tp1,tp2)); }
}
