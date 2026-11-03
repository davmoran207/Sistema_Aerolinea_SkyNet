package modelo;


public class AsientoPrimeraClase extends Asiento {
    
    public AsientoPrimeraClase( String numero, boolean estaLibre, double precioBase){
        super(numero,estaLibre,precioBase);
    }
    
    @Override
    public double calcularPrecio(){
        return getPrecioBase() + (getPrecioBase()*1.5);
    }
}
