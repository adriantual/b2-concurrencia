package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.exception.CustomConcurrencyException;
import ar.unrn.tp.model.*;

import ar.unrn.tp.model.DTO.CategoriaDTO;
import ar.unrn.tp.model.DTO.MarcaDTO;
import ar.unrn.tp.model.DTO.ProductoDTO2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JPAProductoService implements ProductoService {
    @PersistenceContext
    @Autowired
    private EntityManager em;



    @Override
    @Transactional
    public void crearProducto (String codigo, String descripcion, double precio, Long categoria, Long marca){


        try {
            Categoria nuevaCategoria= em.find(Categoria.class, categoria);
            Marca nuevaMarca= em.find(Marca.class, marca);
            Producto producto = new Producto(Integer.parseInt(codigo), descripcion, nuevaCategoria, precio,nuevaMarca);
            em.persist(producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public void modificarProducto(Long idProducto, String codigo, String descripcion,  double precio,  Long categoria, Long marca,Long version) {
        try {

            Categoria nuevaCategoria= em.find(Categoria.class, categoria);
            Marca nuevaMarca= em.find(Marca.class, marca);
            Producto producto = em.getReference(Producto.class, idProducto);

            if(!Objects.equals(producto.getVersion(), version))
                throw new OptimisticLockException();

            producto.setCodigo(Integer.parseInt(codigo));
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setCategoria(nuevaCategoria);
            producto.setMarca(nuevaMarca);

            em.persist(producto);


        } catch (OptimisticLockException e) {
            throw new CustomConcurrencyException("El producto ha sido modificado por otro usuario. Por favor, actualice la página y vuelva a intentarlo.");
        } catch (Exception e) {
            throw new RuntimeException("Error al modificar el producto: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public List listarProductos() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();

    }

    @Override
    @Transactional
    public List<Producto> buscarProductos(List<Long> idsProductos) {
        return em.createQuery("SELECT p FROM Producto p WHERE p.id IN :id", Producto.class)
                .setParameter("id", idsProductos)
                .getResultList();
    }

    @Override
    @Transactional
    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        return categorias.stream()
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNombre()))
                .toList();
    }

    @Override
    @Transactional
    public List<MarcaDTO> listarMarcas() {
        List<Marca> marcas = em.createQuery("SELECT m FROM Marca m", Marca.class).getResultList();
        return marcas.stream()
                .map(marca -> new MarcaDTO(marca.getId(), marca.getNombre()))
                .toList();
    }

    @Override
    @Transactional
    public ProductoDTO2 buscarProductoPorId(Long idProducto) {
        Producto producto = em.find(Producto.class, idProducto);

        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        // Convertimos Producto a ProductoDTO
        return new ProductoDTO2(
                producto.getId(),
                producto.getCodigo(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCategoria().getId(),
                producto.getCategoria().getNombre(),
                producto.getMarca().getId(),
                producto.getMarca().getNombre(),
                producto.getPorcentajeDeDescuento(),
                producto.getVersion()
        );
    }

}
