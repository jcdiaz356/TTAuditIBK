package com.dataservicios.ttauditibk.util;
/**
 * Created by usuario on 11/11/2014.
 */
public final class GlobalConstant {
    //public static String dominio = "http://appfiliaibk.com";
   public static String dominio = "http://ttaudit.com";
   // public static String dominio = "http://192.168.1.40/ttaudit.com/backend/ttaudit1/public";
    //public static String dominio = "http://192.168.1.40/ttaudit.com/backend/ttaudit1/public";
    //public static String dominio = "http://dataservicios.com/ttaudit";
    //Testeo Local:
    //private static final String LOGIN_URL = "http://192.168.1.45/webservice/login.php";
    //Testeo real server:
    //loginUser
    //public static final String LOGIN_URL = dominio + "/webservice/login.php" ;
    public static final String LOGIN_URL = dominio + "/loginUser" ;
    public static final String KEY_USERNAME = "username";
    public static String inicio,fin;
    public static  double latitude_open, longitude_open;
    public static  int global_close_audit =0;
    public static int company_id = 76;
    public static int company_id_palmera = 47;
    public static String type_aplication = "android";

    public static int[] poll_id = new int[]{
         1250, //1151 , // 982  , // 717,   0 Al llegar al establecimiento el cliente incógnito deberá preguntar directamente por el agente de Interbank. Ejemplo: Buenos días/tardes, ¿hay agente de Interbank aquí?
         1251, //1152 , // 983  , // 718,   1 Indicar Rubro
         1252, //1153 , // 984  , // 719,   2 ¿Se encuentra abierto el agente?
         1253, //1154 , // 985  , // 720,   3 ¿El letrero de IBK Agente era visible desde fuera?
         1254, //1155 , // 986  , // 721,   4 ¿El Interbank Agente es visible estando dentro del establecimiento?
         1255, //1156 , // 987  , // 722,   5 ¿Existe algún otro Agente / corresponsal bancario?
         1256, //1157 , // 988  , // 723,   6 El CI deberá preguntar,  ¿Puedo pagar una tarjeta de crédito de Interbank acá?
         1257, //1158 , // 989  , // 724,   7 En el caso de que exista más de un agente en el comercio, preguntar, ¿acá puedo pagar mi teléfono?
         1258, //1159 , // 990  , // 725,   8 Si responde que si en la P8, preguntar ¿Y en cuál agente me conviene pagar mi teléfono?
         1259, //1160 , // 991  , // 726,   9 Si me envían dinero del exterior ¿Lo puedo cobrar acá?
         1260, //1161 , // 992  , // 727,   10 Al preguntar si se podía hacer la operación correspondiente, ¿el dependiente aceptó realizar la operación?
         1261, //1162 , // 993  , // 728,   11 ¿Su solicitud fue atendido de inmediato?
         1262, //1163 , // 994  , // 729,   12  Su solicitud no fue atendida de inmediato porque
         1263, //1164 , // 995  , // 730,   13 Mientras esperaba. ¿La persona que lo atendió se preocupó por su tiempo?
         1264, //1165 , // 996  , // 731,   14 Después de esperar
         1265, //1166 , // 997  , // 732,   15 ¿La transacción se llegó a realizar de manera exitosa?  (Se considera exitosa cuando se entrega el voucher)
         1266, //1167 , // 998  , // 733,   16 ¿Cuántos MINUTOS transcurrieron entre que solicitó la transacción y la persona terminó (le entregá el voucher)?
         1267, //1168 , // 999  , // 734,   17 ¿La persona que lo atendió tuvo que solicitar ayuda de alguna otra persona o hacer alguna consulta al respecto?
         1268, //1169 , // 1000  , // 735,   18 ¿Le entregaron ESPONTÁNEAMENTE un comprobante luego de la transacción? (Si no le entregaron espontáneamente el voucher deben solicitarlo y adjuntarlo al formulario)
         1269, //1170 , // 1001  , // 736,   19 (SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
         1270, //1171 , // 1002  , // 737,   20 (SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
         1271, //1172 , // 1003  , // 738,   21 Escoger tipo de Transacción
         1272, //1173 , // 1004  , // 739,   22 ¿El agente hizo algún cobro fuera del voucher?
         1273, //1174 , // 1005  , // 740,   23 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la amabilidad de la persona que te atendió?
         1274, //1175 , // 1006  , // 741,   24 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías el conocimiento de la persona que lo atendió?
         1275, //1176 , // 1007  , // 742,   25 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la disposición de la persona que lo atendió?
         1276, //1177 , // 1008  , // 743,   26 El CI deberá mostrar interés: Voy a abrir un negocio, ¿usted me recomendaría tener un agente Interbank?
         1277, //1178 , // 1009  , // 746,   27 Otras apreciaciones a comentar
         1278, //1179 , // 1010	, //        28	¿El letrero de Agente BIM era visible desde fuera?
         1279, //1180 , // 1011	, //        29	Al preguntar si se podía hacer la operación, ¿el dependiente aceptó realizar la operación?
         1280, //1181 , // 1012	, //        30	¿Su solicitud fue atendido de inmediato?
         1281, //1182 , // 1013	, //        31	 Su solicitud no fue atendida de inmediato porque
         1282, //1183 , // 1014	, //        32	Después de esperar
         1283, //1184 , // 1015	, //        33	¿La transacción se llegó a realizar de manera exitosa?  (Ojo esta tipo de transacciones no generan voucher, la confirmación es a través de un SMS)
         1284, //1185 , // 1016	, //        34	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
         1285, //1186 , // 1017	, //        35	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
         1286, //1187 , // 1018	, //        36	Otras apreciaciones a comentar


} ;

    public static int[] poll_id_palmera = new int[]{
            630, // 0 Cliente vende Bloqueador en Sachet?"
            631, // 1 Precio de venta del bloqueador"
    } ;
}
