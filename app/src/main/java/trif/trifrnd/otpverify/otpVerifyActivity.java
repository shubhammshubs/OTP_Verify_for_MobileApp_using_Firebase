package trif.trifrnd.otpverify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import trif.trifrnd.otpverify.databinding.ActivityOtpVerifyBinding;

public class otpVerifyActivity extends AppCompatActivity {

    private ActivityOtpVerifyBinding binding;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_otp_verify);

        editTextInput();


        binding.textMobileShowNumber.setText(String.format(
                "+91-%s",getIntent().getStringExtra("Phone")
        ));

        verificationId = getIntent().getStringExtra("verificationId");

        binding.textresendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(otpVerifyActivity.this, "OTP Sent Successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.buttonVerify.setVisibility(View.INVISIBLE);

                if (binding.inputotp1.getText().toString().trim().isEmpty() ||
                        binding.inputotp2.getText().toString().trim().isEmpty() ||
                        binding.inputotp3.getText().toString().trim().isEmpty() ||
                        binding.inputotp4.getText().toString().trim().isEmpty() ||
                        binding.inputotp5.getText().toString().trim().isEmpty() ||
                        binding.inputotp6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(otpVerifyActivity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (verificationId != null) {
                        String code =   binding.inputotp1.getText().toString().trim() +
                                binding.inputotp2.getText().toString().trim() +
                                binding.inputotp3.getText().toString().trim() +
                                binding.inputotp4.getText().toString().trim() +
                                binding.inputotp5.getText().toString().trim() +
                                binding.inputotp6.getText().toString().trim() ;

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
                        FirebaseAuth
                                .getInstance()
                                .signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    binding.progressBar.setVisibility(View.VISIBLE);
                                    binding.buttonVerify.setVisibility(View.INVISIBLE);
                                    Toast.makeText(otpVerifyActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(otpVerifyActivity.this,MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                                else {
                                    binding.progressBar.setVisibility(View.GONE);
                                    binding.buttonVerify.setVisibility(View.VISIBLE);
                                    Toast.makeText(otpVerifyActivity.this, "OTP is Invalid ", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
                    }
                }
            }
        });
    }

    private void editTextInput() {
        binding.inputotp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputotp2.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputotp3.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputotp4.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputotp5.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputotp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.inputotp6.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}