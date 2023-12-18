package ef_centella_espiritu_josue.repository;
import ef_centella_espiritu_josue.model.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRespository extends CrudRepository<Empleado,Long> {
}
