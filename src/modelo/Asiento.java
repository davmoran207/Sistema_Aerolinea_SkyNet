package modelo;

public abstract class Asiento {
    private String numero;
    private boolean estaLibre;
    private double precioBase;
    
    public Asiento( String numero, boolean estaLibre, double precioBase){
        this.numero=numero;
        this.estaLibre=estaLibre;
        this.precioBase=precioBase;
    }
    public String getNumero(){
        return numero; 
    }
    public boolean isEstaLibre(){
        return estaLibre;
    }
    public double getPrecioBase(){
        return precioBase;
    }
    public void setNumero(String numero){
        this.numero=numero;
    }
    public void setEstaLibre(boolean estaLibre){
        this.estaLibre=estaLibre;
    }
    public void setPrecioBase(double precioBase){
        this.precioBase=precioBase;
    }
    public void ocupar(){
        estaLibre=false;
    }
    public void liberar(){
        estaLibre=true;
    }
    
    @Override
    public String toString(){
        return " |Numero: "+numero+" |Ocupado: "+estaLibre+" |Precio: $"+precioBase;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Asiento otroAsiento){
        return this.numero.equals(otroAsiento.numero);
    } 
        return false;
    }
    
    public abstract double calcularPrecio();
    
}