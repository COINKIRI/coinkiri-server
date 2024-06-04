package coinkiri.core.domain.talk.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TalkRepositoryImpl {

	private final JPAQueryFactory queryFactory;


}
