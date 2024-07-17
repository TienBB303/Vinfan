package com.example.demovinfan.service.Implements;


import com.example.demovinfan.entity.SanPham;
import com.example.demovinfan.entity.SanPhamChiTiet;
import com.example.demovinfan.repository.SPCTRepo;
import com.example.demovinfan.repository.SanPhamRepo;
import com.example.demovinfan.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class SanPhamImp implements SanPhamService {

    @Autowired
    private SanPhamRepo sanPhamRepo;
    @Autowired
    private SPCTRepo spctRepo;

    @Override
    public Page<SanPhamChiTiet> findAll(Pageable pageable) {
        return spctRepo.findAll(pageable);
    }

    @Transactional
    @Override
    public void create(SanPham sanPham, SanPhamChiTiet sanPhamChiTiet) {
        sanPham = sanPhamRepo.save(sanPham);
        sanPhamChiTiet.setSanPham(sanPham);
        spctRepo.save(sanPhamChiTiet);
    }

    @Override
    public SanPhamChiTiet findById(Long id) {
        return spctRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean update(SanPhamChiTiet sanPhamChiTiet) {
        if (spctRepo.existsById(sanPhamChiTiet.getId())) {
            spctRepo.save(sanPhamChiTiet);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (spctRepo.existsById(id)) {
            spctRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public String taoMaTuDong() {
        String lastCode = sanPhamRepo.findMaxCode();
        int nextCode = 1;
        if (lastCode != null && !lastCode.isEmpty()) {
            try {
                // Lấy phần số từ mã cuối cùng và tăng nó lên 1
                nextCode = Integer.parseInt(lastCode.replaceAll("[^0-9]", "")) + 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        // Trả về mã mới dưới dạng "SP" cộng với số đã tăng, định dạng thành 3 chữ số
        return String.format("SP%03d", nextCode);
    }


    @Override
    public Page<SanPhamChiTiet> searchProducts(String query, BigDecimal minPrice, BigDecimal maxPrice, Boolean trangThai, Pageable pageable) {
        return spctRepo.searchProducts(query, minPrice, maxPrice, trangThai, pageable);
    }



}
