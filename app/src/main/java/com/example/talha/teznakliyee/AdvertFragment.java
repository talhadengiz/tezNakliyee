package com.example.talha.teznakliyee;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.talha.teznakliyee.models.ServerRequest;
import com.example.talha.teznakliyee.models.ServerResponse;
import com.example.talha.teznakliyee.models.User;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Talha on 28.5.2017.
 */

public class AdvertFragment extends Fragment implements View.OnClickListener {
    private Spinner sp_nereden, sp_nereye, sp_arac;
    private EditText et_tarih, et_saat, et_en, et_boy, et_yukseklik, et_agirlik;
    private AppCompatButton btn_ilan;
    private String aracTuru, nereden, nereye;
    private ProgressBar progress;
    private TextView tv_nereden, tv_nereye, tv_arac;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_advert, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        sp_nereden = view.findViewById(R.id.sp_nereden);
        sp_nereye = view.findViewById(R.id.sp_nereye);
        et_saat = view.findViewById(R.id.et_saat);
        et_tarih = view.findViewById(R.id.et_tarih);
        sp_arac = view.findViewById(R.id.sp_arac);
        btn_ilan = view.findViewById(R.id.btn_ilan);
        progress = view.findViewById(R.id.progress);
        et_en = view.findViewById(R.id.et_en);
        et_boy = view.findViewById(R.id.et_boy);
        et_yukseklik = view.findViewById(R.id.et_yukseklik);
        et_agirlik = view.findViewById(R.id.et_agirlik);
        tv_nereden = view.findViewById(R.id.tv_nereden);
        tv_nereye = view.findViewById(R.id.tv_nereye);
        tv_arac = view.findViewById(R.id.tv_arac);


        btn_ilan.setOnClickListener(this);
        et_tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
                int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

                DatePickerDialog datePicker;//Datepicker objemiz
                datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        et_tarih.setText(dayOfMonth + "/" + monthOfYear + "/" + year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                    }
                }, year, month, day);//başlarken set edilcek değerlerimizi atıyoruz
                datePicker.setTitle("Tarih Seçiniz");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                datePicker.show();
            }
        });

        et_saat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_saat.setText(selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);

                timePicker.show();
            }

        });


        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.iller, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_nereden.setAdapter(adapter);
        sp_nereye.setAdapter(adapter);
        sp_nereden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] ilDizisi = getResources().getStringArray(R.array.iller);
                tv_nereden.setText(ilDizisi[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_nereye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] ilDizisi1 = getResources().getStringArray(R.array.iller);
                tv_nereye.setText(ilDizisi1[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.arac, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_arac.setAdapter(arrayAdapter);
        sp_arac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] aracDizisi = getResources().getStringArray(R.array.arac);
                tv_arac.setText(aracDizisi[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ilan:
                String en = et_en.getText().toString();
                String boy = et_boy.getText().toString();
                String yukseklik = et_yukseklik.getText().toString();
                String tarih = et_tarih.getText().toString();
                String saat = et_saat.getText().toString();
                String agirlik = et_agirlik.getText().toString();

                if (!en.isEmpty() && !boy.isEmpty() && !yukseklik.isEmpty() && !agirlik.isEmpty() && !tarih.isEmpty()
                        && !saat.isEmpty()) {

                    registerAdvert(en, boy, yukseklik, agirlik, tarih, saat);
                } else {
                    Snackbar.make(getView(), "Gerekli Alanları Doldurunuz", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void registerAdvert(String en, String boy, String yukseklik, String agirlik, String tarih, String saat) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEn(en);
        user.setBoy(boy);
        user.setYukseklik(yukseklik);
        user.setAgirlik(agirlik);
        user.setTarih(tarih);
        user.setSaat(saat);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.ADVERT_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG, "failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }

}


