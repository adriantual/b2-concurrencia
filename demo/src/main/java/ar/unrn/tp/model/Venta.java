package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
public class Venta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroUnico;
    private LocalDate fechaYHora;
  //  @OneToMany(cascade = jakarta.persistence.CascadeType.ALL)
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "venta_productos",
          joinColumns = @JoinColumn(name = "venta_id"),
          inverseJoinColumns = @JoinColumn(name = "producto_id")
  )
    private List<Producto> productos;
    private double total;
    @ManyToOne
    private Tarjeta tarjeta;
    @ManyToOne
    private Cliente cliente;

    public Venta(List<Producto> productos, Cliente unCliente, double total, Tarjeta tarjeta, String numeroUnico) {
        this.productos = productos;
        this.cliente=unCliente;
        this.total=total;
        this.fechaYHora=LocalDate.now();
        this.tarjeta=tarjeta;
        this.numeroUnico=numeroUnico;

    }


    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }


    public Long tarjeta()
    {return this.tarjeta.getId();}

    public List<Producto> productosComprados() {
        return productos;
    }


}
