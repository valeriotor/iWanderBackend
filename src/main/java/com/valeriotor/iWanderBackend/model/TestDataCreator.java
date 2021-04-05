package com.valeriotor.iWanderBackend.model;

import com.google.common.collect.Lists;
import com.valeriotor.iWanderBackend.datahandler.repos.TravelPlanRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.core.LocationTime;
import com.valeriotor.iWanderBackend.model.core.TravelPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataCreator {

    private static AppUser ADMIN;

    public static AppUser getADMIN() {
        return ADMIN;
    }

    private final UserDetailsRepo userDetailsRepo;
    private final TravelPlanRepo travelPlanRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TestDataCreator(UserDetailsRepo userDetailsRepo, TravelPlanRepo travelPlanRepo, PasswordEncoder passwordEncoder) {
        this.userDetailsRepo = userDetailsRepo;
        this.travelPlanRepo = travelPlanRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        var valeriotor = new AppUser("valeriotor", passwordEncoder.encode("password"));
        var alessandrotie = new AppUser("alessandrotie", passwordEncoder.encode("password"));
        var alessandrover = new AppUser("alessandrover", passwordEncoder.encode("password"));
        var cristianalf = new AppUser("cristianalf", passwordEncoder.encode("password"));
        var fabiocur = new AppUser("fabiocur", passwordEncoder.encode("password"));

        valeriotor.setName("Valerio");
        valeriotor.setSurname("Torsiello");
        alessandrotie.setName("Alessandro");
        alessandrotie.setSurname("Tierno");
        alessandrover.setName("Alessandro");
        alessandrover.setSurname("Verosimile");
        cristianalf.setName("Cristian");
        cristianalf.setSurname("Alfano");
        fabiocur.setName("Fabio");
        fabiocur.setSurname("Curci");

        List<Day> daysRoma = new ArrayList<>();
        List<Day> daysZurigo = new ArrayList<>();
        List<Day> daysSanFrancisco = new ArrayList<>();
        List<Day> daysParigi = new ArrayList<>();
        List<Day> daysGenova = new ArrayList<>();
        List<Day> daysCasablanca = new ArrayList<>();

        List<LocationTime> day4LTs = new ArrayList<>();
        List<LocationTime> day6LTs = new ArrayList<>();

        var planRoma = new TravelPlan(valeriotor, 0, "Roma", VisibilityType.PUBLIC, daysRoma);
        var planZurigo = new TravelPlan(valeriotor, 0, "Zurigo", VisibilityType.PUBLIC, daysZurigo);
        var planSanFrancisco = new TravelPlan(valeriotor, 0, "San Francisco", VisibilityType.PUBLIC, daysSanFrancisco);
        var planParigi = new TravelPlan(alessandrotie, 0, "Parigi", VisibilityType.PUBLIC, daysParigi);
        var planGenova = new TravelPlan(alessandrotie, 0, "Genova", VisibilityType.PUBLIC, daysGenova);
        var planCasablanca = new TravelPlan(alessandrover, 0, "Casablanca", VisibilityType.PUBLIC, daysCasablanca);

        var day1Roma = new Day(0, LocalDate.of(2021, 9, 1), "dummy", planRoma, day6LTs);
        var day1Zurigo = new Day(0, LocalDate.of(2021, 10, 1), "dummy", planZurigo, new ArrayList<>());
        var day1SanFrancisco = new Day(0, LocalDate.of(2021, 10, 1), "dummy", planSanFrancisco, day4LTs);
        var day2SanFrancisco = new Day(0, LocalDate.of(2021, 10, 2), "dummy", planSanFrancisco, new ArrayList<>());
        var day3SanFrancisco = new Day(0, LocalDate.of(2021, 10, 3), "dummy", planSanFrancisco, new ArrayList<>());
        var day4SanFrancisco = new Day(0, LocalDate.of(2021, 10, 4), "dummy", planSanFrancisco, new ArrayList<>());

        var pantheon = new LocationTime(0, LocalTime.of(23, 44), 0, 0, "Pantheon", "dummy", day1Roma);
        var siliconValley = new LocationTime(0, LocalTime.of(23, 44), 0, 0, "Silicon Valley", "dummy", day1SanFrancisco);

        day4LTs.add(siliconValley);
        day6LTs.add(pantheon);

        planRoma.addDay(day1Roma);
        planZurigo.addDay(day1Zurigo);
        planSanFrancisco.addDay(day1SanFrancisco);
        planSanFrancisco.addDay(day2SanFrancisco);
        planSanFrancisco.addDay(day3SanFrancisco);
        planSanFrancisco.addDay(day4SanFrancisco);

        userDetailsRepo.saveAll(Lists.newArrayList(valeriotor, alessandrotie, alessandrover, cristianalf, fabiocur));
        travelPlanRepo.saveAll(Lists.newArrayList(planRoma, planZurigo, planSanFrancisco, planParigi, planGenova, planCasablanca));

        ADMIN = valeriotor;
    }

}
