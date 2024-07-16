package com.example.demovinfan.service;

import com.example.demovinfan.entity.SanPham;
import com.example.demovinfan.entity.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface SanPhamService {

    Page<SanPhamChiTiet> findAll(Pageable pageable);

    void create(SanPham sanPham, SanPhamChiTiet sanPhamChiTiet);

    SanPhamChiTiet findById(Long id);

    Boolean update(SanPhamChiTiet sanPhamChiTiet);

    Boolean delete(Long id);

    String taoMaTuDong();

    Page<SanPhamChiTiet> searchProducts(String query, BigDecimal minPrice, BigDecimal maxPrice, Boolean trangThai, Pageable pageable);
}
