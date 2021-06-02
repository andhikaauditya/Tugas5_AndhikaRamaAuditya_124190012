package com.example.databaselokal.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databaselokal.R;
import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.Dataperpus;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button button;
    private RecyclerView recyclerView;
    private EditText etnamainstansi,etnamapengunjung,etalamatpengunjung;

    private int id = 0;
    boolean edit = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =findViewById(R.id.btn_submit);
        button.setOnClickListener(this);
        etnamainstansi =findViewById(R.id.et_namainstansi);
        etnamapengunjung =findViewById(R.id.et_namapengunjung);
        etalamatpengunjung =findViewById(R.id.et_alamatpengunjung);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this,"BERHASIL",Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this,"BERHASIL MENGHAPUS DATA",Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etnamainstansi.setText("");
        etnamapengunjung.setText("");
        etalamatpengunjung.setText("");
        button.setText("Submit");
    }

    @Override
    public void getData(List<Dataperpus> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(Dataperpus item) {
        etnamainstansi.setText(item.getNamainstusi());
        etnamapengunjung.setText(item.getNamapengunjung());
        etalamatpengunjung.setText(item.getAlamat());
        id = item.getId();
        edit = true;
        button.setText("EDIT DATA");
    }

    @Override
    public void deleteData(Dataperpus item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("MENGHAPUS DATA")
                .setMessage("Anda Yakin Ingin Menghapus Data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetForm();
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit){
            if (etnamainstansi.getText().toString().equals("")||etnamapengunjung.getText().toString().equals("")||etalamatpengunjung.getText().toString().equals("")){
                Toast.makeText(this,"Harap Semua Data Diisi",Toast.LENGTH_LONG).show();
            }else {
                if (!edit){
                    mainPresenter.insertData(etnamainstansi.getText().toString(), etnamapengunjung.getText().toString(), etalamatpengunjung.getText().toString(),appDatabase);

                }else {
                    mainPresenter.editData(etnamainstansi.getText().toString(),etnamapengunjung.getText().toString(),etalamatpengunjung.getText().toString(),id,appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}