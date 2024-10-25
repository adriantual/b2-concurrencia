package ar.unrn.tp.api;

import ar.unrn.tp.model.Cliente;
import ar.unrn.tp.model.Tarjeta;

import java.time.LocalDate;
import java.util.List;

public interface ClienteService {


// validar que el dni no se repita
        void crearCliente(String nombre, String apellido, String dni, String email);


        // validar que sea un cliente existente
        void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email);


        // validar que sea un cliente existente
        void agregarTarjeta(Long idCliente,int nro, String marca);


        //Devuelve las tarjetas de un cliente específico
        List listarTarjetas(Long idCliente);
        List listarClientes();


        public Cliente buscarCliente(Long idCliente);

        public Tarjeta buscarTarjeta(Long idTarjeta);



}
