package com.dataservicios.ttauditibk.util;
/**
 * Created by usuario on 11/11/2014.
 */
public final class GlobalConstant {
    //public static String dominio = "http://appfiliaibk.com";
   public static String dominio = "http://ttaudit.com";
    //public static String dominio = "http://192.168.8.110";
    //public static String dominio = "http://192.168.1.40/ttaudit.com/backend/ttaudit1/public";
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
    public static int company_id = 226; // 217; //210; //198; //167 ; // 150;
    public static int company_id_palmera = 47;
    public static String type_aplication = "android";

    public static int[] poll_id = new int[]{
      3878, // 3674, // 3474, // 3180, //2917, //2743, // 2492,   0 Al llegar al establecimiento el cliente incógnito deberá preguntar directamente por el agente de Interbank. Ejemplo: Buenos días/tardes, ¿hay agente de Interbank aquí?
      3879, // 3675, // 3475, // 3181, //2918, //2744, // 2493,   1 Indicar Rubro
      3880, // 3676, // 3476, // 3182, //2919, //2745, // 2494,   2 ¿Se encuentra abierto el agente?
      3881, // 3677, // 3477, // 3183, //2920, //2746, // 2495,   3 ¿El letrero de IBK Agente era visible desde fuera?
      3882, // 3678, // 3478, // 3184, //2921, //2747, // 2496,   4 ¿El Interbank Agente es visible estando dentro del establecimiento?
      3883, // 3679, // 3479, // 3185, //2922, //2748, // 2497,   5 ¿Existe algún otro Agente / corresponsal bancario?
      3884, // 3680, // 3480, // 3186, //2923, //2749, // 2498,   6 El CI deberá preguntar,  ¿Puedo pagar una tarjeta de crédito de Interbank acá?
      3885, // 3681, // 3481, // 3187, //2924, //2750, // 2499,   7 En el caso de que exista más de un agente en el comercio, preguntar, ¿acá puedo pagar mi teléfono?
      3886, // 3682, // 3482, // 3188, //2925, //2751, // 2500,   8 Si responde que si en la P8, preguntar ¿Y en cuál agente me conviene pagar mi teléfono?
      3887, // 3683, // 3483, // 3189, //2926, //2752, // 2501,   9 Si me envían dinero del exterior ¿Lo puedo cobrar acá?
      3888, // 3684, // 3484, // 3190, //2927, //2753, // 2502,   10 Al preguntar si se podía hacer la operación correspondiente, ¿el dependiente aceptó realizar la operación?
      3889, // 3685, // 3485, // 3191, //2928, //2754, // 2503,   11 ¿Su solicitud fue atendido de inmediato?
      3890, // 3686, // 3486, // 3192, //2929, //2755, // 2504,   12  Su solicitud no fue atendida de inmediato porque
      3891, // 3687, // 3487, // 3193, //2930, //2756, // 2505,   13 Mientras esperaba. ¿La persona que lo atendió se preocupó por su tiempo?
      3892, // 3688, // 3488, // 3194, //2931, //2757, // 2506,   14 Después de esperar
      3893, // 3689, // 3489, // 3195, //2932, //2758, // 2507,   15 ¿La transacción se llegó a realizar de manera exitosa?  (Se considera exitosa cuando se entrega el voucher)
      3894, // 3690, // 3490, // 3196, //2933, //2759, // 2508,   16 ¿Cuántos MINUTOS transcurrieron entre que solicitó la transacción y la persona terminó (le entregá el voucher)?
      3895, // 3691, // 3491, // 3197, //2934, //2760, // 2509,   17 ¿La persona que lo atendió tuvo que solicitar ayuda de alguna otra persona o hacer alguna consulta al respecto?
      3896, // 3692, // 3492, // 3198, //2935, //2761, // 2510,   18 ¿Le entregaron ESPONTÁNEAMENTE un comprobante luego de la transacción? (Si no le entregaron espontáneamente el voucher deben solicitarlo y adjuntarlo al formulario)
      3897, // 3693, // 3493, // 3199, //2936, //2762, // 2511,   19 (SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
      3898, // 3694, // 3494, // 3200, //2937, //2763, // 2512,   20 (SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
      3899, // 3695, // 3495, // 3201, //2938, //2764, // 2513,   21 Escoger tipo de Transacción
      3900, // 3696, // 3496, // 3202, //2939, //2765, // 2514,   22 ¿El agente hizo algún cobro fuera del voucher?
      3901, // 3697, // 3497, // 3203, //2940, //2766, // 2515,   23 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la amabilidad de la persona que te atendió?
      3902, // 3698, // 3498, // 3204, //2941, //2767, // 2516,   24 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías el conocimiento de la persona que lo atendió?
      3903, // 3699, // 3499, // 3205, //2942, //2768, // 2517,   25 En una escala del 0 al 3 donde 0 significa Debajo del estándar, 2 Estándar y 3 Superior, ¿cómo calificarías la disposición de la persona que lo atendió?
      3904, // 3700, // 3500, // 3206, //2943, //2769, // 2518,   26 El CI deberá mostrar interés: Voy a abrir un negocio, ¿usted me recomendaría tener un agente Interbank?
      3905, // 3701, // 3501, // 3207, //2944, //2770, // 2519,   27 Otras apreciaciones a comentar
      3909, // 3705, // 3505, // 3211, //2948, //2774, // 2523,   28	¿El letrero de Agente BIM era visible desde fuera?
      3910, // 3706, // 3506, // 3212, //2949, //2775, // 2524,   29	Al preguntar si se podía hacer la operación, ¿el dependiente aceptó realizar la operación?
      3911, // 3707, // 3507, // 3213, //2950, //2776, // 2525,   30	¿Su solicitud fue atendido de inmediato?
      3912, // 3708, // 3508, // 3214, //2951, //2777, // 2526,   31	 Su solicitud no fue atendida de inmediato porque---------------falta-----------------
      3913, // 3709, // 3509, // 3215, //2952, //2778, // 2527,   32	Después de esperar ---------------------------falta -----------------------------
      3914, // 3710, // 3510, // 3216, //2953, //2779, // 2528,   33	¿La transacción se llegó a realizar de manera exitosa?  (Ojo esta tipo de transacciones no generan voucher, la confirmación es a través de un SMS)
      3915, // 3711, // 3511, // 3217, //2954, //2780, // 2529,   34	-------FALTA --------(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Por qué no se pudo realizar la transacción?
      3916, // 3712, // 3512, // 3218, //2955, //2781, // 2530,   35	-------FALTA --------(SÓLO SI NO SE REALIZÓ LA TRANSACCIÓN) ¿Le dieron alguna solución para poder realizar la transacción?
      3917, // 3713, // 3513, // 3219, //2956, //2782, // 2531,   36	(BIM) Otras apreciaciones a comentar
      3906, // 3702, // 3502, // 3208, //2945, //2771, // 2520,   37 ¿Tus comisiones te las pagan puntualmente?
      3907, // 3703, // 3503, // 3209, //2946, //2772, // 2521,   38 ¿Me puedes referir a tu ejecutivo IBK?
      3908, // 3704, // 3504, // 3210, //2947, //2773, // 2522,   39 ¿Existen ofertas para adelanto de sueldo o prestamos a travez de sus agentes?
} ;

    public static int[] poll_id_palmera = new int[]{
            630, // 0 Cliente vende Bloqueador en Sachet?"
            631, // 1 Precio de venta del bloqueador"
    } ;
}
