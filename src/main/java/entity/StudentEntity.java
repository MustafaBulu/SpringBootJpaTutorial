package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity // entity classımızı belirtmek için kullandığımız annotation
@Table(name= "student") // tablomuzu isimlendirdik.
@Data //getter setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity implements Serializable {
    private static final long serialVersionUID= 1093298;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentid") // column adını studentid olarak oluşturur.Deafault olarak değişken adı ile oluşturur.
    private long studentid;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "studentSurName")
    private String studentSurName;

    @Column(name = "studenDepartment")
    private String studentDepartment;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;






}
