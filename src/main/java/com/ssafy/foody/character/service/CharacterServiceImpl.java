package com.ssafy.foody.character.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.foody.character.domain.FoodyCharacter;
import com.ssafy.foody.character.dto.CharacterResponse;
import com.ssafy.foody.character.mapper.CharacterMapper;
import com.ssafy.foody.report.mapper.ReportMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

	private final CharacterMapper characterMapper;
	private final ReportMapper reportMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<CharacterResponse> findAllCharacters() {
		return characterMapper.findAllCharacters().stream()
				.map(CharacterResponse::new)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CharacterResponse> findMyCharacters(String userId) {
		// 내가 획득한 캐릭터 ID 목록 조회
		List<Integer> myCharacterIds = reportMapper.findDistinctCharacterIdsByUserId(userId);
		
		// 기본 캐릭터 (1, 2) 추가 및 중복 제거
		Set<Integer> uniqueIds = new HashSet<>();
		if (myCharacterIds != null) {
			uniqueIds.addAll(myCharacterIds);
		}
		uniqueIds.add(1);
		uniqueIds.add(2);
		
		// 캐릭터 정보 조회
		List<FoodyCharacter> characters = characterMapper.findCharactersByIds(new ArrayList<>(uniqueIds));
		
		// 변환 및 반환
		return characters.stream()
				.map(CharacterResponse::new)
				.toList();
	}

}
