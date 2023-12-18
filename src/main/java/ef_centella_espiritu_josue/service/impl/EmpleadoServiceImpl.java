package ef_centella_espiritu_josue.service.impl;


import ef_centella_espiritu_josue.model.Empleado;
import ef_centella_espiritu_josue.repository.EmpleadoRespository;
import ef_centella_espiritu_josue.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRespository empleadoRespository;

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        validarEmpleado(empleado);
        return empleadoRespository.save(empleado);
    }
    @Override
    public Empleado actualizarEmpleado(Empleado empleado) {
        validarEmpleado(empleado);
        if (!empleadoRespository.existsById(empleado.getId())) {
            throw new IllegalArgumentException("El empleado con el ID especificado no existe.");
        }
        return empleadoRespository.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        if (!empleadoRespository.existsById(id)) {
            throw new IllegalArgumentException("El empleado con el ID especificado no existe.");
        }
        empleadoRespository.deleteById(id);
    }

    @Override
    public Empleado obtenerEmpleado(Long id) {
        Optional<Empleado> empleado = empleadoRespository.findById(id);
        if (empleado.isEmpty()) {
            throw new IllegalArgumentException("El empleado con el ID especificado no existe.");
        }
        return empleado.get();
    }

    @Override
    public List<Empleado> listarTodosLosEmpleado() {
        return (List<Empleado>) empleadoRespository.findAll();
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío.");
        }
        if (empleado.getFeci() == null || empleado.getFeci().toString().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        if (empleado.getSalario() == null || empleado.getSalario().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }
        if (empleado.getDepartamento()== null )  {
            throw new IllegalArgumentException("El departamento es obligatorio para el empleado");
        }

    }
}
