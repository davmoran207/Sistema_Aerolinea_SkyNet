package modelo;

public class Pasajero extends Persona implements Descontable {
    private final String pasaporte;
    
    public Pasajero(String nombre, String cedula, int edad, String telefono, String pasaporte){
        super(nombre, cedula,edad,telefono);
        this.pasaporte=pasaporte;  
    }
    
    public String getPasaporte(){
        return pasaporte;
    }
    
    @Override
    public int getPorcentajeDescuento(){
        if (getEdad()<12){
            return 25;    
        } else if (getEdad()>=65){
            return 50;
        } else{
            return 0;
        }
    }
    
    @Override
    public String toString(){
        return super.toString()+ " |Pasaporte: "+pasaporte;
    }
}
