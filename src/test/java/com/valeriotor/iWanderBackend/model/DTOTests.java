package com.valeriotor.iWanderBackend.model;

import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;
import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.core.TravelPlan;
import com.valeriotor.iWanderBackend.model.dto.DayDTO;
import com.valeriotor.iWanderBackend.model.dto.LocationTimeDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanDTO;
import org.assertj.core.util.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DTOTests {
/*
    private DozerBeanMapper mapper;

    public DTOTests() {
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));

    }

    @Test
    public void testMapper() {
        List<DayDTO> days = Lists.newArrayList();
        TravelPlanDTO planDTO = new TravelPlanDTO("Geneva", VisibilityType.PUBLIC, LocalDate.now(), days);
        List<LocationTimeDTO> locationTimeDTOS = new ArrayList<>();
        DayDTO dayDTO = new DayDTO(LocalDate.now(), "dummy", planDTO, locationTimeDTOS);
        DayDTO dayDTO1 = new DayDTO(LocalDate.now().plusDays(1), "dummy", planDTO, new ArrayList<>());
        DayDTO dayDTO2 = new DayDTO(LocalDate.now().plusDays(2), "dummy", planDTO, new ArrayList<>());
        DayDTO dayDTO3 = new DayDTO(LocalDate.now().plusDays(3), "dummy", planDTO, new ArrayList<>());
        days.add(dayDTO);
        days.add(dayDTO1);
        days.add(dayDTO2);
        days.add(dayDTO3);
        LocationTimeDTO locationTimeDTO = new LocationTimeDTO(LocalTime.of(23, 22), 0, 0, "CERN", "test", dayDTO);
        locationTimeDTOS.add(locationTimeDTO);
        System.out.println("\n\n\n\n");
        TravelPlan plan = mapper.map(planDTO, TravelPlan.class);
        System.out.println(plan);
        Day day = mapper.map(dayDTO, Day.class);
        System.out.println(day);
        System.out.println("\n\n\n\n");
    }
*/
}
