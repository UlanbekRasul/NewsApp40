package com.geektech.newsapp40;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.newsapp40.databinding.FragmentRegisterBinding;
import com.geektech.newsapp40.ui.Prefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {
    private static Prefs prefs;
    private static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private static FirebaseAuth mAuth;
    private static FirebaseUser user;

    @Override
    public FragmentRegisterBinding bind() {
        return FragmentRegisterBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initListener();
        createCallback();
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
    }

    private void initListener() {
        binding.login.setOnClickListener(view1 -> {
            String phone = binding.number.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                binding.number.setError("Поле пустое");
            } else {
                register(phone);
                Log.e("TAG", "initListener: " + phone);
                binding.login.setVisibility(View.GONE);
                binding.okay.setVisibility(View.VISIBLE);
            }
        });
    }

    public void createCallback() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                binding.okay.setOnClickListener(view -> {
                    initcode(s);

                });
            }

            private void initcode(String s) {
                String code = binding.code.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    binding.code.setError("Поле пустое");
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(s, code);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        };
    }


    public void register(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(requireActivity())
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithCredential:success");
                            user = task.getResult().getUser();
                            prefs.saveRegisterFragment();
                            Log.e("TAG", "onComplete: ");
                            Navigation.findNavController(requireView()).navigate(R.id.boardFragment);
                        } else {
                            binding.code.setError("Некорректный код");
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }


}
