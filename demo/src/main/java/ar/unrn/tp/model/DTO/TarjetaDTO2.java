package ar.unrn.tp.model.DTO;

public class TarjetaDTO2 {

    private Long id;
    private int nro;
    private String marca;




    public TarjetaDTO2(Long id, int nro, String marca) {
        this.id = id;
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
