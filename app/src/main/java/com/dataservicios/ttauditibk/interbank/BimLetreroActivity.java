package com.dataservicios.ttauditibk.interbank;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.dataservicios.ttauditibk.AndroidCustomGalleryActivity;
import com.dataservicios.ttauditibk.R;
import com.dataservicios.ttauditibk.SQLite.DatabaseHelper;
import com.dataservicios.ttauditibk.model.Audit;
import com.dataservicios.ttauditibk.model.PollDetail;
import com.dataservicios.ttauditibk.util.AuditUtil;
import com.dataservicios.ttauditibk.util.GPSTracker;
import com.dataservicios.ttauditibk.util.GlobalConstant;
import com.dataservicios.ttauditibk.util.SessionManager;

import java.util.HashMap;

public class BimLetreroActivity extends Activity {

    private Activity myActivity = this ;
    private static final String LOG_TAG = BimLetreroActivity.class.getSimpleName();
    private SessionManager session;

    private Switch swSiNo ;
    private Button bt_photo, bt_guardar;


    private Integer user_id, company_id,store_id,audit_id,  poll_id, road_id;

    private int is_sino=0 ;
    private DatabaseHelper db;
    private ProgressDialog pDialog;


    private PollDetail pollDetail;
    private Audit mAudit;
    GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bim_letrero);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Bim");

        swSiNo = (Switch) findViewById(R.id.swSiNo);

        gpsTracker = new GPSTracker(myActivity);

        bt_guardar = (Button) findViewById(R.id.btGuardar);
        bt_photo = (Button) findViewById(R.id.btPhoto);


        Bundle bundle = getIntent().getExtras();
        company_id = GlobalConstant.company_id;
        store_id = bundle.getInt("store_id");
        audit_id = bundle.getInt("audit_id");
        road_id = bundle.getInt("road_id");


        poll_id = GlobalConstant.poll_id[28];

        pDialog = new ProgressDialog(myActivity);
        pDialog.setMessage(getString(R.string.text_loading));
        pDialog.setCancelable(false);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        // id
        user_id = Integer.valueOf(user.get(SessionManager.KEY_ID_USER)) ;


        swSiNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    is_sino = 1;
                } else {
                    is_sino = 0;
                }
            }
        });

        bt_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
                builder.setTitle(R.string.save);
                builder.setMessage(R.string.saveInformation);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        pollDetail = new PollDetail();
                        pollDetail.setPoll_id(poll_id);
                        pollDetail.setStore_id(store_id);
                        pollDetail.setSino(1);
                        pollDetail.setOptions(0);
                        pollDetail.setLimits(0);
                        pollDetail.setMedia(1);
                        pollDetail.setComment(0);
                        pollDetail.setResult(is_sino);
                        pollDetail.setLimite("");
                        pollDetail.setComentario("");
                        pollDetail.setAuditor(user_id);
                        pollDetail.setProduct_id(0);
                        pollDetail.setCategory_product_id(0);
                        pollDetail.setPublicity_id(0);
                        pollDetail.setCompany_id(GlobalConstant.company_id);
                        pollDetail.setCommentOptions(0);
                        pollDetail.setSelectdOptions("");
                        pollDetail.setSelectedOtionsComment("");
                        pollDetail.setPriority("0");

                        new loadPoll().execute();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
                builder.setCancelable(false);

            }
        });

    }

    private void takePhoto() {

        Intent i = new Intent( myActivity, AndroidCustomGalleryActivity.class);
        Bundle bolsa = new Bundle();
        bolsa.putString("idPDV", String.valueOf(store_id));
        bolsa.putString("idPoll", String.valueOf(poll_id));
        bolsa.putString("tipo","1");

        i.putExtras(bolsa);
        startActivity(i);

    }

    class loadPoll extends AsyncTask<Void , Integer , Boolean> {
        /**
         * Antes de comenzar en el hilo determinado, Mostrar progresión
         * */
        boolean failure = false;
        @Override
        protected void onPreExecute() {
            //tvCargando.setText("Cargando Product...");
            pDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO Auto-generated method stub

            if(!AuditUtil.insertPollDetail(pollDetail)) return false;

            return true;
        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(Boolean result) {
            // dismiss the dialog once product deleted

            if (result){
                // loadLoginActivity();
                Bundle argRuta = new Bundle();
                argRuta.clear();
                argRuta.putInt("store_id", store_id);
                argRuta.putInt("road_id", road_id);
                argRuta.putInt("audit_id", audit_id);

                Intent intent;

                intent = new Intent(myActivity, BimAceptoOperacionActivity.class);
                intent.putExtras(argRuta);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(myActivity , R.string.saveError, Toast.LENGTH_LONG).show();
            }
            hidepDialog();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
//                this.finish();
//                Intent a = new Intent(this,PanelAdmin.class);
//                //a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(a);
//                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }




//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
//            //Toast.makeText(MyActivity, "No se puede volver atras, los datos ya fueron guardado, para modificar pongase en contácto con el administrador", Toast.LENGTH_LONG).show();
//            onBackPressed();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//    @Override
//    public void onBackPressed() {
//        Toast.makeText(myActivity,R.string.mesaage_on_back, Toast.LENGTH_LONG).show();
////        super.onBackPressed();
////        this.finish();
////
////        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
//    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
