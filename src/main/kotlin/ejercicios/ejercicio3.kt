package ejercicios

/*
Crea tres interfaces:

    EncendidoApagado con métodos como encender() y apagar().
    DispositivoElectronico con un método llamado reiniciar().
    Vehiculo con dos propiedades: motorEncendido y kmHora; y los métodos acelerar(Int) y frenar(Int).

Implementa estas interfaces en varias clases como Telefono, Lavadora y Coche.
Cada clase debería tener su propia implementación de los métodos de las interfaces que
tengan sentido que implementen, simulando el comportamiento que le obligan a desarrollar a cada una.

Un coche solo acelera y frena si tiene el motor encendido.
Por defecto un objeto coche estará apagado. Si a un coche le mandamos frenar
y su valor final fuera negativo le asignaremos a kmHora el valor 0.
 */

interface EncendidoApagado{
    fun encender()
    fun apagar()
}

interface DispositivoElectronico{
    fun reiniciar()
}

interface Vehiculo{
    var motorEncendido:Boolean
    var kmHora:Int
    fun acelerar(velocidad: Int)
    fun frenar(velocidad: Int)
}

class Telefono(val modelo:String,var bateria:Int , var estadoEncendido:Boolean):EncendidoApagado,DispositivoElectronico{
    init {
        require(bateria in 0..100){"Tiene que tener una cantidad normal de energía"}
        if (bateria == 0)
            estadoEncendido = false
    }

    override fun encender() {
        if (bateria > 0){
            estadoEncendido = true
        }
    }

    override fun apagar() {
        estadoEncendido = false
    }

    override fun reiniciar() {
        if (estadoEncendido==true){
            estadoEncendido = false
            estadoEncendido = true
        }
    }
}

class Lavadora(val modelo:String, private val capacidadRopa:Int, var estadoEncendido:Boolean):EncendidoApagado,DispositivoElectronico{
    var lavandoRopa = false
    private var cantidadDeRopaIngresada = 0

    fun empezarALavarRopa(){
        lavandoRopa = true
    }

    fun terminarDeLavarRopa(){
        lavandoRopa = false
    }

    fun anadirRopa(){
        cantidadDeRopaIngresada ++
    }

    override fun encender() {
        estadoEncendido = true
    }

    override fun apagar() {
        estadoEncendido = false
    }

    override fun reiniciar() {
        terminarDeLavarRopa()
        estadoEncendido = false
        estadoEncendido = true
        empezarALavarRopa()
    }
}

class Coche(val modelo:String, override var motorEncendido: Boolean, override var kmHora: Int):Vehiculo
{
    override fun acelerar(velocidad: Int) {
        kmHora += velocidad
    }

    override fun frenar(velocidad: Int) {
        kmHora -= velocidad
        if (kmHora < 0)
            kmHora = 0
    }

}