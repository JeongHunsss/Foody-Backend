package com.ssafy.foody.admin.conroller;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.foody.admin.dto.UpdateRoleRequest;
import com.ssafy.foody.admin.service.AdminService;
import com.ssafy.foody.food.dto.FoodRequest;
import com.ssafy.foody.food.service.FoodService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	private final AdminService adminService;
	
	/**
	 * 권한 수정 (관리자만 접근 가능)
	 * PATCH /admin
	 * 요청 Body(raw) 
	 * userID : 권한을 변경할 아이디
	 * role : 지정하려는 권한
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping
	public ResponseEntity<String> updateUserRole(
			@RequestBody UpdateRoleRequest request) {
		adminService.updateUserRole(request.getUserId(), request.getRole());
		return ResponseEntity.ok("권한이 성공적으로 수정되었습니다");
		
	}
	
	/**
	 * Foods 테이블 음식 등록 (관리자만 접근 가능)
	 * Post /admin
	 * 요청 Body(raw)
	 * code, name, category, standard, kcal, carb, protein, fat, sugar, natrium 
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<String> addFood(@Valid @RequestBody FoodRequest request) {
		adminService.addFood(request);
		return ResponseEntity.ok("음식이 성공적으로 등록되었습니다");
	}
	/**
	 * 음식 삭제
	 * DELETE /admin/{code}
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{code}")
	public ResponseEntity<String> deleteFood(@PathVariable String code) {
		adminService.deleteFood(code);
		return ResponseEntity.ok("음식이 성공적으로 삭제되었습니다");
	}
}
