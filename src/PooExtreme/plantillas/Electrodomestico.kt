package PooExtreme.plantillas

/*

 */
abstract class Electrodomestico(val nombre : String, val peso : Int,
                                val precio : Int, val marca : Marca, ) {

    abstract var opciones : ArrayList<String>

    enum class Marca {
        SAMSUNG, LG, SONIC, TCL, COLLECTICK, INTEL, AMD, NVIDIA, LENOVO, HP, GENIUS
    }

     protected open fun encender(){
         println("Encendiendo...")
         Thread.sleep(1500)
         println("Bienvenido a $nombre")
    }
    protected open fun mostrarCaracteristicas(){
        println("Producto: $nombre \n Desarrollado por: $marca \n " +
                "Precio: $precio \n Peso en kg: ${peso/1000}")
    }
   protected fun RateUs(commentBad : String, commentSBad : String,
    commentSGood : String, commentGood : String, commentPerfect : String, unknown : String){

        println("Puntúa $nombre con una calificación del 0 al 10")
       val calf = readLine()!!.toInt()
        when(calf) {
         in 0 .. 2 -> println("Tu calificación es: $commentBad ($calf)")
            in 3 .. 5 -> println("Tu calificación es: $commentSBad ($calf)")
            in 6 .. 7 -> println("Tu calificación es: $commentSGood ($calf)")
            in 8 .. 9 -> println("Tu calificación es: $commentGood ($calf)")
            10 -> println("Tu calificación es: $commentPerfect ($calf)")
            else -> println("Tu calificación es: $unknown ($calf)")
        }
    }
    abstract fun ImprimirOpciones()
    abstract fun configuration()




}