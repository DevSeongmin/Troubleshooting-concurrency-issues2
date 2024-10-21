package com.example.couponsystem.service;

import org.springframework.stereotype.Service;

import com.example.couponsystem.domain.Coupon;
import com.example.couponsystem.producer.CouponCreateProducer;
import com.example.couponsystem.repository.AppliedUserReposiory;
import com.example.couponsystem.repository.CouponCountRepository;
import com.example.couponsystem.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyService {

	private final CouponCountRepository couponCountRepository;

	private final CouponCreateProducer couponCreateProducer;

	private final AppliedUserReposiory appliedUserReposiory;

	public void apply(Long userId) {
		Long apply = appliedUserReposiory.add(userId);

		if (apply != 1) {
			throw new IllegalArgumentException("이미 응모한 사용자 입니다.");
		}


		long count = couponCountRepository.increment();

		if (count > 100) {
			throw new IllegalArgumentException("쿠폰은 최대 100개 까지만 발급할 수 있습니다.");
		}

		couponCreateProducer.create(userId);
	}
}
