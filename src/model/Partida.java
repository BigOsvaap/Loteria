package model;

import javafx.beans.property.SimpleStringProperty;

public class Partida {

    private final SimpleStringProperty username;
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty hora;
    private final SimpleStringProperty puntaje;

    public Partida(String username, String fecha, String hora, String puntaje){
        this.username = new SimpleStringProperty(username);
        this.fecha = new SimpleStringProperty(fecha);
        this.hora = new SimpleStringProperty(hora);
        this.puntaje = new SimpleStringProperty(puntaje);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public String getHora() {
        return hora.get();
    }

    public void setHora(String hora) {
        this.hora.set(hora);
    }

    public String getPuntaje() {
        return puntaje.get();
    }

    public void setPuntaje(String puntaje) {
        this.puntaje.set(puntaje);
    }

}
