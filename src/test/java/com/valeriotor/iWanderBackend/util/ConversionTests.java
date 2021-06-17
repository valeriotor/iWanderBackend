package com.valeriotor.iWanderBackend.util;

import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.core.DayRoute;
import com.valeriotor.iWanderBackend.model.dto.CoordinateDTO;
import com.valeriotor.iWanderBackend.model.dto.DayDTO;
import com.valeriotor.iWanderBackend.model.dto.DayRouteDTO;
import org.assertj.core.util.Lists;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ConversionTests {

    private DozerBeanMapper mapper;

    public ConversionTests() {
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Lists.newArrayList(/*"dozerJdk8Converters.xml",*/ "dozer_configuration.xml"));
        mapper.getMappingFiles().forEach(System.out::println);
    }

    @Test
    public void testRouteByteConversion() {
        List<CoordinateDTO> coordinateDTOS = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            coordinateDTOS.add(new CoordinateDTO(1.1*i, 1.1*i));
        }
        byte[] converted = ConversionUtil.routeToBytes(coordinateDTOS);
        List<CoordinateDTO> deconverted = ConversionUtil.bytesToRoute(converted);
        for (int i = 0; i < coordinateDTOS.size(); i++) {
            assert coordinateDTOS.get(i).equals(deconverted.get(i));
        }
    }

    @Test
    public void routeMapping() {
        assert mapper != null;
        DayDTO dayDTO = new DayDTO(LocalDate.now(), "dummy", null, null);
        CoordinateDTO[] coordinateDTOS = new CoordinateDTO[10];
        for (int i = 0; i < 10; i++) {
            coordinateDTOS[i] = new CoordinateDTO(1.1*i, 1.1*i);
        }
        DayRouteDTO dayRouteDTO = new DayRouteDTO();
        dayRouteDTO.setDay(dayDTO);
        dayRouteDTO.setRoute(coordinateDTOS);
        dayDTO.setRoute(dayRouteDTO);
        Day d = mapper.map(dayDTO, Day.class);
        DayDTO dayDTO1 = mapper.map(d, DayDTO.class);
        DayRouteDTO dayRouteDTO1 = dayDTO.getRoute();
        for (int i = 0; i < coordinateDTOS.length; i++) {
            assert coordinateDTOS[i].equals(dayRouteDTO1.getRoute()[i]);
        }
    }


}
