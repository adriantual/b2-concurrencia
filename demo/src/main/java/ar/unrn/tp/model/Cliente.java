package ar.unrn.tp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    private String email;
    @OneToMany (cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Tarjeta> tarjetas;


    public Cliente(String nombre, String apellido, int dni, String email) {
        validarNombre(nombre);
        validarApellido(apellido);
        validarDni(dni);
        validarEmail(email);

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetas = new ArrayList<>();

    }



    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }



    public void agregarTarjeta(Tarjeta unaTarjeta) {
        validarTarjetaNoNula(unaTarjeta); // Validamos que la tarjeta no sea nula
        if (this.tarjetas.contains(unaTarjeta)) {
            throw new IllegalArgumentException("Esta tarjeta ya existe.");
        }
        this.tarjetas.add(unaTarjeta);
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Tarjeta tarjetaMemeCard()
    {
        return this.tarjetas.get(0);
    }







    //Validaciones

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
    }

    private void validarApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
    }

    private void validarDni(int dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("El DNI debe ser un número positivo");
        }
    }

    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido");
        }
    }

    private void validarTarjeta(Tarjeta unaTarjeta) {
        if (unaTarjeta == null || !tarjetas.contains(unaTarjeta)) {
            throw new IllegalArgumentException("La tarjeta no es válida o no pertenece al cliente");
        }
    }

    private void validarTarjetaNoNula(Tarjeta unaTarjeta) {
        if (unaTarjeta == null) {
            throw new IllegalArgumentException("La tarjeta no puede ser nula");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }
}







