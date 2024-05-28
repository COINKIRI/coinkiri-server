package coinkiri.api.controller.post.analysis

import coinkiri.api.service.post.AnalysisService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Analysis")
@RestController
@RequestMapping("/api/v1/analysis")
class AnalysisController (
    private val analysisService: AnalysisService
){

    // 분석글 작성 API


    // 분석글 전체 조회 API


    // 분석글 상세 조회 API
}