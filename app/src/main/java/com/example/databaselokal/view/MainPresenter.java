package com.example.databaselokal.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.databaselokal.entity.AppDatabase;
import com.example.databaselokal.entity.Dataperpus;

import java.util.List;

public class MainPresenter implements MainContact.presenter {
    private MainContact.view view;

    public MainPresenter(MainContact.view view) {
        this.view = view;
    }

    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase appDatabase;
        private Dataperpus dataperpus;

        public InsertData(AppDatabase appDatabase, Dataperpus dataperpus) {
            this.appDatabase = appDatabase;
            this.dataperpus = dataperpus;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataperpus);
        }

        protected void onPostExecute(Long along) {
            super.onPostExecute(along);
            view.successAdd();
        }
    }

    @Override
    public void insertData(String namainstansi, String namapengunjung, String alamat, AppDatabase database) {
        final Dataperpus dataperpus = new Dataperpus();
        dataperpus.setNamainstusi(namainstansi);
        dataperpus.setNamapengunjung(namapengunjung);
        dataperpus.setAlamat(alamat);
        new InsertData(database, dataperpus).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<Dataperpus> list;
        list = database.dao().getData();
        view.getData(list);
    }


    class editData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private Dataperpus dataperpus;

        public editData(AppDatabase appDatabase, Dataperpus dataperpus) {
            this.appDatabase = appDatabase;
            this.dataperpus = dataperpus;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return appDatabase.dao().updateData(dataperpus);
        }

        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db","onPostExecute"+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String namainstansi, String namapengunjung, String alamat, int id, AppDatabase database) {
        final Dataperpus dataperpus = new Dataperpus();
        dataperpus.setNamainstusi(namainstansi);
        dataperpus.setNamapengunjung(namapengunjung);
        dataperpus.setAlamat(alamat);
        dataperpus.setId(id);
        new editData(database, dataperpus).execute();
    }

    class DeleteData extends AsyncTask<Void, Void, Long> {
        private AppDatabase appDatabase;
        private Dataperpus dataperpus;

        public DeleteData(AppDatabase appDatabase, Dataperpus dataperpus) {
            this.appDatabase = appDatabase;
            this.dataperpus = dataperpus;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            appDatabase.dao().deleteData(dataperpus);
            return null;
        }

        protected void onPostExecute(Long along) {
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(Dataperpus dataperpus, AppDatabase database) {
        new DeleteData(database,dataperpus).execute();
    }
}


