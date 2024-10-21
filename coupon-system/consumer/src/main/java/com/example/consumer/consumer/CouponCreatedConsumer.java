package com.example.consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.consumer.domain.Coupon;
import com.example.consumer.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CouponCreatedConsumer {

	private final CouponRepository couponRepository;

	@KafkaListener(topics = "coupon_create", groupId = "group_1")
	public void listener(Long userId) {
		System.out.println("userId = " + userId);
		couponRepository.save(new Coupon(userId));
	}
}
