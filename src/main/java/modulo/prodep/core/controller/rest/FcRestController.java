package modulo.prodep.core.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modulo.prodep.core.model.Fc;
import modulo.prodep.core.model.common.RepBase;
import modulo.prodep.core.repository.FcRepository;

@RestController
@RequestMapping("api/v1/fc")
public class FcRestController {
    
    @Autowired
    private FcRepository repository;

    @PutMapping
    public ResponseEntity<String> create(@RequestBody Fc fc){
        String res = "{'id_generado': " + repository.create(fc) + "}";
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<RepBase> update(@RequestBody Fc fc){
        return ResponseEntity.ok(new RepBase(repository.update(fc)));
    }

    @GetMapping
    public ResponseEntity<List<Fc>> readAll(Pageable pageable){
        return ResponseEntity.ok(repository.readAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fc> readById (@PathVariable int id){
        return ResponseEntity.ok(repository.readById(id));
    }
}
