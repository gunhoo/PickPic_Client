package com.pickpic.Backend;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by sekyo on 2017-05-11.
 */

public class Synchronizer extends AsyncTask<Void, Void, Void> {
    ProgressDialog progressDialog;
    Context context;


    public Synchronizer(Context context){
        this.context = context;
    }

    protected void onPreExecute(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Synchronizing...\nIt may take a few minutes");
        progressDialog.setCancelable(false);
        progressDialog.show();
        super.onPreExecute();
    }

    protected Void doInBackground(Void... voids) {

        TagDBManager tagDBManager = new TagDBManager(context);
        ArrayList<String> sync = tagDBManager.getAllTags();
        Log.v("sync before", "tag num : " + sync.size());
        sync = tagDBManager.getAllImages();
        Log.v("sync before", "image num : " + sync.size());

        Log.v("test", "start synchronizer");
        ArrayList<String> localImages = LocalImageManager.getAllImagePath(context, "DESC");
        ArrayList<String> toBeErasedPath = tagDBManager.getToBeErasedPaths(localImages);
        for (int i = 0; i < toBeErasedPath.size(); i++) {
            tagDBManager.removeImage(toBeErasedPath.get(i));
        }
        Log.v("test", "remove done");
        tagDBManager.insertImagesIfNotExist(localImages);
        Log.v("test", "insert done");
        ArrayList<String> notAutoGeneratedPath = tagDBManager.getPathsNotAutoGenerated();
        for (int i = 0; i < notAutoGeneratedPath.size(); i++) {
            AutoTagGenerator.autoTagGenerate(context, notAutoGeneratedPath.get(i));
        }
        Log.v("test", "sync done");

        sync = tagDBManager.getAllTags();
        Log.v("sync after", "tag num : " + sync.size());
        sync = tagDBManager.getAllImages();
        Log.v("sync after", "image num : " + sync.size());

        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Void voids) {
        progressDialog.dismiss();
    }
}

