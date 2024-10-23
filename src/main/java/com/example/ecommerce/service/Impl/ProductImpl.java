package com.example.ecommerce.service.Impl;

import com.example.ecommerce.domain.UpSertProduct;
import com.example.ecommerce.entity.Order;
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
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public List<Product> findTop4ByIdDesc() {
        Pageable pageable = PageRequest.of(0, 4);
        return productRepository.findTop4ByIdDesc(pageable);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    @Override
    public void updateProd(Long id, UpSertProduct product) throws IOException {
        Product prod = (Product) productRepository.findById(id).orElse(null);
        prod.setProductName(product.getProductName());
        prod.setPrice(product.getPrice());
        prod.setDetails(product.getDetails());
        prod.setImageURL(generateImagePath(product.getImageURL()));
        prod.setDescription(product.getDescription());
        prod.setCategory(product.getCategory());
        productRepository.save(prod);
    }

    @Override
    public void createProd(UpSertProduct product) throws IOException {
        Product entity = new Product();
        entity.setProductName(product.getProductName());
        entity.setPrice(product.getPrice());
        entity.setDetails(product.getDetails());
        entity.setImageURL(generateImagePath(product.getImageURL()));
        entity.setDescription(product.getDescription());
        entity.setCategory(product.getCategory());
        productRepository.save(entity);
    }


    @Override
    public List<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByPriceGreaterThan(Double minPrice) {
        return productRepository.findByPriceGreaterThan(minPrice);
    }
    private String generateImagePath(MultipartFile file) throws IOException {
        File file1 = new File("C:\\Users\\ADMIN\\IdeaProjects\\demo1\\src\\main\\resources\\static\\image\\" + file.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(file1)) {
            os.write(file.getBytes());
        }

        return "/image/" + file.getOriginalFilename();
    }

    private String generateImagePath(String imageURL) {
        return "";
    }
}
