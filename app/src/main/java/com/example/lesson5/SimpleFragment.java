package com.example.lesson5;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.File;


public class SimpleFragment extends Fragment {

    View view;
    Button shareImageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_simple, container, false);
        // get the reference of Button
        shareImageButton = (Button) view.findViewById(R.id.firstButton);
        // perform setOnClickListener on first Button
        shareImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a message by using a Toast

                shareImage();
            }
        });
        return view;
    }

    private void shareImage() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        // can thay duong dan anh de chay duoc
        File fImageToShare =
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        "Banner-01-QC-Android-1024x1024.png");

        intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getActivity(),
                getActivity().getPackageName(),
                fImageToShare));

        startActivity(Intent.createChooser(intent,"Chia se voi"));
    }

    public void setupInfo() {
        Log.d("AAA", "setup info");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity ac = (MainActivity) getActivity();

        ac.doSomeThing();
    }

    @Override
    public void onResume() {
        super.onResume();

        // viet code o day

        //
    }

    @Override
    public void onPause() {
        super.onPause();

        // viet code o day

        //
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
