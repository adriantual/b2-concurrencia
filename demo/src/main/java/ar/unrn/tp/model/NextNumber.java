package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor
public class NextNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int anio;
    private int numeroActual;


    public NextNumber(int anio) {
        this.anio = anio;
        this.numeroActual = 0; // Inicializamos en 0 para el primer uso
    }

    public int recuperarSiguiente() {
        this.numeroActual += 1;
        return this.numeroActual;
    }
}
