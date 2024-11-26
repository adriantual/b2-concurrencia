package ar.unrn.tp.web;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.model.DTO.VentaDTO;
import ar.unrn.tp.model.DTO.VentaFrontEndDTO;
import ar.unrn.tp.model.Venta;
import ar.unrn.tp.servicios.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/ventas")
public class VentaServiceController {

    @Autowired
    private ComprasService ultimasComprasService;
    @Autowired
    private VentaService ventaService;



    @GetMapping("/ultimas/{idCliente}")
    public ResponseEntity<List<VentaFrontEndDTO>> obtenerUltimasCompras(@PathVariable Long idCliente) {
        List<Venta> ultimasVentas = ultimasComprasService.obtenerUltimasCompras(idCliente);
        List<VentaFrontEndDTO> ventasDTO = ultimasVentas.stream()
                .map(VentaFrontEndDTO::convertirDesdeVentas) // Convierte cada Venta en un VentaDTO
                .collect(Collectors.toList()); // Colecta el resultado en una lista

        return ResponseEntity.ok(ventasDTO);
    }

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
    public ResponseEntity<List<VentaFrontEndDTO>> listarVentasPorCliente(@PathVariable Long idCliente) {
        try {
            List<Venta> ventasCliente = ventaService.ventasPorCliente(idCliente);
            List<VentaFrontEndDTO> ventasDTO = ventasCliente.stream()
                    .map(VentaFrontEndDTO::convertirDesdeVentas) // Convierte cada Venta en un VentaDTO
                    .collect(Collectors.toList()); // Colecta el resultado en una lista

            return ResponseEntity.ok(ventasDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVenta(@RequestBody VentaDTO request) {
        try {
            //ventaService.realizarVenta(request.getClienteId(),request.getProductos(), request.getMedioDePagoId());
            ultimasComprasService.registrarNuevaCompra(request.getClienteId(),request.getProductos(), request.getMedioDePagoId());
            return ResponseEntity.ok("Venta registrada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar la venta: " + e.getMessage());
        }
    }




}
