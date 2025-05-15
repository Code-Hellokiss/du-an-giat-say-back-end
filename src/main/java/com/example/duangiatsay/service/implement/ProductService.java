package com.example.duangiatsay.service.implement;

import com.example.duangiatsay.model.Product;
import com.example.duangiatsay.repository.ProductRepository;
import com.example.duangiatsay.service.IProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với ID: " + id));
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        // Nếu client không gửi expressAvailable thì mặc định là false
        if (product.getExpressAvailable() == null) {
            product.setExpressAvailable(false);
        }

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setBasePrice(updatedProduct.getBasePrice());
        product.setImageUrl(updatedProduct.getImageUrl());
        product.setExpressAvailable(updatedProduct.getExpressAvailable() != null ? updatedProduct.getExpressAvailable() : false);
        product.setExpressPrice(updatedProduct.getExpressPrice());
        product.setCategory(updatedProduct.getCategory());

        product.preUpdate(); // Cập nhật updatedAt thủ công nếu cần

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy sản phẩm để xoá với ID: " + id);
        }
        productRepository.deleteById(id);
    }
}
