
package com.mycompany.t11mensajes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

class Mensaje implements Serializable {
    public final long serial= 1L;
    private String texto;
    private String fechaHora;

    public Mensaje() {
    }
    
    public Mensaje(String texto) {
        this.texto= texto;
        this.fechaHora= new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public String getTexto() {
        return texto;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "\n__________________\n[" + fechaHora + "] \n" + texto+"\n__________________";
    }
}
