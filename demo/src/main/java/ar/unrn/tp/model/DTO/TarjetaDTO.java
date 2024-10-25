package ar.unrn.tp.model.DTO;

public class TarjetaDTO {
    private Long id;
    private int nro;
    private String marca;


    public TarjetaDTO(int nro, String marca) {
        this.nro = nro;
        this.marca = marca;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters y setters
    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
