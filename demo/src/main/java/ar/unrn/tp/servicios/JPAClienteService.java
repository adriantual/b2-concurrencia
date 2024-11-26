package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.exception.ClienteException;
import ar.unrn.tp.model.Cliente;
import ar.unrn.tp.model.DTO.TarjetaDTO;
import ar.unrn.tp.model.Tarjeta;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JPAClienteService implements ClienteService {
    @Autowired
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void crearCliente(String nombre, String apellido, String dni, String email) {

        if (dniRepetido(Integer.parseInt(dni))) {
            throw new ClienteException.DniDuplicadoException("El DNI ya está registrado.");
        }

            Cliente nuevoCliente = new Cliente(nombre, apellido, Integer.parseInt(dni), email);
            em.persist(nuevoCliente);
    }
    private boolean dniRepetido(int dni) {
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.dni = :dni", Cliente.class)
                .setParameter("dni", dni)
                .getResultList();
        return !clientes.isEmpty();
    }
    @Override
    @Transactional
    public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {

        Cliente cliente = em.getReference(Cliente.class, idCliente);
        clienteExistente(cliente);

        cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDni(Integer.parseInt(dni));
            cliente.setEmail(email);
            em.persist(cliente);


    }


    @Override
    @Transactional
    public void agregarTarjeta(Long idCliente, int nro, String marca) {

        Cliente cliente = em.find(Cliente.class, idCliente);

        clienteExistente(cliente);


        Tarjeta nuevaTarjeta = new Tarjeta(marca, nro);

        cliente.agregarTarjeta(nuevaTarjeta);
        em.persist(cliente);

    }

    private static void clienteExistente(Cliente cliente) {
        if (cliente == null) {
            throw new ClienteException.ClienteNoEncontradoException("El cliente no existe.");
        }
    }

    @Override
    //no va transactional?
    public List listarTarjetas(Long idCliente) {
        Cliente cliente = em.getReference(Cliente.class, idCliente);
        clienteExistente(cliente);
        //return cliente.getTarjetas();
        return cliente.getTarjetas().stream()
                .map(tarjeta -> new TarjetaDTO(tarjeta.getId(), tarjeta.getNro(),tarjeta.marca()))
                .collect(Collectors.toList());


    }

    @Override

    public Cliente buscarCliente(Long idCliente) {
        Cliente cliente = em.getReference(Cliente.class, idCliente);

        clienteExistente(cliente);

        return cliente;
    }

    @Override

    public Tarjeta buscarTarjeta(Long idTarjeta) {
        return em.getReference(Tarjeta.class, idTarjeta);
    }

    @Override
    public List listarClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();


    }



}
