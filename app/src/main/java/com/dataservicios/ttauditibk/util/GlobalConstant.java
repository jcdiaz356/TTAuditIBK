package com.dataservicios.ttauditibk.util;
/**
 * Created by usuario on 11/11/2014.
 */
public final class GlobalConstant {
    public static String dominio = "http://ttaudit.com";
    //public static String dominio = "http://appfiliaibk.com";
    public static final String LOGIN_URL = dominio + "/loginUser";
    public static final String KEY_USERNAME = "username";
    public static String inicio,fin;
    public static  double latitude_open, longitude_open;
    public static  int global_close_audit =0;
    public static int company_id = 54;
    public static int company_id_palmera = 64;
    public static int[] poll_id = new int[]{
            902, // 0	Al llegar al establecimiento el cliente incógnito deberá preguntar directamente por el agente de Interbank. Ejemplo: Buenos días/tardes, ¿hay agente de Interbank aquí?
            903, // 1	Indicar Rubro
            904, // 2	¿Se encuentra abierto el agente?
            905, // 3	¿El letrero de IBK Agente era visible desde fuera?
            906, // 4	¿El Interbank Agente es visible estando dentro del establecimiento?
            907, // 5	¿Existe algún otro Agente / corresponsal bancario?
            908, // 6	El CI deberá preguntar,  ¿Puedo pagar una tarjeta de crédito de Interbank acá?
            909, // 7	En el caso de que exista más de un agente en el comercio, preguntar, ¿acá puedo pagar mi teléfono?
            910, // 8	Si responde que si en la P8, preguntar ¿Y en cuál agente me conviene pagar mi teléfono?
            911, // 9	Si me envían dinero del exterior ¿Lo puedo cobrar acá?
            912, // 10	Al preguntar si se podía hacer la operación correspondiente, ¿el dependiente aceptó realizar la operación?
            913, // 11	¿Su solicitud fue atendido de inmediato?
            914, // 12	 Su solicitud no fue atendida de inmediato porque
            915, // 13	Mientras esperaba. ¿La persona que lo atendió se preocupó por su tiempo?
            916, // 14	Después de esperar
            917, // 15	¿La transacción se llegó a realizar de manera exitosa?  (Se considera exitosa cuando se entrega el voucher)
            918, // 16	¿Cuántos MINUTOS transcurrieron entre que solicitó la transacción y la persona terminó (le entregá el voucher)?
            919, // 17	¿La persona que lo atendió tuvo que solicitar ayuda de alguna otra persona o hacer alguna consulta al respecto?
            920, // 18	¿Le entregaron ESPONTÁNEAMENTE un comprobante luego de la transacción? (Si no le entregaron espontáneamente el voucher deben solicitarlo y adjuntarlo al formulario)
            921, // 19	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
            922, // 20	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
            923, // 21	Escoger tipo de Transacción
            924, // 22	¿El agente hizo algún cobro fuera del voucher?
            925, // 23	En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la amabilidad de la persona que te atendió?
            926, // 24	En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías el conocimiento de la persona que lo atendió?
            927, // 25	En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la disposición de la persona que lo atendió?
            928, // 26	El CI deberá mostrar interés: Voy a abrir un negocio, ¿usted me recomendaría tener un agente Interbank?
            929, // 27	Otras apreciaciones a comentar
            930, // 28	¿El letrero de Agente BIM era visible desde fuera?
            931, // 29	Al preguntar si se podía hacer la operación, ¿el dependiente aceptó realizar la operación?
            932, // 30	¿Su solicitud fue atendido de inmediato?
            933, // 31	 Su solicitud no fue atendida de inmediato porque
            934, // 32	Después de esperar
            935, // 33	¿La transacción se llegó a realizar de manera exitosa?  (Ojo esta tipo de transacciones no generan voucher, la confirmación es a través de un SMS)
            936, // 34	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
            937, // 35	(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
            938, // 36	Otras apreciaciones a comentar

};

    public static int[] poll_id_palmera = new int[]{
            630, // 0 Cliente vende Bloqueador en Sachet?"
            631, // 1 Precio de venta del bloqueador"
    } ;

    public static int[] audit_id = new int[]{
            38, // 0 Cliente vende Bloqueador en Sachet?"
    } ;

   // public static String albunName = "AlicorpPhoto";
    //public static String directory_images = "/Pictures/" + albunName;
    public static String directory_images = "/Pictures/" ;
    public static String type_aplication = "android";

    public static final String JPEG_FILE_PREFIX = "_KNPos_";
    public static final String JPEG_FILE_SUFFIX = ".jpg";
}

