package com.example.aplikasirentalmotor;

import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgStatus;
    Spinner spMotor,spTipe;
    TextView tvHasil,tvjk;
    Button bPinjam;
    EditText eNama, eAlamat, eJumhari;
    CheckBox ck1,ck2,ck3;
    String gender=null;

    String[][] arTipe = {{"Vario 150","PCX","Supra X 125"},{"Nex II","Satria F150","GSX-R150"},{"NMAX 155","Xride 125","Lexi"},{"KLX 150","Ninja 250SL","W175 SE"}};
    ArrayList<String> listTipe = new ArrayList<>();
    TipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNama = (EditText) findViewById(R.id.editTextNama);
        eAlamat = (EditText) findViewById(R.id.editTextAlamat);
        eJumhari = (EditText) findViewById(R.id.editTextJumlahHari);
        rgStatus = (RadioGroup) findViewById(R.id.radioGroupStatus);
        spMotor = (Spinner) findViewById(R.id.spinnerMobil);
        spTipe = (Spinner) findViewById(R.id.spinnerTipe);
        ck1 = (CheckBox) findViewById(R.id.checkBox);
        ck2 = (CheckBox) findViewById(R.id.checkBox2);
        ck3 = (CheckBox) findViewById(R.id.checkBox3);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvjk = (TextView) findViewById(R.id.textView3);
        bPinjam = (Button) findViewById(R.id.buttonPinjam);

        findViewById(R.id.buttonPinjam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();
            }
        });
        adapter = new TipeAdapter(this, listTipe);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipe.setAdapter(adapter);

        spMotor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listTipe.clear();
                listTipe.addAll(Arrays.asList(arTipe[pos]));
                adapter.setMotor((String)spMotor.getItemAtPosition(pos));
                adapter.notifyDataSetChanged();
                spTipe.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void doClick() {
        if(isValid())
        {
            String nama = eNama.getText().toString();
            String alamat = eAlamat.getText().toString();
            String hari = eJumhari.getText().toString();
            String motor = spMotor.getSelectedItem().toString();
            String tipe = spTipe.getSelectedItem().toString();
            String tambahan = "";


            if (rgStatus.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton) findViewById(rgStatus.getCheckedRadioButtonId());
                gender = rb.getText().toString();
            }
            if(gender==null)
            {
                gender = "Jenis Kelamin Belum Diisi";
            }

            if (ck1.isChecked()) tambahan +=  ck1.getText() + ", ";
            if (ck2.isChecked()) tambahan +=  ck2.getText() + ", ";
            if (ck3.isChecked()) tambahan +=  ck3.getText() + "";

            if (ck1.isChecked() || ck2.isChecked() || ck3.isChecked()) {
                tvHasil.setText("Nama Anda: " + "\n"  + nama + "\n" +
                                "Alamat: " + "\n" + alamat + "\n" +
                                "Jenis Kelamin: " + "\n" + gender + "\n" +
                                "Anda memilih motor: " + "\n" + motor + " " + tipe + "\n" +
                                "Dengan waktu pinjam: " + "\n" + hari + " hari\n" +
                                "Dan tambahan: "+ "\n" + tambahan);
            } else {
                tvHasil.setText("Nama Anda: " + "\n" + nama + "\n" +
                                "Alamat: " + "\n" + alamat + "\n" +
                                "Jenis Kelamin: " + "\n" + gender + "\n" +
                                "Anda memilih motor: " + "\n" + motor + " " + tipe + "\n" +
                                "Dengan waktu pinjam: "  + "\n" + hari + " hari");
            }
        }
    }

    private boolean isValid()
    {
        boolean valid = true;

        String nama = eNama.getText().toString();
        String alamat = eAlamat.getText().toString();
        String hari = eJumhari.getText().toString();

        if(nama.isEmpty())
        {
            eNama.setError("Nama belum diisi");
            valid = false;
        }
        if(alamat.isEmpty())
        {
            eAlamat.setError("Alamat belum diisi");
            valid = false;
        }
        if(hari.isEmpty())
        {
            eJumhari.setError("Jumlah hari belum diisi");
            valid = false;
        }

        return valid;
    }

}