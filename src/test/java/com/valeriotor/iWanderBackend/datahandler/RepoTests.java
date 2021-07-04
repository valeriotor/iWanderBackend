package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.datahandler.repos.DayRepo;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.dto.DayDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
public class RepoTests {

    private final DayRepo dayRepo;
    private final TravelPlanDataHandler travelPlanDataHandler;

    @Autowired
    public RepoTests(DayRepo dayRepo, TravelPlanDataHandler travelPlanDataHandler) {
        this.dayRepo = dayRepo;
        this.travelPlanDataHandler = travelPlanDataHandler;
    }

    @Test
    public void testOrderBy() {
        assert dayRepo != null;
        List<DayDTO> days = new ArrayList<>();
        TravelPlanDTO planDTO = new TravelPlanDTO(0, "TestTravel", VisibilityType.PUBLIC, LocalDate.of(2000, 2, 25), days);
        days.add(new DayDTO(LocalDate.of(2000, 2, 25), "d", planDTO, new ArrayList<>()));
        days.add(new DayDTO(LocalDate.of(2000, 2, 23), "d", planDTO, new ArrayList<>()));
        days.add(new DayDTO(LocalDate.of(2000, 2, 24), "d", planDTO, new ArrayList<>()));
        days.add(new DayDTO(LocalDate.of(2000, 2, 22), "d", planDTO, new ArrayList<>()));
        long id = travelPlanDataHandler.addTravel(planDTO);
        List<Day> allByTravelPlan_id = dayRepo.findAllByTravelPlan_Id(id, PageRequest.of(0, 20));
        List<Day> allByTravelPlan_id_Ordered = dayRepo.findAllByTravelPlan_IdOrderByDate(id, PageRequest.of(0, 20));
        allByTravelPlan_id.sort(null);
        assertArrayEquals(allByTravelPlan_id.stream().mapToLong(Day::getId).toArray(), allByTravelPlan_id_Ordered.stream().mapToLong(Day::getId).toArray());
        travelPlanDataHandler.deleteTravel(id);
    }
}
