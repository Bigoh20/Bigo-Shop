
package bigoShop.productos

import bigoShop.plantillas.*

import java.lang.Exception

import java.lang.NumberFormatException

/**
 * @author: Bigotes20
Lavadoras Samsung. Producto diseñado por: Carlos David.
Cuenta con funciones de últimas tecnologías para que sea amable con el usuario.
Implementa Byxby 1.5 y con actualizaciones mensuales que garantizan el funcionamiento del producto.
 */

class Lavadora : Electrodomestico("Lavadora Bigo", 35000, 699, Marca.SAMSUNG), Consumo,
SamsungServices{


    /**
     * Variable de selección. La opción que escoja el usuario se guardará aquí
     */
   private var seleccion = 0

    /**
     * Historial de lavado con sus características en este ArrayList. (Strings)
     */
   private val record = arrayListOf<String>()

    /**
     * Array con las opciones principales. Proviene de Electrodomestico.
     */
    override var opciones: ArrayList<String> = arrayListOf(
        "Lavar ropa",
        "Mostrar historial de lavado",
        "Acerca de...",
        "Puntuar producto",
        "Calcular consumo eléctrico",
        "Configuración avanzada",
        "Apagar"
    )


    /**
     *  Array que contiene Strings de las nuevas funciones que agregue el usuario.
     */

    private var newFunctions : ArrayList<String> = arrayListOf()


    //Variable para decorar el output.
    private val decoration = "*-*-*-*-*-*-*-*-*-"

    /**
     * Funcion ejecutar, contiene todo el flujo del programa.
     * Es la que se usa en el metodo Main.
     */

   fun ejecutar(){

           encender()
        //Al sacar la longitud del array se refiere a la ultima opcion que es salir. Por lo que si
        //selecciona salir vuelve al while y la condición no se cumple, lo que finaliza el proceso.
       while (seleccion != opciones.size){
           //Reiniciar selección a 0:
               seleccion = 0
           //Mostrar el menú
           showmenu()
           //Realizar la tarea.
           doTask(seleccion)
       }

   }

    /**
     * @param selection: Dependiendo de la opción ingresada se usará un when que ejecutará el método.
     */
    private fun doTask(selection : Int){

    //Kill me, please. I hate think in a solution for that, just for 1 week and I not have nothing...
        //Fixed.

        when(selection){
            1 -> lavarRopa()
            2 -> showHistory()
            3 -> showInfo()
            4 -> rate()
            5 -> System.out.println("El costo de la electricidad por mes es de: $" + calcularConsumo())
            6 -> configuration()
            opciones.size -> finish() //La longitud == ultima opcion
            else -> personOption(selection) //Si no es ninguna principal, puede ser una opcion Bixby.
        }

    }

    /**
     * Función para imprimir el menú con su decoración.
     */
    private fun showmenu(){
        try {
            Thread.sleep(1500)
            println(decoration)
            ImprimirOpciones()
            println(decoration)
            seleccion = readLine()!!.toInt() //Lee la seleccion y se actualiza.
        }catch (e : NumberFormatException){
//Capturamos el error y al no existir la opción seleccion esta se mantiene en 0.
        }
    }


    /**
     * Función que se usa para puntuar el producto. Se usa una dentro de otra para simplificar al llamarla
     */

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

    /**
     * Función que envia mensaje al seleccionarse la opción de array.size (viene de Electrodomestico)
     */
    private fun finish() {
        println("Apagando lavadora...")
        Thread.sleep(1500)
    }

    /**
     * Función que imprime los parametros pasados en el constructor (viene de Electrodomestico)
     */

    private fun showInfo() {
      mostrarCaracteristicas()
    }

    /**
     * Función que imprime el historial de los lavados.
     */

    private fun showHistory() {
        //Si el historial ta vacío:
        if(record.isEmpty()){
           println("El historial se encuentra vacio")
        }else {
            println(record)
        }
    }

    /**
     * Función que hace uso de lavar ropa, usa diferentes caracteristicas dependiendo de la opcion.
     */

    private fun lavarRopa() {
        try {
            var amount: Int? = 0 //Variable para saber el peso de ropa en opción.
            var TipoLavado = "" //Descifrar el tipo de lavado para guardarlo en el historial
            var suavitel = false //Saber si usó suavitel


            println("Seleccione la cantidad de ropa a ingresar")
            println("1.-    1 - 5 KG")
            println("2.-    6 - 10 KG")
            println("3.-    11 - 16 KG")
            amount = readLine()?.toInt()

            //Loop para identificar si la opcion existe.
            while (amount == null || amount >= 4 || amount <= 0) {
                println("La opción no existe, intente nuevamente.")
                amount = readLine()?.toInt()
            }

            //Configurar el tipo de lavado
            when (amount) {
                1 -> TipoLavado = "Lavado intenso"
                2 -> TipoLavado = "Lavado Moderado"
                3 -> TipoLavado = "Lavado Suave."
            }

            //Pedir suavitel.
            println("Añadir suavitel?")
            println("1.- Sí")
            println("Cualquiera.- No")
            when (readLine()?.toInt()) {
                1 -> suavitel = true
            }


            println("Lavando ropa...")
            Thread.sleep(1500)
            println(TipoLavado)
            if (suavitel) {
                println("Suavitel añadido")
                Thread.sleep(1500)
            }

            println("Su ropa está lista!")
            Thread.sleep(4000)

            //Añadir al historial el lavado, con sus argumentos para configurar.
            addHistory(suavitel, amount)
        }catch (e : NumberFormatException){
            println("Ha ocurrido un error de sintaxis durante el lavado. Intente nuevamente.")
        }
    }


    /**
     * @param suavitel: Booleano para identificar si se usó suavitel
     * @param amount: Int para saber la seleccion de KG de ropa.
     */
    private fun addHistory(suavitel : Boolean, amount : Int){
        var record = "" //Variable donde se almacena el texto del historial.

        //Configurar el historial dependiendo de lo escogido:
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

        //Agregarlo al historial.
        this.record.add(record)
    }


    /**
      * Calcular coste electrico.
     * Estas variables vienen de Electrodomestico y se les asigna un valor aquí.
     */
    override var wats_H: Int = 30

    override var Ahorro_A: Boolean = true

    override var CostoMensual: Float = 0f


    /**
     * Se calcula el consumo dividiendo wats entre 3 si hay ahorro tipo A y entre 2 si no lo hay
     * @return CostoMensual: Retorna el nuevo valor que es el costo por mes calculado en dólares.
     */
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

    /**
     * Configuración obligatoria para el producto. En este caso se implemente el asistente Bixby.
     */
    override fun configuration() {

            var option = 0 //La opcion que es escoge dentro de configuracion
            while (option != 3) { //Comprobar que escoge una correcta.
                try {
                    println("1.- Acerca de Bixby")
                    println("2.- Agregar opciones")
                    println("3.- Salir de configuración")
                    option = readLine()!!.toInt()

                    //Realizar tarea dependiendo de la necesidad.
                    when (option) {
                        1 -> aboutBixby()
                        2 -> addOption()
                        3 -> Thread.sleep(1000) //3 significa salir  por eso se pausa.
                        else -> println("La opción no existe, vuelva a intentar.")
                    }
                }catch (e : NumberFormatException){
                        println("La opción no es un número.")
                    }
            }

    }

    /**
     * Se recorre el array opciones con todos los elementos.
     */

    override fun ImprimirOpciones() {
        var numOpcion = 1 //Se usa el número de opcion que va a la izquierda y va aumentando en cada loop
        opciones.forEach { element ->
            println("$numOpcion.- $element")
            numOpcion++
        }
    }

    /**
     * Agrega una opcion que implementa de SamsungServices
     * Pide el nombre de la accion y el mensaje que da al seleccionarse.
     */

    override fun addOption() {


        println("Escriba el nombre de su función: ")
        val funcion = readLine()!!


        println("Escriba el mensaje que quiere que retorne su función")
        val msg = readLine()!!


        println("Agregando función...")
        newFunctions.add(msg) //Agrega la nueva funcion en ese array de opciones nuevas.
        Thread.sleep(2000)
        //Remover la última opción
        opciones.removeAt(opciones.size-1)
        //Agregar la nueva función en la lista de opciones para imprimir.
        opciones.add(funcion)
        //Agregar de nuevo apagar para que se ubique en el final del array.
        opciones.add("Apagar")


    }

    /**
     * Selecciona una opcion añadida por el humano. Si no se encuentra nada, la opcion no existe.
     * @param selection: Es la selección...
     */
    private fun personOption(selection: Int) {
        if(selection <= 0 || selection > opciones.size){ //Comprobar los limites del array.
            println("La opción no existe")
            return
        }
        //Las opciones principales son 7, entonces al haber otra opcion se resta 7 para que acceda
        //al elemento del array de selecciones nuevas.
        System.out.println(newFunctions[selection-7])


    }



}
