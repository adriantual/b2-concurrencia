package ar.unrn.tp.web;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.model.DTO.VentaDTO;
import ar.unrn.tp.model.DTO.DetalleVentaDTO;
import ar.unrn.tp.model.Venta;
import ar.unrn.tp.servicios.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;


import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/ventas")
public class VentaServiceController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private CacheService cacheService;

    // Registrar una venta
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVenta(@RequestBody VentaDTO request) {
        try {
            ventaService.realizarVenta(request.getClienteId(),request.getProductos(), request.getMedioDePagoId());
             return ResponseEntity.ok("Venta registrada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar la venta: " + e.getMessage());
        }
    }


/*
    // Consultar una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerVenta(@PathVariable Long id) {
        try {
            VentaDTO venta = ventaService.obtenerVentaPorId(id);
            return ResponseEntity.ok(venta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Consultar todas las ventas
    @GetMapping("/todas")
    public ResponseEntity<List<VentaDTO>> obtenerTodasLasVentas() {
        try {
            List<VentaDTO> ventas = ventaService.obtenerTodasLasVentas();
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/{idCliente}/ultimas-ventas")
    public ResponseEntity<List<Venta>> obtenerUltimasVentas(@PathVariable Long idCliente) {
        List<Venta> ultimasVentas = cacheService.obtenerUltimasVentas(idCliente);
        return ResponseEntity.ok(ultimasVentas);
    }

    @GetMapping("/{idCliente}/ultimas-ventas")
    public ResponseEntity<List<VentaDTO>> obtenerUltimasVentas(@PathVariable Long idCliente) {
        List<Venta> ultimasVentas = cacheService.obtenerUltimasVentas(idCliente);

        List<VentaDTO> ventasDTO = ultimasVentas.stream()
                .map(VentaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ventasDTO);
    }
*/

    // Calcular total de la compra
    @PostMapping("/calcular-total")
    public ResponseEntity<Double> calcularTotalCompra(@RequestBody VentaDTO request) {
        try {
            double total = ventaService.calcularMonto(request.getClienteId(), request.getProductos(), request.getMedioDePagoId());
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Listar todas las ventas
    @GetMapping("/listar")
    public ResponseEntity<List> listarVentas() {
        try {
            List ventas = ventaService.ventas();
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Listar ventas de un cliente específico
    @GetMapping("/cliente/{idCliente}/ventas")
    public ResponseEntity<List> listarVentasPorCliente(@PathVariable Long idCliente) {
        try {
            List ventasCliente = ventaService.ventasPorCliente(idCliente);
            return ResponseEntity.ok(ventasCliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
