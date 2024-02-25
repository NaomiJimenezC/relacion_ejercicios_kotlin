import java.text.Normalizer.Form

/*
Crea una clase abstracta Figura que tenga métodos abstractos para area() y perimetro().
Implementa subclases concretas como Circulo, Rectangulo, y Triangulo, proporcionando la implementación
específica de estos métodos. La clase Figura podría tener propiedades comunes como el color,
que se inicializarán a través del constructor.

El valor de PI lo conseguimos con Math.PI
 */

abstract class Figura(val color:String){
    abstract fun area():Double
    abstract fun perimetro():Double
}

class Circulo(val diametro:Double,val radio:Double,val colorCirculo: String):Figura(colorCirculo){
    /**
     * Calcula el área de un circulo
     * @return el área del circulo
     */
    override fun area(): Double {
        return 2 * Math.PI * radio
    }


    /**
     * Calcula el perímetro de un Círculo
     * @return el perimetro calculado
     */
    override fun perimetro(): Double {
        return diametro * Math.PI
    }
}

class Rectangulo(val base:Double,val altura:Double,colorRectangulo:String):Figura(colorRectangulo){
    override fun area(): Double {
        return base * altura
    }

    override fun perimetro(): Double {
        return (2*altura) + (2*base)
    }
}

class Triangulo(val lado1:Double,val lado2:Double,val lado3:Double,
                val colorTriangulo: String, val base:Double, val altura:Double): Figura(colorTriangulo)
        {

    init {
        val comprobacionCoherencia = mutableListOf(lado1,lado2,lado3).any { it == base }
        require(comprobacionCoherencia == false){"Uno de los lados tendrá que ser igual a la base del triángulo"}
    }
    override fun area(): Double {
        return (base * altura)/2
    }

    override fun perimetro(): Double {
        return lado1+lado2+lado3
    }
}

fun main(){
    val triangulo = Triangulo(1.9,4.5,7.9,"rojo",7.0,6.0)
    val rectangulo = Rectangulo(8.0,6.0,"verde")
    val circulo = Circulo(33.0,15.0,"azul")
    println(triangulo.area())
    println(triangulo.perimetro())

    println(rectangulo.area())
    println(rectangulo.perimetro())

    println(circulo.area())
    println(circulo.perimetro())
}
