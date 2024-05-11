package com.example.ecommerce.service.Impl;

import com.example.ecommerce.domain.UpSertProduct;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@Transactional
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    @Override
    public Product getProductByID(Long id) {
        return (Product) productRepository.findById(id).orElse(null);
    }
    @Override
    public void saveProduct(Product product){
         productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public void updateProduct(UpSertProduct product) throws IOException{
        Product entity = new Product();
        entity.setProductName(product.getProductName());
        entity.setDescription(product.getDescription());
        entity.setImageURL(generateImagePath(product.getImageURL()));
        entity.setPrice(product.getPrice());
        entity.setDetails(product.getDetails());
        entity.setCategory(product.getCategory());
        productRepository.save(entity);
    }
    private String generateImagePath(MultipartFile file) throws IOException {
        String fileExtension = getFileExtension(file.getOriginalFilename());

        File file1 = new File("C:\\Users\\ADMIN\\IdeaProjects\\demo1\\src\\main\\resources\\static\\image\\" + file.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(file1)) {
            os.write(file.getBytes());
        }

        return file1.getAbsolutePath();
    }
    private String getFileExtension(String originalFilename) {
        return "";
    }

    public List<Product> findTop4ByIdDesc() {
        Pageable pageable = PageRequest.of(0, 4);
        return productRepository.findTop4ByIdDesc(pageable);
    }

}
