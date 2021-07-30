package bigoShop.productos

import bigoShop.plantillas.Electrodomestico

class Microondas : Electrodomestico(nombre = "MicroondasBigo",
    peso = 5500, precio = 200, marca = Marca.SONIC) {


    private var option = 0
    private var decoration = "-*-*-*-*-*-*-*-*-*-*-"
    private var history : ArrayList<String> = arrayListOf()
    fun ejecutar(){

        encender()

        //Do the specific action with the option:

        while(option != opciones.size) {
            showMenu()
            when (option) {
                 1 -> cook()
                 2 -> configuration()
                3 -> historial()
                4 -> mostrarCaracteristicas()
                5 -> println("Apagando...")
                else -> println("La opción no existe, intente nuevamente.")

            }
        }
        Thread.sleep(2000)


    }

    private fun historial() {

        if(history.isEmpty()){
            println("El historial se encuentra vacío")
            return;
        }

        println(history)

    }


    private fun showMenu(){
        //Shows menu to user.
        println(decoration)
         ImprimirOpciones()
        println(decoration)
        option = readLine()!!.toInt()
    }
    private fun cook() {
        var temperatura = 0
        var tiempo = 0
        var msg = ""
        println("Introduzca la temperatura (20 - 120℃)")
        temperatura = readLine()!!.toInt()
        while(temperatura < 20 || temperatura > 120){
            println("La temperatura seleccionada no está permitida, intente nuevamente")
            temperatura = readLine()!!.toInt()
        }

        println("Introduzca el tiempo de calentado (1m - 20m)")
        tiempo = readLine()!!.toInt()
        while (tiempo >= 21 || tiempo <= 0){
            println("Tiempo no permitido, intente nuevamente")
            tiempo = readLine()!!.toInt()
        }
        println("Calentando comida...")
        Thread.sleep(1000)

          when(temperatura){
              in 21 .. 50 -> print("Calentado templado con ")
              in 51 .. 80 -> print("Calentado moderado con ")
              in 81 .. 119 -> print("Calentado intenso con ")

          }

        when(tiempo){
            in 1..4 -> println("tiempo corto")
            in 5..11 -> println("tiempo intermedio")
            in 11..20 -> println("tiempo largo")
        }

     Thread.sleep(1500)
        println("50%...")
        println("100%...")
        println("Su comida está lista.")
        addHistory(tiempo, temperatura)

    }
    private fun addHistory(tiempo : Int, temperatura : Int){
        var texto = "Se cocinó en " //Aqui se guarda el texto.
        when(tiempo){
            in 1..4 -> texto += "tiempo corto"
            in 5..11 -> texto += "tiempo intermedio"
            in 11..20 -> texto += "tiempo largo"
        }
        when(temperatura){
            in 21 .. 50 -> texto += " con calentado templado"
            in 51 .. 80 -> texto += " con calentado moderado"
            in 81 .. 119 -> texto += " con calentado intenso"
        }
        history.add(texto)
    }





    override fun ImprimirOpciones() {
       var numOpcion = 1

        opciones.forEach { item ->
            println("$numOpcion.- $item")
             numOpcion++
        }
    }

    override fun configuration() {
        println("En proceso...")
    }




    override var opciones: ArrayList<String> = arrayListOf(

         "Calentar comida",
        "Configuración",
        "Registro de uso",
         "Acerca de...",
         "Apagar"
    )

}
//Creates new function







