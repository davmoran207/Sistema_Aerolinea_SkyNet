package modelo;

import java.util.ArrayList;

public class Vuelo {
    
    private String codigo;
    private String origen;
    private String destino;
    private String fecha;
    private int capacidadMaxima;
    private ArrayList<Asiento> asientos;
    
    public Vuelo(String codigo, String origen, String destino, String fecha, int capacidadMaxima){
        this.codigo=codigo;
        this.origen=origen;
        this.destino=destino;
        this.fecha=fecha;
        this.capacidadMaxima=capacidadMaxima;
        this.asientos= new ArrayList<>();
    }
    
    public String getCodigo(){
        return codigo;
    }
    public String getOrigen(){
        return origen;
    }
    public String getDestino(){
        return destino;
    }
    public String getFecha(){
        return fecha;
    }
    public int getCapacidadMaxima(){
        return capacidadMaxima;
    }
    public ArrayList<Asiento> getAsientos(){
        return asientos;
    }
    public void generarAsientos(){
        int cantidadPrimera= (int) (capacidadMaxima*0.1);
        int cantidadEjecutivo= (int) (capacidadMaxima*0.2);
        int asientosEconomico= capacidadMaxima - cantidadPrimera - cantidadEjecutivo;
        int numeroActual=1;
        for(int i=0; i<cantidadPrimera;i++){
            asientos.add(new AsientoPrimerClase(""+numeroActual, true,100.0));
            numeroActual++;
        }
    public int contarAsientosOcupados(){
        
    }
    public int contarAsientosOcupados(String clase){
        
    }
}
