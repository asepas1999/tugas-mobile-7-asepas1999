package com.project6_dzikir;

import static java.security.AccessController.getContext;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class DzikirFragment extends Fragment {

    private int position;
    private MediaPlayer mediaPlayer;
    private int counter = 0;

    private Dzikir dzikir;

    public static DzikirFragment newInstance(int position) {
        DzikirFragment fragment = new DzikirFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dzikir, container, false);

        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }

        // Load Dzikir dari JSON Helper
        JsonHelper helper = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            helper = new JsonHelper(this.getContext());
        } else {
            helper = new JsonHelper(this.getActivity());
        }
        List<Dzikir> list = helper.loadDzikir(position);

        // Ambil dzikir pertama (contoh)
        dzikir = list.get(0);

        TextView tvArab = view.findViewById(R.id.tvDzikirArab);
        TextView tvLatin = view.findViewById(R.id.tvDzikirLatin);
        TextView tvTerjemah = view.findViewById(R.id.tvDzikirTerjemah);
        TextView tvCounter = view.findViewById(R.id.tvCounter);

        Button btnPlay = view.findViewById(R.id.btnPlay);
        Button btnAdd = view.findViewById(R.id.btnAdd);
        Button btnReset = view.findViewById(R.id.btnReset);
        Button btnCopy = view.findViewById(R.id.btnCopy);

        tvArab.setText(dzikir.getArab());
        tvLatin.setText(dzikir.getLatin());
        tvTerjemah.setText(dzikir.getTerjemah());
        tvCounter.setText("0");

        btnPlay.setOnClickListener(v -> playAudio());
        btnAdd.setOnClickListener(v -> {
            counter++;
            tvCounter.setText(String.valueOf(counter));
        });
        btnReset.setOnClickListener(v -> {
            counter = 0;
            tvCounter.setText("0");
        });
        btnCopy.setOnClickListener(v -> {
            String copyText = dzikir.getArab() + "\n" + dzikir.getLatin() + "\n" + dzikir.getTerjemah();
            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("dzikir", copyText);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "Teks disalin", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void playAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        int audioRes = (position == 0) ? R.raw.dzikir_pagi : R.raw.dzikir_petang;
        mediaPlayer = MediaPlayer.create(getContext(), audioRes);
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}