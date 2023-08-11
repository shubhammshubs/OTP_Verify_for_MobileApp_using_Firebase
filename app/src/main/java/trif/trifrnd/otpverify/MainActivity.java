package trif.trifrnd.otpverify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import trif.trifrnd.otpverify.databinding.ActivityMainBinding;
import trif.trifrnd.otpverify.databinding.ActivityOtpVerifyBinding;

public class MainActivity extends AppCompatActivity {

    private @NonNull ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);


        binding.showMobileNumber.setText(String.format(
                "+91-%s",getIntent().getStringExtra("Phone")
        ));

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,OtpSendActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Successfully Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}