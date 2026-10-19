package com.example.deployJAVA08.service;
import com.example.deployJAVA08.entity.Product;
import com.example.deployJAVA08.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Cacheable("products")
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Cacheable("product")
    public Optional<Product> getById(Integer id) {
        return repo.findById(id);
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    public Product update(Integer id, Product p) {
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
        repo.deleteById(id);
    }
}