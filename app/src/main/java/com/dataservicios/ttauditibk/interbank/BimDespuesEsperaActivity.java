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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dataservicios.ttauditibk.R;
import com.dataservicios.ttauditibk.SQLite.DatabaseHelper;
import com.dataservicios.ttauditibk.model.Audit;
import com.dataservicios.ttauditibk.model.PollDetail;
import com.dataservicios.ttauditibk.util.AuditUtil;
import com.dataservicios.ttauditibk.util.GlobalConstant;
import com.dataservicios.ttauditibk.util.SessionManager;

import java.util.HashMap;

public class BimDespuesEsperaActivity extends Activity {
    private Activity myActivity = this ;
    private static final String LOG_TAG = BimDespuesEsperaActivity.class.getSimpleName();
    private SessionManager session;
    private Button bt_guardar;
    private TextView tv_Pregunta;
    private LinearLayout lyPermitio, lyOpenClose, lyAuditoria;
    private String tipo,cadenaruc, fechaRuta,  type, region,typeBodega;
    private Integer user_id, company_id,store_id,audit_id, road_id, poll_id;
    private Integer is_sino=0;
    private DatabaseHelper db;
    private ProgressDialog pDialog;
    private RadioGroup rgOpt1;
    private String opt1="";
    private RadioButton[] radioButton1Array;
    private PollDetail pollDetail, pollDetail2;
    private Audit mAudit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bim_despues_espera);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Tienda");


        lyAuditoria = (LinearLayout) findViewById(R.id.lyAuditoria);
        lyOpenClose = (LinearLayout) findViewById(R.id.lyOpenClose);
        rgOpt1=(RadioGroup) findViewById(R.id.rgOpt1);

        radioButton1Array = new RadioButton[] {
                (RadioButton) findViewById(R.id.rbA1),
                (RadioButton) findViewById(R.id.rbB1),
        };


        bt_guardar = (Button) findViewById(R.id.btGuardar);

        Bundle bundle = getIntent().getExtras();
        company_id = GlobalConstant.company_id;
        store_id = bundle.getInt("store_id");
        audit_id = bundle.getInt("audit_id");
        road_id = bundle.getInt("road_id");

        poll_id = GlobalConstant.poll_id[32] ;// 5 "Se encuentra Abierto el punto?"


        //poll_id = 72 , solo para exhibiciones de bayer, directo de la base de datos

        pDialog = new ProgressDialog(myActivity);
        pDialog.setMessage(getString(R.string.text_loading));
        pDialog.setCancelable(false);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        // id
        user_id = Integer.valueOf(user.get(SessionManager.KEY_ID_USER)) ;



        rgOpt1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(radioButton1Array[0].isChecked())
                {
                    is_sino = 0 ;
                }
                else if (radioButton1Array[1].isChecked())
                {
                    is_sino = 1 ;
                }
            }
        });


        bt_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opt1="";

                if(radioButton1Array[0].isChecked())
                {
                    is_sino = 0 ;
                }
                else if (radioButton1Array[1].isChecked())
                {
                    is_sino = 1 ;
                }

                long id1 = rgOpt1.getCheckedRadioButtonId();
                if (id1 == -1){
                    Toast.makeText(myActivity, R.string.message_select_options , Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    for (int x = 0; x < radioButton1Array.length; x++) {
                        if(id1 ==  radioButton1Array[x].getId())  opt1 = poll_id.toString() + radioButton1Array[x].getTag();
                    }

                }


                AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
                builder.setTitle("Guardar Encuesta");
                builder.setMessage("Está seguro de guardar todas las encuestas: ");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pollDetail = new PollDetail();
                        pollDetail.setPoll_id(poll_id);
                        pollDetail.setStore_id(store_id);
                        pollDetail.setSino(0);
                        pollDetail.setOptions(1);
                        pollDetail.setLimits(0);
                        pollDetail.setMedia(0);
                        pollDetail.setComment(0);
                        pollDetail.setResult(0);
                        pollDetail.setLimite("");
                        pollDetail.setComentario("");
                        pollDetail.setAuditor(user_id);
                        pollDetail.setProduct_id(0);
                        pollDetail.setCategory_product_id(0);
                        pollDetail.setPublicity_id(0);
                        pollDetail.setCompany_id(GlobalConstant.company_id);
                        pollDetail.setCommentOptions(1);
                        pollDetail.setSelectdOptions(opt1);
                        pollDetail.setSelectedOtionsComment("");
                        pollDetail.setPriority("0");


                        new loadPoll().execute();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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




    class loadPoll extends AsyncTask<Void, Integer , Boolean> {
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

                if(is_sino == 0) {
                    Bundle argRuta = new Bundle();
                    argRuta.clear();
                    argRuta.putInt("store_id", store_id);
                    argRuta.putInt("road_id", road_id);
                    argRuta.putInt("audit_id", audit_id);

                    Intent intent;
                    intent = new Intent(myActivity, BimTransaccionExitosaActivity.class);
                    intent.putExtras(argRuta);
                    startActivity(intent);
                    finish();
                } else if(is_sino == 1) {
                    Bundle argRuta = new Bundle();
                    argRuta.clear();
                    argRuta.putInt("store_id", store_id);
                    argRuta.putInt("road_id", road_id);
                    argRuta.putInt("audit_id", audit_id);

                    Intent intent;
                    intent = new Intent(myActivity, BimNoRealizoTrasaccionActivity.class);
                    intent.putExtras(argRuta);
                    startActivity(intent);
                    finish();
                }


            } else {
                Toast.makeText(myActivity , "No se pudo guardar la información intentelo nuevamente", Toast.LENGTH_LONG).show();
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




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            //Toast.makeText(MyActivity, "No se puede volver atras, los datos ya fueron guardado, para modificar pongase en contácto con el administrador", Toast.LENGTH_LONG).show();
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(myActivity, "No se puede volver atras, los datos ya fueron guardado, para modificar póngase en contácto con el administrador", Toast.LENGTH_LONG).show();
//        super.onBackPressed();
//        this.finish();
//
//        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
