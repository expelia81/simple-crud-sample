package com.example.samplecrud.etc.user_resolver;

import java.util.Random;

public class RandomNameGenerator {
	private static String[] names = {
					"참새", "독수리", "까치", "비둘기", "오리", "백로", "두루미", "갈매기", "물총새", "제비",
					"황새", "부엉이", "올빼미", "솔개", "매", "꿩", "꾀꼬리", "멧비둘기", "종달새", "까마귀",
					"까마귀", "홍학", "고니", "꿀벌새", "물까마귀", "뻐꾸기", "딱따구리", "쏙독새", "도요새", "왕새매",
					"비오리", "흰죽지", "청둥오리", "뿔논병아리", "멧새", "물닭", "겨울철새", "봄도래새", "해오라기", "쇠박새",
					"큰기러기", "쇠백로", "노랑부리저어새", "황조롱이", "종다리", "박새", "흰눈썹황금새", "바다제비", "큰재갈매기", "두견새"
};

public static String getRandomBirdName() {
		Random random = new Random();
		return names[random.nextInt(names.length)];
	}
}
