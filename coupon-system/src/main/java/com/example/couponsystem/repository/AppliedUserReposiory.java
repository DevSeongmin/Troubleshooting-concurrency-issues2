package com.example.couponsystem.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AppliedUserReposiory {

	private final RedisTemplate<String, String> redisTemplate;

	public Long add(Long userId){
		return redisTemplate
			.opsForSet()
			.add("applied_user", userId.toString());
	}
}
