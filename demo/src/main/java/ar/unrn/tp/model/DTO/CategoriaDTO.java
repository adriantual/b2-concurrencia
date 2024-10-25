package ar.unrn.tp.model.DTO;

public class CategoriaDTO {
    private Long id;
    private String nombre;

    public CategoriaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
