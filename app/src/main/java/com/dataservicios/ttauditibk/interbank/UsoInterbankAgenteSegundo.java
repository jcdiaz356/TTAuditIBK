package com.dataservicios.ttauditibk.interbank;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dataservicios.ttauditibk.AndroidCustomGalleryActivity;
import com.dataservicios.ttauditibk.R;
import com.dataservicios.ttauditibk.SQLite.DatabaseHelper;
import com.dataservicios.ttauditibk.app.AppController;
import com.dataservicios.ttauditibk.model.Audit;
import com.dataservicios.ttauditibk.model.Encuesta;
import com.dataservicios.ttauditibk.model.PollDetail;
import com.dataservicios.ttauditibk.util.AuditUtil;
import com.dataservicios.ttauditibk.util.GlobalConstant;
import com.dataservicios.ttauditibk.util.SessionManager;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by usuario on 07/04/2015.
 */
public class UsoInterbankAgenteSegundo extends Activity {
    private static final String LOG_TAG = UsoInterbankAgenteSegundo.class.getSimpleName();
    private ProgressDialog pDialog;
    private Integer idCompany, store_id, idRuta, idAuditoria,user_id,poll_id ;
    private JSONObject params;
    private SessionManager session;
    private String email_user, name_user;
    private  int result;
    Activity MyActivity = (Activity) this;
    TextView pregunta ;
    Button guardar , btPhoto;
    RadioGroup rgTipo;
    RadioButton rbSi,rbNo;
    //EditText comentario;

    LinearLayout ly_ChkSi ;
    //CheckBox cb_A,cb_B,cb_C ;
    //private String opciones="";

    private CheckBox[] checkBoxArray;

    private EditText etCommentOption;
    private LinearLayout lyOptionComment;
    private String  opt1="", commentOptions;

