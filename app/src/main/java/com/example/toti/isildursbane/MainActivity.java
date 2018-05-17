package com.example.toti.isildursbane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.toti.myopenssl.OpenSSLCertificate;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    Button btnDec;
    Button btnRefPtr;
    Button btnCall;
    TextView tv;
    TextView tvBen;
    TextView tvMal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        btnDec = (Button)findViewById(R.id.button);
        btnRefPtr = (Button)findViewById(R.id.button2);
        btnCall = (Button)findViewById(R.id.button3);
        tvBen = (TextView) findViewById(R.id.textView);
        tvMal = (TextView) findViewById(R.id.textView2);

        tvBen.setText(Long.toString(getPtrValue()));
        tvMal.setText(Long.toString(getMalPtrAddress()));

        btnRefPtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.gc();
                long ptr = getPtrValue();
                tv.setText(Long.toString(ptr));
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long ptr = getPtrAddress();
                //decrement(ptr);
                OpenSSLCertificate cert = new OpenSSLCertificate(ptr);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = callFunc();
                tv.setText("Func result: " + Integer.toString(res));
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native void decrement(long ptr);
    public native long getPtrAddress();
    public native long getMalPtrAddress();
    public native long getPtrValue();
    public native int callFunc();
    public native void dummy();
}
