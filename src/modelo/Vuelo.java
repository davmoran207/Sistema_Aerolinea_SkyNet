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
            asientos.add(new AsientoPrimeraClase(""+numeroActual, true ,100.0));
            numeroActual++;
        }
        for(int i=0; i<cantidadEjecutivo;i++){
            asientos.add(new AsientoEjecutivo(""+numeroActual, true , 100.0));
            numeroActual++;
        }
        for(int i=0;i<asientosEconomico;i++){
            asientos.add(new AsientoEconomico(""+numeroActual, true ,100.0));
            numeroActual++;   
        }
    }
    
    public int contarAsientosOcupados(){
        int contador=0;
        
        for(Asiento a: asientos){
            if(!a.isEstaLibre() ){
              contador+=1;  
            }
        }
        
        return contador;
    }
    
    public int contarAsientosOcupados(String clase){
        int contador=0;
        
        for(Asiento a: asientos){
            if(!a.isEstaLibre()){
                if (clase.equalsIgnoreCase("PrimeraClase") && a instanceof AsientoPrimeraClase){
                    contador+=1;
                } else if(clase.equalsIgnoreCase("Ejecutivo") && a instanceof AsientoEjecutivo){
                    contador+=1;    
                } else if(clase.equalsIgnoreCase("Economico") && a instanceof AsientoEconomico ){
                    contador+=1;
                }
            }
        }
        
        return contador;
    }
    
    @Override
    public String toString(){
        return " |Codigo: "+codigo+" |Origen: "+origen+" |Destino: "+destino+" |Fecha: "+fecha+" |Capacidad: "+capacidadMaxima;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Vuelo otroVuelo){
        return this.codigo.equals(otroVuelo.codigo);
    } 
        return false;
    }
}
