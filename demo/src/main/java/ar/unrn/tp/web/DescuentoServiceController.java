package ar.unrn.tp.web;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.model.DTO.DescuentoMedioDePagoDTO;
import ar.unrn.tp.model.DTO.DescuentoProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/descuentos")
public class DescuentoServiceController {

    @Autowired
    private DescuentoService descuentoService;

    // Crear descuento sobre medio de pago
    @PostMapping("/crear/medioDePago")
    public ResponseEntity<String> crearDescuentoMedioDePago(@RequestBody DescuentoMedioDePagoDTO request) {
        try {
            descuentoService.crearDescuentoSobreTotal(request.getMarcaTarjeta(), request.getFechaDesde(), request.getFechaHasta(), request.getPorcentaje());
            return ResponseEntity.ok("Descuento sobre medio de pago creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear descuento sobre medio de pago: " + e.getMessage());
        }
    }

    // Crear descuento sobre producto
    @PostMapping("/crear/producto")
    public ResponseEntity<String> crearDescuentoProducto(@RequestBody DescuentoProductoDTO request) {
        try {
            descuentoService.crearDescuentoProducto(request.getMarcaProducto(), request.getFechaDesde(), request.getFechaHasta(), request.getPorcentaje());
            return ResponseEntity.ok("Descuento sobre producto creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear descuento sobre producto: " + e.getMessage());
        }
    }


    // Listar todos los descuentos
    @GetMapping("/listar")
    public ResponseEntity<?> listarDescuentos() {
        try {
            var descuentos = descuentoService.listarDescuentos();
            return ResponseEntity.ok(descuentos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al listar descuentos: " + e.getMessage());
        }
    }



}