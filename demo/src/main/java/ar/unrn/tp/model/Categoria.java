package ar.unrn.tp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;

    public Categoria(Long id, String nombre) {
        this.id=id;

        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public long getId() {
        return id;
    }
}
