package ar.unrn.tp.model;

import jakarta.persistence.*;

@Entity
public class NextNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int anio;
    private int numeroActual;

    @Version
    private Long version;  // Control de concurrencia optimista

    public NextNumber(int anio) {
        this.anio = anio;
        this.numeroActual = 0; // Inicializamos en 0 para el primer uso
    }

    public int recuperarSiguiente() {
        this.numeroActual += 1;
        return this.numeroActual;
    }
}
