package ejercicios

import java.math.RoundingMode
import java.util.UUID

/*
Diseña una clase abstracta Empleado con propiedades como nombre, id, y un método abstracto calculaSalario().
Crea clases derivadas como EmpleadoPorHora y EmpleadoFijo, que implementen el método calculaSalario()
de diferentes maneras. Considera añadir una clase Departamento que tenga una lista de empleados
y pueda calcular el salario total que se debe pagar a todos sus empleados.

EmpleadoPorHora podría implementar dos propiedades cómo horasTrabajadas al mes y tarifaPorHora para realizar
el cálculo de su salario mensual. EmpleadoFijo podría tener a su vez dos propiedades distintas,
salarioFijo y numPagas del que podríamos calcular su salario mensual.

Departamento podría tener una lista de empleados y dos métodos:
agregarEmpleado y calculaSalarioTotal que tienen sus empleados al mes.

En el main crea una instancia de Departamento, agrega varios empleados, recorre la lista de los
 empleados mostrando su información "Nombre con ID-0001 tiene un salario de 28697.96 al mes."
 (el id siempre con 4 posiciones numéricas y el salario con 2 decimales)

¿Se te ocurre alguna restricción lógica que podríamos introducir a las propiedades?
 */

abstract class Empleado(val nombre:String,private val ID:UUID = UUID.randomUUID()){
    abstract fun calculaSalario():Double
    override fun toString(): String {
        return "Nombre: $nombre con ID: ${ID} tiene un salario de ${calculaSalario()} al mes"
    }
}

class EmpleadoPorHora(val nombreEmpleadoPorHora: String,private val horasAlMes:Int,private val tarifaPorHora: Double)
    :Empleado(nombreEmpleadoPorHora) {
    override fun calculaSalario(): Double {
        return (horasAlMes * tarifaPorHora).toBigDecimal().setScale(2,RoundingMode.HALF_UP).toDouble()
    }
}

class EmpleadoFijo(val nombreEmpleadoFijo:String, private val salarioFijo:Double, private val numPagas:Int)
    :Empleado(nombreEmpleadoFijo) {
    override fun calculaSalario(): Double {
        val pagaProrrateada = (salarioFijo / numPagas).toBigDecimal().setScale(2,RoundingMode.HALF_UP).toDouble()
        return salarioFijo+pagaProrrateada
        //el toBigDecimal con el setScale es para redondear hasta 2 decimales
    }
}

class Departamento (){
    private val listaDeEmpleados = mutableListOf<Empleado>()
    fun agregarEmpleado(empleado:Empleado){
        listaDeEmpleados.add(empleado)
    }

    fun calcularSalarioTotal():Double{
        var salarioTotal = 0.0
        listaDeEmpleados.map { salarioTotal += it.calculaSalario() }
        return salarioTotal
    }

    fun mostrarInfo(){
        listaDeEmpleados.forEach {
            println(it)
        }
    }
}

fun main(){
    val missJason = EmpleadoPorHora("Jason",36,11.0)
    val peterMcCain = EmpleadoFijo("Peter McCain",1500.0,14)

    val DeptInformatica = Departamento()

    DeptInformatica.agregarEmpleado(missJason)
    DeptInformatica.agregarEmpleado(peterMcCain)

    DeptInformatica.mostrarInfo()
}