package com.example.couponsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couponsystem.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
