package com.example.demovinfan.repository;

import com.example.demovinfan.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SanPhamRepo extends JpaRepository<SanPham, Long> {
    @Query("SELECT MAX(sp.ma) FROM SanPham sp")
    String findMaxCode();





}
