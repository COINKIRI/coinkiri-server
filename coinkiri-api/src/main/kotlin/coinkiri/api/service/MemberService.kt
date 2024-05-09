package coinkiri.api.service

import coinkiri.api.controller.member.dto.request.RegisterDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService {

    @Transactional
    fun saveMember(request: RegisterDto) {

    }
}