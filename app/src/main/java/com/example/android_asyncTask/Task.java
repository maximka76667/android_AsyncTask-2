package com.example.android_asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

interface onPostTask {
    public void onFinish(Long factorial);
}

public class Task extends AsyncTask<Integer, Integer, Long> {

    ProgressDialog dialog;
    Context context;
    onPostTask onPostTask;

    public Task(Context context, onPostTask onPostTask) {
        this.context = context;
        this.onPostTask = onPostTask;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Calculando...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
    }

    @Override
    protected Long doInBackground(Integer... integers) {
        int n = integers[0];
        dialog.setMax(n - 1);
        if(n < 0) throw new Error("Tiene que ser >= 0");
        Long factorial = Long.valueOf(1);
        for(int i = 2; i <= n; i++) {
            factorial *= i;
            try {
                // Simulacion
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i - 1);
        }
        return factorial;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        dialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Long factorial) {
        super.onPostExecute(factorial);
        dialog.dismiss();
        onPostTask.onFinish(factorial);
    }
}
