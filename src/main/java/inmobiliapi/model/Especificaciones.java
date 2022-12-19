package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Especificaciones {
    
    @Schema(example = "Cali", description = "")
    private String ciudad;
    
    @Schema(example = "calle 1 #10-20", description = "")
    private String direccion;
    
    @Schema(example = "Apartamento", description = "")
    private TipoInmueble tipoInmueble;
    
    @Schema(example = "1000000", description = "")
    private double valorArriendo;
    
    @Schema(example = "100000", description = "")
    private double valorAdmin;
    
    @Schema(example = "55", description = "Tamaño del inmueble en metros cuadrados")
    private int tamaño;
    
    @Schema(example = "3", description = "")
    private int estrato;
    
    @Schema(example = "2", description = "")
    private int habitaciones;
    
    @Schema(example = "1", description = "")
    private int baños;
    
    @Schema(example = "true", description = "")
    private boolean parqueadero;

    public Especificaciones() {
    }

    
    
    public Especificaciones(String ciudad, String direccion, TipoInmueble tipoInmueble, double valorArriendo) {
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.tipoInmueble = tipoInmueble;
        this.valorArriendo = valorArriendo;
    }

    public Especificaciones(String ciudad, String direccion, TipoInmueble tipoInmueble, double valorArriendo, double valorAdmin, int tamaño, int estrato, int habitaciones, int baños, boolean parqueadero) {
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.tipoInmueble = tipoInmueble;
        this.valorArriendo = valorArriendo;
        this.valorAdmin = valorAdmin;
        this.tamaño = tamaño;
        this.estrato = estrato;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.parqueadero = parqueadero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public double getValorArriendo() {
        return valorArriendo;
    }

    public void setValorArriendo(double valorArriendo) {
        this.valorArriendo = valorArriendo;
    }

    public double getValorAdmin() {
        return valorAdmin;
    }

    public void setValorAdmin(double valorAdmin) {
        this.valorAdmin = valorAdmin;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getBaños() {
        return baños;
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public boolean isParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(boolean parqueadero) {
        this.parqueadero = parqueadero;
    }

}
