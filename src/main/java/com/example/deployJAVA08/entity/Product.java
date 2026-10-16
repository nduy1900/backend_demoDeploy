package com.example.deployJAVA08.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_san_pham")
    private Integer maSanPham;

    @Column(name = "ten_san_pham", nullable = false, length = 200)
    private String tenSanPham;

    @Column(name = "danh_muc", length = 100)
    private String danhMuc;

    @Column(name = "nha_cung_cap", length = 200)
    private String nhaCungCap;

    @Column(name = "gia", nullable = false, precision = 18, scale = 2)
    private BigDecimal gia;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "mo_ta", length = 1000)
    private String moTa;

    @Column(name = "hinh_anh", length = 500)
    private String hinhAnh;

    @Column(name = "trang_thai")
    private Byte trangThai;

    @Column(name = "ngay_tao", insertable = false, updatable = false)
    private LocalDateTime ngayTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;


}