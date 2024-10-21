package com.example.couponsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CouponCountRepository {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public Long increment() {
		return redisTemplate
			.opsForValue().
			increment("coupon_count");
	}

	public void reset() {
		redisTemplate.delete("coupon_count");
		redisTemplate.delete("applied_user");
	}
}
