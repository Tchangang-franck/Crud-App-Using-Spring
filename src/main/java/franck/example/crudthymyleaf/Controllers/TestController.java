package franck.example.crudthymyleaf.Controllers;

import franck.example.crudthymyleaf.Entities.Product;
import franck.example.crudthymyleaf.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/product")
public class TestController {

    @Autowired
    ProductRepository repo;
    @GetMapping(value="/test")
    public List<Product> getAllProduct(){
        return  repo.findAll();
    }

}