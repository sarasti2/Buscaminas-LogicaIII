# Buscaminas-LogicaIII

El programa es bastante sencillo y consta solo de dos clases principales.

1. Main: Esta clase es la que corre todo el programa en esta ocasión implementando la interface Runnable.
La clase main tambien desplega el menu que permite elegir el modo de juego, lo hace mediante el uso de un JOptionPane.
Ademas ya que la clase tablero esta funcionando como un subproceso debido a la interfaz, tambien se hace uso de dos metodos declarados en la clase tablero para saber si gano o perdio.

2.Tablero:  Esta clase contiene toda la jugabilidad del programa contieniendo las funciones listadas a continuación.
- Jpanel Grid: Crea la cantidad de celdas necesarias segun el modo de juego elegido.
-int BoxX y BoxY: Permiten saber las coordenadas x e y, respectivas de cada cuadro cuando se clikean.
-boolean revisarVictoria: Permite saber cuando se despejan todas las celdas que no contengan minas.
-int totalMinas: Permite contar todas las minas que se encuentran en el tablero.
-int casillas reveladas: Retorna el numero de casillas que aun no se han revelado.
-boolean isN: Esta función nos permite saber si una casilla es vecina de otra.
- void transformarTripleta: Esta función permite transformar las matrices originales en matrices expresadas como tripletas.
- Tambien existen otras funciones autogeneradas que son los  Listener del mouse.


         
