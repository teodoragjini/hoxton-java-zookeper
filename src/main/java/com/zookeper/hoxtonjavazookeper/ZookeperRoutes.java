package com.zookeper.hoxtonjavazookeper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import antlr.collections.List;

@RestController
public class ZookeperRoutes {
    @Autowired
    private ZooAnimalRepository zooAnimalRepository;

    @GetMapping("/animals")
    public List<ZooAnimal> getAllAnimals(){
        return zooAnimalRepository.findAll();
    }

    @PostMapping("/animals")
    public ZooAnimal createNewAnimal(@RequestBody ZooAnimal zooAnimalData){
        return zooAnimalRepository.save(zooAnimalData);
    }

    @DeleteMapping("/animals/{id}")
    public String deleteAnimal(@PathVariable Integer id){
        zooAnimalRepository.deleteById(id);
        return "Successfully Deleted";
    }

    @PatchMapping("/animals/{id}")
    public ZooAnimal updateAnimal(@RequestBody ZooAnimal zooAnimalData, @PathVariable Integer id){
        zooAnimalData.id = id;
        return zooAnimalRepository.save(zooAnimalData);
    }
}


@Entity
class ZooAnimal {
 @Id
 @GeneratedValue
 public Integer id;
 public String name;
 public  String species;
 public String origin;
 public Boolean isHungry;

 public ZooAnimal(){

 }
}

interface ZooAnimalRepository extends JpaRepository<ZooAnimal, Integer>{

}