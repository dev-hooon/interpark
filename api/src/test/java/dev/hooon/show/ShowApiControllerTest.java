package dev.hooon.show;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dev.hooon.common.support.ApiTestSupport;

@DisplayName("[ShowApiController API 테스트]")
@Sql("/sql/seat_dummy.sql")
class ShowApiControllerTest extends ApiTestSupport {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("[공연 아이디를 통해 API 를 호출하면 해당 공연의 예매 가능한 날짜와 회차 정보를 응답한다]")
	void getAbleBookingDateRoundInfoTest() throws Exception {
		//when
		ResultActions actions = mockMvc.perform(
			MockMvcRequestBuilders
				.get("/api/shows/1/available")
		);

		//then
		actions.andExpectAll(
			status().isOk(),
			jsonPath("$.availableDates.size()").value(2),
			jsonPath("$.availableDates[0].showDate").value("2023-10-10"),
			jsonPath("$.availableDates[0].round").value(1),
			jsonPath("$.availableDates[0].startTime").value("12:34:56"),
			jsonPath("$.availableDates[1].showDate").value("2023-10-10"),
			jsonPath("$.availableDates[1].round").value(2),
			jsonPath("$.availableDates[1].startTime").value("14:34:56")
		);
	}
}