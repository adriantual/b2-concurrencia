package ar.unrn.tp.api;

import ar.unrn.tp.model.Promociones;

import java.time.LocalDate;
import java.util.List;

public interface DescuentoService {

    // validar que las fechas no se superpongan
    void crearDescuentoSobreTotal(String marcaTarjeta, String fechaDesde,
                                  String fechaHasta, double porcentaje);

    // validar que las fechas no se superpongan
    void crearDescuentoProducto(Long idMarcaProducto, String fechaDesde, String
            fechaHasta, double porcentaje);


    List<Promociones> listarDescuentos();
}