    // Database Helper
    private DatabaseHelper db;
    private PollDetail pollDetail;
    private Audit mAudit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inter_uso_interbank_agente_segundo);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Uso de Interbank Agente");
        overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_left);
        db = new DatabaseHelper(getApplicationContext());

        pregunta = (TextView) findViewById(R.id.tvPregunta);
        guardar = (Button) findViewById(R.id.btGuardar);
        rgTipo=(RadioGroup) findViewById(R.id.rgTipo);
        rbSi=(RadioButton) findViewById(R.id.rbSi);
        rbNo=(RadioButton) findViewById(R.id.rbNo);
        //comentario = (EditText) findViewById(R.id.etComentario) ;
        btPhoto =(Button) findViewById(R.id.btPhoto);

        ly_ChkSi = (LinearLayout) findViewById(R.id.lyChkSi);


        checkBoxArray = new CheckBox[] {
                (CheckBox) findViewById(R.id.cbA),
                (CheckBox) findViewById(R.id.cbB),
                (CheckBox) findViewById(R.id.cbC),
                (CheckBox) findViewById(R.id.cbD),
        };

        lyOptionComment = (LinearLayout) findViewById(R.id.lyOptionComment);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Cargando...");
        pDialog.setCancelable(false);

        session = new SessionManager(MyActivity);
        HashMap<String, String> user = session.getUserDetails();
        // name
        name_user = user.get(SessionManager.KEY_NAME);
        // email
        email_user = user.get(SessionManager.KEY_EMAIL);
        // id
        user_id = Integer.valueOf(user.get(SessionManager.KEY_ID_USER)) ;
        params = new JSONObject();
        //Recogiendo paramentro del anterior Activity
        //Bundle bundle = savedInstanceState.getArguments();
        Bundle bundle = getIntent().getExtras();
        idCompany=bundle.getInt("company_id");
        store_id= bundle.getInt("idPDV");
        idRuta= bundle.getInt("idRuta");
        idAuditoria= bundle.getInt("idAuditoria");
        try {
            params.put("idPDV", store_id);
            //params.put("idRuta", idRuta);
            params.put("idAuditoria", idAuditoria);
            params.put("idCompany", idCompany);
            params.put("idUser", user_id);

            //params.put("id_pdv",idPDV);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        leerEncuesta();


        btPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        enabledControl(false);
        rgTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == rbSi.getId()) {
                    enabledControl(true);
                }
                if(checkedId == rbNo.getId()) {
                    enabledControl(false);
                }

            }
        });

        etCommentOption     = new EditText(MyActivity);
        checkBoxArray[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    lyOptionComment.removeAllViews();
                    etCommentOption.setHint("Comentario");
                    etCommentOption.setText("");
                    lyOptionComment.addView(etCommentOption);
                }
                else
                {
                    etCommentOption.setText("");
                    lyOptionComment.removeAllViews();
                }
            }
        });


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opt1 = "" ;
                commentOptions="";

                long id = rgTipo.getCheckedRadioButtonId();
                if (id == -1){
                    //no item selected
                    //valor ="";
                    Toast toast;
                    toast = Toast.makeText(MyActivity,"Debe seleccionar una opción" , Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                else{
                    if (id == rbSi.getId()){
                        //Do something with the button
                        result = 1;

                        int contador = 0;
                        for (int x = 0; x < checkBoxArray.length; x++) {
                            if(checkBoxArray[x].isChecked()) contador ++;
                        }

                        if (contador == 0){
                            Toast.makeText(MyActivity,"Seleccionar una opción " , Toast.LENGTH_LONG).show();
                            return;
                        } else{
                            for (int x = 0; x < checkBoxArray.length; x++) {
                                if(checkBoxArray[x].isChecked())  {
                                    opt1 =  poll_id.toString() + checkBoxArray[x].getTag() + "|" + opt1;
                                    if(x==3){
                                        //commentOptions = etCommentOption.getText().toString(); else comentario="" ;
                                        commentOptions = etCommentOption.getText().toString() + "|" + commentOptions;
                                    } else {
                                        commentOptions =  "|" + commentOptions;
                                    }

                                }

                            }
                        }

                        // enabledControl(true);
                    } else if(id == rbNo.getId()){
                        result = 0;
                        // enabledControl(false);
                    }
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity);
                builder.setTitle("Guardar Encuesta");
                builder.setMessage("Está seguro de guardar todas las encuestas: ");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {



                        pollDetail = new PollDetail();
                        pollDetail.setPoll_id(poll_id);
                        pollDetail.setStore_id(store_id);
                        pollDetail.setSino(1);
                        pollDetail.setOptions(1);
                        pollDetail.setLimits(0);
                        pollDetail.setMedia(1);
                        pollDetail.setComment(0);
                        pollDetail.setResult(result);
                        pollDetail.setLimite("0");
                        pollDetail.setComentario("");
                        pollDetail.setAuditor(user_id);
                        pollDetail.setProduct_id(0);
                        pollDetail.setPublicity_id(0);
                        pollDetail.setCompany_id(GlobalConstant.company_id);
                        pollDetail.setCategory_product_id(0);
                        pollDetail.setCommentOptions(1);
                        pollDetail.setSelectdOptions(opt1);
                        pollDetail.setSelectedOtionsComment(commentOptions);
                        pollDetail.setPriority("0");

                        new loadPoll().execute();
                        dialog.dismiss();


                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });

                builder.show();
                builder.setCancelable(false);

            }
        });

    }
    private void leerEncuesta() {
        if(db.getEncuestaCount()>0) {
            // Encuesta encuesta = db.getEncuesta(530);
            Encuesta encuesta = db.getEncuesta(GlobalConstant.poll_id[4]);
            //if (idPregunta.equals("2")  ){
            pregunta.setText(encuesta.getQuestion());
            pregunta.setTag(encuesta.getId());
            poll_id= encuesta.getId();
            //}
        }
    }

    // Camera
    private void takePhoto() {

        Intent i = new Intent( MyActivity, AndroidCustomGalleryActivity.class);
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
                argRuta.putInt("company_id",idCompany);
                argRuta.putInt("idPDV",store_id);
                argRuta.putInt("idRuta", idRuta );

                argRuta.putInt("idAuditoria",idAuditoria);
                Intent intent;
                //intent = new Intent("com.dataservicios.ttauditibk.USOIBKSEGUNDO");
                intent = new Intent(MyActivity,UsoInterbankAgenteTercero.class);
                intent.putExtras(argRuta);
                startActivity(intent);
                finish();



            } else {
                Toast.makeText(MyActivity , "No se pudo guardar la información intentelo nuevamente", Toast.LENGTH_LONG).show();
            }
            hidepDialog();
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
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
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        this.finish();
//
//        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
    }

    private void enabledControl(boolean state){
        if (state) {
            ly_ChkSi.setVisibility(View.VISIBLE);
            checkBoxArray[0].setChecked(false);
            checkBoxArray[1].setChecked(false);
            checkBoxArray[2].setChecked(false);
            checkBoxArray[3].setChecked(false);


        } else {
            ly_ChkSi.setVisibility(View.INVISIBLE);
            checkBoxArray[0].setChecked(false);
            checkBoxArray[1].setChecked(false);
            checkBoxArray[2].setChecked(false);
            checkBoxArray[3].setChecked(false);

        }

    }
}
