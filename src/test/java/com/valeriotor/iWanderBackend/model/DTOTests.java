package com.valeriotor.iWanderBackend.model;

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
