package PooExtreme.productos

import PooExtreme.plantillas.*


class Lavadora : Electrodomestico("Lavadora Bigo", 35000, 699, Marca.SAMSUNG), Consumo,
SamsungServices{

   private var seleccion = 0
   private val record = arrayListOf<String>()
   private val decoration = "*-*-*-*-*-*-*-*-*-"

   fun ejecutar(){

           encender()
       while (seleccion != opciones.size){
           showmenu()
           doTask(seleccion)
       }

   }

    private fun showmenu(){
        Thread.sleep(1500)
        println(decoration)
        ImprimirOpciones()
        println(decoration)
        seleccion = readLine()!!.toInt()
    }
    private fun doTask(selection : Int){
        when(selection){
            1 -> lavarRopa()
            2 -> showHistory()
            3 -> showInfo()
            4 -> rate()
            5 -> println("El consumo por mes es de: ${calcularConsumo()} dólares al mes")
            6 -> configuration()

            opciones.size -> finish()
            else -> println("La opción no existe, vuelva a intentar.")
        }
    }



    private fun rate() {
        RateUs(
            "No lo recomiendo en lo absoluto.",
        "Muy malo, puede mejorar",
        "Está decente, pero aun deja mucho que desear",
        "Está muy bueno, lo recomiendo",
        "Es perfecto.",
        "desconocido"
        )
    }

    private fun finish() {
        println("Apagando lavadora...")
        Thread.sleep(1500)
    }

    private fun showInfo() {
      mostrarCaracteristicas()
    }

    private fun showHistory() {
        //Si el historial ta vacío:
        if(record.isEmpty()){
           println("El historial se encuentra vacio")
        }else {
            println(record)
        }
    }


    private fun lavarRopa() {
        var amount: Int? = 0
        var TipoLavado = ""
        var suavitel = false

    //Comprobar que escribió algún número o que escribió alguna opcion existente
        while(amount == null || amount >= 4 || amount <= 0) {
            println("Seleccione la cantidad de ropa a ingresar")
            println("1.-    1 - 5 KG")
            println("2.-    6 - 10 KG")
            println("3.-    11 - 16 KG")

            amount = readLine()?.toInt()
            if(amount == null || amount >= 4 || amount <= 0){
                println("La opción no es válida, intente nuevamente")
            }
        }
        //Configurar el tipo de lavado
        when(amount){
            1 -> TipoLavado = "Lavado intenso"
            2 -> TipoLavado = "Lavado Moderado"
            3 -> TipoLavado = "Lavado Suave."
        }

        println("Añadir suavitel?")
        println("1.- Sí")
        println("Cualquiera.- No")
        when(readLine()?.toInt()){
            1 -> suavitel = true
        }


        println("Lavando ropa...")
        Thread.sleep(1500)
        println(TipoLavado)
        if(suavitel){
            println("Suavitel añadido")
            Thread.sleep(1500)
        }

        println("Su ropa está lista!")
        Thread.sleep(4000)

        //Añadir al historial el lavado:
        addHistory(suavitel, amount)

    }

    //This fun add to the array the record.
    private fun addHistory(suavitel : Boolean, amount : Int){
        var record = ""

        //Configure "historial" for add to record array
        when(amount){
            1 -> record += "Se hizo un Lavado Intenso "
            2 -> record += "Se hizo un Lavado Moderado "
            3 -> record += "Se hizo un Lavado Suave "
        }

        if(suavitel){
            record += "con suavitel añadido"
        }else{
            record += "sin suavitel añadido"
        }
        //Add that to record:
        this.record.add(record)
    }


    override var wats_H: Int = 30

    override var Ahorro_A: Boolean = true

    override var CostoMensual: Float = 0f

    override fun calcularConsumo() : Float{
        if(wats_H <= 0){
         return CostoMensual
        }else{
            //Si el ahorro a es positivo, dividir solo entre 3
            if(Ahorro_A){
               CostoMensual =  (wats_H / 3) * 5 .toFloat()

                return CostoMensual
            }else{
                //Sino, dividir entre 2.
                CostoMensual =  (wats_H / 2) * 5.toFloat()
                return CostoMensual
            }
        }
    }
    override fun configuration() {
        var option = 0
        while(option != 3) {
            println("1.- Acerca de Bixby")
            println("2.- Agregar opciones")
            println("3.- Salir de configuración")
            option = readLine()!!.toInt()

            when (option) {
                1 -> aboutBixby()
                2 -> addOption()
                3 -> Thread.sleep(1000)
                4 -> println("La opción no existe, vuelva a intentar.")
            }
        }
    }

    override fun ImprimirOpciones() {
        var numOpcion = 1
        opciones.forEach { element ->
            println("$numOpcion.- $element")
            numOpcion++
        }
    }




/*
Array de opciones.
 */

     override var opciones = arrayListOf(
        "Lavar ropa",
        "Historial de lavado",
         "Acerca de...",
        "Puntuar",
        "Calcular costo eléctrico",
         "Configuración avanzada",
        "Apagar"

    )

    override fun addOption() {

        println("Escriba el nombre de su función: ")
        val funcion = readLine()!!

        /*
        Aqui se pide el funcionamiento y esas mierda despues xD
        */

        println("Agregando función...")
        //Remover la última opción
        opciones.removeAt(opciones.size-1)
        //Agregar la nueva función
        opciones.add(funcion)
        //Agregar de nuevo apagar.
        opciones.add("Apagar")
    }


}
