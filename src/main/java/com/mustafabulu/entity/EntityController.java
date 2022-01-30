package com.mustafabulu.entity;



import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
public class EntityController {




    @Autowired(required = true)
    private IStudentRepository iStudentRepository;

    //Create
    //http://localhost:3333/jpa/student/insert

    @GetMapping("/jpa/student/insert")
    @ResponseBody // bunu yazmazsak return kısmındaki sayfayı döndürmeye çalışır
    public String insertStudent(){
        StudentEntity studentEntity= new StudentEntity(0,"Mustafa","Bulu","Computer Engineering");
        iStudentRepository.save(studentEntity); // 0 ise insert yapıyor 0 dan farklı ise güncelleme yapıyor.
        log.info(studentEntity.getStudentid() + ""+ studentEntity.getStudentName(),studentEntity.getStudentSurName(),studentEntity.getStudentDepartment());
        return "Ogrenci Eklendi:  "+studentEntity.getStudentid() + "  " + studentEntity.getStudentName()+ " " +studentEntity.getStudentSurName() + "  "+studentEntity.getStudentDepartment();
    }

    //Find
    //http://localhost:3333/jpa/student/find/4

    @GetMapping("/jpa/student/find/{id}")
    @ResponseBody // bunu yazmazsak return kısmındaki sayfayı döndürmeye çalışır
    public String findStudent(@PathVariable(name = "id") Long studentid){
        //name kısmındaki id ile yukarıdaki kısım aynı olmak zorunda
        java.util.Optional<StudentEntity> optional = iStudentRepository.findById(studentid);
        if (optional.isPresent()){
            StudentEntity studentEntity=optional.get();
            log.info(studentEntity.toString());
            return "Ogrenci Numarası "+ studentid+ " olan ogrenci: " +studentEntity.toString();

        }else{

            return "Öğrenci Numarası:  " + studentid+ "   olan öğrenci bulunamadı";
        }


    }

    //Update
    //http://localhost:3333/jpa/student/update/2?studentName=guncelisim&studentSurname=guncelsoyadi&studentDepartment=gunceldepartman

    @GetMapping("/jpa/student/update/{id}")
    @ResponseBody
    public String updateStudent(
            @PathVariable(name = "id") Long studentid,
            @RequestParam(name = "studentName") String studentName,
            @RequestParam(name = "studentSurname") String studentSurname,
            @RequestParam(name = "studentDepartment") String studentDepartment

    ){
    java.util.Optional<StudentEntity> optional=iStudentRepository.findById(studentid);


    if (optional.isPresent()){
        StudentEntity studentEntity=optional.get();
        studentEntity.setStudentName(studentName);
        studentEntity.setStudentSurName(studentSurname);
        studentEntity.setStudentDepartment(studentDepartment);
        iStudentRepository.save(studentEntity); // save işlemini burada yapıyoruz.
        return "Guncelleme Basarili  "+studentEntity;
    }else{
        return "Guncelleme Basarisiz"+ " " + studentid +"  "+ "numarali ögrenci bulunamadi.";

    }


    }


    //Delete
    //http://localhost:3333/jpa/student/delete/2

    @GetMapping("/jpa/student/delete/{id}")
    @ResponseBody
    public String deleteStudent(@PathVariable(name = "id") Long studentid){

        java.util.Optional<StudentEntity> optional=iStudentRepository.findById(studentid);


        if (optional.isPresent()){
            StudentEntity studentEntity= optional.get();
            iStudentRepository.delete(studentEntity);

            return "Silme Basarili  " + studentEntity.toString();


        }else{
            return "Silme Basarisiz istenilen id ile eşleşen öğrenci yok";


        }

    }

    //ListAllElement
    //http://localhost:3333/jpa/student/selectList
    @GetMapping("/jpa/student/selectList")
    @ResponseBody
    public String selectList(){

        Iterable<StudentEntity> iterableList= iStudentRepository.findAll();
        List<StudentEntity> list= new ArrayList<>();


        for (StudentEntity studentEntity:iterableList) {
            list.add(studentEntity);
            log.info(studentEntity.toString());
            
        }

        return "Tum ogrencilerin listesi:  " + list ;
    }




}
