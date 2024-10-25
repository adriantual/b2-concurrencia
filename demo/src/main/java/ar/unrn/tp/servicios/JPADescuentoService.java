package ar.unrn.tp.servicios;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.model.Marca;
import ar.unrn.tp.model.PromocionDeMedioDePago;
import ar.unrn.tp.model.PromocionPorProducto;
import ar.unrn.tp.model.Promociones;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JPADescuentoService implements DescuentoService {

    @Autowired
    @PersistenceContext
    private  EntityManager em;

    @Override
    @Transactional
    public void crearDescuentoSobreTotal(String marcaTarjeta, String fechaDesde, String fechaHasta, double porcentaje) {

        try {

            Promociones descuento = new PromocionDeMedioDePago(LocalDate.parse(fechaDesde), LocalDate.parse(fechaHasta), porcentaje, marcaTarjeta);
            em.persist(descuento);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void crearDescuentoProducto(Long idMarcaProducto, String fechaDesde, String fechaHasta, double porcentaje) {

        try {

            Marca nuevaMarca= em.find(Marca.class, idMarcaProducto);
            Promociones descuento = new PromocionPorProducto(LocalDate.parse(fechaDesde), LocalDate.parse(fechaHasta), porcentaje,nuevaMarca);
            em.persist(descuento);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public List<Promociones> listarDescuentos() {
        String jpql = "SELECT p FROM Promociones p";
        return em.createQuery(jpql, Promociones.class).getResultList();
    }
}
