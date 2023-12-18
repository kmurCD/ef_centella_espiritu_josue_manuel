package ef_centella_espiritu_josue.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name="tbl_departamento")
@Entity
@Getter
@Setter

public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String Descripcion;

    @OneToMany(mappedBy = "departamento")
    @JsonIgnore
    private List<Empleado> empleados;

}
