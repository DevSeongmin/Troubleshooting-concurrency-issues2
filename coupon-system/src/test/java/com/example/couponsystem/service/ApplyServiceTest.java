package com.example.couponsystem.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.couponsystem.repository.CouponCountRepository;
import com.example.couponsystem.repository.CouponRepository;

@SpringBootTest
class ApplyServiceTest {

	@Autowired
	private ApplyService applyService;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CouponCountRepository couponCountRepository;

	@BeforeEach
	public void setUp() {
		couponRepository.deleteAll();
		couponCountRepository.reset();
	}

	@Test
	public void 한번만응모() {
		applyService.apply(1L);

		long count = couponRepository.count();

		assertThat(count).isEqualTo(1);

		assertEquals(1, couponRepository.count());
	}

	@Test
	public void 여러명응모() throws InterruptedException {
		int threadCount = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0 ; i < threadCount ; i++) {
			long userId = i;
			executorService.submit(() -> {
				try{
					applyService.apply(userId);
				} finally {
					latch.countDown();
				}
			});
		}
		latch.await();

		Thread.sleep(5000);

		long count = couponRepository.count();

		assertThat(count).isEqualTo(100);
	}
}