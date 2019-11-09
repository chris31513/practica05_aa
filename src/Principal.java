
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class Principal{
    /**
    * Método que recibe un objeto tipo Grafica y la dibuja.
    * @param g: gráfica que recibe.
    */
    public void dibujaGrafica(Grafica g){
        Graph graph = new SingleGraph("Ventana"); //Se crea una gráfica de graphstream
        
        //En esta parte se agregan todos los vértices a "graph".
        for(Vertice v : g.vertices){
            graph.addNode(v.nombre);
            graph.getNode(v.nombre).addAttribute("ui.label", v.nombre); //Se agrega una etiqueta con el nombre del vértice.
        }

        //En esta parte se agregan las aristas.
        if(g.dirigida){ //Si es dirigida
            for(Vertice v : g.vertices){
                for(Vertice u : v.vecinos){
                    String nombreArista = v.nombre+","+u.nombre;
                    graph.addEdge(nombreArista, v.nombre, u.nombre,true);
                }
            }
        }else{ //Si no es dirigida
            for(Vertice v : g.vertices){
                for(Vertice u : v.vecinos){
                    String nombreArista = v.nombre+","+u.nombre;
                    String nombreArista2 = u.nombre+","+v.nombre;
                    if(graph.getEdge(nombreArista2) == null){ //Si no se ha agregado la arista.
                        graph.addEdge(nombreArista, v.nombre, u.nombre);
                    }
                }
            }
        }

        graph.display(); //Se pone la gráfica en pantalla.
    }

    public static void main(String[] args){
        Lector l = new Lector();
        l.lee(args[0]); //Se lee el archivo.
        Grafica g1 = l.creaGrafica(); //Se crea una gráfica con el archivo leído.
        Principal pr = new Principal();

        /**
        * En esta sección deben ejecutar su algoritmo para calcular las componentes conexas.
        * Después deben imprimir cada componente separada por un salto de línea.
        */

        pr.dibujaGrafica(g1); //Se dibuja la gráfica en pantalla
        g1.connectedComponents();
    }
}




