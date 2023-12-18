package ef_centella_espiritu_josue.service;


import ef_centella_espiritu_josue.model.Empleado;

import java.util.List;

public interface EmpleadoService {
    Empleado guardarEmpleado(Empleado empleado);

    Empleado actualizarEmpleado(Empleado empleado);

    void eliminarEmpleado(Long id);

    Empleado obtenerEmpleado(Long id);

    List<Empleado> listarTodosLosEmpleado();
}
