package ef_centella_espiritu_josue.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;



@Table(name = "tbl_empleado")
@Getter
@Setter
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "salario")
    private BigDecimal salario;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date feci;


    @ManyToOne(fetch = FetchType.EAGER)
    private Departamento departamento;
}
