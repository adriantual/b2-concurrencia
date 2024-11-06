package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.exception.NumberException;
import ar.unrn.tp.model.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class JPAVentaService implements VentaService {
    @Autowired
    @PersistenceContext
    private  EntityManager em;
    @Autowired
    private  ClienteService clienteService;
    @Autowired
    private DescuentoService descuentoService;
    @Autowired
    private CacheService cacheService;

    @Override
    @Transactional
    public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {

        try {

            Cliente cliente = em.find(Cliente.class, idCliente);

            Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);


            CarritoDeCompras carrito = new CarritoDeCompras(cliente);
            armarCarrito(productos,carrito);

            // Generar el número único para la venta
            String numeroUnico = generarNumeroUnico();



            // Pasamos numeroUnico a procesoDePago
            Venta venta = carrito.procesoDePago(tarjeta, numeroUnico);
            // Actualizar caché con la nueva venta
            cacheService.agregarVenta(cliente.getId(), venta);


            em.persist(venta);

        } catch (Exception  e){
            throw new RuntimeException ("No se econtraron resultados para el id de cliente o la tarjeta: " + e.getMessage());

        }

    }

    private void armarCarrito(List<Long> productos, CarritoDeCompras carrito) {


        List<Producto> listaProductos = productos.stream()
                .map(idProducto -> em.find(Producto.class, idProducto))
                .toList();


        carrito.agregarMuchosProductos(listaProductos);

        List<Promociones> descuentos = this.descuentoService.listarDescuentos();
        descuentos.forEach(carrito::agregarPromocion);
    }

    @Override
    public double calcularMonto(Long idCliente, List<Long> productos, Long idTarjeta) {

        Cliente cliente = em.find(Cliente.class, idCliente);
        Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);


        CarritoDeCompras carrito = new CarritoDeCompras(cliente);
        armarCarrito(productos,carrito);





        return carrito.getTotal(tarjeta);

    }

    @Override
    public List<Venta> ventas() {
        return em.createQuery("SELECT v FROM Venta v", Venta.class).getResultList();
    }


    @Override
    public List<Venta> ventasPorCliente(Long idCliente) {
        return em.createQuery("SELECT v FROM Venta v WHERE v.cliente.id = :idCliente", Venta.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }


    // Método para generar el número único (N-AÑO)
    @Transactional
    private String generarNumeroUnico() {
        int anioActual = LocalDate.now().getYear();

        // Consultamos el número actual para el año
        TypedQuery<NextNumber> query = em.createQuery(
                "FROM NextNumber WHERE anio = :anioActual", NextNumber.class);
        query.setParameter("anioActual", anioActual);


        NextNumber nextNumber;

        try {
            query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
            nextNumber = query.getSingleResult();


        } catch (NoResultException e) {
            // Si no existe un registro para el año actual, creamos uno nuevo
            nextNumber = new NextNumber(anioActual);
            em.persist(nextNumber);
        }catch (NonUniqueResultException ue) {
            throw new NumberException("Se encontraron múltiples resultados para este año : " + anioActual);

        } catch (Exception e) {
            throw new NumberException("Error al generar el numero de factura " );
        }

        // Obtenemos el siguiente número y lo formateamos como N-AÑO

        int numero = nextNumber.recuperarSiguiente();
        em.persist(nextNumber);
        return numero + "-" + anioActual;
    }


    @Transactional
    public List<Venta> obtenerUltimasVentasCliente(Long idCliente, int limit) {
        return em.createQuery("SELECT v FROM Venta v WHERE v.cliente.id = :idCliente ORDER BY v.fechaYHora DESC", Venta.class)
                .setParameter("idCliente", idCliente)
                .setMaxResults(limit)
                .getResultList();
    }

    @Transactional
    public void registrarVenta(Venta nuevaVenta) {
        em.persist(nuevaVenta);
    }




}
