package franck.example.crudthymyleaf.Entities;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProductDto {

    @NotEmpty(message="the name is required")
    private String name;

    @NotEmpty(message="the brand is required")
    private String brand;

    @NotEmpty(message="the category is required")
    private String category;

    @Min(0)
    private Double price;

    @Size(min=10,message="the description should at be at least 10 characters")
//   @Size(max=2000,message="the description cannot exceed 2000 characters")
    private String  Description;

    private MultipartFile imageFileName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public MultipartFile getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(MultipartFile imageFileName) {
        this.imageFileName = imageFileName;
    }
}
