package com.example.deployJAVA08.service;

import com.example.deployJAVA08.entity.Product;
import com.example.deployJAVA08.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repo;

    @Cacheable("products")
    public List<Product> getAll() {
        List<Product> products = repo.findAll();
        logger.info("API được gọi: ProductController.getAll()");
        logger.info("Số lượng sản phẩm trong danh sách là: " + products.size());
        return products;
    }

    @Cacheable("product")
    public Product getById(Integer id) {
        Product findProductIdDB = repo.findById(id).orElseThrow(
                () -> {
                    logger.error("Không tìm thấy sản phẩm với ID: " + id);
                    return new RuntimeException("Không tìm thấy sản phẩm");
                }
        );
        logger.info("API được gọi: ProductController.getById()");
        logger.info("Lấy sản phẩm có id: " + id);
        logger.info("Tên sản phẩm : " + findProductIdDB.getTenSanPham());
        return findProductIdDB;
    }

    public Product create(Product p) {
        logger.info("API được gọi: ProductController.create()");
        logger.info("Bắt đầu thêm sản phẩm: " + p.getTenSanPham());
        Product savedProduct = repo.save(p);
        logger.info("Thêm sản phẩm " + savedProduct.getTenSanPham() + " thành công");
        return savedProduct;
    }


    public Product update(Integer id, Product p) {
        logger.info("API được gọi: ProductController.update()");
        return repo.findById(id).map(existing -> {
            existing.setTenSanPham(p.getTenSanPham());
            existing.setDanhMuc(p.getDanhMuc());
            existing.setNhaCungCap(p.getNhaCungCap());
            existing.setGia(p.getGia());
            existing.setSoLuongTon(p.getSoLuongTon());
            existing.setMoTa(p.getMoTa());
            existing.setHinhAnh(p.getHinhAnh());
            existing.setTrangThai(p.getTrangThai());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public void delete(Integer id) {
        logger.info("API được gọi: ProductController.delete()");
        Product deleteProduct = repo.findById(id).orElseThrow(
                () -> {
                    logger.error("Không tìm thấy sản phẩm với ID : " + id);
                    return new RuntimeException("Không tìm thấy sản phẩm");
                }
        );
        repo.deleteById(id);
        logger.info("Xóa sản phẩm thành công, ID: {}", id);
    }
}