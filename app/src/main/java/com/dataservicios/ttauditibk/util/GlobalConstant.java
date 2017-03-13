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
    public static int company_id = 69;
    public static int company_id_palmera = 47;
    public static String type_aplication = "android";

    public static int[] poll_id = new int[]{
          982  , // 717,   0 Al llegar al establecimiento el cliente incógnito deberá preguntar directamente por el agente de Interbank. Ejemplo: Buenos días/tardes, ¿hay agente de Interbank aquí?
          983  , // 718,   1 Indicar Rubro
          984  , // 719,   2 ¿Se encuentra abierto el agente?

          985  , // 720,   3 ¿El letrero de IBK Agente era visible desde fuera?
          986  , // 721,   4 ¿El Interbank Agente es visible estando dentro del establecimiento?
          987  , // 722,   5 ¿Existe algún otro Agente / corresponsal bancario?
          988  , // 723,   6 El CI deberá preguntar,  ¿Puedo pagar una tarjeta de crédito de Interbank acá?
          989  , // 724,   7 En el caso de que exista más de un agente en el comercio, preguntar, ¿acá puedo pagar mi teléfono?
          990  , // 725,   8 Si responde que si en la P8, preguntar ¿Y en cuál agente me conviene pagar mi teléfono?
          991  , // 726,   9 Si me envían dinero del exterior ¿Lo puedo cobrar acá?
          992  , // 727,   10 Al preguntar si se podía hacer la operación correspondiente, ¿el dependiente aceptó realizar la operación?
          993  , // 728,   11 ¿Su solicitud fue atendido de inmediato?
          994  , // 729,   12  Su solicitud no fue atendida de inmediato porque
          995  , // 730,   13 Mientras esperaba. ¿La persona que lo atendió se preocupó por su tiempo?
          996  , // 731,   14 Después de esperar
          997  , // 732,   15 ¿La transacción se llegó a realizar de manera exitosa?  (Se considera exitosa cuando se entrega el voucher)
          998  , // 733,   16 ¿Cuántos MINUTOS transcurrieron entre que solicitó la transacción y la persona terminó (le entregá el voucher)?
          999  , // 734,   17 ¿La persona que lo atendió tuvo que solicitar ayuda de alguna otra persona o hacer alguna consulta al respecto?
          1000  , // 735,   18 ¿Le entregaron ESPONTÁNEAMENTE un comprobante luego de la transacción? (Si no le entregaron espontáneamente el voucher deben solicitarlo y adjuntarlo al formulario)
          1001  , // 736,   19 (SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
          1002  , // 737,   20 (SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
          1003  , // 738,   21 Escoger tipo de Transacción
          1004  , // 739,   22 ¿El agente hizo algún cobro fuera del voucher?
          1005  , // 740,   23 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la amabilidad de la persona que te atendió?
          1006  , // 741,   24 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías el conocimiento de la persona que lo atendió?
          1007  , // 742,   25 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la disposición de la persona que lo atendió?
          1008  , // 743,   26 El CI deberá mostrar interés: Voy a abrir un negocio, ¿usted me recomendaría tener un agente Interbank?
          1009  , // 746,   27 Otras apreciaciones a comentar
         1010	, //        28	¿El letrero de Agente BIM era visible desde fuera?
         1011	, //        29	Al preguntar si se podía hacer la operación, ¿el dependiente aceptó realizar la operación?
         1012	, //        30	¿Su solicitud fue atendido de inmediato?
         1013	, //        31	 Su solicitud no fue atendida de inmediato porque
         1014	, //        32	Después de esperar
         1015	, //        33	¿La transacción se llegó a realizar de manera exitosa?  (Ojo esta tipo de transacciones no generan voucher, la confirmación es a través de un SMS)
         1016	, //        34	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
         1017	, //        35	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
         1018	, //        36	Otras apreciaciones a comentar


} ;

    public static int[] poll_id_palmera = new int[]{
            630, // 0 Cliente vende Bloqueador en Sachet?"
            631, // 1 Precio de venta del bloqueador"
    } ;
}
