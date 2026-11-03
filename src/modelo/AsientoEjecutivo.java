package modelo;


public class AsientoEjecutivo extends Asiento{
    public AsientoEjecutivo( String numero, boolean estaLibre, double precioBase){
        super(numero,estaLibre,precioBase);
    }
    
    @Override
    public double calcularPrecio(){
        return getPrecioBase() + (getPrecioBase()*0.5);
    }
    
}
