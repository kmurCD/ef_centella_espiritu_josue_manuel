package ef_centella_espiritu_josue.controller;


import ef_centella_espiritu_josue.model.Empleado;
import ef_centella_espiritu_josue.service.EmpleadoService;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> listarTodosLosProductos() {
        List<Empleado> empleado = empleadoService.listarTodosLosEmpleado();
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable("id") Long id) {
        Empleado empleado = empleadoService.obtenerEmpleado(id);
        return empleado != null
                ? new ResponseEntity<>(empleado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado= empleadoService.guardarEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("id") Long id, @Valid @RequestBody Empleado empleado) {
        empleado.setId(id);
        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(empleado);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable("id") Long id) {
        empleadoService.eliminarEmpleado(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reporte01")
    public ResponseEntity<byte[]> descargarReporte01() throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reportes/report01.jrxml");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoService.listarTodosLosEmpleado());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("titulo", "Reporte de Departamentos");
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("departamentos.pdf").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(reporte);
    }


}

