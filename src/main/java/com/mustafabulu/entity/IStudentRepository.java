package com.mustafabulu.entity;

import org.springframework.data.repository.CrudRepository;

public interface IStudentRepository extends CrudRepository<StudentEntity,Long> {
    //CrudRepository yi extend ediyoruz. Entity olarak daha önceden oluşturduğumuz StudentEntity i yazıyoruz
    //Entity mizdeki id bileşenimiz (studentid) long olduğu için Long yazıyoruz.

}
