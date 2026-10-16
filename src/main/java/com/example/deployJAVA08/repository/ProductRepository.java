package com.example.deployJAVA08.repository;


import com.example.deployJAVA08.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByDanhMuc(String danhMuc);

    List<Product> findByNhaCungCap(String nhaCungCap);

    List<Product> findByTrangThai(Byte trangThai);

    List<Product> findByTenSanPhamContainingIgnoreCase(String keyword);
}