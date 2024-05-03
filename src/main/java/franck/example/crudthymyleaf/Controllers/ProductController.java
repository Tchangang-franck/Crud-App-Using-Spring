package franck.example.crudthymyleaf.Controllers;

import franck.example.crudthymyleaf.Entities.Product;
import franck.example.crudthymyleaf.Entities.ProductDto;
import franck.example.crudthymyleaf.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Path;
import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository repo;

    @GetMapping("")
    public String showListProducts(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";

    }

    @GetMapping("/create")
    public String showCreateProduct(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/createProducts";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {
        if (productDto.getImageFileName()==null) {
            result.addError(new FieldError("productDto", "imageFileName", "the image file is required"));
        }


        if (result.hasErrors()) {

            return "products/createProducts";
        }

        // save imageFile
        MultipartFile image= productDto.getImageFileName();
        Date createdAt = new Date();
        String storeFileName=createdAt.getTime()+ "_"+ image.getOriginalFilename();
        try{
            String uploadDir="public/images";
            java.nio.file.Path uploadPath= Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream=image.getInputStream()){
                Files.copy(inputStream,Paths.get(uploadDir+storeFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (Exception ex){
            System.out.println("Exception" + ex.getMessage());
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storeFileName);

        repo.save(product);
        return "redirect:/products";
    }

    @GetMapping("edit")
    public String showEditPage(Model model, @RequestParam int id){
        try{
            Product product=repo.findById(id).get();
            model.addAttribute("product",product);
            ProductDto productDto= new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            model.addAttribute("productDto",productDto);
        }
        catch(Exception ex){
            System.out.println("Exception"+ ex.getMessage());
            return  "redirect:/product";
        }
        return  "products/editProducts";
    }

    @PostMapping("edit")
    public String updateProduct(@RequestParam int id, @Valid @ModelAttribute ProductDto productDto,BindingResult result,Model model){

        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if(result.hasErrors()){
                return "products/editProducts";
            }


            if(!productDto.getImageFileName().isEmpty()){
                //delete old image
                String uploadDir="public/images";
                java.nio.file.Path oldImagePath= Paths.get(uploadDir+product.getImageFileName());

                try{
                    Files.delete(oldImagePath);
                }
                catch (Exception ex){
                    System.out.print("Exception"+ex.getMessage());
                }
                //save new image
                MultipartFile image= productDto.getImageFileName();
                Date createdAt = new Date();
                String storeFileName=createdAt.getTime()+ "_"+ image.getOriginalFilename();
                    try(InputStream inputStream=image.getInputStream()){
                        Files.copy(inputStream,Paths.get(uploadDir+storeFileName),
                                StandardCopyOption.REPLACE_EXISTING);
                    }

                product.setImageFileName(storeFileName);
                product.setName(productDto.getName());
                product.setBrand(productDto.getBrand());
                product.setCategory(productDto.getCategory());
                product.setPrice(productDto.getPrice());
                product.setDescription(productDto.getDescription());
                repo.save(product);

            }
        }
        catch(Exception ex){
            System.out.print("Exception"+ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("delete")
    public String deleteProduct(@RequestParam int id){
        try{
            Product product= repo.findById(id).get();

            //delete image in folder image

            String uploadDir="public/images";
            java.nio.file.Path  oldImagePath= Paths.get(uploadDir+product.getImageFileName());

            try{
                Files.delete(oldImagePath);
            }
            catch (Exception ex){
                System.out.print("Exception"+ex.getMessage());
            }
            //delete the product
            repo.delete(product);
        }
        catch (Exception ex){
            System.out.println("Exception"+ ex.getMessage());
        }
        return "redirect:/products";
    }



}
